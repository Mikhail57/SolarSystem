package ru.mustakimov.solarsystem.custom;

import android.graphics.Canvas;
import android.graphics.Paint;

import lombok.AccessLevel;
import lombok.Getter;

public class Sun extends Planet {
    @Getter(AccessLevel.PRIVATE)
    private Paint paint;

    public Sun(float radius, float distance, int color, Planet parent) {
        super(radius, distance, color, parent);
        paint = new Paint();
        paint.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        int x = canvas.getWidth() / 2;
        int y = canvas.getHeight() / 2;
        canvas.drawCircle(x, y, getRadius(), getPaint());
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public void nextStep() {
    }
}
