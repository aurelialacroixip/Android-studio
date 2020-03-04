package com.example.lab_comp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private ImageView ivDinamic; //Объявляем картинку для компаса
    private TextView tvDegree; //Объявляем объект TextView
    private float current_degree = 0f; //Объявляем функцию поворота картинки
    private SensorManager sensorManager; //Объявляем работу с сенсором устройства

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init()
    {
        ivDinamic = findViewById(R.id.ivDinamic); //Связываем объект ImageView с нашим изображением
        tvDegree = findViewById(R.id.tvDegree); //TextView в котором будет отображаться градус поворота
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); //Инициализируем возможность работать с сенсором устройства:

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Устанавливаем слушателя ориентации сенсора:
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Останавливаем при надобности слушателя ориентации
        //сенсора с целью сбережения заряда батареи:
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]); //Получаем градус поворота от оси, которая направлена на север, север = 0 градусов

        tvDegree.setText("Degree from North: " + Float.toString(degree) + " degrees");

        //Создаем анимацию вращения:
        RotateAnimation ra = new RotateAnimation(current_degree, -degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        ra.setDuration(210); //Продолжительность анимации в миллисекундах

        ra.setFillAfter(true); //Настраиваем анимацию после завершения подсчетных действий датчика

        ivDinamic.startAnimation(ra); //Запускаем анимацию
        current_degree = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Этот метод не используется, но без него программа будет ругаться
    }
}
