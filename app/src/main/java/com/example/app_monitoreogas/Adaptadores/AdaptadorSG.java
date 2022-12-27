package com.example.app_monitoreogas.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_monitoreogas.Modelo.SensorGas;
import com.example.app_monitoreogas.R;

import java.util.ArrayList;

public class AdaptadorSG extends BaseAdapter {
    private Context contexto;
    private ArrayList<SensorGas> listItems;

    public AdaptadorSG(Context contexto, ArrayList<SensorGas> listItems) {
        this.contexto = contexto;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(contexto).inflate(R.layout.items_listview_sg,null);

        TextView GasDetectado = (TextView) view.findViewById(R.id.textGasDetectado);
        TextView Dia = (TextView) view.findViewById(R.id.textDia);

        SensorGas sg = (SensorGas) getItem(position);
        GasDetectado.setText(sg.getGasDetectado());
        Dia.setText(sg.getDia());

        return view;
    }

}