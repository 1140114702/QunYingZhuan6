package com.example.zyb.qunyingzhuan6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * BitmapShader
 * Created by zyb on 2017/5/6.
 */

public class ShaderView1 extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private BitmapShader mShader;

    public ShaderView1(Context context) {
        this(context, null);
    }

    public ShaderView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShaderView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.guagua_big_bg);
        mShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(mShader);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));

    }

//    private int measureWidth(int widthMeasureSpec) {
//        int result;
//        int mode = MeasureSpec.getMode(widthMeasureSpec);
//        int size = MeasureSpec.getSize(widthMeasureSpec);
//        if (mode == MeasureSpec.EXACTLY) {
//            result = size;
//        } else {
//            result = 400;
//            if (mode == MeasureSpec.AT_MOST) {
//                result = Math.min(result, size);
//            }
//        }
//        return result;
//    }

//    private int measureHeight(int heightMeasureSpec) {
//        int result;
//        int mode = MeasureSpec.getMode(heightMeasureSpec);
//        int size = MeasureSpec.getSize(heightMeasureSpec);
//        if (mode == MeasureSpec.EXACTLY) {
//            result = size;
//        } else {
//            result = 400;
//            if (mode == MeasureSpec.AT_MOST) {
//                result = Math.min(result, size);
//            }
//        }
//        return result;
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, getMeasuredWidth() / 2, mPaint);
    }
}
