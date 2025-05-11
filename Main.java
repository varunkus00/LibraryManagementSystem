import Books.NovelBook;
import Books.TextBook;
import Login.LoginInfo;
import Users.Member;
import Users.User;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        System.out.println("This is Library Management System Project");
        //Ask for Login Credentials
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
            if ( !lInfo.validateUserInfo(enteredUsername, enteredPassword) ) {
                return;
            }
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

            LoginInfo lInfo = new LoginInfo(enteredUsername, enteredPassword, enteredRole);
            if( lInfo.saveUserCred(lInfo) ) {
                System.out.println(" User Account Created! Please re-login with username password ");
            } else {
                System.out.println(" Account Creation Failed as username is already present");
            }
        } else {
            System.out.println(" Invalid Input Entered! ");
        }
        LMS lms = new LMS();

        Member m1 = new Member("Varun", "919191");
        Member m2 = new Member("Priya", "176541");
        lms.registerUser(m1);
        lms.registerUser(m2);

        TextBook tBook = new TextBook("1234", "DSA", "Karumanchi", "CS", "TEXTBOOK");
        NovelBook nBook = new NovelBook("1231", "The Art of Doing Things", "Michael Misht", "Philosphy");
        lms.addBook( tBook );
        lms.addBook( nBook );

        lms.displayUserDetails(m1);
        lms.displayBookDetails(tBook);

        if(tBook.lend(m1)) {
            System.out.println(" Lended Book to " + m1.getUserName() + " successfully ");
        }
        else {
            System.out.println(" Book Lending failed ");
        }

        System.out.println(" Total Users in the system are : " + lms.getTotalUsers());
        System.out.println(" Total Books in the system are : " + lms.getTotalBooks());

    }

}
