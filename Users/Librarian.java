package Users;

import Books.Book;

public class Librarian extends User {

    private String employeeNumber;
    private static int uniqueEmployeeId = 0;

    public Librarian(String username, String contactInfo) {
        super(username, contactInfo);
        uniqueEmployeeId++;
        this.employeeNumber = String.valueOf(uniqueEmployeeId);
    }
    public void displayDashboard() {
        System.out.println(getUserName() + getContactInfo() + employeeNumber);
    }
    public boolean canBorrowBooks() {
        return true;
    }

    public void incrementBorrowedBooksCount() {};
    public void decrementBorrowedBooksCount() {};

    public void addNewBook(Book book) {

    }

    public void removeBook(Book book) {

    }

}
