package Users;

import Books.Book;
import Books.NovelBook;
import Books.TextBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Librarian extends User {

    private static final long serialVersionUID = 1L;
    private String employeeNumber;
    private static int uniqueEmployeeId = 0;

    public Librarian(String username, String contactInfo, String password) {
        super(username, contactInfo, password);
        uniqueEmployeeId++;
        this.employeeNumber = String.valueOf(uniqueEmployeeId);
    }

    @Override
    public String toString() {
        return " Username : " + getUserName() + " ContactInfo : " + getContactInfo() + " EmployeeNumber : " + employeeNumber;
    }

    public void displayDashboard() {
        System.out.println(this);
    }
    public boolean canBorrowBooks() {
        return true;
    }

    public void incrementBorrowedBooksCount() {};
    public void decrementBorrowedBooksCount() {};

    public void addNewBook(Book book) {
        List<Book> books = StorageUtil.loadBooks();
        Map<String, String> booksMapping = new HashMap<>();
        for( Book b : books ) {
            if( b instanceof TextBook)
                booksMapping.put(b.getIsbn(), "TEXTBOOK");
            else
                booksMapping.put(b.getIsbn(), "NOVELBOOK");
        }
        if( booksMapping.containsKey(book.getIsbn())) {
            books.add(book);
            StorageUtil.saveBooks(books);
        } else {
            System.out.println(" A book with this ISBN already present. Please ensure ISBN is unique");
        }
    }

    public void removeBook(String title) {
        List<Book> books = StorageUtil.loadBooks();
        books.removeIf(b -> b.getTitle().equalsIgnoreCase(title));
        StorageUtil.saveBooks(books);
    }

    private void librarianPageMenu() {
        System.out.println("======Welcome to Library Management System======");
        System.out.println("1. Get List Of Available Books ");
        System.out.println("2. Get List Of Not Available Books ");
        System.out.println("3. Add a Book ");
        System.out.println("4. Remove a Book ");
        System.out.println("5. List Members ");
        System.out.println("6. List Book Details ");
        System.out.println("0. LogOut ");
        System.out.println("================================================");
    }

    @Override
    public void userPage() {
        System.out.println("Librarian Page");
        displayDashboard();
        while(true) {
            librarianPageMenu();
            Scanner scn = new Scanner(System.in);
            Integer readInput = scn.nextInt();
            if( readInput.equals(1) ) {
                System.out.println(" Books Available are : ");

                System.out.println("=====================================================================================================================");
                System.out.printf("| %-2s | %-9s | %-6s | %-40s | %-18s | %-18s |\n",
                        "#", "Type", "ISBN", "Title", "Author", "Subject/Genre");
                System.out.println("=====================================================================================================================");

                List<Book> listOfBooks = StorageUtil.loadBooks();
                int counter = 1;
                if( !listOfBooks.isEmpty() ) {
                    for( Book b : listOfBooks ) {
                        if( b.isAvailable() ) {
                            System.out.println(b.getFormattedRow(counter++));
                        }
                    }
                }
                System.out.println("=====================================================================================================================");
            } else if( readInput.equals(2) ) {
                System.out.println(" Books Not Available are : ");

                System.out.println("=====================================================================================================================");
                System.out.printf("| %-2s | %-9s | %-6s | %-40s | %-18s | %-18s |\n",
                        "#", "Type", "ISBN", "Title", "Author", "Subject/Genre");
                System.out.println("=====================================================================================================================");

                List<Book> listOfBooks = StorageUtil.loadBooks();
                int counter = 1;
                if( !listOfBooks.isEmpty() ) {
                    for( Book b : listOfBooks ) {
                        if( !b.isAvailable() ) {
                            System.out.println(b.getFormattedRow(counter++));
                        }
                    }
                }
                System.out.println("=====================================================================================================================");
            } else if( readInput.equals(3) ) {
                System.out.println(" Please Enter ");
                System.out.println("1. Add Book Manually");
                System.out.println("2. Add Book from a file. ");

                Scanner read = new Scanner(System.in);
                Integer inputType = read.nextInt();
                if( inputType.equals(1)) {
                    Book newBook;
                    Scanner type = new Scanner(System.in);
                    String bType = type.nextLine();

                    if( !bType.equalsIgnoreCase("TEXTBOOK") && !bType.equalsIgnoreCase("NOVELBOOK") ) {
                        System.out.println(" Not A Valid Book Type. Currently we support TextBook or NovelBook");
                    }

                    System.out.println(" Enter ISBN ");
                    Scanner scnBookISBN = new Scanner(System.in);
                    String bookISBN = scnBookISBN.nextLine();

                    System.out.println(" Enter Title ");
                    Scanner scnBookTitle = new Scanner(System.in);
                    String bookTitle = scnBookTitle.nextLine();

                    System.out.println(" Enter Author ");
                    Scanner scnBookAuthor = new Scanner(System.in);
                    String bookAuthor = scnBookAuthor.nextLine();

                    if( bType.equalsIgnoreCase("TEXTBOOK") ) {
                        System.out.println(" Enter Subject ");
                        Scanner scnBookSubject = new Scanner(System.in);
                        String bookSubject = scnBookSubject.nextLine();
                        newBook = new TextBook(bookISBN, bookTitle, bookAuthor, bookSubject, bType);
                    } else {
                        System.out.println(" Enter Genre ");
                        Scanner scnBookGenre = new Scanner(System.in);
                        String bookGenre = scnBookGenre.nextLine();
                        newBook = new NovelBook(bookISBN, bookTitle, bookAuthor, bookGenre);
                    }

                    addNewBook(newBook);
                } else {

                    System.out.println(" Please make sure each line in the file provided should have format as below : ");

                    System.out.println("======================================================================================");
                    System.out.println("For TextBook  - Booktype,ISBN,Title,Author,Subject");
                    System.out.println("For NovelBook - Booktype,ISBN,Title,Author,Genre");
                    System.out.println("Make Sure Booktype is either TextBook or NovelBook and no space before and after comma");
                    System.out.println("=======================================================================================");

                    List<Book> listBooks = StorageUtil.loadBooks();
                    Map<String, String> booksMapping = new HashMap<>();
                    for( Book b : listBooks ) {
                        if( b instanceof TextBook)
                            booksMapping.put(b.getIsbn(), "TEXTBOOK");
                        else
                            booksMapping.put(b.getIsbn(), "NOVELBOOK");
                    }
                    System.out.println(" Please provide the absoulte path of file in format - C:\\\\Users\\\\shriv\\\\Downloads\\\\Documents\\\\ListOfBooksForLMS.txt ");
                    Scanner scnFile = new Scanner(System.in);
                    String fileInput = scnFile.nextLine();

                    try {
                        File file = new File(fileInput);
                        Scanner fileReader = new Scanner(file);
                        while(fileReader.hasNextLine() ) {
                            String line = fileReader.nextLine();
                            List<String> lineDetails = List.of(line.split(","));
                            if( lineDetails.size() != 5) {
                                continue;
                            }
                            if( booksMapping.containsKey(lineDetails.get(1)) ) {
                                continue;
                            }
                            Book b;
                            String isbn, title, author, booktype, subject, genre;
                            isbn = lineDetails.get(1);
                            title = lineDetails.get(2);
                            author = lineDetails.get(3);
                            if( lineDetails.get(0).equalsIgnoreCase("TEXTBOOK")) {
                                booktype = "TextBook";
                                subject = lineDetails.get(4);
                                b = new TextBook(isbn, title, author, subject, booktype);
                                listBooks.add(b);
                            } else {
                                booktype = "NovelBook";
                                genre = lineDetails.get(4);
                                b = new NovelBook(isbn, title, author, genre);
                                listBooks.add(b);
                            }
                        }
                    } catch(FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    StorageUtil.saveBooks(listBooks);
                }

            } else if( readInput.equals(4) ) {
                System.out.println(" Enter the Book Title to Remove ");
                Scanner in = new Scanner(System.in);
                String readTitle = in.nextLine();

                removeBook(readTitle);
            } else if( readInput.equals(5) ) {
                System.out.println(" List of Users registered to this LMS are : ");
                List<User> users = StorageUtil.loadUsers();
                for( User u : users ) {
                    if( u instanceof Member ) {
                        System.out.println(" Username : " + u.getUserName() + " Contact Info : " + u.getContactInfo());
                    }
                }
            } else if( readInput.equals(6) ) {
                //System.out.println(" List of Books present in this LMS are : ");
                List<Book> books = StorageUtil.loadBooks();
                System.out.println(" Total Books Present Are : " + books.size());
                System.out.println("Book Details are : ");
                for( Book b : books ) {
                    b.displayBookDetails();
                }
            } else {
                System.out.println(" Logging Out! ");
                return;
            }
        }

    }
}
