package com.exemple.profedam.intentsimplicits.comunicacio;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.exemple.profedam.intentsimplicits.R;



public class TelefonActivity extends AppCompatActivity implements View.OnClickListener {
    public final int PETICION_PERMISO_LLAMADA = 1000;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefon);
        ((Button) findViewById(R.id.btnTrucar)).setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnTrucar) {

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                llamar();

            } else {
                   /* A partir de la versió Android 6.0 hem de sol.licitar
            els permisos en temps d'execució */

                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                /* Si no tenim otorgat el permís CALL_PHONE */

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            PETICION_PERMISO_LLAMADA);
                } else {
                    llamar();
                }
            }


        }
    }

    public void llamar() {

        String numeroTelefon = ((EditText) findViewById (R.id.etTelefon)).getText().toString();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse ("tel:"+numeroTelefon));
        startActivity(intent);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PETICION_PERMISO_LLAMADA) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Permiso concedido
                llamar();



            } else {
                //Permiso denegado:
                //Deberíamos deshabilitar toda la funcionalidad relativa a la localización.

                Log.e("ERROR", "Permiso denegado");
            }
        }
    }
}




