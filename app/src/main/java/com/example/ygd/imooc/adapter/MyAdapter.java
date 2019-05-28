package com.example.ygd.imooc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ygd.imooc.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
课程未购买
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<HashMap<String, String>> dataList;
    private ViewHolder vh;
    private Map<String, Integer> pitchOnMap;
    private RefreshPriceInterface refreshPriceInterface;
    public MyAdapter(Context context, List<HashMap<String, String>> list) {
        this.context = context;
        this.dataList = list;
        pitchOnMap = new HashMap<>();
        for (int i = 0; i < dataList.size(); i++) {
            pitchOnMap.put(dataList.get(i).get("id"), 0);
        }
    }




    @Override
    public int getCount() {//数据源项的数
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {//每一项是什么
        return position;
    }

    @Override
    public long getItemId(int position) {//这一项的ID
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view;
        //ViewHolder holder = null;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_commodity, parent, false);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
            /*ImageView image = holder.getImage();
            image.setImageResource(imResIds[position]);
            TextView names = holder.getClassName();
            names.setText(name[position]);
            TextView moneys = holder.getClassPrice();
            moneys.setText(money[position]);
            CheckBox checkBox = holder.getCheckBox();*/


        if (dataList.size() > 0) {
            if (pitchOnMap.get(dataList.get(position).get("id")) == 1)
                vh.getCheckBox().setChecked(true);
            else vh.getCheckBox().setChecked(false);
            HashMap<String, String> map = dataList.get(position);
            vh.getClassName().setText(map.get("name"));
            vh.getClassPrice().setText("￥ " + Double.valueOf(map.get("price")));
            vh.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (((CheckBox) view).isChecked())
                        pitchOnMap.put(dataList.get(position).get("id"), 1);
                    else
                        pitchOnMap.put(dataList.get(position).get("id"),0);

                    refreshPriceInterface.refreshPrice(pitchOnMap);
                }
            });
        }



        return view;//相应的项的view
    }

    public Map<String, Integer> getPitchOnMap() {
        return pitchOnMap;
    }

    public void setPitchOnMap(Map<String, Integer> pitchOnMap) {
        this.pitchOnMap = new HashMap<>();
        this.pitchOnMap.putAll(pitchOnMap);
    }

    public interface RefreshPriceInterface {
        void refreshPrice(Map<String, Integer> pitchOnMap);
    }

    public void setRefreshPriceInterface(RefreshPriceInterface refreshPriceInterface) {
        this.refreshPriceInterface = refreshPriceInterface;
    }

    class ViewHolder {
        private View view;
        private CheckBox checkBox;
        private ImageView image;
        private TextView className;
        private TextView classPrice;

        public ViewHolder(View view) {
            this.view = view;
        }

        public ImageView getImage() {
            if (image == null) {
                image = (ImageView) view.findViewById(R.id.imageView1);
            }
            return image;
        }

        public TextView getClassName() {
            if (className == null) {
                className = (TextView) view.findViewById(R.id.classname);
            }
            return className;
        }


        public TextView getClassPrice() {
            if (classPrice == null) {
                classPrice = (TextView) view.findViewById(R.id.price);
            }
            return classPrice;
        }

        public CheckBox getCheckBox() {
            if (checkBox == null) {
                checkBox = (CheckBox) view.findViewById(R.id.pro_checkbox);
            }
            return checkBox;
        }
    }
}
