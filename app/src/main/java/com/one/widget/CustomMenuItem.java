package com.one.widget;

/**
 * Created by renxia on 14-9-15.
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

    public CustomMenuItem(int iconId, int textId) {
        this.iconId = iconId;
        this.textId = textId;
    }

    public CustomMenuItem(int iconId, String text) {
        this.iconId = iconId;
        this.text = text;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public int getIconId() {
        return iconId;
    }

    public int getTextId() {
        return textId;
    }

    public String getText() {
        return text;
    }

}
