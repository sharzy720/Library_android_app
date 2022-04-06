package edu.wcu.jstrong1.library;
//https://stackoverflow.com/questions/4999064/regex-for-string-contains
// openlibrary.org
//https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java

import android.app.Application;
import android.app.ProgressDialog;
import android.util.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ParseJson extends Application {
    private String isbn;
    private String titleString;
    private String authorString;
    private String numOfPages;
    private String smallCoverURL;
    private String mediumCoverURL;
    private String largeCoverURL;
    private Boolean validBook;
    ProgressDialog pd;


    public ParseJson() {
        validBook = false;
//        this.titleString = null;
//        this.authorString = null;
//        this.numOfPages = null;
//        this.smallCoverURL = null;
//        this.mediumCoverURL = null;
//        this.largeCoverURL = null;
    }

    public void retrieveBook(String isbn) {
        String sURL = "https://openlibrary.org/api/volumes/brief/isbn/" + isbn + ".json";
        // Connect to the URL using java's native library
        URL url = null;
        URLConnection request = null;
        try {
            url = new URL(sURL);
            //        HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request = url.openConnection();
            request.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        //Convert the input stream to a json element
//        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        InputStream stream = null;
        try {
            stream = request.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonElement root = jp.parse(new InputStreamReader(stream));
        if (root.isJsonNull()) {
            System.out.println("No book with this isbn");
        } else if (root.isJsonArray()) {
            if (root.getAsJsonArray().size() == 0) {
                System.out.println("No book with this isbn");
            }
        } else {
//            getBookDetails(root);
        }
    }

    public void getBookDetails(JsonElement root) {
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
        JsonObject records = (JsonObject) rootobj.get("records"); //just grab the zipcode
        Set<Map.Entry<String, JsonElement>> entrySet = records.entrySet();

        for(Map.Entry<String, JsonElement> entry : entrySet) {
            JsonObject book = (JsonObject) records.get(entry.getKey());
            JsonObject bookData = (JsonObject) book.get("data");
            JsonArray authorsArray = (JsonArray) bookData.get("authors");
            JsonObject authorObject = (JsonObject) authorsArray.get(0);
            JsonObject coverObject = (JsonObject) bookData.get("cover");

//            String smallCoverURL = "No cover found";
//            String mediumCoverURL = "No cover found";
//            String largeCoverURL = "No cover found";

            // Getting json elements
            JsonElement author = authorObject.get("name");
            JsonElement title = bookData.get("title");
            JsonElement numberOfPages = bookData.get("number_of_pages");
            if (coverObject != null) {
                JsonElement smallCover = coverObject.get("small");
                JsonElement mediumCover = coverObject.get("medium");
                JsonElement largeCover = coverObject.get("large");

                smallCoverURL = smallCover.getAsString();
                mediumCoverURL = mediumCover.getAsString();
                largeCoverURL = largeCover.getAsString();
            }

            titleString = title.getAsString();
            authorString = author.getAsString();
            numOfPages = numberOfPages.getAsString();

            if (titleString != null || !(titleString.equals(""))) {
                validBook = true;
            }

            Log.v("strong", "Book title: " + titleString);
            Log.v("strong", "Book author: " + authorString);
            Log.v("strong", "Number of pages: " + numOfPages);
            Log.v("strong", "Large cover: " + largeCoverURL);
            Log.v("strong", "Medium cover: " + mediumCoverURL);
            Log.v("strong", "Small cover: " + smallCoverURL);
            break;
        }
    }

    public void parseFromString(String json) {
//        Gson g = new Gson();
//        JsonElement root = g.toJsonTree(json);
        JsonObject rootobj = new JsonParser().parse(json).getAsJsonObject();
//        getBookDetails(rootobj);
        Log.v("strong", "reached parseFromString");
    }

    @Override
    public String toString() {
        return "ParseJson{" +
                "isbn='" + isbn + '\'' +
                ", titleString='" + titleString + '\'' +
                ", authorString='" + authorString + '\'' +
                ", numOfPages='" + numOfPages + '\'' +
                ", smallCoverURL='" + smallCoverURL + '\'' +
                ", mediumCoverURL='" + mediumCoverURL + '\'' +
                ", largeCoverURL='" + largeCoverURL + '\'' +
                ", pd=" + pd +
                '}';
    }

    public Boolean getValidBook() {
        return validBook;
    }

    public void setValidBook(Boolean validBook) {
        this.validBook = validBook;
    }

    public String getIsbn() { return isbn; }

    public void setIsbn(String newIsbn) { isbn = newIsbn; }

    public String getTitleString() {
        return titleString;
    }

    public String getAuthorString() {
        return authorString;
    }

    public String getNumOfPages() {
        return numOfPages;
    }

    public String getSmallCoverURL() {
        return smallCoverURL;
    }

    public String getMediumCoverURL() {
        return mediumCoverURL;
    }

    public String getLargeCoverURL() {
        return largeCoverURL;
    }


////    private class JsonTask extends AsyncTask<String, String, String> {
////
//        protected void onPreExecute() {
////            super.onPreExecute();
//
//            pd = new ProgressDialog(ParseJson.this);
//            pd.setMessage("Please wait");
//            pd.setCancelable(false);
//            pd.show();
//        }
//
//        protected String doInBackground(String imageURL) {
//
//
//            HttpURLConnection connection = null;
//            BufferedReader reader = null;
//
//            try {
//                URL url = new URL(imageURL);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.connect();
//
//
//                InputStream stream = connection.getInputStream();
//
//                reader = new BufferedReader(new InputStreamReader(stream));
//
//                StringBuffer buffer = new StringBuffer();
//                String line = "";
//
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line+"\n");
//                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
//
//                }
//
//                return buffer.toString();
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (connection != null) {
//                    connection.disconnect();
//                }
//                try {
//                    if (reader != null) {
//                        reader.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
////        @Override
//        protected void onPostExecute(String result) {
////            super.onPostExecute(result);
//            if (pd.isShowing()){
//                pd.dismiss();
//            }
//            Log.v("strong", "parsed json");
//        }
////    }
}
