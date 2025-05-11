package Books;

public class TextBook extends Book {

    private static int uniqueBookId;
    private final int bookId;
    private String subject;
    private String bookType;

    public TextBook(String isbn, String title, String author, String subject, String bookType) {
        super(isbn, title, author);
        this.bookId = getUniqueBookId();
        this.subject = subject;
        this.bookType = bookType;
    }

    private final int getUniqueBookId() {
        uniqueBookId++;
        return uniqueBookId;
    }

    public void displayBookDetails() {
        System.out.println(" TEXTBOOK " + " ISBN : " + getIsbn() + " TITLE : " + getTitle() + " AUTHOR : " + getAuthor() + " SUBJECT : " + subject);
    }

    public String getBookType(TextBook t) {
        return t.bookType;
    }
}
