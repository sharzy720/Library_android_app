package edu.wcu.jstrong1.library;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import java.io.*;

/**
 * Application class for the apps settings
 */
public class AppSettings extends Application {

    /** Drawable name for the app's current background theme */
    private String appColor = "blue_gradient";

    String filename = "LibrarySavedSettings.txt";


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
            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
        }
        catch(FileNotFoundException e){
            Toast.makeText(this, "Error saving file", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        catch(IOException e){
            Toast.makeText(this, "Error saving file", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        catch (Exception e) {//else if failed trying do this
            Toast.makeText(this, "Error saving file", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

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
        }
        else
            Toast.makeText(this, "There is no data to display", Toast.LENGTH_LONG).show();
    }

    // Getters and Setters

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
