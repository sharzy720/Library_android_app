package edu.wcu.jstrong1.library;

/**
 * Model for a book
 */
public class BookModel {

    /** Books id in the database */
    private int id;

    /** Books isbn */
    private String isbn;

    /** Books title */
    private String title;

    /** Books author */
    private String author;

    /** Byte array for small version of books cover */
    private byte[] smallCover;

    /** Byte array for medium version of books cover */
    private byte[] mediumCover;

    /** Byte array for large version of books cover */
    private byte[] largeCover;

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

    /**
     * Gets a books id
     * @return Books id number
     */
    public int getId() {
        return id;
    }

    /**
     * Set a books id
     * @param id Books new id number
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get a books isbn
     * @return Books isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Set a books isbn
     * @param isbn Books new isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Get a books title
     * @return Books title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set a books title
     * @param title Books new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get a books author
     * @return Books author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set a books author
     * @param author Books author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets a books small sized cover
     * @return Books small sized cover
     */
    public byte[] getSmallCover() {
        return smallCover;
    }

    /**
     * Set a books small sized cover
     * @param smallCover Books new small sized cover
     */
    public void setSmallCover(byte[] smallCover) {
        this.smallCover = smallCover;
    }

    /**
     * Get a books medium-sized cover
     * @return Books medium-sized cover
     */
    public byte[] getMediumCover() {
        return mediumCover;
    }

    /**
     * Set a books medium-sized cover
     * @param mediumCover Books new medium-sized cover
     */
    public void setMediumCover(byte[] mediumCover) {
        this.mediumCover = mediumCover;
    }

    /**
     * Gets a books large sized cover
     * @return Books large sized cover
     */
    public byte[] getLargeCover() {
        return largeCover;
    }

    /**
     * Set a books large sized cover
     * @param largeCover Books new large sized cover
     */
    public void setLargeCover(byte[] largeCover) {
        this.largeCover = largeCover;
    }
}
