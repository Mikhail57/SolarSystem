package ru.mustakimov.solarsystem.custom;

import android.graphics.Canvas;
import android.graphics.Paint;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class Sun extends Planet {
    @Getter(AccessLevel.PRIVATE)
    private Paint paint;

    @Getter
    private float x, y;

    public Sun(float radius, float distance, int color, Planet parent) {
        super(radius, distance, color, parent);
        paint = new Paint();
        paint.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        int x = canvas.getWidth() / 2;
        int y = canvas.getHeight() / 2;
        this.x = x;
        this.y = y;
        canvas.drawCircle(x, y, getRadius(), getPaint());
    }

    @Override
    public void nextStep() {
    }
}
