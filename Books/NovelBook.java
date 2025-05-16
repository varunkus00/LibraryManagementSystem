package Books;

public class NovelBook extends Book {

    private static final long serialVersionUID = 1L;
    protected String genre;

    public NovelBook(String isbn, String title, String author, String genre) {
        super(isbn, title, author);
        this.genre = genre;
    }

    public void displayBookDetails() {
        System.out.println(" NOVELBOOK=>" + " ISBN : " + getIsbn() + " TITLE : " + getTitle() + " AUTHOR : " + getAuthor() + " GENRE : " + genre);
    }

}
