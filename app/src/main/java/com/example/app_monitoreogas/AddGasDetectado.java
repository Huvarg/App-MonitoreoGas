package com.example.app_monitoreogas;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.app_monitoreogas.Modelo.SensorGas;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddGasDetectado extends Fragment {

    private EditText GasDetectado, Dia, Hora, Duracion;
    private Button AddGasDetectado, VolverAtras;
    private DatabaseReference database;

    public static AddGasDetectado newInstance() {
        AddGasDetectado fragment = new AddGasDetectado();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_gas_detectado, container, false);

        //Inicializamos el objeto firebaseDatabase
        database = FirebaseDatabase.getInstance().getReference();
        //Referenciamos los views
        GasDetectado = (EditText) view.findViewById(R.id.etGasDetectado);
        Dia = (EditText) view.findViewById(R.id.etDia);
        Hora = (EditText) view.findViewById(R.id.etHora);
        Duracion = (EditText) view.findViewById(R.id.etDuracion);

        AddGasDetectado = (Button) view.findViewById(R.id.btnRegistro);
        AddGasDetectado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gasDetectado = GasDetectado.getText().toString().trim();
                String dia = Dia.getText().toString().trim();
                String hora = Hora.getText().toString().trim();
                String duracion = Duracion.getText().toString().trim();

                if (TextUtils.isEmpty(gasDetectado)) {
                    Toast.makeText(getActivity(), "Se debe ingresar una medicion de gas", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(dia)) {
                    Toast.makeText(getActivity(), "Se debe ingresar un dia", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(hora)) {
                    Toast.makeText(getActivity(), "Se debe ingresar una hora", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(duracion)) {
                    Toast.makeText(getActivity(), "Se debe ingresar una duracion", Toast.LENGTH_LONG).show();
                    return;
                }

                final String randomKey = FirebaseDatabase.getInstance().getReference().push().getKey();
                SensorGas sg = new SensorGas(randomKey, gasDetectado, dia, hora, duracion);
                database.child("GasDetectado").child(randomKey).setValue(sg);
                Toast.makeText(getActivity(), "Registro realizado con exito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), PanelPrincipal.class);
                startActivity(intent);
            }
        });

        VolverAtras = (Button) view.findViewById(R.id.btnVolverAtrasAGT);
        VolverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PanelPrincipal.class);
                startActivity(intent);
            }
        });
        return view;
    }

}