package com.zj.example.colorfilter;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class LightColorFilterDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_colorfilter_demo);

        ImageView imageView1 = (ImageView) findViewById(R.id.img_1);
        ImageView imageView2 = (ImageView) findViewById(R.id.img_2);

        /**
         * LightingColorFilter (int mul, int add)
         * mul全称是colorMultiply意为色彩倍增，而add全称是colorAdd意为色彩添加，这两个值都是16进制的色彩值0xAARRGGBB
         */
        //添加红色,第二个参数可以增加颜色效果, RGB,红绿蓝, R参数改为FF会添加红色
        LightingColorFilter lightingColorFilter1 = new LightingColorFilter(0xFFFFFFFF, 0x00FF0000);

        //去掉绿色,但是会变红, RGB,红绿蓝,修改第一个参数的G会修改绿色
        LightingColorFilter lightingColorFilter2 = new LightingColorFilter(0xFFFF00FF, 0x00000000);


        imageView1.setColorFilter(lightingColorFilter1);
        imageView2.setColorFilter(lightingColorFilter2);
    }
}
