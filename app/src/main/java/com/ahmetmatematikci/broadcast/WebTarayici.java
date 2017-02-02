package com.ahmetmatematikci.broadcast;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

/**
 * Created by a on 2/2/17.
 */

public class WebTarayici extends Activity {
    private EditText et;
    private WebView wv;

    private WebViewClient mViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(String.valueOf(request));
            return true;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webtarayici);
        et = (EditText)findViewById(R.id.et_internet);
        wv = (WebView)findViewById(R.id.wv_internet);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.button3:
                String adres =et.getText().toString();
                wv.getSettings().setJavaScriptEnabled(true);
                wv.loadUrl(adres);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode ==KeyEvent.KEYCODE_BACK  && wv.canGoBack()) {
            wv.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
