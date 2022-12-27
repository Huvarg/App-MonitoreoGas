package com.example.app_monitoreogas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.app_monitoreogas.Adaptadores.AdaptadorSG;
import com.example.app_monitoreogas.Modelo.SensorGas;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListadoSG extends Fragment {

    private DatabaseReference database;
    private SensorGas sg;
    private ListView listV;
    private ArrayList<SensorGas> listadoSG;

    public ListadoSG() {
    }

    public static ListadoSG newInstance() {
        ListadoSG fragment = new ListadoSG();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listado_s_g, container, false);

        listadoSG = new ArrayList<>();
        sg = new SensorGas();
        listV = view.findViewById(R.id.listadoGasDetectado);
        AdaptadorSG adaptadorSG = new AdaptadorSG(getContext(), listadoSG);

        database = FirebaseDatabase.getInstance().getReference("GasDetectado");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    listadoSG.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String idGasDetectado = ds.child("idGasDetectado").getValue(String.class);
                        String gasDetectado = ds.child("gasDetectado").getValue(String.class);
                        String dia = ds.child("dia").getValue(String.class);
                        String hora = ds.child("hora").getValue(String.class);
                        String duracion = ds.child("duracion").getValue(String.class);
                        SensorGas sg = new SensorGas(idGasDetectado, gasDetectado, dia, hora, duracion);
                        listadoSG.add(sg);
                        listV.setAdapter(adaptadorSG);
                    }
                    adaptadorSG.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Fallo de lectura: " + error.getCode());
            }
        });

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SensorGas sg = listadoSG.get(position);
                Toast.makeText(getActivity(), "Selecciono el gas de id: "+sg.getIdGasDetectado(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DetalleSG.class);
                intent.putExtra("valores-gas",sg);
                startActivity(intent);
            }
        });
        return view;
    }

}