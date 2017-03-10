package br.com.fiap.pizzaria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private List<PizzaBean> pizzas;
    private ArrayAdapter<PizzaBean> adpSabor;
    private Spinner spSabores;
    private ImageView imgPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spSabores = (Spinner) findViewById(R.id.spSabor);
        imgPizza = (ImageView) findViewById(R.id.imgPizza);
        obterPizzas();
        exibeSabores();
        spSabores.setOnItemSelectedListener(this);
    }

    private void obterPizzas(){
        pizzas = new ArrayList<PizzaBean>();
        pizzas.add(new PizzaBean("Bacon", 27.0, R.drawable.pizzabacon));
        pizzas.add(new PizzaBean("Carbonara", 31.0, R.drawable.pizzacarbonara));
        pizzas.add(new PizzaBean("Pancia del Nono", 35.0, R.drawable.pizzapancianono));
        pizzas.add(new PizzaBean("Queijo", 22.0, R.drawable.pizzaqueijo));
        pizzas.add(new PizzaBean("Rúcula", 31.0, R.drawable.pizzarucula));
    }

    private void exibeSabores(){
        adpSabor = new ArrayAdapter<PizzaBean>(this, android.R.layout.simple_spinner_item, pizzas);
        spSabores.setAdapter(adpSabor);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (view.getId() == R.id.spSabor){
            PizzaBean sel = (PizzaBean) parent.getItemAtPosition(position);
            Toast.makeText(this, sel.getSabor(), Toast.LENGTH_SHORT).show();
            imgPizza.setImageResource(sel.getImagem());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Escolha um sabor", Toast.LENGTH_SHORT).show();
    }
}
