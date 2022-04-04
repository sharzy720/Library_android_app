package edu.wcu.jstrong1.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

// TODO maybe breakout searching database onto its own thread
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String BOOK_TABLE = "BOOK_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_BOOK_ISBN = " BOOK_ISBN";
    public static final String COLUMN_BOOK_TITLE = "BOOK_TITLE";
    public static final String COLUMN_BOOK_AUTHOR = "BOOK_AUTHOR";
    public static final String COLUMN_SMALL_COVER = "SMALL_COVER";
    public static final String COLUMN_MEDIUM_COVER = "MEDIUM_COVER";
    public static final String COLUMN_LARGE_COVER = "LARGE_COVER";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    // this is called the first time a database is accessed. There should be code in here to
    // create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {


        String createTableStatement = "CREATE TABLE " + BOOK_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BOOK_ISBN + " TEXT, " +
                COLUMN_BOOK_TITLE + " TEXT, " +
                COLUMN_BOOK_AUTHOR + " INT, " +
                COLUMN_SMALL_COVER + " BLOB, " +
                COLUMN_MEDIUM_COVER + " BLOB, " +
                COLUMN_LARGE_COVER + " BLOB)";

        db.execSQL(createTableStatement);
    }

    // this is called if the database cersion number chanes. Is prevents previoud users apps from
    // breakin when you change the databse design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     *
     * @param bookModel
     * @return
     */
    public boolean addBook(BookModel bookModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BOOK_ISBN, bookModel.getIsbn());
        cv.put(COLUMN_BOOK_TITLE, bookModel.getTitle());
        cv.put(COLUMN_BOOK_AUTHOR, bookModel.getAuthor());
        cv.put(COLUMN_SMALL_COVER, bookModel.getSmallCover());
        cv.put(COLUMN_MEDIUM_COVER, bookModel.getMediumCover());
        cv.put(COLUMN_LARGE_COVER, bookModel.getLargeCover());

        long insert = db.insert(BOOK_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param isbn
     * @return
     */
    public boolean deleteBookByIsbn(String isbn) {
        // find BookModel in the database. if is found, delete it and return true.
        // if it is not found, return false.

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString =
                "DELETE FROM " + BOOK_TABLE +
                        " WHERE " + COLUMN_BOOK_ISBN + " = '" + isbn + "'";

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param author
     * @return
     */
    public List<BookModel> searchAuthor(String author) {
        List<BookModel> returnList = new ArrayList<>();

        String queryString =
                "SELECT *" +
                        " FROM " + BOOK_TABLE +
                        " WHERE " + COLUMN_BOOK_AUTHOR + " LIKE '%" + author + "%'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int bookID = cursor.getInt(0);
                String bookISBN = cursor.getString(1);
                String bookTitle = cursor.getString(2);
                String bookAuthor = cursor.getString(3);
                byte[] smallCover = cursor.getBlob(4);
                byte[] mediumCover = cursor.getBlob(5);
                byte[] largeCover = cursor.getBlob(6);

                BookModel newBook = new BookModel(bookID, bookISBN,
                        bookTitle, bookAuthor, smallCover, mediumCover, largeCover);
                returnList.add(newBook);

            } while (cursor.moveToNext());
        } else {

        }
        // close both the cursor and the db when done.
        cursor.close();
        db.close();
        return returnList;
    }

    /**
     *
     * @param isbn
     * @return
     */
    public List<BookModel> searchISBN(String isbn) {
        List<BookModel> returnList = new ArrayList<>();

        String queryString =
                "SELECT *" +
                        " FROM " + BOOK_TABLE +
                        " WHERE " + COLUMN_BOOK_ISBN + " LIKE '%" + isbn + "%'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {

            do {
                int bookID = cursor.getInt(0);
                String bookISBN = cursor.getString(1);
                String bookTitle = cursor.getString(2);
                String bookAuthor = cursor.getString(3);
                byte[] smallCover = cursor.getBlob(4);
                byte[] mediumCover = cursor.getBlob(5);
                byte[] largeCover = cursor.getBlob(6);

                BookModel newBook = new BookModel(bookID, bookISBN,
                        bookTitle, bookAuthor, smallCover, mediumCover, largeCover);
                returnList.add(newBook);

            } while (cursor.moveToNext());
        } else {

        }
        // close both the cursor and the db when done.
        cursor.close();
        db.close();
        return returnList;
    }

    /**
     *
     * @param title
     * @return
     */
    public List<BookModel> searchTitle(String title) {
        List<BookModel> returnList = new ArrayList<>();

        String queryString =
                "SELECT *" +
                        " FROM " + BOOK_TABLE +
                        " WHERE " + COLUMN_BOOK_TITLE + " LIKE '%" + title + "%'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {

            do {
                int bookID = cursor.getInt(0);
                String bookISBN = cursor.getString(1);
                String bookTitle = cursor.getString(2);
                String bookAuthor = cursor.getString(3);
                byte[] smallCover = cursor.getBlob(4);
                byte[] mediumCover = cursor.getBlob(5);
                byte[] largeCover = cursor.getBlob(6);

                BookModel newBook = new BookModel(bookID, bookISBN,
                        bookTitle, bookAuthor, smallCover, mediumCover, largeCover);
                returnList.add(newBook);

            } while (cursor.moveToNext());
        } else {

        }
        // close both the cursor and the db when done.
        cursor.close();
        db.close();
        return returnList;
    }

    /**
     *
     * @return
     */
    public List<BookModel> getAllBooks() {
        List<BookModel> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT *" +
                                " FROM " + BOOK_TABLE;

        // getWritableDatabase locks the database while it is being written to
        // getReadableDatabase does not lock database
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop throuh the cursor (result set) and create new customer objects. Put them into
            // the return list.

            do {
                int bookID = cursor.getInt(0);
                String bookISBN = cursor.getString(1);
                String bookTitle = cursor.getString(2);
                String bookAuthor = cursor.getString(3);
                byte[] smallCover = cursor.getBlob(4);
                byte[] mediumCover = cursor.getBlob(5);
                byte[] largeCover = cursor.getBlob(6);

                BookModel newBook = new BookModel(bookID, bookISBN,
                        bookTitle, bookAuthor, smallCover, mediumCover, largeCover);
                returnList.add(newBook);

            } while (cursor.moveToNext());

        } else {
            // failure. do not add anything to the list.
        }
        // close both the cursor and the db when done.
        cursor.close();
        db.close();
        return returnList;
    }
}
