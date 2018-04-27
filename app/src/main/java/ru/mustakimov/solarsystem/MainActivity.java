package ru.mustakimov.solarsystem;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.mustakimov.solarsystem.custom.PhysicsPlanet;
import ru.mustakimov.solarsystem.custom.SolarSystemView;

public class MainActivity extends AppCompatActivity {

    SolarSystemView solarSystem;
    Button startButton;
    Button stopButton;
    Button addPlanetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация view из xml-файла
        solarSystem = findViewById(R.id.solarSystem);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        addPlanetButton = findViewById(R.id.addPlanetButton);

        /*
         * Пример работы с солнечной системой
         */
        // Добавление планеты с радиусом 40 пикселей, расстоянием от солнца в 250 пикселей, имеющую картинку планеты
        solarSystem.addPlanet(40, 250, getResources().getDrawable(R.drawable.planet1));
        // Добавление спутника к предыдущей планете. Радиус 15 пикселей, расстояние от центра планеты 70 пикселей, красного цвета
        solarSystem.addSatellite(15, 70, Color.RED);
        // Добавление спутника к спутнику
        solarSystem.addSatellite(5, 30, Color.GREEN);
        // Добавляем планету с радиусом 100, расстоянем от солнца 500, цвета голубого цвета, с родителем солнце (относительно кого движется)
        solarSystem.addPlanet(new PhysicsPlanet(100, 500, Color.CYAN, solarSystem.getSolar()));
        // Старт анимации
        solarSystem.start();
    }

    /**
     * Метод, обрабатывающий нажатие кнопки "Старт"
     *
     * @param v кнопка "Старт" на которую нажали
     */
    public void onStartButtonClick(View v) {
        // Необходимо перенести старт анимации из метода onCreate сюда
    }

    /**
     * Метод, обрабатывающий нажатие кнопки "Стоп"
     *
     * @param v кнопка "Стоп"
     */
    public void onStopButtonClick(View v) {
        // Останавливаем анимацию солнечной системы
        solarSystem.stop();
    }

    /**
     * Метод, обрабатывающий нажатие кнопки "Добавить планету"
     *
     * @param v кнопка "Добавить планету"
     */
    public void onAddPlanetButtonClick(View v) {
        // Необходимо реализовать метод добавления планеты
    }
}
