package ru.mustakimov.solarsystem.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class SolarSystemView extends View {
    @Getter
    @Setter
    private List<Planet> planets;
    @Getter
    private Planet solar;

    private UpdateTask task;

    /**
     * Конструктор, вызываемый при создании через код
     *
     * @param context контекст
     */
    public SolarSystemView(Context context) {
        super(context);
        init();
    }

    /**
     * Конструктор, вызываемый при инициализации view через XML
     *
     * @param context контектс
     * @param attrs атрибуты, указываемые в xml-файле
     */
    public SolarSystemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        planets = new ArrayList<>();

        solar = new Sun(90, 0, Color.YELLOW, null);
        task = new UpdateTask();
    }

    /**
     * Рисует планеты этой солнечной системы
     *
     * @param canvas полотно, на котором осуществляется рисование
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLUE);

        solar.draw(canvas);
        for (Planet planet : planets) {
            planet.draw(canvas);
        }
    }

    /**
     * Сбрасывает все планеты солнечной системы
     */
    @SuppressWarnings("unused")
    public void reset() {
        planets.clear();
        invalidate();
    }

    /**
     * Добавление планеты в солнечную систему с определенным цветом
     *
     * @param radius радиус планеты
     * @param distance расстояние от центра солнца до центра планеты
     * @param color цвет планеты
     */
    @SuppressWarnings("unused")
    public void addPlanet(float radius, float distance, int color) {
        addPlanet(new PhysicsPlanet(radius, distance, color, solar));
    }

    /**
     * Добавление планеты в солнечную систему с картинкой
     *
     * @param radius радиус планеты
     * @param distance расстояние от центра солнца до центра планеты
     * @param drawable ресурс с картинкой планеты
     */
    public void addPlanet(float radius, float distance, Drawable drawable) {
        addPlanet(new PhysicsPlanet(radius, distance, drawable, solar));
    }

    /**
     * Добавляет спутник к последней планете.
     * Внимание! Если хотите добавить несколько спутников к одной планете, то воспользуйтесь
     * методом {@link #addPlanet(Planet)}, так как, если вы используете этот метод несколько раз
     * подряд, ваши спутники будут добавляться друг к другу
     *
     * @param radius радиус планеты в пикселях
     * @param distance растояние от центра родительской планеты до центра спутника в пикселях
     * @param color цвет спутника
     */
    @SuppressWarnings("unused")
    public void addSatellite(float radius, float distance, int color) {
        addPlanet(new PhysicsPlanet(radius, distance, color, getPlanets().get(getPlanets().size() - 1)));
    }

    /**
     * Добавляет планету к солнечной системе
     *
     * @param planet планета, которую нужно добавить
     */
    public void addPlanet(@NonNull Planet planet) {
        this.planets.add(planet);
        invalidate();
    }

    /**
     * Начинает анимацию планет
     */
    public void start() {
        if (task == null || task.isCancelled() || task.getStatus() == AsyncTask.Status.FINISHED) {
            task = new UpdateTask();
        }
        if (!task.planetsMoving.get())
            task.execute();
    }

    /**
     * Останавливает анимацию передвижения объектов
     */
    @SuppressWarnings("unused")
    public void stop() {
        task.planetsMoving.set(false);
        invalidate();
    }

    /**
     * Класс, выполняющий задрежку между каждым кадром
     */
    class UpdateTask extends AsyncTask<Void, Void, Void> {
        AtomicBoolean planetsMoving = new AtomicBoolean(false);

        @Override
        protected Void doInBackground(Void... voids) {
            while (planetsMoving.get()) {
                try {
                    // Время задрежки между кадрами
                    TimeUnit.MILLISECONDS.sleep(15);
                } catch (InterruptedException ignored) { }
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            planetsMoving.set(true);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            for (Planet planet : planets) {
                planet.nextStep();
            }
            invalidate();
        }
    }
}
