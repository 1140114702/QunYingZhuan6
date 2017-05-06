package com.example.zyb.qunyingzhuan6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PathDashPathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zyb on 2017/5/6.
 */

public class ShaderView3 extends View {

    private Bitmap mSrcBitmap, mRefBitmap;
    private Paint mPaint;
    private PorterDuffXfermode mXfermode;
    private Rect mRect;

    public ShaderView3(Context context) {
        this(context, null);
    }

    public ShaderView3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShaderView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.guagua_big_bg);
        Matrix matrix = new Matrix();
        matrix.setScale(1f, -1f);
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0,
                mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix, true);

        mPaint = new Paint();
        mPaint.setShader(new LinearGradient(0, mSrcBitmap.getHeight(),
                0, mSrcBitmap.getHeight() + mSrcBitmap.getHeight() / 4, 0xdd000000, 0x10000000,
                Shader.TileMode.CLAMP));

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        mRect = new Rect(0, mSrcBitmap.getHeight(),
                mSrcBitmap.getWidth(), mSrcBitmap.getHeight() * 2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mSrcBitmap.getWidth(), mSrcBitmap.getHeight() * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mSrcBitmap, 0, 0, null);
        canvas.drawBitmap(mRefBitmap, 0, mSrcBitmap.getHeight(), null);
        // 绘制渐变效果矩形
        canvas.drawRect(mRect, mPaint);
        mPaint.setXfermode(null);
    }
}
