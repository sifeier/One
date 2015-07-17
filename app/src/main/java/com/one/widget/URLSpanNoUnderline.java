package com.one.widget;

import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.widget.TextView;

import java.net.URLStreamHandler;

/**
 * Created by buke on 15/7/16.
 */
public class URLSpanNoUnderline  extends URLSpan{

    public URLSpanNoUnderline(String url) {
        super(url);
    }

    @Override public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setUnderlineText(false);
    }

    public static void stripUnderlines(TextView textView) {
        Spannable s = (Spannable)textView.getText();
        URLSpan[] urlSpans = s.getSpans(0, s.length(), URLSpan.class);
        for(URLSpan span : urlSpans) {
            int start = s.getSpanStart(span);
            int end   = s.getSpanEnd(span);
            s.removeSpan(span);
            span = new URLSpanNoUnderline(span.getURL());
            s.setSpan(span, start, end, 0);
        }
    }
}
