package edu.wcu.jstrong1.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Class for library.xml
 */
public class Library extends AppCompatActivity {

    private EditText searchEntry;
    private SwitchCompat titleSwitch;
    private SwitchCompat authorSwitch;
    private SwitchCompat isbnSwitch;
    private Button search;
    private Button viewAll;
    private RecyclerView bookList;
    private LinearLayout l_main_layout;

    /** App's application class */
    private AppSettings settings;

    ArrayAdapter<BookModel> bookArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);

        searchEntry = findViewById(R.id.l_search_entry);
        titleSwitch = findViewById(R.id.l_title_switch);
        authorSwitch = findViewById(R.id.l_author_switch);
        isbnSwitch = findViewById(R.id.l_isbn_switch);
        search = findViewById(R.id.l_search_but);
        viewAll = findViewById(R.id.l_view_all_but);
        bookList = findViewById(R.id.l_book_list);
        l_main_layout = findViewById(R.id.l_main_layout);

        // Setting the background
        settings = (AppSettings) getApplication();
        String background = settings.getAppColor();
        int id = getResources().getIdentifier(background, "drawable", this.getPackageName());
        l_main_layout.setBackgroundResource(id);

        dataBaseHelper = new DataBaseHelper(Library.this);
        showBooksOnRecyclerView(dataBaseHelper.getAllBooks());

        // On click for search switches
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // call database search method depending on what switch is active
                String search = searchEntry.getText().toString();
                if (titleSwitch.isChecked()) {
//                    Toast.makeText(Library.this, "title checked", Toast.LENGTH_SHORT).show();
                    showBooksOnRecyclerView(dataBaseHelper.searchTitle(searchEntry.getText().toString()));
                }
                else if (authorSwitch.isChecked()) {
//                    Toast.makeText(Library.this, "author checked", Toast.LENGTH_SHORT).show();
                    showBooksOnRecyclerView(dataBaseHelper.searchAuthor(searchEntry.getText().toString()));
                }
                else if (isbnSwitch.isChecked()) {
//                    Toast.makeText(Library.this, "isbn checked", Toast.LENGTH_SHORT).show();
                    showBooksOnRecyclerView(dataBaseHelper.searchISBN(searchEntry.getText().toString()));
                }
                else {
                    Toast.makeText(Library.this, "Select title, author, or isbn", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // On click for view all button
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // grabs everyone from the database
                showBooksOnRecyclerView(dataBaseHelper.getAllBooks());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showBooksOnRecyclerView(dataBaseHelper.getAllBooks());
    }

    /**
     * Display all books in given list in recyclerView
     * @param books List of books to display
     */
    private void showBooksOnRecyclerView(List<BookModel> books) {
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this);

        LibraryListAdapter libraryListAdapter = new LibraryListAdapter(books);

        bookList.setAdapter(libraryListAdapter);

        bookList.setLayoutManager(layout);

        libraryListAdapter.setOnItemClickedObserver( new LibraryListAdapter.ItemWasClicked() {

            @Override
            public void itemWasClicked(BookModel book) {
                Intent nextPage = new Intent(Library.this, BookDetails.class);
                nextPage.putExtra("mediumCover", book.getMediumCover());
                nextPage.putExtra("title", book.getTitle());
                nextPage.putExtra("author", book.getAuthor());
                nextPage.putExtra("isbn", book.getIsbn());
                startActivity(nextPage);

//                Toast.makeText(Library.this, book.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
