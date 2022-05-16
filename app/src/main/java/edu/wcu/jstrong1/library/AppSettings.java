package edu.wcu.jstrong1.library;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;

import java.io.*;

/**
 * Application class for the apps settings
 */
public class AppSettings extends Application {

    /** Drawable name for the app's current background theme */
    private String appColor = "blue_gradient";

    /** Name of file where settings are saved */
    String filename = "LibrarySavedSettings.txt";

    /**
     * export database file to user given location
     */
    public void exportDatabase() {
        // TODO implement
    }

    /**
     * import Database file from user given location
     */
    public void importDatabase() {
        // TODO implement
    }

    /**
     * Save settings to a file
     */
    public void saveSettings() {
        //Create a file if its not already on disk
        File file = new File(this.getFilesDir(), filename);

        //By default black text if no text can be generated from the notePad.
        String string = "";

        //Save app color to file
        string = appColor;

        FileOutputStream outputStream;//declare FOS

        try{  //to do this
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
            Log.v("settings", "Saved");
//            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
        } catch(IOException e){
            Toast.makeText(this, "Error saving file", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (Exception e) {//else if failed trying do this
            Toast.makeText(this, "Error saving file", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    /**
     * Load saved settings from a file
     */
    public void loadSettings() {
        // See http://developer.android.com/guide/topics/data/data-  storage.html#filesExternal

        //Create a file if its not already on disk
        File extDir = new File(this.getFilesDir(),  filename);

        //Read text from file
        StringBuilder text = new StringBuilder();

        //Needs lots of try and catch blocks because so much can go wron
        try{
            BufferedReader br = new BufferedReader(new FileReader(extDir));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }//end while

            br.close();//Close the buffer
        }//end try
        catch (FileNotFoundException e){//If file not found on disk here.
            Toast.makeText(this, "There was no data to load", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        catch (IOException e)//If io Exception here
        {
            Toast.makeText(this, "Error loading file", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }//end catch

        //Set the data from the file content and conver it to a String
        String data = new String(text);

        //Safety first
        if (data.length() > 0) {

            //put the loaded data into the text view
            appColor = data.replace("\n","");
            checkMode();
        }
        else
            Toast.makeText(this, "There is no data to display", Toast.LENGTH_LONG).show();
    }

    // Getters and Setters

    /**
     * Set the background theme
     * @param color String of a color [red, green, blue, orange, purple]
     */
    public void setAppGradient(String color) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        this.appColor = color + "_gradient";
        checkMode();
    }

    /**
     * Sets the app to dark mode depending on parameter
     * @param mode If dark mode should be enabled or not
     */
    public void setDarkMode(boolean mode) {
        if (mode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            this.appColor = "dark_mode";
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            this.appColor = "light_mode";
        }
    }

    /**
     * Checks if the app should be in dark mode depending on the app theme
     */
    private void checkMode() {
        if (appColor.contains("red") || appColor.contains("blue") || appColor.contains("orange") ||
                appColor.contains("purple") || appColor.contains("dark")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
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
