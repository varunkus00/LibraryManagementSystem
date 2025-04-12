import Books.NovelBook;
import Books.TextBook;
import Users.Member;
import Users.User;

public class Main {

    public static void main(String[] args) {
        System.out.println("This is Library Management System Project");

        LMS lms = new LMS();

        Member m1 = new Member("Varun", "919191");
        Member m2 = new Member("Priya", "176541");
        lms.registerUser(m1);
        lms.registerUser(m2);

        TextBook tBook = new TextBook("1234", "DSA", "Karumanchi", "CS");
        NovelBook nBook = new NovelBook("1231", "The Art of Doing Things", "Michael Misht", "Philosphy");
        lms.addBook( tBook );
        lms.addBook( nBook );

        lms.displayUserDetails(m1);
        lms.displayBookDetails(tBook);
    }

}
