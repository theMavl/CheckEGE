package com.mavl.showmethefuckingresults;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebView;

public class LoginActivity extends AppCompatActivity {

    WebView webLogin;
    String participant = "";
    boolean succeed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Вход в личный кабинет");
        webLogin = (WebView) findViewById(R.id.webLogin);
        webLogin.getSettings().setJavaScriptEnabled(true);
        webLogin.getSettings().setAllowContentAccess(true);
        webLogin.clearCache(true);
        webLogin.clearHistory();
        webLogin.setWebViewClient(new EgeWebViewClient());
        webLogin.invokeZoomPicker();
        webLogin.getSettings().setSupportZoom(true);

        webLogin.getSettings().setBuiltInZoomControls(true);
        webLogin.loadUrl(getResources().getString(R.string.url_login));
    }

    public void success() {
        Log.d("PART", participant);
        Intent intent = new Intent();
        intent.putExtra("participantID", participant);
        setResult(MainActivity.RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Login", "Destroyed with "+succeed);
        if (!succeed) {
            setResult(MainActivity.RESULT_NEOK);
        }
    }

    public String getCookie(String siteName, String CookieName){
        String CookieValue = null;

        CookieManager cookieManager = CookieManager.getInstance();
        String cookies = cookieManager.getCookie(siteName);
        if(cookies != null){
            String[] temp=cookies.split(";");
            for (String ar1 : temp ){
                if(ar1.contains(CookieName)){
                    String[] temp1=ar1.split("=");
                    CookieValue = temp1[1];
                }
            }
        }
        return CookieValue;
    }

    class EgeWebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            //if (url.equals("http://check.ege.edu.ru/exams"))
            //    success();

        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            participant = getCookie("http://check.ege.edu.ru/", "Participant");
            if ((participant != null) && (!succeed)) {
                succeed = true;
                success();
            }
        }
    }
}


