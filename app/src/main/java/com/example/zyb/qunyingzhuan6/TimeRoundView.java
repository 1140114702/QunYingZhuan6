package com.example.zyb.qunyingzhuan6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 仪表盘
 * Created by zyb on 2017/5/3.
 */

public class TimeRoundView extends View {

    private Paint paintCircle;
    private Paint painDegree;
    private Paint paintPointer;
    private Paint paintHour;
    private Paint paintMinute;

    public TimeRoundView(Context context) {
        this(context, null);
    }

    public TimeRoundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeRoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setDither(true);
        paintCircle.setStrokeWidth(5);

        painDegree = new Paint();
        painDegree.setAntiAlias(true);
        painDegree.setDither(true);
        painDegree.setStrokeWidth(3);


        paintPointer = new Paint();
        paintPointer.setStrokeWidth(30);


        paintHour = new Paint();
        paintHour.setStrokeWidth(20);
        paintMinute = new Paint();
        paintMinute.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取宽高参数
        int mWidth = getMeasuredWidth();
        int mHeight = getMeasuredHeight();
        // 画外圆
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2 - 2, paintCircle);
        // 画刻度
        for (int i = 0; i < 24; i++) {
            // 区分整点与非整点
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                painDegree.setStrokeWidth(5);
                painDegree.setTextSize(30);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + 60,
                        painDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,
                        mWidth / 2 - painDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 90,
                        painDegree);
            } else {
                painDegree.setStrokeWidth(3);
                painDegree.setTextSize(15);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + 30,
                        painDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,
                        mWidth / 2 - painDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 60,
                        painDegree);
            }
            // 通过旋转画布简化坐标运算
            canvas.rotate(360 / 24, mWidth / 2, mHeight / 2);
        }

        // 画圆心
        canvas.drawPoint(mWidth / 2, mHeight / 2, paintPointer);
        // 画指针
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawLine(0, 0, 100, 100, paintHour);
        canvas.drawLine(0, 0, 100, 200, paintMinute);
        canvas.restore();

    }
}
