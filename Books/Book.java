package Books;

import Users.User;

public abstract class Book implements Lendable {

    private static final long serialVersionUID = 1L;
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
        isAvailable = true;
    }

    public String getFormattedRow(int index) {
        String type = this instanceof TextBook ? "TEXTBOOK" : "NOVELBOOK";
        String genreOrSubject = this instanceof TextBook ?
                ((TextBook) this).subject :
                ((NovelBook) this).genre;

        return String.format("| %-2d | %-9s | %-6s | %-40s | %-18s | %-18s |",
                index, type, getIsbn(), getTitle(), getAuthor(), genreOrSubject);
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
