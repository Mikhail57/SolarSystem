package ru.mustakimov.solarsystem.custom;

import android.graphics.Canvas;
import android.graphics.Color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Planet {
    private String name = "Planet";
    private float radius = 1;
    private float weight = 1;
    private float distance = 1;
    private int color = Color.YELLOW;

    public void draw(Canvas canvas) {
        
    }
}
