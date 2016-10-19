package com.exemple.profedam.intentsimplicits.navegacio;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.exemple.profedam.intentsimplicits.R;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Button btnNavegar = (Button) findViewById (R.id.btnNavegar);
        Button btnCercar = (Button) findViewById (R.id.btnCercar);
        btnNavegar.setOnClickListener(this);
        btnCercar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText editText = (EditText) findViewById (R.id.etCerca);
        Intent intent = null;

        if (v.getId() == R.id.btnNavegar) {
            CheckBox checkbox = (CheckBox) findViewById (R.id.encriptat);
            String protocol =  (checkbox.isChecked()) ? "https://":"http://";
            String uriCompleta = protocol+editText.getText().toString();
            intent = new Intent("android.intent.action.VIEW", Uri.parse(uriCompleta));
            /* Això es el mateix que
            intent = new Intent();
            intent.setAction ("android.intent.action.VIEW");
            intent.setData (Uri.parse(uriCompleta));
             */
        }

        if (v.getId() == R.id.btnCercar) {
            intent = new Intent("android.intent.action.WEB_SEARCH");
            intent.putExtra("query", editText.getText().toString());
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
