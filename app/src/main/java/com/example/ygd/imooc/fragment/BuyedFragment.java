package com.example.ygd.imooc.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ygd.imooc.R;
import com.example.ygd.imooc.adapter.StudyHistoryAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


/*
   已购买的tab页面   Arvin
 */
@SuppressLint("ValidFragment")
public class BuyedFragment extends Fragment {
    private ListView listView;
    private StudyHistoryAdapter adapter;
    private List<HashMap<String, String>> goodsList;

    View view;
    Context context;
    private final String title;


    public String getTitle() {
        return title;
    }
    public BuyedFragment(String title){
        super();
        this.title = title;

    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //创建视图
        /*textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);*/
        view = inflater.inflate(R.layout.tab_buyed, container, false);

        context=view.getContext();
        initDate();
        return view;
    }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.buyed_listview);

        adapter = new StudyHistoryAdapter(context, goodsList);
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
}
