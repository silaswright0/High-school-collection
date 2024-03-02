/*Silas Wright
  November 30 2021

A new Patron must have a name and ID number to exist
A new Patron may also be created with name, ID, and up to three Books
A Patron’s Books may change, but their name and ID number may not
A Patron can tell us whether or not it has borrowed a given Book (identified by title)
A Patron can be told to return a given Book (identified by title). 
This removes that Book from their inventory. Since it’s possible for a Patron not to have the given Book,
the Patron should give feedback on whether or not it was able to return the Book.
A Patron can borrow a given book (identified by Book). This stores the Book in the Patron’s inventory.
Since it’s possible that the Patron already has three Books signed out (and their inventory is full),
this method should return whether or not the Book was successfully borrowed.
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrightlibrary;

/**
 *
 * @author silas wright
 */
public class Patron {
    //declare fields

    private String name;
    private int idNum;
    private Book book1;
    private Book book2;
    private Book book3;

    //create constructors
    /**
     * Primary constructor creates patron object with a minimum of a name and
     * idNum
     *
     * @param name the name of patron as a string
     * @param idNum the idNum of a patron as an integer
     */
    public Patron(String name, int idNum) {
        //set name and idNum to user input
        this.name = name;
        this.idNum = idNum;

    }

    /**
     * This constructor creates an instance of the patron class with a name
     * idNum and a book
     *
     * @param name the name of patron as a string
     * @param idNum the idNum of a patron as an integer
     * @param book1 the first book of the patron
     *
     */
    public Patron(String name, int idNum, Book book1) {
        //chain previous contructor
        this(name, idNum);
//set the first book to user input
        this.book1 = book1;
    }

    /**
     * This constructor creates an instance of the patron class with a name
     * idNum and two books
     *
     * @param name the name of patron as a string
     * @param idNum the idNum of a patron as an integer
     * @param book1 the first book of the patron
     * @param book2 the second book of the patron
     */
    public Patron(String name, int idNum, Book book1, Book book2) {
        //chain previous contructor
        this(name, idNum, book1);
//set the second book to user input
        this.book2 = book2;
    }

    /**
     * This constructor creates an instance of the patron class with all fields
     * filled by user input
     *
     * @param name the name of patron as a string
     * @param idNum the idNum of a patron as an integer
     * @param book1 the first book of the patron
     * @param book2 the second book of the patron
     * @param book3 the third book of the patron
     */
    public Patron(String name, int idNum, Book book1, Book book2, Book book3) {
        //chain previous contructor
        this(name, idNum, book1, book2);
//set the third book to user input
        this.book3 = book3;
    }

//create accessors    
    /**
     * This method returns the name of the patron
     *
     * @return the name of the patron as a string
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the id number of the patron
     *
     * @return the id number of the patron as a integer
     */
    public int getIDNum() {
        return idNum;
    }

    /**
     * This method returns the first book of a patron
     *
     * @return the first book of the patron
     */
    public Book getBook1() {
        return book1;
    }

    /**
     * This method returns the second book of a patron
     *
     * @return the second book of the patron
     */
    public Book getBook2() {
        return book2;
    }

    /**
     * This method returns the third book of a patron
     *
     * @return the third book of the patron
     */
    public Book getBook3() {
        return book3;
    }

//create mutators
    /**
     * This method allows the user to modify book 1
     *
     * @param newBook the new book to replace book1
     */
    public void setBook1(Book newBook) {
        book1 = newBook;
    }

    /**
     * This method allows the user to modify book 2
     *
     * @param newBook the new book to replace book 2
     */
    public void setBook2(Book newBook) {
        book2 = newBook;
    }

    /**
     * This method allows the user to modify book 3
     *
     * @param newBook the new book to replace book 3
     */
    public void setBook3(Book newBook) {
        book3 = newBook;
    }

    @Override
    /**
     * This method will return a patron as a string
     *
     * @return the patron as a string
     */
    public String toString() {
        String sBook1 = "";
        String sBook2 = "";
        String sBook3 = "";
        //use if statments to avoid runtime error
        if (book1 == null) {
            sBook1 = "No book";
        } else {
            sBook1 = book1.toString();
        }
        if (book2 == null) {
            sBook2 = "No book";
        } else {
            sBook2 = book2.toString();
        }
        if (book3 == null) {
            sBook3 = "No book";
        } else {
            sBook3 = book3.toString();
        }

        String s = "Patron name:\t" + name + "\nID num:\t" + idNum + "\nBook 1:\n" + sBook1 + "\nBook 2:\n" + sBook2
                + "\nBook 3:\n" + sBook3;
        return s;
    }

    //action methods
    /**
     * This method will return true if a patron is borrowing a specific book
     * indicated by the title
     *
     * @param bookTitle the title of book we are checking
     * @return a boolean of if the patron has that book signed out
     */
    public boolean hasBook(String bookTitle) {
        //declare boolean
        boolean patronHasBook = false;
//use if statments to determine if the patron has the requested book signed out
        if (book1 != null) {//add nested if statments to ensure we do not request info from a null object
            if (book1.getTitle().equalsIgnoreCase(bookTitle)) {
                patronHasBook = true;
            }
        }
        if (book2 != null) {
            if (book2.getTitle().equalsIgnoreCase(bookTitle)) {
                patronHasBook = true;
            }
        }
        if (book3 != null) {
            if (book3.getTitle().equalsIgnoreCase(bookTitle)) {
                patronHasBook = true;
            }
        }
//return the boolean determining if the patron has the book signed our or not
        return patronHasBook;
    }

    /**
     * This method will attempt to return a book by removing the book from the
     * patrons inventory
     *
     * @param bookTitle the book that we will try to remove
     * @return if the removal was successful
     */
    public boolean returnBook(String bookTitle) {
        //create boolean for if the book was successfully returned
        boolean successfullyReturned = false;
        // use if statments to determine if the patron has the requested book signed out
        //if a patrons book matches the requested book set the patrons book to null
        if (book1 != null) {//add nested if statments to ensure we do not request info from a null object
            if (book1.getTitle().equalsIgnoreCase(bookTitle)) {
                book1 = null;
                successfullyReturned = true;
            }
        }
        if (book2 != null) {
            if (book2.getTitle().equalsIgnoreCase(bookTitle)) {
                book2 = null;
                successfullyReturned = true;
            }
        }
        if (book3 != null) {
            if (book3.getTitle().equalsIgnoreCase(bookTitle)) {
                book3 = null;
                successfullyReturned = true;
            }
        }
        //if the requested book did not match any of the patrons books then the method will return false
        return successfullyReturned;
    }

    /**
     * This method will attempt to save a new book to an open inventory space if
     * the patron has no spaces we will return a false boolean
     *
     * @param newBook the new book to be saved
     * @return determines if the book was successfully borrowed
     */
    public boolean borrowBook(Book newBook) {
        //declare new boolean to determine if the borrow attempt was a success
        boolean successfulBorrow = false;

//use if statments to check if the patron has open inventory for a new book
//if a users book spot = null then set that book spot to the new book and save borrow as a success
        if (book1 == null) {
            book1 = newBook;
            successfulBorrow = true;
        } else if (book2 == null) {
            book2 = newBook;
            successfulBorrow = true;
        } else if (book3 == null) {
            book3 = newBook;
            successfulBorrow = true;
        }
        //if there was no space the borrow attempt will stay false
        //return the successfulBorrow boolean
        return successfulBorrow;
    }
}
