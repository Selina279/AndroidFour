package com.example.ygd.imooc.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ygd.imooc.R;
import com.example.ygd.imooc.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;


//未购买呢
/*
   未购买的tab页面   Arvin
 */
@SuppressLint("ValidFragment")
public class UnBuyFragment extends Fragment implements MyAdapter.RefreshPriceInterface, View.OnClickListener{
    private ListView listView;
    private CheckBox cb_check_all;
    private TextView tv_total_price;
    private TextView tv_delete;
    private TextView tv_go_to_pay;
    private double totalPrice = 0.00;
    private int totalCount = 0;
    private MyAdapter adapter;
    private List<HashMap<String, String>> goodsList;
    View view;
    Context context;
    private final String title;
    public String getTitle() {
        return title;
    }
    public UnBuyFragment(String title){
        super();
        this.title = title;

    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //创建视图
        /*textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);*/
        view = inflater.inflate(R.layout.activity_confirm, container, false);

        context=view.getContext();
        initDate();
        return view;
    }
    private void priceControl(Map<String, Integer> pitchOnMap) {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < goodsList.size(); i++) {
            if (pitchOnMap.get(goodsList.get(i).get("id")) == 1) {
                //  totalCount = totalCount + Integer.valueOf(goodsList.get(i).get("count"));
                double goodsPrice = Double.valueOf(goodsList.get(i).get("price"));
                totalPrice += goodsPrice;
                totalCount+=1;
            }
        }
        tv_total_price.setText("￥ " + totalPrice);
        tv_go_to_pay.setText("付款(" + totalCount + ")");
    }

    public void refreshPrice(Map<String, Integer> pitchOnMap) {

        priceControl(pitchOnMap);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_chekbox:
                AllTheSelected();
                break;
            case R.id.jiesuan_button://付款
                if (totalCount <= 0) {
                    Toast.makeText(context, "请选择要付款的商品~", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(context, "付款成功", Toast.LENGTH_SHORT).show();
                checkDelete(adapter.getPitchOnMap());
                break;
            case R.id.tv_delete:
                if (totalCount <= 0) {
                    Toast.makeText(context, "请选择要删除的商品~", Toast.LENGTH_SHORT).show();
                    return;
                }
                checkDelete(adapter.getPitchOnMap());
                break;
        }
    }

    private void checkDelete(Map<String, Integer> map) {
        List<HashMap<String, String>> waitDeleteList = new ArrayList<>();
        Map<String, Integer> waitDeleteMap = new HashMap<>();
        for (int i = 0; i < goodsList.size(); i++) {
            if (map.get(goodsList.get(i).get("id")) == 1) {
                waitDeleteList.add(goodsList.get(i));
                waitDeleteMap.put(goodsList.get(i).get("id"), map.get(goodsList.get(i).get("id")));
            }
        }
        goodsList.removeAll(waitDeleteList);
        map.remove(waitDeleteMap);
        priceControl(map);
        adapter.notifyDataSetChanged();
    }

    //全选或反选
    private void AllTheSelected() {
        Map<String, Integer> map = adapter.getPitchOnMap();
        boolean isCheck = false;
        boolean isUnCheck = false;
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if (Integer.valueOf(entry.getValue().toString()) == 1) isCheck = true;
            else isUnCheck = true;
        }
        if (isCheck == true && isUnCheck == false) {//已经    全选做反选
            for (int i = 0; i < goodsList.size(); i++) {
                map.put(goodsList.get(i).get("id"), 0);
            }
            cb_check_all.setChecked(false);
        } else if (isCheck == true && isUnCheck == true) {//部分选择做全选
            for (int i = 0; i < goodsList.size(); i++) {
                map.put(goodsList.get(i).get("id"), 1);
            }
            cb_check_all.setChecked(true);
        } else if (isCheck == false && isUnCheck == true) {//一个没选做全选
            for (int i = 0; i < goodsList.size(); i++) {
                map.put(goodsList.get(i).get("id"), 1);
            }
            cb_check_all.setChecked(true);
        }
        priceControl(map);
        adapter.setPitchOnMap(map);
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.listview1);
        cb_check_all = (CheckBox) view.findViewById(R.id.all_chekbox);
        tv_total_price = (TextView) view.findViewById(R.id.sumTextView);
        tv_total_price.setText(""+totalPrice);
        tv_delete = (TextView) view.findViewById(R.id.tv_delete);
        tv_go_to_pay = (TextView) view.findViewById(R.id.jiesuan_button);
        tv_go_to_pay.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        cb_check_all.setOnClickListener( this);
        adapter = new MyAdapter(context, goodsList);
        adapter.setRefreshPriceInterface(this);
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
