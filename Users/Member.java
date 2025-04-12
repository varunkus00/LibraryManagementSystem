package Users;

public class Member extends User {

    private int borrowedBooksCount;
    private final int MAX_borrow_LIMIT = 5;

    public Member(String username, String contactInfo) {
        super(username, contactInfo);
        borrowedBooksCount = 0;
    }

    public void displayDashboard() {
        System.out.println(" Username : " + getUserName() + " Contact Info : " + getContactInfo() + " Books Borrowed : " + borrowedBooksCount);
    }
    public boolean canBorrowBooks() {
        return borrowedBooksCount < MAX_borrow_LIMIT;
    }

    public void incrementBorrowedBooksCount() {
        borrowedBooksCount++;
    }

    public void decrementBorrowedBooksCount() {
        borrowedBooksCount--;
    }

}
