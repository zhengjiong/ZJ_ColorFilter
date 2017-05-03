package com.zj.example.colorfilter.PorterDuff.PorterDuffXfermode;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.zj.example.colorfilter.PorterDuff.PorterDuffXfermode.view.PorterDuffXfermodeView1;
import com.zj.example.colorfilter.R;

/**
 * Created by zj on 2017/5/3.
 */

public class PorterDuffXfermodeDemo1 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_porter_duff_xfermode);

        final PorterDuffXfermodeView1 porterDuffXfermodeView1 = (PorterDuffXfermodeView1) findViewById(R.id.portduffview);

        ((RadioGroup) findViewById(R.id.radiogroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        porterDuffXfermodeView1.setXfermode(null);
                        break;
                    case R.id.rb_2:
                        //只保留目标图像的 alpha 和 color，所以绘制出来的只有目标图，也就是屏幕上的的蓝色图
                        porterDuffXfermodeView1.setXfermode(PorterDuff.Mode.DST);
                        break;
                    case R.id.rb_3:
                        //只保留源图像的 alpha 和 color ，所以绘制出来只有源图，有时候会感觉分不清先绘制的是源图还是后绘制的是源图，这个时候可以这么记，先绘制的是目标图，不管任何时候，一定要做一个有目标的人，目标在前！
                        //这个时候绘制的情形是只有屏幕上的红色圆；
                        porterDuffXfermodeView1.setXfermode(PorterDuff.Mode.SRC);
                        break;
                    case R.id.rb_4:
                        //在两者相交的地方绘制源图像，并且绘制的效果会受到目标图像对应地方透明度的影响；
                        porterDuffXfermodeView1.setXfermode(PorterDuff.Mode.SRC_IN);
                        break;
                    case R.id.rb_5:
                        //可以和SRC_IN进行类比，在两者相交的地方绘制目标图像，并且绘制的效果会受到源图像对应地方透明度的影响；
                        porterDuffXfermodeView1.setXfermode(PorterDuff.Mode.DST_IN);
                        break;

                    case R.id.rb_6:
                        //将目标图像绘制在上方,这时候就会看到先绘制的蓝色盖在了红色圆的上面
                        porterDuffXfermodeView1.setXfermode(PorterDuff.Mode.DST_OVER );
                        break;
                }
            }
        });
    }
}
