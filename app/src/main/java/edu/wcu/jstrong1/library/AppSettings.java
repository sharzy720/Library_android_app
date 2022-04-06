package edu.wcu.jstrong1.library;

import android.app.Application;
import android.graphics.drawable.Drawable;

/**
 * Application class for the apps settings
 */
public class AppSettings extends Application {

    /** Drawable name for the app's current background theme */
    private String appColor = "blue_gradient";

    /**
     * Set the background theme
     * @param color String of a color [red, green, blue, orange, purple]
     */
    public void setAppColor(String color) {
        this.appColor = color + "_gradient";
    }

    /**
     * Get the current background theme
     * @return Current background theme
     */
    public String getAppColor() {
        return appColor;
    }

//    public Drawable getAppDrawable() {
//        Context context = imageView.getContext();
//        int id = context.getResources().getIdentifier("picture0001", "drawable", context.getPackageName());
//        imageView.setImageResource(id);
//    }
}
