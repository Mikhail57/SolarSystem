package ru.mustakimov.solarsystem.custom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public abstract class Planet {
    private String name = "Planet";
    private float radius = 1;
    private float distance = 1;
    private int color = Color.YELLOW;
    private Drawable imageDrawable;
    private Planet parent;

    public Planet(float radius, float distance, int color, Planet parent) {
        this.radius = radius;
        this.distance = distance;
        this.color = color;
        this.parent = parent;
    }

    public Planet(float radius, float distance, Drawable imageDrawable, Planet parent) {
        this.radius = radius;
        this.distance = distance;
        this.imageDrawable = imageDrawable;
        this.parent = parent;
    }

    public abstract void draw(Canvas canvas);
    public abstract float getX();
    public abstract float getY();
    public abstract void nextStep();

}
