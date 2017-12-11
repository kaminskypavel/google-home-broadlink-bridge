package com.pavel_kaminsky.googlehome_broadlink_bridge.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.pavel_kaminsky.googlehome_broadlink_bridge.models.ToastError;

import org.greenrobot.eventbus.EventBus;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by PK on 12/11/2017.
 */

public class RMBridgeHttpAsync extends AsyncTask<Void, Void, Response> {
    String httpUrl;

    public RMBridgeHttpAsync(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    @Override
    protected Response doInBackground(Void... voids) {
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(httpUrl)
                    .build();

            Log.d("RMBridgeHttpAsync", httpUrl);
            Response response = client.newCall(request).execute();
            Log.d("RMBridgeHttpAsync", response.body().string());
            return response;
        } catch (Exception e) {
            EventBus.getDefault().post(new ToastError("Can't Reach RM Bridge App"));
            return null;
        }
    }

}
