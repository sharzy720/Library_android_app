package edu.wcu.jstrong1.library;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class BookDetails extends AppCompatActivity {

    private ImageView cover;
    private TextView title;
    private TextView author;
    private TextView isbn;
    private Button deleteBook;

    private CoverHelper coverHelper;

    private RelativeLayout bd_main_layout;
    private AppSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details);

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
                DataBaseHelper dataBaseHelper = new DataBaseHelper(BookDetails.this);
                dataBaseHelper.deleteBookByIsbn(extras.getString("isbn"));
                Toast.makeText(BookDetails.this, "Book deleted!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
