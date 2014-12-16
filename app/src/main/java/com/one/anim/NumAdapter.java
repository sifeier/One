package com.one.anim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.one.R;
/**
 * Created by sifeier on 14-12-5.
 */
public class NumAdapter extends BaseAdapter {

    private Context mContext;
    LayoutInflater inflater;
    private int position;
    private List<String> mList = new ArrayList<>(10);

    public NumAdapter(Context context, List<String> mList) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(mContext);
        this.mList = mList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        this.position = position;
        View view ;
        if(convertView == null) {
            view = inflater.inflate(R.layout.item_num, parent, false);
            ViewHolder vv = new ViewHolder();
            vv.stringTV = (TextView) view.findViewById(R.id.item_tv);
            view.setTag(vv);
        } else {
            view = convertView;
        }
        ViewHolder vh = (ViewHolder)view.getTag();
        vh.stringTV.setText(mList.get(position));
        return view;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        if(position < 0 || position > mList.size()) {
            return null;
        }
        return mList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        TextView stringTV;
    }

}
