package com.example.newsapp42.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.example.newsapp42.R;

public class Prefs {
    private final SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("names", Context.MODE_PRIVATE);
    }
    public void saveState(){
        preferences.edit().putBoolean("isShown", true).apply();
    }
    public boolean isShown (){
        return preferences.getBoolean("isShown", false);
    }

    public void saveImage(String image) {
        preferences.edit().putString("key_image", image).apply();
    }
    public String getImage(){
        Uri uri = Uri.parse("android.resource://com.example.newsapp42/"+ R.drawable.gg);
        String imgUrl = uri.toString();
        return preferences.getString("key_image",imgUrl);
    }
    public void saveText(String text){
        preferences.edit().putString("key_text", text).apply();
    }
    public String getText(){
        return preferences.getString("key_text","");
    }
}
