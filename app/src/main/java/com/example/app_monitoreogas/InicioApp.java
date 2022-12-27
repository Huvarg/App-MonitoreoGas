package com.example.app_monitoreogas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class InicioApp extends Fragment {

    private Button icon1, icon2, icon3;

    public  static  InicioApp newInstance() {
        return new InicioApp();}

    public InicioApp() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_inicio_app, container, false);

        icon1 = vista.findViewById(R.id.icon1);
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedor, AddGasDetectado.newInstance());
                transaction.commit();
                Toast.makeText(getActivity().getApplication(), "Vas a agregar datos del sensor", Toast.LENGTH_SHORT).show();
            }
        });

        icon2 = vista.findViewById(R.id.icon2);
        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedor, ListadoSG.newInstance());
                transaction.commit();
                Toast.makeText(getActivity().getApplication(), "Vas a listado 1 de datos", Toast.LENGTH_SHORT).show();
            }
        });
        icon3 = vista.findViewById(R.id.icon3);
        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedor, readJSON.newInstance());
                transaction.commit();
                Toast.makeText(getActivity().getApplication(), "Vas a listado 2 de datos", Toast.LENGTH_SHORT).show();
            }
        });
        return vista;
    }

}