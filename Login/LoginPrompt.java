package Login;

import Users.User;

import java.util.Scanner;

public class LoginPrompt {

    public LoginPrompt(){

    }

    public User initiateLogin() {
        System.out.println(" Enter 1 For Login or 2 for Creating New Account ");

        Scanner userScan = new Scanner(System.in);
        int userInput = userScan.nextInt();
        if( userInput == 1 ) {
            System.out.println("Enter UserName");
            Scanner readUsername = new Scanner(System.in);
            String enteredUsername = readUsername.next();

            System.out.println("Enter Password");
            Scanner readPass = new Scanner(System.in);
            String enteredPassword = readPass.next();

            LoginInfo lInfo = new LoginInfo();
            return lInfo.validateAndReturnUserInfo(enteredUsername, enteredPassword);
        } else if( userInput == 2) {
            System.out.println("Enter UserName");
            Scanner readUsername = new Scanner(System.in);
            String enteredUsername = readUsername.next();

            System.out.println("Enter Password");
            Scanner readPass = new Scanner(System.in);
            String enteredPassword = readPass.next();

            System.out.println("Enter Role");
            Scanner readRole = new Scanner(System.in);
            String enteredRole = readPass.next();

            System.out.println("Enter ContactDetails");
            Scanner contactInfoIn = new Scanner(System.in);
            String contactInfo = contactInfoIn.next();

            LoginInfo lInfo = new LoginInfo(enteredUsername, enteredPassword, enteredRole, contactInfo);
            if( lInfo.saveUserCred(lInfo) ) {
                System.out.println(" User Account Created! Please re-login with username password ");
            } else {
                System.out.println(" Account Creation Failed as username is already present");
            }
        } else {
            System.out.println(" Invalid Input Entered! ");
        }

        return null; //This is the case when user does not exist or new user is created.
    }

}
