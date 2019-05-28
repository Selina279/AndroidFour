package com.example.ygd.imooc.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ListView;

import com.example.ygd.imooc.R;
import com.example.ygd.imooc.adapter.StudyHistoryAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/*
学习 历史

数据需要在这进行显示
 */

public class Activity_StudyHistory extends AppCompatActivity {


    private ListView listView;
    private StudyHistoryAdapter adapter;
    private List<HashMap<String, String>> goodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_history);
        initDate();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.studyHistory_listview);

        adapter = new StudyHistoryAdapter(this, goodsList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    ////为数据的传入
    public void initDate() {
        goodsList = new ArrayList<>();
        /*for (int i = 0; i < 10; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", (new Random().nextInt(10000) % (10000 - 2900 + 2900) + 2900) + "");
            map.put("name", "购物车里的第" + (i + 1) + "件商品");
            map.put("price", (new Random().nextInt(100) % (100 - 29 + 29) + 29) + "");
            goodsList.add(map);
        }*/
        HashMap<String, String> map = new HashMap<>();
        map.put("id", (new Random().nextInt(10000) % (10000 - 2900 + 2900) + 2900) + "");
        map.put("name", "JAVA");
        map.put("price", "500");
        goodsList.add(map);
        map = new HashMap<>();
        map.put("id", (new Random().nextInt(10000) % (10000 - 2900 + 2900) + 2900) + "");
        map.put("name", "Web");
        map.put("price", "200");
        goodsList.add(map);
        initView();
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