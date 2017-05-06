package com.example.zyb.qunyingzhuan6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * LinearGradient
 * Created by zyb on 2017/5/6.
 */

public class ShaderView2 extends View {

    private Paint mPaint;
    private Rect rect;

    public ShaderView2(Context context) {
        this(context, null);
    }

    public ShaderView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShaderView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setShader(new LinearGradient(
                0, 0, 400, 400, Color.BLACK, Color.GREEN, Shader.TileMode.REPEAT));
        rect = new Rect(0, 0, 600, 600);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(600, 600);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rect, mPaint);
    }
}
