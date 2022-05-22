package edu.wcu.jstrong1.library;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Class for book_details.xml
 */
public class BookDetails extends AppCompatActivity {

    /** ImageView for displaying a books cover **/
    private ImageView cover;

    /** TextView for displaying a books title **/
    private TextView title;

    /** TextView for displaying a books author **/
    private TextView author;

    /** TextView for displaying a books isbn **/
    private TextView isbn;

    /** Button to delete the displayed book **/
    private Button deleteBook;

    /** CoverHelper for getting an images' bitmap or byte array **/
    private CoverHelper coverHelper;

    /** Background layout of activity **/
    private RelativeLayout bd_main_layout;

    /** Application class for apps settings */
    private AppSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details);

        // Setting all views using their ids
        cover = findViewById(R.id.bd_cover);
        title = findViewById(R.id.bd_title);
        author = findViewById(R.id.bd_author);
        isbn = findViewById(R.id.bd_isbn);
        deleteBook = findViewById(R.id.bd_delete_but);
        bd_main_layout = findViewById(R.id.bd_main_layout);

        // Setting the background
        settings = (AppSettings) getApplication();
        String background = settings.getAppColor();
        int id = getResources().getIdentifier(background, "drawable", this.getPackageName());
        bd_main_layout.setBackgroundResource(id);

        coverHelper = new CoverHelper();

        Bundle extras = getIntent().getExtras();

        Bitmap imageBitmap = coverHelper.getImageBitmap(extras.getByteArray("mediumCover"));
        if (imageBitmap != null) {
            cover.setImageBitmap(imageBitmap);
        } else {
            cover.setImageResource(R.drawable.missing_book_cover);
        }
        title.setText("Title: " + extras.getString("title"));
        author.setText("Author: " + extras.getString("author"));
        isbn.setText("ISBN: " + extras.getString("isbn"));

        deleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteBookWarning(extras.getString("isbn"));
            }
        });

    }

    /**
     * Displays a warning when the user presses the delete button
     * @param isbn Isbn of book to be removed from the database
     */
    private void deleteBookWarning(String isbn) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(BookDetails.this);
                        dataBaseHelper.deleteBookByIsbn(isbn);
                        Toast.makeText(BookDetails.this, "Book deleted!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BookDetails.this, Library.class));
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}
