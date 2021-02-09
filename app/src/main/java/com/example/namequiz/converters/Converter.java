package com.example.namequiz.converters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class Converter {

    @TypeConverter
    public static Bitmap fromString(String s){
        try {
            byte [] encodeByte= Base64.decode(s, Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    @TypeConverter
    public static String fromBitmap(Bitmap b){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] bytes=baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }
}
