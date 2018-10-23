package com.mazitekgh.mtnmomo;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * MtnMomo
 * Created by Zakaria on 11-Oct-18 at 12:39 PM.
 */
class SharedPref {

    private static final String MOMO_KEY = "momo_key";
    private static final String TOTAL_RECEIVED_KEY = "total_received_key";
    private static final String TOTAL_SENT_KEY = "total_sent_key";
    private static String BALANCE_KEY = "balance_key";
    private static String SHARED_KEY = "com.mazitekgh.mtnmomo.total_received_amount";
    private Context c;

    public SharedPref(Context context) {
        this.c = context;
    }

    public void storeTotalAmount(String totalReceivedAmount, String totalSentAmount, String balance) {
        SharedPreferences sp = c.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(TOTAL_RECEIVED_KEY, totalReceivedAmount);
        editor.putString(TOTAL_SENT_KEY, totalSentAmount);
        editor.putString(BALANCE_KEY, balance);
        editor.apply();

    }

    public HashMap<String, String> getTotalValues() {
        SharedPreferences sp = c.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        HashMap<String, String> hs = new HashMap<>(3);

        hs.put(TOTAL_RECEIVED_KEY, sp.getString(TOTAL_RECEIVED_KEY, "0"));
        hs.put(TOTAL_SENT_KEY, sp.getString(BALANCE_KEY, "0"));
        hs.put(BALANCE_KEY, sp.getString(BALANCE_KEY, "0"));

        return hs;
    }

    public void storeMomoMessages(String gsonForm) {

        SharedPreferences sp = c.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(MOMO_KEY, gsonForm);
        editor.apply();
    }

    public List getStoreMomoMessages() {
        Gson gs = new Gson();

        SharedPreferences sp = c.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        String gsonString = sp.getString(MOMO_KEY, null);
        if (gsonString == null) {
            return null;
        }
        return gs.fromJson(gsonString, List.class);
    }
}