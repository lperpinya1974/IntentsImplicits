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
        ((Button) findViewById(R.id.btnLocalizar)).setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnLocalizar) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("geo:0,0?q=" + ((EditText) findViewById(R.id.etLocalitzar)).getText().toString())));
        }
    }
}
