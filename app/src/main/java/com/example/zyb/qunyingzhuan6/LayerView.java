package com.example.zyb.qunyingzhuan6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zyb on 2017/5/4.
 */

public class LayerView extends View {

    private Paint mPaint;
    private static final int LAYER_FLAGS =
            Canvas.MATRIX_SAVE_FLAG |
                    Canvas.CLIP_SAVE_FLAG |
                    Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                    Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                    Canvas.CLIP_TO_LAYER_SAVE_FLAG;

    public LayerView(Context context) {
        this(context, null);
    }

    public LayerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(150, 150, 100, mPaint);

        canvas.saveLayerAlpha(0, 0, 400, 400, 255, LAYER_FLAGS);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(200, 200, 100, mPaint);
        mPaint.setColor(Color.BLUE);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(30);
        canvas.drawText("第二个图层",200,200,mPaint);
        canvas.restore();
        mPaint.setColor(Color.RED);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(30);
        canvas.drawText("第一个图层",150,150,mPaint);
    }
}
