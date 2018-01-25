package com.exemple.profedam.intentsimplicits.localitzacio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.exemple.profedam.intentsimplicits.R;


public class LocalitzacioActivity extends AppCompatActivity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localitzacio);

        Button btnLocalizar = (Button) findViewById (R.id.btnLocalizar);
        btnLocalizar.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnLocalizar) {

            EditText etLocalizar = (EditText) findViewById(R.id.etLocalitzar);
            String ciudad = etLocalizar.getText().toString();
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("geo:0,0?q=" + ciudad));

            startActivity(intent);
        }
    }
}
