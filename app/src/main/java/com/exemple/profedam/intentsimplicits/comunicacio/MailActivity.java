package com.exemple.profedam.intentsimplicits.comunicacio;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.exemple.profedam.intentsimplicits.R;

public class MailActivity extends AppCompatActivity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        ((Button) findViewById(R.id.btnEnviar)).setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnEnviar) {
            EditText etAdreca = (EditText) findViewById(R.id.etAdreca);
            EditText etAssumpte = (EditText) findViewById(R.id.etAssumpte);
            EditText etContingut = (EditText) findViewById(R.id.etContingut);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setData(Uri.parse("mailto:"));
            //String[] to = direccionesEmail;
            //String[] cc = copias;
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{etAdreca.getText().toString()} );
           // intent.putExtra(Intent.EXTRA_CC, cc);
            intent.putExtra(Intent.EXTRA_SUBJECT, etAssumpte.getText().toString());
            intent.putExtra(Intent.EXTRA_TEXT, etContingut.getText().toString());
            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Email "));
        }
    }
}
