package ru.mustakimov.solarsystem.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem extends View {

    private int width;
    private int height;
    private List<Planet> planets;

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
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        width = r - l;
        height = b - t;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLUE);
        for (Planet planet : planets) {
            planet.draw(canvas);
        }
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    public void addPlanet(Planet planet) {
        if (planet != null)
            this.planets.add(planet);
        else
            throw new IllegalArgumentException("Planet must not be null!");
    }
}
