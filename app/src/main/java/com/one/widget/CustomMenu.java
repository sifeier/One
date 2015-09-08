package com.one.widget;


import com.one.R;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义menu 和 item
 * Created by renxia on 14-9-11.
 */
public class CustomMenu extends Dialog {

    private Activity mActivity;

    private ListView listView;
    private List<CustomMenuItem> list = new ArrayList<CustomMenuItem>();

    private AdapterView.OnItemClickListener itemClickListener;

    private int hasAvatorPosition = -1;

    private boolean isLeft = false;

    public CustomMenu(Activity activity) {
        this(activity, R.style.menu_dialog_style);
        this.mActivity = activity;
    }

    public CustomMenu(Activity activity, int style){
        super(activity, style);
        this.mActivity = activity;
    }

    public void addList(CustomMenuItem itemObj){
        list.add(itemObj);
    }

    public void addList(List<CustomMenuItem> itemObjs){
        list.addAll(itemObjs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_menu);
        WindowManager.LayoutParams a = this.getWindow().getAttributes();
        a.dimAmount = 0; //去背景遮盖
        this.getWindow().setAttributes(a);

        this.setCancelable(true);
        this.setCanceledOnTouchOutside(true);

        listView = (ListView) findViewById(R.id.menu_list);
        listView.setAdapter(new MenuAdapter());

        View mPopupBg = this.findViewById(R.id.popup_bg);
        mPopupBg.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    dismiss();
                }
                return true;
            }
        });

        listView.setOnItemClickListener(itemClickListener);
        listView.post(new Runnable() {
            @Override
            public void run() {
                if(isLeft){
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) listView.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                    listView.setLayoutParams(params);
                }else {
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) listView.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                    listView.setLayoutParams(params);
                }
            }
        });
    }

    public void setLeft(boolean isLeft) {
        this.isLeft = isLeft;
    }

    public void setItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setHasAvatorPosition(int hasAvatorPosition) {
        this.hasAvatorPosition = hasAvatorPosition;
    }


    public class MenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null){
                viewHolder = new ViewHolder();
                view = mActivity.getLayoutInflater().inflate(R.layout.custom_menu_item, null);
                viewHolder.avatarView = (ImageView) view.findViewById(R.id.item_icon);
                viewHolder.textView = (TextView) view.findViewById(R.id.item_name);
                view.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) view.getTag();
            }

            final CustomMenuItem itemObj = list.get(i);
            viewHolder.avatarView.setVisibility(View.VISIBLE);
            if (itemObj.getIconId() != 0){
                viewHolder.avatarView.setImageResource(itemObj.getIconId());
            }else {
                viewHolder.avatarView.setImageDrawable(null);
                viewHolder.avatarView.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(itemObj.getText()))
                viewHolder.textView.setText(itemObj.getText());
            else if (itemObj.getTextId() != 0)
                viewHolder.textView.setText(itemObj.getTextId());
            else
                viewHolder.textView.setText("");

            return view;
        }
    }

    class ViewHolder{
       ImageView avatarView;
       TextView textView;
    }
}
