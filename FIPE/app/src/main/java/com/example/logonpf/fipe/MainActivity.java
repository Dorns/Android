package com.example.logonpf.fipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;


// http://fipeapi.appspot.com/api/1/carros/marcas.json
// http://fipeapi.appspot.com/api/1/carros/veiculos/21.json
// http://fipeapi.appspot.com/api/1/carros/veiculo/21/4828.json
// http://fipeapi.appspot.com/api/1/carros/veiculo/21/4828/2013-1.json


public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener{

    private Spinner spMarca;
    private ArrayAdapter<Marca> adpMarca;
    private List<Marca> marcas = new ArrayList<Marca>();

    private Spinner spVeiculo;
    private ArrayAdapter<Veiculo> adpVeiculo;
    private List<Veiculo> veiculos = new ArrayList<Veiculo>();

    private Spinner spModelo;
    private ArrayAdapter<Modelo> adpModelo;
    private List<Modelo> modelos = new ArrayList<Modelo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spMarca = (Spinner) findViewById(R.id.spMarca);
        spVeiculo = (Spinner) findViewById(R.id.spVeiculo);
        spModelo = (Spinner) findViewById(R.id.spModelo);


        adpMarca = new ArrayAdapter<Marca>(this,
                android.R.layout.simple_spinner_dropdown_item,
                marcas);
        spMarca.setAdapter(adpMarca);
        spMarca.setOnItemSelectedListener(this);

        adpVeiculo = new ArrayAdapter<Veiculo>(this,
                android.R.layout.simple_spinner_dropdown_item,
                veiculos);
        spVeiculo.setAdapter(adpVeiculo);
        spVeiculo.setOnItemSelectedListener(this);


        adpModelo = new ArrayAdapter<Modelo>(this,
                android.R.layout.simple_spinner_dropdown_item,
                modelos);
        spModelo.setAdapter(adpModelo);
        spModelo.setOnItemSelectedListener(this);

        carregarMarca();
    }

    private void carregarMarca() {

        String urlTXT = "http://fipeapi.appspot.com/api/1/carros/marcas.json";

        JsonArrayRequest req = new JsonArrayRequest(urlTXT,
                                                    new RequestMarca(adpMarca),
                                                    new RequestError());
        RequestQueue q = Volley.newRequestQueue(this);
        q.add(req);

    }

    private void carregarVeiculo(int idMarca) {

        String urlTXT =
                "http://fipeapi.appspot.com/api/1/carros/veiculos/" + idMarca + ".json";

        JsonArrayRequest req = new JsonArrayRequest(urlTXT,
                new RequestVeiculo(adpVeiculo),
                new RequestError());
        RequestQueue q = Volley.newRequestQueue(this);
        q.add(req);

    }

    private void carregarModelo(int idMarca, int idVeiculo) {

        String urlTXT =
                "http://fipeapi.appspot.com/api/1/carros/veiculo/" + idMarca + "/" + idVeiculo + ".json";
        JsonArrayRequest req = new JsonArrayRequest(urlTXT,
                new RequestModelo(adpModelo),
                new RequestError());
        RequestQueue q = Volley.newRequestQueue(this);
        q.add(req);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Object sel = parent.getItemAtPosition(position);

        if (sel instanceof Marca) {
            adpVeiculo.clear();
            int idMarca = ((Marca) sel).getId();
            carregarVeiculo(idMarca);

        } else  if (sel instanceof Veiculo) {
            adpModelo.clear();
            int idVeiculo = ((Veiculo) sel).getId();
            carregarModelo(idVeiculo);

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
