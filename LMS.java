import Books.Book;
import Users.User;

import java.util.ArrayList;
import java.util.List;

public class LMS {

    List<Book> bookInventory = new ArrayList<>();
    List<User> registeredUsers = new ArrayList<>();

    public void addBook(Book book) {
        bookInventory.add(book);
    }

    public void registerUser(User user) {
        registeredUsers.add(user);
    }

    public User getUsersByName(String name) {
        for(User user : registeredUsers ) {
            if( user.getUserName().equals(name) ) {
                return user;
            }
        }

        return null;
    }

    public void displayUserDetails(User user) {
        user.displayDashboard();
    }

    public void displayBookDetails(Book book) {
        book.displayBookDetails();
    }

}
