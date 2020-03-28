package com.example.funny_stud;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

class Panel extends View {
    float p = 0;
    public Panel(Context context) {
        super(context);
        // установим обработчик нажатия
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View e, MotionEvent event) {
                // Здесь выводится тип нажатия и координаты X Y
                Log.e("Ivan", "Pos:" + event.getAction() + event.getX() + " " + event.getY());

                return false;
            }
        });
    }


    void Risyem(Canvas c) {
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.tema_before); // Загружаем картинку из ресурсов
        Matrix matrix = new Matrix();     // Создаем матрицу преобразований
        matrix.postScale(0.5f, 0.5f);   // Масштабируем
        matrix.preRotate(p, b.getWidth() / 2, b.getHeight() / 2); // Поворачиваем
        matrix.postTranslate(0.0f, 0.0f);  // перемещаем по координатам X и  Y
        c.drawBitmap(b, matrix, null);    // Отрисовываем картинку с у четом матрицы

        //Paint p1 = new Paint(); // Создаем кисть для рисования
        //p1.setColor(Color.RED); // Меняем цвет кисти
        //p1.setStyle(Paint.Style.STROKE); //только линия окружности
        //p1.setStyle(Paint.Style.FILL); //Закрашивается весь круг
        //p1.setPathEffect(new DashPathEffect(new float[]{1, 20, 15, 20, 10, 30, 50, 10}, 0));  // пунктирная линия
        //p1.setStrokeWidth(4.5f);     //Толщина линии
        //c.drawCircle(200, 200, 100, p1);  // рисуем окружность
        //canvas.drawRect(0, 0, 100, 100, mPaint);
        //c.drawLine(100, 150, 900, 150, p1);   // Рисуем линию
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint p1 = new Paint();// Создаем кисть для рисования
        p1.setColor(Color.rgb(100, 255, 10));  // Меняем цвет
        //p1.setTypeface(Typeface.createFromAsset(getAssets(),"cour.ttf")); //Задаем шрифт
        p1.setTextSize(20);
        //canvas.drawText("resolution:" + canvas.getHeight() + "X" + canvas.getWidth(), 100, 100, p1); // Пишем текст на экране ( getHeight() – функция возвращает высоту экрана, getWidth() – ширину. )
        Risyem(canvas);
        p = (p > 360) ? 0 : p + 6f; // Увеличиваем угол поворота изображения
        invalidate();  //перерисовать весь холст заново
        canvas.drawText("Нажми меня:",400,400,p1); // Выводим текст
    }
    Thread myThread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            // Работа №1 в отдельном потоке
            } });
}

