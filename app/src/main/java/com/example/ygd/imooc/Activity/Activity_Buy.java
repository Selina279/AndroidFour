package com.example.ygd.imooc.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.ygd.imooc.R;
import com.example.ygd.imooc.adapter.ViewPagerAdapter;
import com.example.ygd.imooc.fragment.BuyedFragment;
import com.example.ygd.imooc.fragment.UnBuyFragment;

import java.util.ArrayList;
/*
   为购买页面   Arvin
 */
public class Activity_Buy extends AppCompatActivity {

    ViewPager viewPager;
    // ArrayList<MyFragment> fragments;
    ArrayList<Fragment> fragments;

    ViewPagerAdapter adapter;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_state);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        //初始化数据
        fragments=new ArrayList<>();
        for(int i=0;i<2;i++){
            switch (i){
                case 0:
                    fragments.add(new UnBuyFragment("学习记录"));
                    break;
                case 1:
                    tabLayout.addTab(tabLayout.newTab().setText("我的订单"));
                    fragments.add(new BuyedFragment("我的订单"));
                    break;
            }
        }
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            finish();//返回关闭当前Activity
            return true;//应该是不在往下进行了
        } else {
            return super.dispatchKeyEvent(event);
        }
    }
}
