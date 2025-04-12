package Books;

public class TextBook extends Book {

    private String subject;

    public TextBook(String isbn, String title, String author, String subject) {
        super(isbn, title, author);
        this.subject = subject;
    }

    public void displayBookDetails() {
        System.out.println(" TEXTBOOK " + " ISBN : " + getIsbn() + " TITLE : " + getTitle() + " AUTHOR : " + getAuthor() + " SUBJECT : " + subject);
    }
}
