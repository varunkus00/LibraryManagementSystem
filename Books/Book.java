package Books;

import Users.User;

public abstract class Book implements Lendable {

    private String isbn;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public boolean lend(User user){
        if( isAvailable() && user.canBorrowBooks() ) {
            isAvailable = false;
            user.incrementBorrowedBooksCount();
            return true;
        }
        return false;
    }
    public void returnBook(User user) {
        user.decrementBorrowedBooksCount();
        isAvailable = false;
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    public abstract void displayBookDetails();

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
