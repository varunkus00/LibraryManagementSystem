package Login;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LoginInfo {

    private String username;
    private String password;
    private String role;
    private boolean isUserNameExist;
    private final String userCredentialFile = "UserCredentials.txt";
    private final String userCredentialFilePath = "C:/Users/shriv/Downloads/Documents/";
    private static int lineNumber=0;
    //private final String userCredentialFilePermission;

    public LoginInfo() {
        this(null, null, null);
    }

    public LoginInfo(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isUserNameExist = false;
    }

    public boolean saveUserCred(LoginInfo userDetails) {
        try {
            // This line 33 is duplicating code. Need to re-write it in a better way.
            validateUserInfo(userDetails.username, userDetails.password);
            if( !this.isUserNameExist ) {
                FileWriter saveCred = new FileWriter(userCredentialFilePath+userCredentialFile, true);
                lineNumber++;
                saveCred.write(lineNumber + ". " + userDetails.username + ", " + userDetails.password + " , " + userDetails.role);
                saveCred.write("\n");
                saveCred.close();
            } else {
                System.out.println("This username already exist! Cannot Create user");
                return false;
            }
        } catch(IOException e) {
            System.out.println("File " + userCredentialFilePath+userCredentialFile + " Not Found");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean validateUserInfo(String username, String password) {
        try {
            File readUserCred = new File(userCredentialFilePath + userCredentialFile);
            Scanner read = new Scanner(readUserCred);
            boolean isAuthenticated = false;
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] parts = line.split("\\.", 2);
                if (parts.length < 2) continue;

                String[] userDetails = parts[1].trim().split(",");
                if (userDetails.length < 3) continue;

                String userName = userDetails[0].trim();
                String userPass = userDetails[1].trim();
                String userRole = userDetails[2].trim();

                if (userName.equals(username)) {
                    isUserNameExist = true;
                    if (userPass.equals(password)) {
                        isAuthenticated = true;
                        System.out.println("Login Successfull for role : " + userRole);
                        break;
                    } else {
                        System.out.println(" Wrong Password Entered ");
                    }
                }
            }

            read.close();
            if( isAuthenticated ) {
                return true;
            } else {
                System.out.println(" Invalid Credentials");
            }
        } catch (FileNotFoundException e) {
            System.out.println(" User Credentials File Not Found! Login Denied ");
        }



        return false;
    }

}
