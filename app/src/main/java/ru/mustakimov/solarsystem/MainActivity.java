package ru.mustakimov.solarsystem;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.mustakimov.solarsystem.custom.PhysicsPlanet;
import ru.mustakimov.solarsystem.custom.SolarSystem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SolarSystem solarSystem = findViewById(R.id.solarSystem);
        solarSystem.addPlanet(40, 250, Color.CYAN);
        solarSystem.addPlanet(new PhysicsPlanet(15, 70, Color.RED, solarSystem.getPlanets().get(0)));
        solarSystem.start();
    }
}
