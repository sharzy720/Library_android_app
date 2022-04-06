package edu.wcu.jstrong1.library;

/**
 * Model for a book
 */
public class BookModel {

    private int id;             // A book's id given by the database
    private String isbn;        // Isbn of a book
    private String title;       // Title of a book
    private String author;      // Author of a book
    private byte[] smallCover;  // Byte array for a book's small cover
    private byte[] mediumCover; // Byte array for a book's medium cover
    private byte[] largeCover;  // Byte array for a book's large cover

    /**
     * Default constructor
     */
    public BookModel() {
        id = -1;
        isbn = "error";
        title = null;
        author = null;
        smallCover = null;
        mediumCover = null;
        largeCover = null;
    }

    /**
     * Constructor for creating a book with values
     * @param id The books' database id
     * @param isbn The books isbn
     * @param title The books title
     * @param author The books author
     * @param smallCoverURL Url for the books small cover
     * @param mediumCoverURL Url for the books medium cover
     * @param largeCoverURL Url for the books large cover
     */
    public BookModel(int id, String isbn, String title, String author, byte[] smallCoverURL, byte[] mediumCoverURL, byte[] largeCoverURL) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.smallCover = smallCoverURL;
        this.mediumCover = mediumCoverURL;
        this.largeCover = largeCoverURL;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", smallCoverURL='" + smallCover + '\'' +
                ", mediumCoverURL='" + mediumCover + '\'' +
                ", largeCoverURL='" + largeCover + '\'' +
                '}';
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getSmallCover() {
        return smallCover;
    }

    public void setSmallCover(byte[] smallCover) {
        this.smallCover = smallCover;
    }

    public byte[] getMediumCover() {
        return mediumCover;
    }

    public void setMediumCover(byte[] mediumCover) {
        this.mediumCover = mediumCover;
    }

    public byte[] getLargeCover() {
        return largeCover;
    }

    public void setLargeCover(byte[] largeCover) {
        this.largeCover = largeCover;
    }
}
