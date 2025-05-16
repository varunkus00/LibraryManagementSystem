package Users;

import Books.Book;
import Books.NovelBook;
import Books.TextBook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StorageUtil {

    public static void initializeListOfBooksIfNeeded() {
        File file = new File("books.dat");
        if(!file.exists()) {
                List<Book> initialBookList = new ArrayList<>();
                initialBookList.add(new TextBook("1234", "DSA", "Karumanchi", "CS", "TextBook"));
                initialBookList.add(new NovelBook("1231", "The Art of Doing Things", "Michael Misht", "Philosphy"));
                StorageUtil.saveBooks(initialBookList);
        }
    }

    public static void saveUsers(List<User> listOfUsers ) {
        try(ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            ois.writeObject(listOfUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> loadUsers() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"))) {
            return (List<User>)ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void saveBooks(List<Book> listOfBooks ) {
        try(ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("books.dat"))) {
            ois.writeObject(listOfBooks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> loadBooks() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("books.dat"))) {
            return (List<Book>)ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
