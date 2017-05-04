package br.com.fiap.cep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText edtCEP;
    private TextView txtRua;
    private TextView txtCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtCEP = (EditText) findViewById(R.id.edtCEP);
        txtRua = (TextView) findViewById(R.id.txtRua);
        txtCidade = (TextView) findViewById(R.id.txtCidade);
    }

    class Erro implements Response.ErrorListener{

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(MainActivity.this, "Erro: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    class BuscaCEP implements Response.Listener<JSONObject>{

        @Override
        public void onResponse(JSONObject response) {
            Log.i("JSON_CEP", response.toString());

            try {
                txtRua.setText(response.getString("logradouro"));
                txtCidade.setText(response.getString("localidade"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void consultar(View v){
        String cep = edtCEP.getText().toString();
        String url = "http://viacep.com.br/ws/" + cep + "/json/";

        JsonObjectRequest req = new JsonObjectRequest(url, null, new BuscaCEP(), new Erro());

        RequestQueue q = Volley.newRequestQueue(this);

        q.add(req);
    }
}
