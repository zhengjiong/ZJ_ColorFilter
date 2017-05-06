package com.zj.example.colorfilter.PorterDuff.PorterDuffXfermode.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.zj.example.colorfilter.R;

/**
 * Created by zj on 2017/5/5.
 */

public class PorterDuffXfermodeView3 extends View {
    private Paint mWavePaint;
    private Paint mCirclePaint;

    private Bitmap mWaveBitmap;
    private Bitmap mCircleBitmap;

    private Rect mWaveSrcRect;
    private Rect mWaveDstRect;

    private Rect mCircleSrcRect;
    private Rect mCircleDstRect;
    private int mLeft;
    private int speed;

    public PorterDuffXfermodeView3(Context context) {
        this(context, null);
    }

    public PorterDuffXfermodeView3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorterDuffXfermodeView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        speed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, context.getResources().getDisplayMetrics());

        mWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWavePaint.setDither(true);
        mWavePaint.setFilterBitmap(true);

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setDither(true);
        mCirclePaint.setFilterBitmap(true);

        mWaveBitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.wave_2000)).getBitmap();
        mCircleBitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.circle_500)).getBitmap();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    mLeft += speed;
                    if (mLeft > mWaveBitmap.getWidth()) {
                        mLeft = 0;
                    }
                    SystemClock.sleep(30);
                    postInvalidate();
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int saveCount = canvas.saveLayer(0, 0, getMeasuredWidth(), getMeasuredHeight(), null, Canvas.ALL_SAVE_FLAG);

        /*mWaveSrcRect.set(
                mLeft,
                0,
                mLeft + mCircleBitmap.getWidth(),
                mWaveBitmap.getHeight()
        );*/

        mWaveSrcRect.set(
                mLeft,
                0,
                mLeft + getMeasuredWidth()/2,
                getMeasuredHeight()
        );

        //先绘制的是dst目标图
        canvas.drawBitmap(mWaveBitmap, mWaveSrcRect, mWaveDstRect, mWavePaint);

        mWavePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        //后面绘制的是src原图
        canvas.drawBitmap(mCircleBitmap, mCircleSrcRect, mCircleDstRect, mWavePaint);

        mWavePaint.setXfermode(null);
        canvas.restoreToCount(saveCount);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCircleSrcRect = new Rect(0, 0, mCircleBitmap.getWidth(), mCircleBitmap.getHeight());
        mCircleDstRect = new Rect(
                getWidth()/2 - mCircleBitmap.getWidth()/2,
                getHeight()/2 - mCircleBitmap.getHeight()/2,
                getWidth()/2 + mCircleBitmap.getWidth()/2,
                getHeight()/2 + mCircleBitmap.getHeight()/2
        );

        mWaveSrcRect = new Rect(0, 0, mCircleBitmap.getWidth(), mWaveBitmap.getHeight());
        mWaveDstRect = new Rect(
                getWidth()/2 - mCircleBitmap.getWidth()/2,
                getHeight()/2 - mCircleBitmap.getHeight()/2,
                getWidth()/2 + mCircleBitmap.getWidth()/2,
                getHeight()/2 + mCircleBitmap.getHeight()/2
        );


    }
}
