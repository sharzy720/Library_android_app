package edu.wcu.jstrong1.library;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Class for activity_main.xml
 */
public class MainActivity extends AppCompatActivity {

    private Button mm_view_books;
    private Button mm_add_book;
    private Button mm_settings;

    private RelativeLayout mm_main_layout;

    /** App's application class */
    private AppSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mm_view_books = findViewById(R.id.mm_view_books_but);
        mm_add_book = findViewById(R.id.mm_add_book_but);
        mm_settings = findViewById(R.id.mm_settings_but);
        mm_main_layout = findViewById(R.id.mm_main_layout);

        // Setting the background
        settings = (AppSettings) getApplication();
        String background = settings.getAppColor();
        int id = getResources().getIdentifier(background, "drawable", this.getPackageName());
        mm_main_layout.setBackgroundResource(id);

        // On click for View Books button
        mm_view_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPage = new Intent(MainActivity.this, Library.class);
                startActivity(nextPage);
            }
        });

        // On click for Add Book button
        mm_add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPage = new Intent(MainActivity.this, AddBook.class);
                startActivity(nextPage);
            }
        });

        // On click for Settings button
        mm_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPage = new Intent(MainActivity.this, SettingsPage.class);
                startActivity(nextPage);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Setting the background
        String background = settings.getAppColor();
        int id = getResources().getIdentifier(background, "drawable", this.getPackageName());
        mm_main_layout.setBackgroundResource(id);
    }
}