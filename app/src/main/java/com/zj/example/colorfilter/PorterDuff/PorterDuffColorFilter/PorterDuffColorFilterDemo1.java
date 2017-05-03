package com.zj.example.colorfilter.PorterDuff.PorterDuffColorFilter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.zj.example.colorfilter.R;

/**
 *
 * Created by zhengjiong on 15/11/10.
 */
public class PorterDuffColorFilterDemo1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.porter_duff_colorfilter_demo);

        ImageView imageView = (ImageView) findViewById(R.id.img_1);


        //变暗了……也变红了……这就是PorterDuff.Mode.DARKEN模式给我们的效果
        PorterDuffColorFilter porterDuffColorFilter =
                new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN);

        imageView.setColorFilter(porterDuffColorFilter);
    }
}
