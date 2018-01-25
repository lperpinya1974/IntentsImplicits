package com.exemple.profedam.intentsimplicits;

import android.content.Intent;
import android.graphics.Color;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById (R.id.listView);
        ArrayAdapter<String> adaptador = new ArrayAdapter(this,  android.R.layout.simple_list_item_1);

        /* Llista de layouts xml possibles:
        https://github.com/aosp-mirror/platform_frameworks_base/tree/master/core/res/res/layout
        Documentació android.R.layout:

accepted
Zakaria, that is a reference to an built-in XML layout document that is part of the Android OS, rather than one of your own XML layouts.

Here is a further list of layouts that you can use: http://developer.android.com/reference/android/R.layout.html
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

        }
        if (intent != null) {
            startActivity(intent);
        }
    }
    }


