package edu.wcu.jstrong1.library;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

/**
 * Class for add_book.xml
 */
public class AddBook extends AppCompatActivity {

    private EditText isbnEntry;
    private Button search;
    private Button addToLibrary;

    private FloatingActionButton helpBut;

    private ImageView bookCover;
    private TextView bookTitle;
    private TextView bookAuthor;

    private ParseJson pj;
    private CoverHelper ch;

    private ProgressDialog pd;

    private RelativeLayout ab_main_layout;

    /** App's application class */
    private AppSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book);

        isbnEntry = findViewById(R.id.ab_isbn_entry);
        search = findViewById(R.id.ab_search_but);
        addToLibrary = findViewById(R.id.ab_add_but);
        helpBut = findViewById(R.id.ab_help_but);
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

        // On click for search button, searches API for book with matching isbn
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(AddBook.this, "Search clicked", Toast.LENGTH_SHORT).show();
                String isbn = isbnEntry.getText().toString();
                if (isbn.length() == 9 || isbn.length() == 10 || isbn.length() == 13) {
                    if (isbn.length() == 9) {
                        isbn = isbn + "X";
                    }
//                String isbn = "9781501120602";
                    pj = getJson(isbn);
                    pj.setIsbn(isbn);
                    setBookCover(AddBook.this.pj.getMediumCoverURL(), bookCover);
                    bookTitle.setText("Title: " + AddBook.this.pj.getTitleString());
                    bookAuthor.setText("Author: " + AddBook.this.pj.getAuthorString());
                    // search api for book with matching isbn
                } else {
                    isbnEntry.setError("Enter valid isbn-10 or isbn-13");
                }
            }
        });

        // On click for add to library button, adds found book to database
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
//                        isbn = "9781501120602"; // test isbn
                        pj = getJson(isbn);
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

        // On click for help button
        helpBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(AddBook.this);
                builder.setMessage("To Search for a book enter its isbn.\n If the isbn ends with an X leave it off.")
                       .setPositiveButton("Ok", dialogClickListener)
//                       .setNegativeButton("No", dialogClickListener)
                       .show();
            }
        });
    }

    /**
     * Retrieves json file for book with given isbn
     * @param isbn Book's isbn
     * @return ParseJson holding all the books information
     */
    private ParseJson getJson(String isbn) {
        ParseJson parseJson = new ParseJson();
        try {
//            test = new JsonTask().execute("https://openlibrary.org/api/volumes/brief/isbn/" + isbn + ".json").get();
            JsonTaskThread jsonTaskThread = new JsonTaskThread();
            jsonTaskThread.setJsonUrl("https://openlibrary.org/api/volumes/brief/isbn/" + isbn + ".json");
            Thread thread = new Thread(jsonTaskThread);
            thread.start();
            thread.join();
            parseJson = jsonTaskThread.getPj();
        } catch (InterruptedException e) { // ExecutionException |
            e.printStackTrace();
        }
        return parseJson;
    }

    /**
     * Set book cover to an image url using glide
     * @param imageAddress url to image
     * @param coverLocation imageView to set image to
     */
    private void setBookCover(String imageAddress, ImageView coverLocation) {
        Glide.with(this).load(imageAddress).into(coverLocation);
    }
}
