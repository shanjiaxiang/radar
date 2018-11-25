package com.shan.basedrawdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class BaseView extends View {
    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint1 = genPaint(Color.YELLOW, Paint.Style.STROKE, 2);
        Paint paint2 = genPaint(Color.GREEN, Paint.Style.STROKE, 2);


        Rect rect = new Rect(0,0,200, 200);
        canvas.drawRect(rect,paint1);

        canvas.translate(100,100);
        canvas.drawRect(rect,paint2);



    }

    private void drawRegion(Canvas canvas, Region region, Paint paint){
        RegionIterator iterator = new RegionIterator(region);
        Rect r = new Rect();
        while (iterator.next(r)){
            canvas.drawRect(r, paint);
        }
    }

    private Paint genPaint(int color, Paint.Style style, int strokedWidth){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(style);
        if (strokedWidth != 1){
            paint.setStrokeWidth(strokedWidth);
        }
        return paint;
    }


}
