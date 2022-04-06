package edu.wcu.jstrong1.library;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class AddBook extends AppCompatActivity {

    private EditText isbnEntry;
    private Button search;
    private Button addToLibrary;
    private ImageView bookCover;
    private TextView bookTitle;
    private TextView bookAuthor;

    private ParseJson pj;
    private CoverHelper ch;

    private ProgressDialog pd;

    private RelativeLayout ab_main_layout;
    private AppSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book);

        isbnEntry = findViewById(R.id.ab_isbn_entry);
        search = findViewById(R.id.ab_search_but);
        addToLibrary = findViewById(R.id.ab_add_but);
        bookCover = findViewById(R.id.ab_book_cover);
        bookTitle = findViewById(R.id.ab_book_title);
        bookAuthor = findViewById(R.id.ab_book_author);

        ab_main_layout = findViewById(R.id.ab_main_layout);

        // Setting the background
        settings = (AppSettings) getApplication();
        String background = settings.getAppColor();
        int id = getResources().getIdentifier(background, "drawable", this.getPackageName());
        ab_main_layout.setBackgroundResource(id);

        pj = new ParseJson();
        pj.setIsbn("");
        ch = new CoverHelper();

        // Searching api for book with matching isbn number
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(AddBook.this, "Search clicked", Toast.LENGTH_SHORT).show();
                String isbn = isbnEntry.getText().toString();
                // test isbn
//                String isbn = "9781501120602";
                pj.setIsbn(isbn);
                try {
                    new JsonTask().execute("https://openlibrary.org/api/volumes/brief/isbn/" + isbn + ".json").get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                // search api for book with matching isbn
            }
        });

        // Adds found book to database
        addToLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(AddBook.this, "Add clicked", Toast.LENGTH_SHORT).show();
                String isbn = null;
//                if (isbnEntry.getText().toString().matches("")) {
                if (!pj.getValidBook()) {
                    Toast.makeText(AddBook.this, "Search for book first", Toast.LENGTH_SHORT).show();
                } else {
                    if (pj.getTitleString() == null) {
//                        Toast.makeText(AddBook.this, "No previous search", Toast.LENGTH_SHORT).show();
                        isbn = isbnEntry.getText().toString();
                        // test isbn
//                        isbn = "9781501120602";
                        String test;
                        try {
                            test = new JsonTask().execute("https://openlibrary.org/api/volumes/brief/isbn/" + isbn + ".json").get();
                        } catch (ExecutionException | InterruptedException e) {
                            e.printStackTrace();
                        }
                        pj.setIsbn(isbn);
                    }
                    // Add book to database and display toast

                    BookModel bookModel;
                    try {
                        byte[] testImageBytes = ch.getImageFromUrl(pj.getSmallCoverURL());
                        bookModel = new BookModel(-1, pj.getIsbn(), pj.getTitleString(),
                                pj.getAuthorString(),
                                ch.getImageFromUrl(pj.getSmallCoverURL()),
                                ch.getImageFromUrl(pj.getMediumCoverURL()),
                                ch.getImageFromUrl(pj.getLargeCoverURL()));

//                        Toast.makeText(AddBook.this, bookModel.toString(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddBook.this, "Error creating book",
                                Toast.LENGTH_SHORT).show();
                        bookModel = new BookModel();
                    }
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(AddBook.this);

                    boolean success = dataBaseHelper.addBook(bookModel);

                    if (success) {
                        Toast.makeText(AddBook.this, "Book Added to library", Toast.LENGTH_SHORT).show();
                    }

//                showCustomersOnListView();
                    Log.v("strong", "pj value in AddBook: " + pj.toString());
                }
            }
        });
    }

    private void setBookCover(String imageAddress, ImageView coverLocation) {
        Glide.with(this).load(imageAddress).into(coverLocation);
    }

    // TODO breakout to Thread class
    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(AddBook.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {
            Log.v("strong", "Searching api");

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
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

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setBookCover(AddBook.this.pj.getMediumCoverURL(), bookCover);
                            bookTitle.setText("Title: " + AddBook.this.pj.getTitleString());
                            bookAuthor.setText("Author: " + AddBook.this.pj.getAuthorString());
                        }
                    });
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
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }
            Log.v("strong", "parsed json");
        }
    }
}
