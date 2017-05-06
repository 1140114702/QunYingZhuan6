package com.example.zyb.qunyingzhuan6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zyb on 2017/5/6.
 */

public class PathEffectView extends View {

    private Paint mPaint;
    private Path mPath;
    private PathEffect[] mPathEffect;

    public PathEffectView(Context context) {
        this(context, null);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.GREEN);

        mPath = new Path();
        mPath.moveTo(0, 0);
        for (int i = 1; i < 30; i++) {
            mPath.lineTo(i * 35, (float) (Math.random() * 100));
        }

        mPathEffect = new PathEffect[6];
        mPathEffect[0] = null;
        mPathEffect[1] = new CornerPathEffect(30);
        mPathEffect[2] = new DiscretePathEffect(3.0f,5.0f);
        mPathEffect[3] = new DashPathEffect(new float[]{20,10,5,10}, 0);
        Path path = new Path();
        path.addRect(0, 0, 8, 8, Path.Direction.CCW);//Path.Direction.CCW逆时针闭合
//        path.addCircle(4,4,4, Path.Direction.CCW);
        mPathEffect[4] = new PathDashPathEffect(path, 12, 0, PathDashPathEffect.Style.ROTATE);
        mPathEffect[5] = new ComposePathEffect(mPathEffect[3], mPathEffect[1]);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mPathEffect.length; i++) {
            mPaint.setPathEffect(mPathEffect[i]);
            canvas.drawPath(mPath, mPaint);
            canvas.translate(0, 200);
        }
    }
}
