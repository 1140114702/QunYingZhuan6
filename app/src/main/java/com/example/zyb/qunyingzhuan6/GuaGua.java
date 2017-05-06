package com.example.zyb.qunyingzhuan6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 刮刮卡
 * Created by zyb on 2017/5/4.
 */

public class GuaGua extends View {

    private Paint mPaint;
    private Path mPath;
    private Bitmap mBgBitmap;
    private Bitmap mFgBitmap;
    private Canvas mCanvas;

    public GuaGua(Context context) {
        this(context, null);
    }

    public GuaGua(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuaGua(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);//关闭硬件加速
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(50);
        mPath = new Path();
        mBgBitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.guagua_big_bg);
        mFgBitmap = Bitmap.createBitmap(mBgBitmap.getWidth(),
                mBgBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mFgBitmap);
        mCanvas.drawColor(Color.GRAY);
//        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
//        textPaint.setColor(Color.WHITE);
//        textPaint.setTextSize(50);
//        textPaint.setTextAlign(Paint.Align.CENTER);
//        mCanvas.drawText("刮啊刮",mFgBitmap.getWidth()/2,mFgBitmap.getHeight()/2+50/2,textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mBgBitmap.getWidth(), mBgBitmap.getHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                new Thread(mRunnable);
                break;
        }
        mCanvas.drawPath(mPath, mPaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBgBitmap, 0, 0, null);
        if (!isComplete) {
            canvas.drawBitmap(mFgBitmap, 0, 0, null);
        }

    }

    private boolean isComplete;

    /**
     * 统计擦除区域任务
     */
    private Runnable mRunnable = new Runnable() {

        private int[] mPixels;

        @Override
        public void run() {

            int w = mFgBitmap.getWidth();
            int h = mFgBitmap.getHeight();

            float wipeArea = 0;
            float totalArea = w * h;

            Bitmap bitmap = mFgBitmap;

            mPixels = new int[w * h];


            //拿到所有的像素信息
            bitmap.getPixels(mPixels, 0, w, 0, 0, w, h);

            //遍历统计擦除的区域
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int index = i + j * w;
                    if (mPixels[index] == 0) {
                        wipeArea++;
                    }
                }
            }

            //根据所占百分比，进行一些操作
            if (wipeArea > 0 && totalArea > 0) {
                int percent = (int) (wipeArea * 100 / totalArea);

                if (percent > 60) {
                    isComplete = true;
                    postInvalidate();
                }
            }
        }

    };
}
