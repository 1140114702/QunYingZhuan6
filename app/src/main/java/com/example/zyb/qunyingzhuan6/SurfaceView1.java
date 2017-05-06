package com.example.zyb.qunyingzhuan6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by zyb on 2017/5/6.
 */

public class SurfaceView1 extends SurfaceView implements SurfaceHolder.Callback, Runnable {


    private SurfaceHolder mHolder; // SurfaceHolder
    private Canvas mCanvas;// 用于绘图的Canvas
    private boolean mIsDrawing; // 子线程标志位
    private Paint mPaint;
    private Path mPath;
    private int x = 0;
    private int y = 0;

    public SurfaceView1(Context context) {
        this(context, null);
    }

    public SurfaceView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SurfaceView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);//键盘获取焦点
        setFocusableInTouchMode(true);//触摸获取焦点
        //mHolder.setFormat(PixelFormat.OPAQUE);

        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        mPath.moveTo(0, 400);
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            draw();
            x += 1;
            y = (int) (100 * Math.sin(x * 2 * Math.PI / 180) + 400);
            mPath.lineTo(x, y);
        }
    }

    private void draw() {

        try {
            mCanvas = mHolder.lockCanvas();
            //draw something
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
            if (x == getWidth()) {
                mIsDrawing = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }

    }

}
