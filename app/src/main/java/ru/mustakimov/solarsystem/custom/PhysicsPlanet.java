package ru.mustakimov.solarsystem.custom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import java.util.Random;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class PhysicsPlanet extends Planet {

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private float angle;
    @Getter
    @Setter
    private float velocity;
    @Getter
    private Paint planetPaint;
    @Getter
    private Paint radiusPaint;


    public PhysicsPlanet(String name, float radius, float distance, int color,
                         Drawable imageDrawable, @NonNull Planet parent) {
        super(name, radius, distance, color, imageDrawable, parent);
        init();
    }

    public PhysicsPlanet(float radius, float distance, int color, @NonNull Planet parent) {
        super(radius, distance, color, parent);
        init();
    }

    public PhysicsPlanet(float radius, float distance, Drawable imageDrawable, @NonNull Planet parent) {
        super(radius, distance, imageDrawable, parent);
        init();
    }

    private void init() {
        planetPaint = new Paint();
        planetPaint.setColor(getColor());
        planetPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        planetPaint.setStrokeWidth(2);

        radiusPaint = new Paint();
        radiusPaint.setStyle(Paint.Style.STROKE);
        radiusPaint.setStrokeWidth(2);
        radiusPaint.setColor(Color.argb(50, 0, 0, 0));

        velocity = new Random().nextFloat() * (float) (Math.PI / 60);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(getParent().getX(), getParent().getY(), getDistance(), getRadiusPaint());
        if (getImageDrawable() != null) {
            Drawable d = getImageDrawable();
            int left = (int) (getX() - getRadius());
            int top = (int) (getY() - getRadius());
            int right = (int) (getX() + getRadius());
            int bottom = (int) (getY() + getRadius());
            d.setBounds(left, top, right, bottom);
            d.draw(canvas);
        } else {
            canvas.drawCircle(getX(), getY(), getRadius(), getPlanetPaint());
        }
    }

    @Override
    public float getX() {
        return getParent().getX() + getDistance() * (float) cos(getAngle());
    }

    @Override
    public float getY() {
        return getParent().getY() + getDistance() * (float) sin(getAngle());
    }

    @Override
    public void nextStep() {
        setAngle(getAngle() + getVelocity());
    }
}
