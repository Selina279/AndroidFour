package com.example.ygd.imooc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
/*
页面的滚动   付款的页面
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> fragments;
    private String str="学习记录";

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    /**
     * 根据位置返回对应的Fragment
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 得到页面的标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "未购买";
        }else if(position==1){
            return "已购买";
        }
        return "";
    }
}
