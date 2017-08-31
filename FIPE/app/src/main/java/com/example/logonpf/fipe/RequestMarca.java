package com.example.logonpf.fipe;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestMarca implements Response.Listener<JSONArray>{

    private ArrayAdapter<Marca> adpMarca;

    public RequestMarca(ArrayAdapter<Marca> adpMarca){
        this.adpMarca = adpMarca;
    }

    @Override
    public void onResponse(JSONArray response) {

        for (int i = 0; i < response.length(); i++) {
            try {

                JSONObject obj = response.getJSONObject(i);
                adpMarca.add(new Marca(obj.getInt("id"), obj.getString("name")));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        Log.i("FIPE", response.toString());

    }
}
