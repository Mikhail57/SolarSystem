package ru.mustakimov.solarsystem.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class SolarSystemView extends View {
    @Getter
    @Setter
    private List<Planet> planets;
    @Getter
    private Planet solar;

    private UpdateTask task;

    public SolarSystemView(Context context) {
        super(context);
        init();
    }

    public SolarSystemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        planets = new ArrayList<>();

        solar = new Sun(90, 0, Color.YELLOW, null);
        task = new UpdateTask();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLUE);

        solar.draw(canvas);
        for (Planet planet : planets) {
            planet.draw(canvas);
        }
    }

    public void reset() {
        planets.clear();
        invalidate();
    }

    public void addPlanet(float radius, float distance, int color) {
        planets.add(new PhysicsPlanet(radius, distance, color, solar));
        invalidate();
    }

    public void addPlanet(float radius, float distance, Drawable drawable) {
        planets.add(new PhysicsPlanet(radius, distance, drawable, solar));
        invalidate();
    }

    public void addPlanet(@NonNull Planet planet) {
        this.planets.add(planet);
        invalidate();
    }

    public void start() {
        if (task.planetsMoving.get())
            throw new IllegalStateException("Already running");
        task.execute();
    }

    public void stop() {
        if (!task.planetsMoving.get())
            throw new IllegalStateException("Not running");
        task.planetsMoving.set(false);
        invalidate();
    }

    class UpdateTask extends AsyncTask<Void, Void, Void> {
        AtomicBoolean planetsMoving = new AtomicBoolean(false);

        @Override
        protected Void doInBackground(Void... voids) {
            while (planetsMoving.get()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(15);
                } catch (InterruptedException ignored) { }
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            planetsMoving.set(true);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            for (Planet planet : planets) {
                planet.nextStep();
            }
            invalidate();
        }
    }
}
