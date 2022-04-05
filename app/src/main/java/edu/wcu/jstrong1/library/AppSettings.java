package edu.wcu.jstrong1.library;

import android.app.Application;
import android.graphics.drawable.Drawable;

public class AppSettings extends Application {

    private String appColor = "blue_gradient";

    public void setAppColor(String color) {
        this.appColor = color + "_gradient";
    }

    public String getAppColor() {
        return appColor;
    }

//    public Drawable getAppDrawable() {
//        Context context = imageView.getContext();
//        int id = context.getResources().getIdentifier("picture0001", "drawable", context.getPackageName());
//        imageView.setImageResource(id);
//    }
}
