package com.zj.example.colorfilter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zj.example.colorfilter.PorterDuff.PorterDuffColorFilter.PorterDuffColorFilterDemo1;
import com.zj.example.colorfilter.PorterDuff.PorterDuffXfermode.PorterDuffXfermodeDemo1;
import com.zj.example.colorfilter.PorterDuff.PorterDuffXfermode.PorterDuffXfermodeDemo2;
import com.zj.example.colorfilter.PorterDuff.PorterDuffXfermode.PorterDuffXfermodeDemo3;

/**
 * ColorMatrixDemo
 * http://blog.csdn.net/aigestudio/article/details/41316141
 * <p>
 * Created by zhengjiong on 15/11/9.
 */
public class ListActivity extends AppCompatActivity {

    private String[] items = new String[]{
            "ColorMatrixColorFilter Demo",
            "LightColorFilter Demo",
            "PorterDuffColorFilter Demo1",
            "PorterDuffXfermode Demo1",
            "PorterDuffXfermode Demo2",
            "PorterDuffXfermode Demo3"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);


        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(
                new ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1, items
                )
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(ListActivity.this, ColorMatrixColorFilterDemo.class));
                        break;
                    case 1:
                        startActivity(new Intent(ListActivity.this, LightColorFilterDemo.class));
                        break;
                    case 2:
                        startActivity(new Intent(ListActivity.this, PorterDuffColorFilterDemo1.class));
                        break;
                    case 3:
                        startActivity(new Intent(ListActivity.this, PorterDuffXfermodeDemo1.class));
                        break;
                    case 4:
                        startActivity(new Intent(ListActivity.this, PorterDuffXfermodeDemo2.class));
                        break;
                    case 5:
                        startActivity(new Intent(ListActivity.this, PorterDuffXfermodeDemo3.class));

                        break;
                }
            }
        });
    }
}
