
import Login.LoginPrompt;
import Users.StorageUtil;
import Users.User;

public class Main {

    public static void main(String[] args) {

        System.out.println("Library Management System Project Starting...");
        StorageUtil.initializeListOfBooksIfNeeded();
        LoginPrompt lPrompt = new LoginPrompt();
        User user = lPrompt.initiateLogin();

        LMS lms = new LMS();
        try {
            lms.registerUser(user);
        } catch(NullPointerException e) {
            System.out.println("Please use the login prompt to login with your username and password");
            return;
        }

        if( user != null) {
            //lms.displayUserDetails(user);
            user.userPage();
        } else {
            System.out.println(" No such user exists, Use 2nd Option to Create User First. ");
        }
    }

}
