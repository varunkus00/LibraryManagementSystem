package Books;

public class NovelBook extends Book {

    private String genre;

    public NovelBook(String isbn, String title, String author, String genre) {
        super(isbn, title, author);
        this.genre = genre;
    }

    public void displayBookDetails() {
        System.out.println(" Novel " + getIsbn() + " " + getTitle() + " " + getAuthor() + " " + genre);
    }

}
