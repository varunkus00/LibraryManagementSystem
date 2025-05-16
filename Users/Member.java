package Users;

import Books.Book;
import Books.NovelBook;
import Books.TextBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Member extends User {

    private static final long serialVersionUID = 1L;

    private List<Book> myList;
    private int borrowedBooksCount;
    private final int MAX_borrow_LIMIT = 5;

    public Member(String username, String contactInfo, String password) {
        super(username, contactInfo, password);
        myList = new ArrayList<>();
        borrowedBooksCount = 0;
    }

    public void displayDashboard() {
        displayUserMenu();
    }

    public void userPage() {
        System.out.println(" Username : " + getUserName() + " Contact Info : " + getContactInfo() /*+ " Books Borrowed : " + borrowedBooksCount*/);
        while(true) {
            displayDashboard();
            Scanner in = new Scanner(System.in);
            Integer read = in.nextInt();
            if( read.equals(1) ) {
                System.out.println(" Books Available are : ");

                System.out.println("=====================================================================================================================");
                System.out.printf("| %-2s | %-9s | %-6s | %-40s | %-18s | %-18s |\n",
                        "#", "Type", "ISBN", "Title", "Author", "Subject/Genre");
                System.out.println("=====================================================================================================================");

                List<Book> listOfBooks = StorageUtil.loadBooks();
                int counter = 1;
                for( Book b : listOfBooks ) {
                    if( b.isAvailable() ) {

                        System.out.println(b.getFormattedRow(counter++));

                    }
                }
                System.out.println("=====================================================================================================================");
            } else if ( read.equals(2) ) {
                System.out.println(" Books Not Available are : ");

                System.out.println("=====================================================================================================================");
                System.out.printf("| %-2s | %-9s | %-6s | %-40s | %-18s | %-18s |\n",
                        "#", "Type", "ISBN", "Title", "Author", "Subject/Genre");
                System.out.println("=====================================================================================================================");

                List<Book> listOfBooks = StorageUtil.loadBooks();
                int counter = 1;
                for( Book b : listOfBooks ) {
                    if( !b.isAvailable() ) {

                        System.out.println(b.getFormattedRow(counter++));

                    }
                }
                System.out.println("=====================================================================================================================");
            } else if( read.equals(3) ) {
                System.out.println("This option is not available currently");
            } else if( read.equals(4) ) {
                System.out.println("Please provide the book type to search for... ");
                Scanner inType = new Scanner(System.in);
                String bookType = inType.nextLine().toUpperCase();
                List<Book> listOfBooks = StorageUtil.loadBooks();

                if( bookType.equals("TEXTBOOK") || bookType.equals("NOVELBOOK")) {
                    for (Book b : listOfBooks) {
                        if (b instanceof TextBook && bookType.equals("TEXTBOOK")) {
                            b.displayBookDetails();
                        } else if (b instanceof NovelBook && bookType.equals("NOVELBOOK")) {
                            b.displayBookDetails();
                        }
                    }
                }
            } else if( read.equals(5)  ) {
                List<Book> books = StorageUtil.loadBooks();
                System.out.println("1. Provide the Book Title");
                System.out.println("2. Provide the ISBN ");
                Scanner inType = new Scanner(System.in);
                Integer inputType = inType.nextInt();
                if( inputType.equals(1) ) {
                    System.out.println(" Enter the book title ");
                    Scanner inBook = new Scanner(System.in);
                    String book = inBook.nextLine();
                    for( Book b : books ) {
                        if( b.getTitle().equalsIgnoreCase(book) ) {
                            if( b.lend(this) ) {
                                System.out.println("Book has been lend to you");
                                myList.add(b);
                            } else {
                                System.out.println("Book Not Available to lend");
                            }
                        }
                    }
                } else if( inputType.equals(2) ) {
                    System.out.println(" Enter the book ISBN ");
                    System.out.println(" Total Books Available are : " + books.size());
                    Scanner inBook = new Scanner(System.in);
                    String bookISBN = inBook.nextLine();
                    for( Book b : books ) {
                        if( b.getIsbn().equals(bookISBN) ) {
                            if( b.lend(this) ) {
                                System.out.println("Book has been lend to you");
                                myList.add(b);
                            } else {
                                System.out.println("Book Not Available to lend");
                            }
                        }
                    }
                } else {
                    System.out.println(" Invalid Input ");
                    continue;
                }
                StorageUtil.saveBooks(books);
                updateUserState(this);
            } else if( read.equals(6) ) {
                System.out.println(this.borrowedBooksCount);
            } else if( read.equals(7) ) {
                System.out.println(" Username : " + getUserName() + " Contact Info : " + getContactInfo() + " Books Borrowed : " + borrowedBooksCount);
                System.out.println("User Book List Page");
                while(true) {
                    System.out.println("1. Show List Of Books");
                    System.out.println("2. Return to Main Menu");

                    Scanner scn = new Scanner(System.in);
                    Integer input = scn.nextInt();
                    System.out.println("=====================================================================================================================");
                    System.out.printf("| %-2s | %-9s | %-6s | %-40s | %-18s | %-18s |\n",
                            "#", "Type", "ISBN", "Title", "Author", "Subject/Genre");
                    System.out.println("=====================================================================================================================");
                    if( input.equals(1) ) {
                        if( myList.size() == 0 ) {
                            System.out.println("No Books Borrowed so far");
                            break;
                        }
                        int counter = 1;
                        for( Book myBook : myList ) {
                            System.out.println(myBook.getFormattedRow(counter++));
                        }
                    } else {
                        break;
                    }
                    System.out.println("=====================================================================================================================");
                }
            } else if( read.equals(8) ) {
                List<Book> books = StorageUtil.loadBooks();
                System.out.println("1. Provide the Book Title");
                System.out.println("2. Provide the ISBN ");
                Scanner inType = new Scanner(System.in);
                Integer inputType = inType.nextInt();
                if( inputType.equals(1) ) {
                    System.out.println(" Enter the book title ");
                    Scanner inBook = new Scanner(System.in);
                    String book = inBook.nextLine();
                    for( Book b : books ) {
                        if( b.getTitle().equalsIgnoreCase(book) ) {
                            b.returnBook(this);
                            for( Book myBooks : myList ) {
                                if( myBooks.getTitle().equalsIgnoreCase(b.getTitle())) {
                                    myList.remove(myBooks);
                                    break;
                                }
                            }
                            //myList.remove(b); //Not working, A little enhancement might be needed here.
                            break;
                        }
                    }
                } else if( inputType.equals(2) ) {
                    System.out.println(" Enter the book ISBN ");
                    //System.out.println(" Total Books Available are : " + books.size());
                    Scanner inBook = new Scanner(System.in);
                    String bookISBN = inBook.nextLine();
                    for (Book b : books) {
                        if (b.getIsbn().equalsIgnoreCase(bookISBN)) {
                            b.returnBook(this);
                            for (Book myBooks : myList) {
                                if (myBooks.getTitle().equalsIgnoreCase(b.getTitle())) {
                                    myList.remove(myBooks);
                                    break;
                                }
                            }
                            //myList.remove(b); //Not working, A little enhancement might be needed here.
                            break;
                        }
                    }
                } else {
                    System.out.println(" Invalid Input");
                }
                StorageUtil.saveBooks(books);
                updateUserState(this);
            } else if( read.equals(0) ){
                return;
            }
        }
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
