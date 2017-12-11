package com.pavel_kaminsky.googlehome_broadlink_bridge.models;

import java.io.Serializable;

/**
 * Created by PK on 12/11/2017.
 */

public class ToastError implements Serializable{

    String error;

    public ToastError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
