package com.koteswara.gsonexamplewithvolley.pojowithgson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.koteswara.gsonexamplewithvolley.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private String URL="https://api.learn2crack.com/android/jsonandroid";
    ReccyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycleview);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        getInformation();
    }
    // parsing the inner json array as the GSON need a JSON Array as a starting point for parsing.
   private void typeOne(String response){
       Gson gson=new GsonBuilder().create();
       try {
           JSONObject jsonObject=new JSONObject(response);
           JSONArray jsonArray=jsonObject.getJSONArray("android");

           List<Android> androidList=new ArrayList<>();

           for(int i=0;i<jsonArray.length(); i++){
               Android android= gson.fromJson(jsonArray.get(i).toString(),Android.class);
               androidList.add(android);
           }
           adapter=new ReccyclerAdapter(androidList);
           recyclerView.setAdapter(adapter);

       } catch (JSONException e) {
           e.printStackTrace();
       }
    }
    // paring with using sigle line using the type
    private void typeTwo(String response){
        JSONObject jsonObject= null;
        JSONArray jsonArray=null;
        try {
            jsonObject = new JSONObject(response);
            jsonArray=jsonObject.getJSONArray("android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<Android> versionList = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<Android>>() {}.getType());
        adapter=new ReccyclerAdapter(versionList);
        recyclerView.setAdapter(adapter);

    }
    //parsing with GSON and Type as the sepearate objects
    private void typeThree(String response){
        Gson gson = new Gson();
        JSONObject jsonObject= null;
        JSONArray jsonArray=null;
        try {
            jsonObject = new JSONObject(response);
            jsonArray=jsonObject.getJSONArray("android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<List<Android>>() {}.getType();

        List<Android> versionList = gson.fromJson(jsonArray.toString(), type);

        adapter=new ReccyclerAdapter(versionList);
        recyclerView.setAdapter(adapter);
    }
    private void typeFour(String response){

        Gson gson = new Gson();

        AndroidVersion generalInfoObject = gson.fromJson(response, AndroidVersion.class);

        adapter=new ReccyclerAdapter(generalInfoObject.getAndroid());
        recyclerView.setAdapter(adapter);

    }

    private void getInformation(){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                typeFour(response);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}
