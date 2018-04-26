package ru.mustakimov.solarsystem.custom;

import android.graphics.Canvas;
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
    @Setter
    private Paint paint;

    public PhysicsPlanet(String name, float radius, float distance, int color,
                         Drawable imageDrawable, @NonNull Planet parent) {
        super(name, radius, distance, color, imageDrawable, parent);
        init();
    }

    public PhysicsPlanet(float radius, float distance, int color, @NonNull Planet parent) {
        super(radius, distance, color, parent);
        velocity = new Random().nextFloat() * (float) (Math.PI / 60);
        init();
    }

    public PhysicsPlanet(float radius, float distance, Drawable imageDrawable, Planet parent) {
        super(radius, distance, imageDrawable, parent);
        velocity = new Random().nextFloat() * (float) (Math.PI / 360);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(getColor());
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(2);
    }

    @Override
    public void draw(Canvas canvas) {
        if (getImageDrawable() != null) {
            Drawable d = getImageDrawable();
            int left = (int) (getX() - getRadius());
            int top = (int) (getY() - getRadius());
            int right = (int) (getX() + getRadius());
            int bottom = (int) (getY() + getRadius());
            d.setBounds(left, top, right, bottom);
            d.draw(canvas);
        } else {
            canvas.drawCircle(getX(), getY(), getRadius(), getPaint());
        }
    }

    @Override
    public float getX() {
        return getParent().getX() + getDistance()*(float)cos(getAngle());
    }

    @Override
    public float getY() {
        return getParent().getY() + getDistance()*(float)sin(getAngle());
    }

    @Override
    public void nextStep() {
        setAngle(getAngle() + getVelocity());
    }
}
