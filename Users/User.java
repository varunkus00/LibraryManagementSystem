package Users;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public abstract class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String userId;
    private String name;
    private String contactInfo;
    private final String password;
    private static int uniqueUserId = 0;

    public User() {
        this(null, null, null);
    }

    public User(String name, String contactInfo, String password) {
        this.userId = String.valueOf(generateUniqueId());
        this.name = name;
        this.contactInfo = contactInfo;
        this.password = password;
    }

    //TO-DO : Implement Copy Constructor
    private User(User u) {
        this.userId = String.valueOf(generateUniqueId());
        this.name = u.name;
        this.contactInfo = u.contactInfo;
        this.password = u.password;
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

    public String getPassword() {
        return password;
    }

    public int getTotalUsers() {
        return uniqueUserId;
    }

    public void displayUserMenu() {
        System.out.println("======Welcome to Library Management System======");
        System.out.println("1. Get List Of Available Books ");
        System.out.println("2. Get List Of Not Available Books ");
        System.out.println("3. Get Free Books ");
        System.out.println("4. Search By Book Type ");
        System.out.println("5. Lend A Book ");
        System.out.println("6. Get My Books Count ");
        System.out.println("7. Get My Account Details ");
        System.out.println("8. Return Book ");
        System.out.println("0. LogOut ");
        System.out.println("================================================");
    }

    public void updateUserState(User user) {
        List<User> users = StorageUtil.loadUsers();
        for( int i = 0; i < users.size(); i++ ) {
            if( users.get(i).getUserName().equalsIgnoreCase(user.getUserName())) {
                users.set(i, user);
                break;
            }
        }
        StorageUtil.saveUsers(users);
    }

    public abstract void displayDashboard();
    public abstract void userPage();
    public abstract boolean canBorrowBooks();
    public abstract void incrementBorrowedBooksCount();
    public abstract void decrementBorrowedBooksCount();
}
