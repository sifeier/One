package com.one.mdesign;

import com.one.R;
import com.one.util.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ButtonFloatSmall extends ButtonFloat {

    public ButtonFloatSmall(Context context, AttributeSet attrs) {
        super(context, attrs);
        sizeRadius = 20;
        sizeIcon = 20;
        setDefaultProperties();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                Utils.dp2px(getContext(), sizeIcon), Utils.dp2px(getContext(), sizeIcon));
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        icon.setLayoutParams(params);
    }

    protected void setDefaultProperties() {
        rippleSpeed = Utils.dp2px(getContext(), 2);
        rippleSize = 10;
        // Min size
        setMinimumHeight(Utils.dp2px(getContext(), sizeRadius * 2));
        setMinimumWidth(Utils.dp2px(getContext(), sizeRadius * 2));
        // Background shape
        setBackgroundResource(R.drawable.background_button_float);
    }

}
