package Users;

public abstract class User {

    private final String userId;
    private String name;
    private String contactInfo;
    private static int uniqueUserId = 0;

    public User() {
        this(null, null);
    }

    public User(String name, String contactInfo) {
        this.userId = String.valueOf(generateUniqueId());
        this.name = name;
        this.contactInfo = contactInfo;
    }

    //TO-DO : Implement Copy Constructor
    public User(User u) {
        this.userId = String.valueOf(generateUniqueId());
        this.name = u.name;
        this.contactInfo = u.contactInfo;
    }

    private final int generateUniqueId() {
        uniqueUserId++;
        return uniqueUserId;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String name) {
        this.name =  name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getTotalUsers() {
        return uniqueUserId;
    }

    public abstract void displayDashboard();
    public abstract boolean canBorrowBooks();
    public abstract void incrementBorrowedBooksCount();
    public abstract void decrementBorrowedBooksCount();
}
