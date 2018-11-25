package com.shan.basedrawdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static java.lang.Math.PI;
import static java.lang.Math.max;

public class SpiderView extends View {

    private Paint radarPaint, valuePaint;

    private float radius;
    private int centerX;
    private int centerY;

    private int count = 6;
    private double angle = 60;

    private double[] data = {2, 5, 1, 6, 4, 5};
    private float maxValue = 6;


    public SpiderView(Context context) {
        super(context);
        init();
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w) / 2 * 0.9f;

        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);

        drawLines(canvas);

        drawRegion(canvas);




    }

    //绘制网格
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count);
        for (int i = 1; i <= count; i++) {
            path.reset();
            float curR = r * i;
//            Log.d("SpiderView", "curR: " + String.valueOf(curR));
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                } else {
                    double cal_angle = Math.toRadians(angle * j);
                    float x = (float) (centerX + curR * Math.cos(cal_angle));
                    float y = (float) (centerY + curR * Math.sin(cal_angle));
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, radarPaint);
        }
    }

    //绘制网格中心点与角的连线
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i <= count; i++) {
            path.reset();
            path.moveTo(centerX, centerY);

            double cal_angle = Math.toRadians(angle * i);
            float x = (float) (centerX + radius * Math.cos(cal_angle));
            float y = (float) (centerY + radius * Math.sin(cal_angle));
            path.lineTo(x, y);
            canvas.drawPath(path, radarPaint);
        }
    }

    //绘制数据填充区域
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(127);
        for (int i = 0; i < count; i++) {
            double percent = data[i] / maxValue;
            double cal_angle = Math.toRadians(angle * i);
            float x = (float) (centerX + radius * Math.cos(cal_angle) * percent);
            float y = (float) (centerY + radius * Math.sin(cal_angle) * percent);
            if (i == 0) {
                path.moveTo(x, centerY);
            } else {
                path.lineTo(x, y);
            }
            //绘制小圆点
            canvas.drawCircle(x,y,10, valuePaint);
        }
        //绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }





    private void init() {
        radarPaint = new Paint();
        radarPaint.setStyle(Paint.Style.STROKE);
        radarPaint.setStrokeWidth(2);
        radarPaint.setColor(Color.GREEN);

        valuePaint = new Paint();
        valuePaint.setStyle(Paint.Style.FILL);
        valuePaint.setColor(Color.BLUE);



    }
}
