package edu.wcu.jstrong1.library;

import android.util.Log;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonTaskThread implements Runnable{

    /** Address for a books Json **/
    private String jsonUrl;

    private ParseJson pj;

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
        } catch (MalformedURLException e) {
            e.printStackTrace();
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
     *
     * @param url
     */
    public void setJsonUrl(String url) {this.jsonUrl = url;}


    public ParseJson getPj() {return this.pj;}
}
