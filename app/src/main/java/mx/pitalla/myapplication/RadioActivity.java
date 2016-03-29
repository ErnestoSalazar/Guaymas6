package mx.pitalla.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import mx.pitalla.myapplication.funciones.miActionBar;

public class RadioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        miActionBar ab = new miActionBar(getSupportActionBar(),"radio");

        WebView browser = (WebView) findViewById(R.id.webRadio);

        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setDomStorageEnabled(true);
       // browser.loadUrl("http://mixlr.com/users/1847448/embed/?autoplay=true");

        browser.loadUrl("http://pitalla.com/radioguaymas/");
    }
}
