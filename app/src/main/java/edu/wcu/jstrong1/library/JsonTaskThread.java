package edu.wcu.jstrong1.library;

import android.util.Log;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonTaskThread implements Runnable{

    /** Address for a books Json **/
    private String jsonUrl;

    /** Holds all found data about given book **/
    private ParseJson pj;

    // TODO look into adding a progress dialog
//  Start progress dialog
//            pd = new ProgressDialog(AddBook.this);
//            pd.setMessage("Please wait");
//            pd.setCancelable(false);
//            pd.show();
//  End progress dialog
//            if (pd.isShowing()){
//                pd.dismiss();
//            }

    @Override
    public void run() {
        Log.v("strong", "Searching api");

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        pj = new ParseJson();

        try {
            URL url = new URL(jsonUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader(stream)); //Convert the input
            // stream to a json element
            if (root.isJsonNull()) {
                System.out.println("No book with this isbn");
            } else if (root.isJsonArray()) {
                if (root.getAsJsonArray().size() == 0) {
                    System.out.println("No book with this isbn");
                }
            } else {
                pj.getBookDetails(root);
                Log.v("strong", "test's value in JsonTask: " + pj.toString());

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        setBookCover(AddBook.this.pj.getMediumCoverURL(), bookCover);
//                        bookTitle.setText("Title: " + AddBook.this.pj.getTitleString());
//                        bookAuthor.setText("Author: " + AddBook.this.pj.getAuthorString());
//                    }
//                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set url for json file
     * @param url Json url
     */
    public void setJsonUrl(String url) {this.jsonUrl = url;}

    /**
     * Gets ParseJson created from url
     * @return ParseJson of book details
     */
    public ParseJson getPj() {return this.pj;}
}
