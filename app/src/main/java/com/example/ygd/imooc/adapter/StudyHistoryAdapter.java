package com.example.ygd.imooc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ygd.imooc.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
     Arvin
     此类用于学习记录的配置器
 */
public class StudyHistoryAdapter extends BaseAdapter {
    private Context context;
    private List<HashMap<String, String>> dataList;
    private ViewHolder vh;
    private Map<String, Integer> pitchOnMap;
    public StudyHistoryAdapter(Context context, List<HashMap<String, String>> list) {
        this.context = context;
        this.dataList = list;
        pitchOnMap = new HashMap<>();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
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
            HashMap<String, String> map = dataList.get(position);
            vh.getClassName().setText(map.get("name"));

            if(Double.valueOf(map.get("price"))==Double.valueOf(0)){
                vh.getClassPrice().setText("免费");
            }else{
                vh.getClassPrice().setText("￥ " + Double.valueOf(map.get("price")));
            }
        }
        return view;//相应的项的view
    }

    public Map<String, Integer> getPitchOnMap() {
        return pitchOnMap;
    }




    private class ViewHolder {
        private View view;
        private ImageView image;
        private TextView className;
        private TextView classPrice;

        public ViewHolder(View view) {
            this.view = view;
        }

        public ImageView getImage() {
            if (image == null) {
                image = (ImageView) view.findViewById(R.id.studyHistory_imageView1);
            }
            return image;
        }

        public TextView getClassName() {
            if (className == null) {
                className = (TextView) view.findViewById(R.id.studyHistory_classname);
            }
            return className;
        }


        public TextView getClassPrice() {
            if (classPrice == null) {
                classPrice = (TextView) view.findViewById(R.id.studyHistory_price);
            }
            return classPrice;
        }


    }
}
