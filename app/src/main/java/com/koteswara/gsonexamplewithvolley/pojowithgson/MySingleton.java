package com.koteswara.gsonexamplewithvolley.pojowithgson;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private String TAG=getClass().getSimpleName();
    private static  MySingleton ourInstance;
    private RequestQueue requestQueue;
    private MySingleton(Context context) {
        this.context=context;
        requestQueue = getRequestQueue();
    }
    Context context;
    public static MySingleton getInstance(Context context) {
        if (ourInstance==null){
            ourInstance=new MySingleton(context);
        }
        return ourInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue==null){
            requestQueue=Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
    public void cancelPendingRequests(Object tag) {
        if (this.requestQueue != null) {
            this.requestQueue.cancelAll(tag);
        }
    }

}
