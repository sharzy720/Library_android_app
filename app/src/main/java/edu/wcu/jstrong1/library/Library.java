package edu.wcu.jstrong1.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Library extends AppCompatActivity {

    private EditText searchEntry;
    private Switch titleSwitch;
    private Switch authorSwitch;
    private Switch isbnSwitch;
    private Button search;
    private Button viewAll;
    private RecyclerView bookList;

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

        dataBaseHelper = new DataBaseHelper(Library.this);
        showCustomersOnListView(dataBaseHelper.getAllBooks());

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // call database search method depending on what switch is active
                String search = searchEntry.getText().toString();
                if (titleSwitch.isChecked()) {
                    Toast.makeText(Library.this, "title checked", Toast.LENGTH_SHORT).show();
                    showCustomersOnListView(dataBaseHelper.searchTitle(searchEntry.getText().toString()));
                    //database.titleSearch(search);
                } else if (authorSwitch.isChecked()) {
                    Toast.makeText(Library.this, "author checked", Toast.LENGTH_SHORT).show();
                    showCustomersOnListView(dataBaseHelper.searchAuthor(searchEntry.getText().toString()));
                    //database.authorSearch(search);
                } else if (isbnSwitch.isChecked()) {
                    Toast.makeText(Library.this, "isbn checked", Toast.LENGTH_SHORT).show();
                    showCustomersOnListView(dataBaseHelper.searchISBN(searchEntry.getText().toString()));
                    //database.isbnSearch(search);
                } else {
                    Toast.makeText(Library.this, "Select title, author, or isbn",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // grabs everyone from the database
                showCustomersOnListView(dataBaseHelper.getAllBooks());
            }
        });

//        bookList.set
//
//        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // pass clicked items details to book_details
//                BookModel clickedBook = (BookModel) parent.getItemAtPosition(position);
////                dataBaseHelper.deleteBook(clickedBook);
////                showCustomersOnListView();
////                Toast.makeText(Library.this, "Deleted " + clickedBook.getTitle(),
////                        Toast.LENGTH_SHORT).show();
//                Intent nextPage = new Intent(Library.this, BookDetails.class);
//                nextPage.putExtra("mediumCover", clickedBook.getMediumCover());
//                nextPage.putExtra("title", clickedBook.getTitle());
//                nextPage.putExtra("author", clickedBook.getAuthor());
//                nextPage.putExtra("isbn", clickedBook.getIsbn());
//                startActivity(nextPage);
//            }
//        });
    }

    private void showCustomersOnListView(List<BookModel> books) {
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
//                nextPage.putExtra("book", book);
                startActivity(nextPage);

//                dataBaseHelper.deleteBook(book);
//                showCustomersOnListView();

//                Toast.makeText(Library.this, book.toString(), Toast.LENGTH_LONG).show();
            }
        });
////        bookArrayAdapter = new ArrayAdapter<BookModel>(Library.this,
////                android.R.layout.simple_list_item_1, dataBaseHelper.getAllBooks());
//        //Init adapter and fill it
//        LibraryListAdapter bookRecyclerView = new LibraryListAdapter((ArrayList<BookModel>) dataBaseHelper.getAllBooks());
//
//
//        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
////        recyclerView.setHasFixedSize(true);
////        recyclerView.setLayoutManager(llm);
////        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        bookList.setAdapter(deviceRecyclerView);
////        bookArrayAdapter = new ArrayAdapter<BookModel>(Library.this,
////                R.layout.library_list_item, dataBaseHelper.getAllBooks());
////        bookList.setAdapter(bookArrayAdapter);
    }
}
