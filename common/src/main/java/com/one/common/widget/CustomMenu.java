package com.one.common.widget;

import com.one.common.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by buke on 15/7/17.
 */
public class CustomMenu extends Dialog {

    private Context mContext;

    private ListView mListView;

    private List<CustomMenuItem> mListItem = new ArrayList<CustomMenuItem>();

    private AdapterView.OnItemClickListener mOnItemClickListener;

    private boolean isLeft = false;

    private int hasAvatorPosition = -1;

    public CustomMenu(Context context) {
        this(context, R.style.menu_dialog_style);
    }

    public CustomMenu(Context context, int theme) {
        super(context, theme);
        mContext = context;
        initView();
    }

    private void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_menu);


    }



    class ViewHolder{

    }


}
