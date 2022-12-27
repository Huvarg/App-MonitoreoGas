package com.example.app_monitoreogas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class readJSON extends Fragment {

    private static final String URL_READ = "https://api.thingspeak.com/channels/1989979/fields/1.json?api_key=6QGMP45K376W2LDV&results=20";
    private TextView txt;
    private Button volverAtras;

    public readJSON() {
    }

    public static readJSON newInstance() {
        readJSON fragment = new readJSON();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_read_j_s_o_n, container, false);

        txt = vista.findViewById(R.id.listadoJSON);
        volverAtras = vista.findViewById(R.id.btnVolverJSON);
        readJSONListado();

        return vista;
    }

    public void readJSONListado () {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL_READ, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    String response = new String(responseBody);
                    processJSON(response);
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }

    private void processJSON (String json){
        try {
            JSONObject root = new JSONObject(json);
            JSONArray feeds = root.getJSONArray("feeds");
            String valor, texto="";
            for (int i=0; i<feeds.length(); i++){
                valor= feeds.getJSONObject(i).getString("field1");
                texto = texto + "Gas Detectado: " + valor + "\n";
            }
            txt.setText(texto);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}