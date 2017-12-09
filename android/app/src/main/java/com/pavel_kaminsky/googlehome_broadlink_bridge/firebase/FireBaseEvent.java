package com.pavel_kaminsky.googlehome_broadlink_bridge.firebase;

/**
 * Created by PK on 12/9/2017.
 */

public class FireBaseEvent  {

    String data;

    public FireBaseEvent(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
