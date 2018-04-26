package ru.mustakimov.solarsystem.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class SolarSystem extends View {
    @Getter
    @Setter
    private List<Planet> planets;
    @Getter
    private Planet solar;
    private Paint paint;

    public SolarSystem(Context context) {
        super(context);
        init();
    }

    public SolarSystem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);

        planets = new ArrayList<>();

        solar = new Sun(50, 0, Color.YELLOW, null);
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
    }

    public void addPlanet(float radius, float distance, int color) {
        planets.add(new PhysicsPlanet(radius, distance, color, solar));
    }

    public void addPlanet(float radius, float distance, Drawable drawable) {
        planets.add(new PhysicsPlanet(radius, distance, drawable, solar));
    }

    public void addPlanet(@NonNull Planet planet) {
        this.planets.add(planet);
    }

    public void start() {

    }

    public void stop() {

    }

    public void pause() {

    }
}
