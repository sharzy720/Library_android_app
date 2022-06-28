# Library_android_app

An android app that allows for you to simply catalog your book collection.

## Key Features

---

* Book details are pulled from the [OpenLibrary API](https://openlibrary.org/developers/api)
* Saved books are stored in a local SQLite database
* Personal library can be searched by title, author, or isbn
* A book can be removed from the collection on its detail page
* Multithreading is used to speed up retrieving book details from the internet

## How to install

---

Navigate to the latest release and download the apk. Find where you saved the apk and tap it to begin the installation. When prompted press install and if a "play protect" warning pops up press install anyway. Once the app is installed find the app titled "Library" with a green android icon and open it to begin your book collection.

## How to use

---

#### To add a book to your collection:

[![video for adding a new book to your library](http://johnathynstrong.rf.gd/vid/library_app_add_new_book.mp4/0.jpg)](http://johnathynstrong.rf.gd/vid/library_app_add_new_book.mp4)

Press the + button in your collection to bring up the search page. Enter a book's isbn and press search to search the api for a book with that isbn. When found the cover, title, and author will be displayed on the page. If the shown data matches the book you want to add to your collection press add to library. More help can be found by pressing the ? button to bring up a help menu.


#### Search your book collection:

![video for searching your library](http://johnathynstrong.rf.gd/vid/library_app_search_library.gif)

[![video for searching your library](http://johnathynstrong.rf.gd/vid/library_app_search_library.mp4/0.jpg)](http://johnathynstrong.rf.gd/vid/library_app_search_library.mp4)

To search your book collection navigate to the collection page and in the search bar at the top enter either a books title, author, or isbn. Then select the relevant switch for your search and press search. If you want to go back to viewing all the books in your collection press view all. 

## Credits:

---

This software uses the following open source packages:

* [Glide](https://github.com/bumptech/glide)
* [Gson](https://github.com/google/gson)

## Author:

---

Johnathyn Strong - [@sharzy720](https://github.com/sharzy720) on GitHub, [@sharzy720](https://twitter.com/sharzy720) on Twitter

## License:

---

Licensed under the [MIT License](LICENSE)
