package com.one.net;

import com.one.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by buke on 15/7/31.
 *
 * test WebViewCoreThread running mode
 */
public class WebViewTest extends Activity {

    private WebView mWebView;
    private WebSettings mWebSettings;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_webview_test);

        mWebView = (WebView)findViewById(R.id.webview_wv);
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setBuiltInZoomControls(true);

        mWebView.loadUrl("https://www.baidu.com");

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);  // 使用当前WebView处理跳转
                return true;    //true表示此事件在此处被处理，不需要再广播
            }

            @Override	//转向错误时的处理
            public void onReceivedError(WebView view, int errorCode,
                    String description, String failingUrl) {
                Toast.makeText(WebViewTest.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }

        });
    }

}
