package com.zj.example.colorfilter.PorterDuff.PorterDuffXfermode.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zj.example.colorfilter.R;

/**
 * Created by zj on 2017/5/4.
 */

public class PorterDuffXfermodeView2 extends View {
    private Paint mPaint;

    private Bitmap mBitmap;

    private Rect mBitmapSrc;
    private Rect mBitmapDst;

    private Rect mRedRectDst;

    public PorterDuffXfermodeView2(Context context) {
        this(context, null);
    }

    public PorterDuffXfermodeView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorterDuffXfermodeView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setFilterBitmap(true);
        mPaint.setColor(Color.parseColor("#FF0000"));

        mBitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ga_studio)).getBitmap();
        mBitmapSrc = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        mBitmapDst = new Rect(
                getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin),
                getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin),
                getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin) + mBitmap.getWidth(),
                getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin) + mBitmap.getHeight()
        );


        int top = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin) + mBitmap.getHeight();

        mRedRectDst = new Rect(
                getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin),
                top,
                getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin) + mBitmap.getWidth(),
                top + getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin) + mBitmap.getHeight()
        );

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(15);
                    mRedRectDst.top = mRedRectDst.top - 1;
                    if (mRedRectDst.top <= 0) {
                        break;
                    }

                    postInvalidate();
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * saveLayer可以为canvas创建一个新的透明图层，在新的图层上绘制，并不会直接绘制到屏幕上，而会在restore之后，
         * 绘制到上一个图层或者屏幕上（如果没有上一个图层）。为什么会需要一个新的图层，例如在处理xfermode的时候，
         * 原canvas上的图（包括背景）会影响src和dst的合成，这个时候，使用一个新的透明图层是一个很好的选择。
         * 又例如需要当前绘制的图形都带有一定的透明度，那么创建一个带有透明度的图层，也是一个方便的选择。

         当然，图层使用比较方便，但是代价昂贵，一个新的图层带来的可能不止多一倍的渲染，特别是图层比较大的时候，所以要谨慎使用。


         * save方法可以保存当前的matrix and clip，并且在restore把它恢复，一些平移，旋转，
         * 缩放等操作都会影响Canvas的matrix，所以save操作一般可以保存这些信息以及clip信息；
         *
         * 而saveLayer则强大很多，它相当于另外起一张干净图层，并在上面进行绘制操作，然后在restoreToCount的时候，
         * 把刚才所绘制的重新绘制在原本的Canvas上。当时正如所知的那样，它会绘制两次，所以消耗是十分巨大，对此，
         * 官方注释也进行了很长的说明和建议，请自行翻译。
         */
        /**
         * 如果不用 :下面3行保存图层会出现黑屏现象
         * canvas.saveLayer,
         * mPaint.setXfermode(null);
         * canvas.restoreToCount(count);
         */
        int count = canvas.saveLayer(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint, Canvas.ALL_SAVE_FLAG);

        //先绘制的是dst目标图
        canvas.drawBitmap(mBitmap, mBitmapSrc, mBitmapDst, mPaint);


        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        //后面绘制的是src原图
        canvas.drawRect(mRedRectDst, mPaint);

        mPaint.setXfermode(null);
        canvas.restoreToCount(count);
    }
}
