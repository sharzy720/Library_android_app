# Library_android_app

this is a test

An android app that allows for you to simply catalog your book collection.

## Key Features

---

* Book details are pulled from the [OpenLibrary API](https://openlibrary.org/developers/api)
* Saved books are stored in a local SQLite database
* Personal library can be searched by title, author, or isbn
* A book can be removed from the collection on its detail page
* Multithreading is used to speed up retrieving book details from the internet

## How to use

---

#### To add a book to the library:

Press the 'Add Book' button. Entering the books isbn and pressing search will search the 
OpenLibrary api for details about the given book. To add the book to the library simple press 
the 'Add to library' button.

#### Search book collection:

Once you are on the collection page searching is easy. Simple enter the title, author, or isbn 
you want to search for. Then select the respective switch followed by the search button. If you 
want to return to viewing all the books in the collection simply press the 'View all' button.

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
