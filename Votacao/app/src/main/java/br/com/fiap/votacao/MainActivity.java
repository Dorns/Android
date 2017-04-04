package br.com.fiap.votacao;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    private List<CandidatoBean> prefeito;
    private List<CandidatoBean> vereador;

    private ArrayAdapter<CandidatoBean> adpPrefeito;
    private ArrayAdapter<CandidatoBean> adpVereador;

    private CandidatoBean prefeitoVoto;
    private CandidatoBean vereadorVoto;

    private RadioGroup rgCargo;

    private Spinner spCandidato;

    private TextView txtPrefeito;
    private TextView txtVereador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgCargo = (RadioGroup) findViewById(R.id.rgCargo);

        //populando o Spinner
        spCandidato = (Spinner) findViewById(R.id.spCandidato);

        txtPrefeito = (TextView) findViewById(R.id.txtPrefeito);
        txtVereador = (TextView) findViewById(R.id.txtVereador);

        criarCandidatos();
        popularPrefeito();
        popularVereador();

        rgCargo.setOnCheckedChangeListener(this);
        spCandidato.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void criarCandidatos() {
        prefeito = new ArrayList<CandidatoBean>();
        vereador = new ArrayList<CandidatoBean>();

        prefeito.add(new CandidatoBean("Zé da feira", "XTC"));
        prefeito.add(new CandidatoBean("Maria Melhor", "LLL"));
        prefeito.add(new CandidatoBean("Brandão Filho", "ZT0"));

        vereador.add(new CandidatoBean("Joana Nascimento", "XTC"));
        vereador.add(new CandidatoBean("Lucio Della Pimenta", "LLL"));
        vereador.add(new CandidatoBean("Mariano Maria", "ZT0"));
    }

    private void popularPrefeito() {
        adpPrefeito = new ArrayAdapter<CandidatoBean>(this, android.R.layout.simple_spinner_item, prefeito);
        spCandidato.setAdapter(adpPrefeito);
    }

    private void popularVereador() {
        adpVereador = new ArrayAdapter<CandidatoBean>(this, android.R.layout.simple_spinner_item, vereador);
        spCandidato.setAdapter(adpVereador);
    }

    public void votar(View v){
        if (rgCargo.getCheckedRadioButtonId() == R.id.rbPrefeito)
            txtPrefeito.setText(prefeitoVoto.getNome());
        else
            txtVereador.setText(prefeitoVoto.getNome());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (rgCargo.getCheckedRadioButtonId() == R.id.rbPrefeito)
            prefeitoVoto = (CandidatoBean) parent.getItemAtPosition(position);
        else
            vereadorVoto = (CandidatoBean) parent.getItemAtPosition(position);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

    }
}
