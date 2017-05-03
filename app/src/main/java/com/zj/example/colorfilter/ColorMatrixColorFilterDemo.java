package com.zj.example.colorfilter;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ColorMatrixColorFilterDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_matrix_colorfilter_demo);

        ImageView imageView1 = (ImageView) findViewById(R.id.img_1);
        ImageView imageView2 = (ImageView) findViewById(R.id.img_2);
        ImageView imageView3 = (ImageView) findViewById(R.id.img_3);

        //颜色变暗效果
        /*ColorMatrix colorMatrix1 = new ColorMatrix(new float[]{
                0.5F, 0, 0, 0, 0,
                0, 0.5F, 0, 0, 0,
                0, 0, 0.5F, 0, 0,
                0, 0, 0, 1, 0,
        });*/
        ColorMatrix colorMatrix1 = new ColorMatrix(new float[]{
                0.01F, 0, 0, 0, 0,
                0, 0.01F, 0, 0, 0,
                0, 0, 0.01F, 0, 0,
                0, 0, 0, 1F, 0,
        });

        //变透明
        ColorMatrix colorMatrix2 = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 0.5f, 0,
        });

        //变灰
        ColorMatrix colorMatrix3 = new ColorMatrix(new float[]{
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0, 0, 0, 1, 0,
        });

        imageView1.setColorFilter(new ColorMatrixColorFilter(colorMatrix1));
        imageView2.setColorFilter(new ColorMatrixColorFilter(colorMatrix2));
        imageView3.setColorFilter(new ColorMatrixColorFilter(colorMatrix3));
    }
}
