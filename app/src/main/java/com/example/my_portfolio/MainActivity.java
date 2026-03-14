package com.example.my_portfolio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // XML layout load ho rahi hai
        setContentView(R.layout.activity_main);

        // XML ke WebView ko yahan connect kar rahe hain
        WebView myWebView = (WebView) findViewById(R.id.myWebView);

        if (myWebView != null) {
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            // Link handling logic yahan add ki hai
            myWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    // Agar link LinkedIn ya GitHub ka hai, toh phone ke default browser mein kholo
                    if (url != null && (url.contains("linkedin.com") || url.contains("github.com"))) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        view.getContext().startActivity(intent);
                        return true; // Iska matlab app ke andar load mat karo
                    }
                    // Baaki local files (index.html) app ke andar hi chalein
                    return false;
                }
            });

            // Portfolio load karna
            myWebView.loadUrl("file:///android_asset/index.html");
        }
    }
}