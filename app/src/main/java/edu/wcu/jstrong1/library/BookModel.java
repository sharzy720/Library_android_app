package edu.wcu.jstrong1.library;

public class BookModel {

    private int id;
    private String isbn;
    private String title;
    private String author;
    private byte[] smallCover;
    private byte[] mediumCover;
    private byte[] largeCover;


    public BookModel() {
        id = -1;
        isbn = "error";
        title = null;
        author = null;
        smallCover = null;
        mediumCover = null;
        largeCover = null;
    }

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
