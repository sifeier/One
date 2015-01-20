package com.one.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.one.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sifeier on 15/1/20.
 */
public class ActionBaStylerActivity extends Activity {

    private static final String TAG = "ActionBaStylerActivity";
    private List<String> mList = new ArrayList<String>(40);
    private ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actionbar_style);


        for(int i=0; i<30; i++)
        {
            mList.add(String.valueOf(i));
        }

        MyAdapter adapter = new MyAdapter(this, mList);
        mLv = (ListView) findViewById(R.id.lv_container);
        mLv.setAdapter(adapter);
    }

    private class MyAdapter extends BaseAdapter {

        private List<String> mList;
        private Context mContext;
        private LayoutInflater mInflater;


        public MyAdapter(Context context, List<String> list) {
            mContext = context;
            mList = list;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = mInflater.inflate(R.layout.item_num, parent, false);
            }
            View v = convertView;
            TextView tv = (TextView) v.findViewById(R.id.item_tv);
            tv.setText(mList.get(position));
            return v;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

    }

}
