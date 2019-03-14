package com.example.acer.pixabayfragments;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class MainActivity extends AppCompatActivity
{
    TabLayout tl;
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tl=findViewById(R.id.tab);
        vp=findViewById(R.id.pager);
        vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tl.setupWithViewPager(vp);
    }
}
