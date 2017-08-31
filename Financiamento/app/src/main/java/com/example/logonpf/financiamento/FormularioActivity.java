package com.example.logonpf.financiamento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText edtValor;
    private EditText edtParcelas;
    private EditText edtJuros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        edtValor = (EditText) findViewById(R.id.edtValor);
        edtParcelas = (EditText) findViewById(R.id.edtParcelas);
        edtJuros = (EditText) findViewById(R.id.edtJuros);
    }

    public void calcular(View v) {
        // ler as propriedades
        Double val = Double.parseDouble(edtValor.getText().toString());
        Integer par = Integer.parseInt(edtParcelas.getText().toString());
        Float jur = Float.parseFloat(edtJuros.getText().toString());

        // enviar as propriedades para CalculoActivity
        Intent i = new Intent(this, CalculoActivity.class);
        i.putExtra("valor", val);
        i.putExtra("parcelas", par);
        i.putExtra("juros", jur);

        startActivityForResult(i,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            Double r = data.getExtras().getDouble("retorno");
            Toast.makeText(this, "VoRRtei!: " + r, Toast.LENGTH_SHORT).show();

        }

    }
}
