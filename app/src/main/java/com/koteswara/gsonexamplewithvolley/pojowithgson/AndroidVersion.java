
package com.koteswara.gsonexamplewithvolley.pojowithgson;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class  AndroidVersion {

@SerializedName("android")
    List<Android> android;

    /*@SerializedName("android")
    @Expose
    private List<Android> android = null;
*/
    public List<Android> getAndroid() {
        return android;
    }

    public void setAndroid(List<Android> android) {
        this.android = android;
    }

}
