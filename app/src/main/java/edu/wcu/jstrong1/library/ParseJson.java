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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

/**
 * Class for pulling data from a json element
 */
public class ParseJson extends Application {
    /** Books isbn **/
    private String isbn;

    /** Books title **/
    private String titleString;

    /** Books title **/
    private String authorString;

    /** Books number of pages **/
    private String numOfPages;

    /** Books small sized cover url **/
    private String smallCoverURL;

    /** Books medium sized cover url **/
    private String mediumCoverURL;

    /** Books large sized cover url **/
    private String largeCoverURL;

    /** Whether a book is valid or not **/
    private Boolean validBook;

//    private ProgressDialog pd;

    /**
     * Default constructor
     */
    public ParseJson() {
        validBook = false;
//        this.titleString = null;
//        this.authorString = null;
//        this.numOfPages = null;
//        this.smallCoverURL = null;
//        this.mediumCoverURL = null;
//        this.largeCoverURL = null;
    }

    /**
     * Find a book using its isbn
     * @param isbn Book's isbn
     */
    public void retrieveBook(String isbn) {
        String sURL = "https://openlibrary.org/api/volumes/brief/isbn/" + isbn + ".json";
        // Connect to the URL using java's native library
        URL url;
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
            assert request != null;
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

    /**
     * Pulls all book details from root JsonElement
     * @param root Top JsonElement
     */
    public void getBookDetails(JsonElement root) {
        JsonObject rootobj = root.getAsJsonObject(); //Maybe an array, maybe an object.
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

            if (numberOfPages == null) {
                numOfPages = "null";
            } else {
                numOfPages = numberOfPages.getAsString();
            }

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

//    public void parseFromString(String json) {
////        Gson g = new Gson();
////        JsonElement root = g.toJsonTree(json);
//        JsonObject rootobj = new JsonParser().parse(json).getAsJsonObject();
////        getBookDetails(rootobj);
//        Log.v("strong", "reached parseFromString");
//    }

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
//                ", pd=" + pd +
                '}';
    }

    // Getters and Setters

    /**
     * Return if the book is valid or not
     * @return True if the book is valid else false
     */
    public Boolean getValidBook() {
        return validBook;
    }

    /**
     * Set whether the book is valid or not
     * @param validBook True if the book is valid else false
     */
    public void setValidBook(Boolean validBook) {
        this.validBook = validBook;
    }

    /**
     * Get a book's isbn
     * @return Book's isbn
     */
    public String getIsbn() { return isbn; }

    /**
     * Set a book's isbn
     * @param newIsbn Book's new isbn
     */
    public void setIsbn(String newIsbn) { isbn = newIsbn; }

    /**
     * Get a book's title
     * @return Book's title
     */
    public String getTitleString() {
        return titleString;
    }

    /**
     * Get a book's author
     * @return Book's author
     */
    public String getAuthorString() {
        return authorString;
    }

    /**
     * Get a book's number of pages
     * @return
     */
    public String getNumOfPages() {
        return numOfPages;
    }

    /**
     * Get url for the small version of a book's cover image
     * @return Url for small version of a book's cover
     */
    public String getSmallCoverURL() {
        return smallCoverURL;
    }

    /**
     * Get url for the medium version of a book's cover image
     * @return Url for medium version of a book's cover
     */
    public String getMediumCoverURL() {
        return mediumCoverURL;
    }

    /**
     * Get url for the large version of a book's cover image
     * @return Url for large version of a book's cover
     */
    public String getLargeCoverURL() {
        return largeCoverURL;
    }
}
