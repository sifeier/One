package com.one.common.widget;

/**
 * Created by buke on 15/7/20.
 */
public class CustomMenuItem {

    private int iconId;

    private int textId;

    private String text;

    public CustomMenuItem() {
    }

    public CustomMenuItem(String text) {
        this.text = text;
    }

    public CustomMenuItem(int iconId, String text) {
        this.iconId = iconId;
        this.text = text;
    }

    public CustomMenuItem(int iconId, int textId) {
        this.iconId = iconId;
        this.textId = textId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getIconId() {
        return this.iconId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public int getTextId() {
        return this.textId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

}
