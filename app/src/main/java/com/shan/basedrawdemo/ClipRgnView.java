package com.shan.basedrawdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ClipRgnView extends View {
    private Bitmap mBitmap;
    private Bitmap mScaledBitmap;
    private int clipWidth = 0;
    private int width;
    private int height;
    private static final int CLIP_HEIGHT = 30;
    private Path mPath;


    public ClipRgnView(Context context) {
        super(context);
        init();
    }

    public ClipRgnView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClipRgnView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scenery);
        mScaledBitmap = Bitmap.createScaledBitmap(mBitmap, 768,1280, true);
        width = mScaledBitmap.getWidth();
        height = mScaledBitmap.getHeight();
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        int i = 0;
        while (i * CLIP_HEIGHT <= height) {
            if (i % 2 == 0) {
                mPath.addRect(new RectF(0, i * CLIP_HEIGHT, clipWidth, (i + 1) * CLIP_HEIGHT), Path.Direction.CCW);
            } else {
                mPath.addRect(new RectF(width - clipWidth, i * CLIP_HEIGHT, width, (i + 1) * CLIP_HEIGHT), Path.Direction.CCW);
            }
            i++;
        }
        canvas.clipPath(mPath);

        canvas.drawBitmap(mScaledBitmap, 0, 0, new Paint());
        if (clipWidth > width) {
            return;
        }
        clipWidth += 5;
        invalidate();
    }
}
