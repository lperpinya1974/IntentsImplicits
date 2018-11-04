package com.exemple.profedam.intentsimplicits;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.exemple.profedam.intentsimplicits.comunicacio.MailActivity;
import com.exemple.profedam.intentsimplicits.comunicacio.TelefonActivity;
import com.exemple.profedam.intentsimplicits.localitzacio.LocalitzacioActivity;
import com.exemple.profedam.intentsimplicits.navegacio.WebActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


   private final int MY_PERMISSIONS_REQUEST_CAMARA = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById (R.id.listView);
        ArrayAdapter<String> adaptador = new ArrayAdapter(this,  android.R.layout.simple_list_item_1);

        /* Llista de layouts xml possibles:
        https://github.com/aosp-mirror/platform_frameworks_base/tree/master/core/res/res/layout
        Documentació android.R.layout:
         http://developer.android.com/reference/android/R.layout.html
         */

       /* Por motivos didáctivos creo el adaptador paso a paso,
       lo habitual sería cargarlo a traves del metodo createFromResource
        */
        String llista[] = getResources().getStringArray(R.array.intents);
        adaptador.addAll(llista);
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(this);
        Toast.makeText(this, "Id del ListView es "+listView.getId(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        /* -- parent és el ListView
           -- view es el item donde se ha hecho click
           -- position es la position en la que está el item
           -- id es el id del item, NO ES EL MISMO QUE view.getId(), en un ListView position i id suelen coincidir
          */

        /* TODO canviar el següent codi per la funcionalitat
        real de la aplicació

        Toast.makeText(this, "Has clickado en " + position, Toast.LENGTH_SHORT).show();
        view.setBackgroundColor(Color.BLUE);
        Toast.makeText(this, "El id del item es" + id, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "El id del listView es" + parent.getId(), Toast.LENGTH_SHORT).show();
         */


        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(MainActivity.this, LocalitzacioActivity.class);
                break;
            case 1:
                intent = new Intent(MainActivity.this, TelefonActivity.class);
                break;
            case 2:
                intent = new Intent(MainActivity.this, WebActivity.class);

                break;
            case 3:
                intent = new Intent(MainActivity.this, MailActivity.class);

                break;

            case 4:
                requestPermissionAndOpenCamera();
                break;

            case 5:
                intent = new Intent("VoyaMorir");
                break;



        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    private void openCamera() {

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,0);
    }

    private void requestPermissionAndOpenCamera()
    {
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1) {// Marshmallow+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?

                    // No se necesita dar una explicación al usuario, sólo pedimos el permiso.
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMARA );
                    // MY_PERMISSIONS_REQUEST_CAMARA es una constante definida en la app. El método callback obtiene el resultado de la petición.

            }else{ //have permissions
                openCamera();
            }
        }else{ // Pre-Marshmallow
            openCamera();;
        }
    }
     @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMARA : {
                // Si la petición es cancelada, el array resultante estará vacío.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // El permiso ha sido concedido.
                    openCamera();
                } else {
                    // Permiso denegado, deshabilita la funcionalidad que depende de este permiso.
                }
                return;
            }
            // otros bloques de 'case' para controlar otros permisos de la aplicación
        }
    }
}


