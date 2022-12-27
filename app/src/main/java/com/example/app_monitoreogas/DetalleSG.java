package com.example.app_monitoreogas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_monitoreogas.Modelo.SensorGas;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetalleSG extends AppCompatActivity {

    private TextView gasDetectado, dia, hora, duracion;
    private Button Eliminar, VolverAtras;
    private SensorGas sg;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_sg);

        //Inicializamos el objeto firebaseDatabase
        database = FirebaseDatabase.getInstance().getReference();
        //Referenciamos los views
        gasDetectado = (TextView) findViewById(R.id.sg_gas);
        dia = (TextView) findViewById(R.id.sg_dia);
        hora = (TextView) findViewById(R.id.sg_hora);
        duracion = (TextView) findViewById(R.id.sg_duracion);

        Bundle paquete = getIntent().getExtras();
        if(paquete!=null){
            sg = (SensorGas) paquete.getSerializable("valores-gas");

            gasDetectado.setText(sg.getGasDetectado().toString());
            dia.setText(sg.getDia().toString());
            hora.setText(sg.getHora().toString());
            duracion.setText(sg.getDuracion().toString());
        }

        Eliminar = (Button) findViewById(R.id.btnEliminar);
        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance().getReference("GasDetectado");
                database.child(sg.getIdGasDetectado()).removeValue();
                Toast.makeText(DetalleSG.this.getApplication(), "Se ha eliminado con exito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetalleSG.this, PanelPrincipal.class);
                startActivity(intent);
            }
        });

        VolverAtras = (Button) findViewById(R.id.btnVolverAtrasDSG);
        VolverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleSG.this, PanelPrincipal.class);
                startActivity(intent);
            }
        });
    }

}