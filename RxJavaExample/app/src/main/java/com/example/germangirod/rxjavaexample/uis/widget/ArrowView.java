package com.example.germangirod.rxjavaexample.uis.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by germangirod on 11/25/15.
 */
public class ArrowView extends ImageView {

    private float angleRotation;

    public ArrowView(Context context) {
        super(context);
    }

    public ArrowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ArrowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAngleRotation(float angleRotation) {
        this.angleRotation = angleRotation;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect clipBounds = canvas.getClipBounds();
        canvas.save();
        canvas.rotate(angleRotation, clipBounds.exactCenterX(), clipBounds.exactCenterY());
        super.onDraw(canvas);
        canvas.restore();
    }

}
