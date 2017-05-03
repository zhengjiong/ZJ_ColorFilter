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
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zj.example.colorfilter.R;

/**
 * Created by zj on 2017/5/3.
 */

public class PorterDuffXfermodeView1 extends View {
    private Paint mPaint;
    private Bitmap mBitmapBlue;
    private Bitmap mBitmapRed;

    private Rect mRectBlueDst;
    private Rect mRectRedDst;

    private Rect mRectBlueSrc;
    private Rect mRectRedSrc;
    private PorterDuffXfermode mXfermode;

    public PorterDuffXfermodeView1(Context context) {
        this(context, null);
    }

    public PorterDuffXfermodeView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorterDuffXfermodeView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setFilterBitmap(true);

        mBitmapBlue = ((BitmapDrawable) getResources().getDrawable(R.mipmap.blue)).getBitmap();
        mBitmapRed = ((BitmapDrawable) getResources().getDrawable(R.mipmap.red)).getBitmap();

        mRectBlueSrc = new Rect(0, 0, mBitmapBlue.getWidth(), mBitmapBlue.getHeight());
        mRectRedSrc = new Rect(0, 0, mBitmapRed.getWidth(), mBitmapRed.getHeight());

        mXfermode = null;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectBlueDst = new Rect(0, 0, w, h / 2);//蓝色高度为控件的一半高
        mRectRedDst = new Rect(0, 0, w, h);
    }

    public void setXfermode(PorterDuff.Mode mode) {
        if (mode == null) {
            mXfermode = null;
        } else {
            mXfermode = new PorterDuffXfermode(mode);
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画一个白色背景方便测试
        //canvas.drawColor(Color.WHITE);

        // 保存为单独的层
        int count = canvas.saveLayer(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(mBitmapBlue, mRectBlueSrc, mRectBlueDst, mPaint);//先画的为Dst

        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(mBitmapRed, mRectRedSrc, mRectRedDst, mPaint);//后画的为SRC

        mPaint.setXfermode(null);
        canvas.restoreToCount(count);
    }
}
