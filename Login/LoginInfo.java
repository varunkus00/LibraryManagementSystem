package Login;

import Users.Librarian;
import Users.Member;
import Users.StorageUtil;
import Users.User;
import java.util.List;

public class LoginInfo {

    private String username;
    private String password;
    private String role;
    private String contactInfo;

    public LoginInfo() {
        this(null, null, null, null);
    }

    public LoginInfo(String username, String password, String role, String contactInfo) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.contactInfo = contactInfo;
    }

    public boolean saveUserCred(LoginInfo userDetails) {

        List<User> listOfUsers = StorageUtil.loadUsers();
        for( User user : listOfUsers ) {
            if( user.getUserName().equalsIgnoreCase(userDetails.username) ) {
                System.out.println(" UserName Already Exists, Please provide a different username to create the account ");
                return false;
            }
        }
        if( "Librarian".equalsIgnoreCase(userDetails.role) ) {
            listOfUsers.add(new Librarian(userDetails.username, userDetails.contactInfo, userDetails.password));
        } else {
            listOfUsers.add(new Member(userDetails.username, userDetails.contactInfo, userDetails.password));
        }

        StorageUtil.saveUsers(listOfUsers);
        return true;

    }

    public User validateAndReturnUserInfo(String username, String password) {
        List<User> listOfUsers = StorageUtil.loadUsers();
        for( User user : listOfUsers ) {
            if( user.getUserName().equalsIgnoreCase(username) ) {
                if( user.getPassword().equalsIgnoreCase(password)) {
                    return user;
                }
            }
        }
        System.out.println(" Invalid Login Credentials! ");
        return null;
    }

}
