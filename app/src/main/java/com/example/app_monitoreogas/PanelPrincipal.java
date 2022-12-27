package com.example.app_monitoreogas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PanelPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_principal);

        //Inicio
        InicioApp i = new InicioApp();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,i).commit();
    }

}