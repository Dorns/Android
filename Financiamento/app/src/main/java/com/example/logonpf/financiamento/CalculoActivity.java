package com.example.logonpf.financiamento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CalculoActivity extends AppCompatActivity {

    private TextView txtResultado;
    private double resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);

        txtResultado = (TextView) findViewById(R.id.txtCalculo);
        Bundle param = getIntent().getExtras();

        double val = param.getDouble("valor");
        int par = param.getInt("parcelas");
        float jur = param.getFloat("juros");

        resultado = val * par * jur;

        txtResultado.setText(String.valueOf(resultado));


    }

    @Override
    public void finish() {

        Intent ret = new Intent();
        ret.putExtra("retorno", resultado);
        setResult(RESULT_OK, ret);

        super.finish();
    }
}
