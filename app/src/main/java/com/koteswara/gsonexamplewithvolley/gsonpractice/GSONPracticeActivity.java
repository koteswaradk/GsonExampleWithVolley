package com.koteswara.gsonexamplewithvolley.gsonpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koteswara.gsonexamplewithvolley.pojowithgson.MySingleton;
import com.koteswara.gsonexamplewithvolley.R;

import java.util.List;

public class GSONPracticeActivity extends AppCompatActivity {
    private String TAG=getClass().getSimpleName();
    private String URL="https://kylewbanks.com/rest/posts.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsonpractice);
        getInformation();

    }
    private void getInformation(){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                List<Topic> versionList = new Gson().fromJson(response, new TypeToken<List<Topic>>() {}.getType());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
