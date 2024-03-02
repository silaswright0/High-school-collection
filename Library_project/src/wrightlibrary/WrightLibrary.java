/*Silas Wright
  November 30 2021

Patrons of the Canadian Library of Parliament (or Bibliothèque du Parlement) can borrow up to three Books.
A Patron has a name, an ID number, and up to three Books. A Book has an author, a title, and a number of pages.
Design and implement two classes, Patron and Book, to represent these objects and the following behaviour:
A new Book must have a title, author, and number of pages to exist
A Book’s title, author, and number of pages can be examined, but never modified
A new Patron must have a name and ID number to exist
A new Patron may also be created with name, ID, and up to three Books
A Patron’s Books may change, but their name and ID number may not
A Patron can tell us whether or not it has borrowed a given Book (identified by title)
A Patron can be told to return a given Book (identified by title). This removes that Book from their inventory.
Since it’s possible for a Patron not to have the given Book,
the Patron should give feedback on whether or not it was able to return the Book.
A Patron can borrow a given book (identified by Book). This stores the Book in the Patron’s inventory.
Since it’s possible that the Patron already has three Books signed out (and their inventory is full),
this method should return whether or not the Book was successfully borrowed.


PROGRAM REQUIREMENTS:


Part 1:
Write class summary boxes for the Book Class and the Patron Class, as described above.
These will be submitted at the end of the project. You may choose to get these checked by your teacher before continuing.

Part 2: 
Create a new project called LastNameLibrary and implement the Book and Patron classes described above. 

Part 3:
Write a main class program that instantiates P Patrons, and stores them in an array.
P is a number contained in the file patrons.txt and is followed by P Patron records of the form
{Name, ID, Book1Title, Book1Author, Book1Pages, Book2Title, etc}.
You will need to create a new Book object for each of the books held by the Patron.
If a Patron has a Book titled “Empty”, assign that Book reference a value of null.

Your Library Simulation will provide the user with a menu that allows them to search either by Patron or by Book.
If the user is looking for a Book, provide the names of all of the Patrons who have borrowed the Book
(or inform them it has not been borrowed). Also inform the user if the Book does not exist.
If the user is looking for a Patron, provide the names of all of the Books that Patron has borrowed. 
Inform the user if the Patron does not exist. The user may choose to search by name or by ID number.
Your menu should continue to loop until the user decides to exit. 

Part 4:
Add an action to the menu which makes sense in the context of the assignment 
(e.g. add Book, return Book, add Patron, or another option of your choosing).




Notes:
This program is not a fully featured Library Simulation in the sense that the user cannot make a Patron borrow or return a book.
The user may simply view the state of the Library at one particular point in time. 
Consider using the String method equalsIgnoreCase to make your searches more effective
You should be encapsulating your data
Classes should have toString methods
An array of objects, after being instantiated contains “null” in each of its elements.
Each element will need to be set to a new object
Using methods to break down the assignment into smaller problems will make it much easier. 
You can pass objects as parameters, just remember that like arrays, they are reference types,
meaning that a new copy of the object is not created in the method that it is passed to.
Changes made to the object in the method will still be there after the method completes. 
at no point should the Patron or Book classes be reading from files, or prompting the user for data
Your program will be evaluated using a different file than the one provided

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
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class WrightLibrary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //try to read from data file using scanner
        try {
            //create new file and scanner
            File f = new File("src\\wrightlibrary\\patrons.txt");
            Scanner s = new Scanner(f);
            //declare int p for how many patrons are in the data file
            int p = Integer.parseInt(s.nextLine());
            //create arrays based on p
            Patron[] patrons = new Patron[p];
            //create loop based on p and save data from data files into arrays
            for (int i = 0; i < p; i++) {
                //load data into array of patron objects
                patrons[i] = new Patron(s.nextLine(), Integer.parseInt(s.nextLine()), new Book(s.nextLine(), s.nextLine(), Integer.parseInt(s.nextLine())), new Book(s.nextLine(), s.nextLine(), Integer.parseInt(s.nextLine())), new Book(s.nextLine(), s.nextLine(), Integer.parseInt(s.nextLine())));
                //create if statment that converts empty books to null
                if (patrons[i].getBook1().getTitle().equalsIgnoreCase("empty")) {
                    //if book 1 is empty then set that book to null
                    patrons[i].setBook1(null);
                } else if (patrons[i].getBook2().getTitle().equalsIgnoreCase("empty")) {
                    //if book 2 is empty then set that book to null
                    patrons[i].setBook2(null);
                } else if (patrons[i].getBook3().getTitle().equalsIgnoreCase("empty")) {
                    //if book 3 is empty then set that book to null
                    patrons[i].setBook3(null);
                }
            }
//invoke the menu method and pass the array of patron objects
            menu(patrons);
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR:\t" + e);
        }

    }

    /**
     * This method will provide a menu which allows the user to choose what the
     * library program should do
     *
     * @param patrons the array of patrons
     */
    public static void menu(Patron[] patrons) {
        //create boolean for if the users input is valid
        boolean inputValid = false;
        //sent welcome message
        JOptionPane.showMessageDialog(null, "Welcome to the Canadian Library of Parliment");
//declare variables
        int userOption = 0;
        String patronToSearch;//declare variables to hold the name or id number of patron
        String sUserOption;
        int patronIDToSearch;
//create our loop while user does not want to quit
        while (userOption != 6) {
            while (inputValid == false) {
                //show user the menu
                sUserOption = (JOptionPane.showInputDialog("Choose an option\n1. Search for book\n2. Search for patron\n"
                        + "3. Borrow Book\n4. Return Book\n5. Add Patron\n6. Quit"));

                try {
                    userOption = Integer.parseInt(sUserOption);
                    //if successful then input is valid
                    inputValid = true;
                } catch (NumberFormatException e) {
                    //if not valid input tell user
                    JOptionPane.showMessageDialog(null, "Enter a valid input", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            }
            inputValid = false;
            //use if statments to determine which smaller method to run
            if (userOption == 1) {
                searchByBook(patrons);
            } else if (userOption == 2) {
                patronToSearch = JOptionPane.showInputDialog("Enter the name or id number of patron");
                try {//try to parse into id number
                    patronIDToSearch = Integer.parseInt(patronToSearch);
                    //if it was successful run search patron method with id number
                    searchByPatron(patrons, patronIDToSearch);
                } catch (NumberFormatException e) {
                    //if it doesn't work there is no real error the user simply entered the patron's name  
                    //so invoke the search patrons method with the patron's name
                    searchByPatron(patrons, patronToSearch);
                }

            } else if (userOption == 3) {
                //run borrow method
                attemptBorrow(patrons);
            } else if (userOption == 4) {
                //run return method
                attemptReturn(patrons);
            } else if (userOption == 5) {
                //run add patron method
                //does not call method because I was not able to fully complete the addPatron method
                //I tried for a little but was not able to get around to ArrayIndexOutOfBoundsException
                //I figured I would leave my work in the method but simply not call it 
                //since that method is not a required method for this assignment
            } else if (userOption == 6) {
                //let the program finish
            } else {
                //if the user entered something not allowed tell them
                JOptionPane.showMessageDialog(null, "Enter a valid input", "ERROR", JOptionPane.ERROR_MESSAGE);

            }
        }

    }

    /**
     * This method will search through the patrons and determine which patrons
     * have a specific book signed out
     *
     * @param patrons the array of patrons of the library
     *
     */
    public static void searchByBook(Patron[] patrons) {
        String bookTitle = JOptionPane.showInputDialog("Enter the name of the book");
//declare string which will accumulate names of patrons who have the book
        String patronsWithBook = "";

//loop through patrons
        for (int i = 0; i < patrons.length; i++) {
            //use if statment conditional on the hasBook method in the patron class to determine if a patron has a book signed out
            if (patrons[i].hasBook(bookTitle)) {
                //if the patron has the book accumulate the string
                patronsWithBook += patrons[i].getName() + "\n";
            }
        }
//use if statment to dermine if there were any patrons with that book signed out
        if (patronsWithBook.equals("")) {//if there are no patrons with the book the patronsWithBook variable will be empty
//send message to user
            JOptionPane.showMessageDialog(null, "There are no patrons who are currently borrowing " + bookTitle);
        } else {//if there is any patrons with the book
            //show the user all patrons with the book using JOptionPane
            JOptionPane.showMessageDialog(null, bookTitle + " is borrowed by:\n" + patronsWithBook);
        }
    }

    /**
     * This method will find a patron using their name then use to toString
     * method in patrons class to print out patron
     *
     * @param patrons the array of patrons
     * @param name the name the user is looking for
     */
    public static void searchByPatron(Patron[] patrons, String name) {
//declare boolean for if the patron exists
        boolean patronFound = false;
        //boolean bookExists = true;
//loop through array and for each patron with the name identical to user input use toString method from patron class
        for (int i = 0; i < patrons.length; i++) {

            //use if statment to determine if the user name matches the patron name
            if (patrons[i].getName().equalsIgnoreCase(name)) {
                //if the name matches
                patronFound = true; //set patrons found to true
                //and send information of patron to user
                JOptionPane.showMessageDialog(null, patrons[i].toString());
            }
        }

//if the name was not found notify user
        if (patronFound == false) {
            JOptionPane.showMessageDialog(null, "The patron " + name + " is not in our records");
        }
    }

    /**
     * This method will find a patron using their idNumber then use to toString
     * method in patrons class to print out patron
     *
     * @param patrons the array of patrons
     * @param idNum the id number that the user is looking for
     */
    public static void searchByPatron(Patron[] patrons, int idNum) {
//declare boolean for if the patron exists
        boolean patronFound = false;

//loop through array and for each patron with the idnumber identical to user input use toString method from patron class
        for (int i = 0; i < patrons.length; i++) {
            //use if statment to determine if the user idnumber matches the patron idnum
            if (patrons[i].getIDNum() == idNum) {
                //if the id number matches
                patronFound = true; //set patrons found to true
                //and send information of patron to user
                JOptionPane.showMessageDialog(null, patrons[i].toString());
            }
        }
//if the idnumber was not found notify user
        if (patronFound == false) {
            JOptionPane.showMessageDialog(null, "The patron with an id number of " + idNum + " is not in our records");
        }
    }

    //extra methods
    /**
     * This method will attempt to sign out a book for a patron
     *
     * @param patrons the array of patrons
     *
     */
    public static void attemptBorrow(Patron[] patrons) {
        //declare and initilize the patron ID
        int patronID = 0;
        //declare boolean of if the attempted borrow is a success
        boolean borrowSuccess = false;
        boolean inputValid = false;//to ensure user provides a good id number
        while (inputValid == false) {
            //get the patrons id number
            String sPatronID = (JOptionPane.showInputDialog("Enter the id number of the patron"));
            //try to parse input
            try {
                patronID = Integer.parseInt(sPatronID);
                //if successful inputValid is true
                inputValid = true;
            } catch (NumberFormatException e) {
                //if the user entered bad input tell them
                JOptionPane.showMessageDialog(null, "Enter a valid id number", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        //get the book to be signed out
        String bookTitle = JOptionPane.showInputDialog("Enter the title of the book");
        String bookAuthor = JOptionPane.showInputDialog("Enter the author of the book");
        inputValid = false;//reset valid input to false 
        //initialize number of pages
        int numPages = 0;
        //create loop to ensure user enters good input
        while (inputValid == false) {
            //get the patrons id number    
            String sNumPages = (JOptionPane.showInputDialog("Enter the number of pages in the book"));
            //try to parse input
            try {
                numPages = Integer.parseInt(sNumPages);
                //if successful inputValid is true
                inputValid = true;
            } catch (NumberFormatException e) {
                //if the user entered bad input tell them
                JOptionPane.showMessageDialog(null, "Enter a number", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        //use id number to find the patron object
        for (int i = 0; i < patrons.length; i++) {
            //use if statment to see if id number matches 
            if (patrons[i].getIDNum() == patronID) {
//all patrons will have unique id numbers so we can invoke method here
//invoke borrow book method from the patron class and store if it is a success in the borrow success variable
                borrowSuccess = patrons[i].borrowBook(new Book(bookTitle, bookAuthor, numPages));
            }
        }

        //use if statment to determine what output to show user
        if (borrowSuccess) {
            //if it was a success
            JOptionPane.showMessageDialog(null, "Patron number " + patronID + " successfuly borrowed " + bookTitle);
        } else {
            //if it was a faliure
            JOptionPane.showMessageDialog(null, "Patron number " + patronID + " already has three books borrowed");
        }

    }

    /**
     * This method will attempt to remove a book from a patrons inventory
     *
     * @param patrons the array of patrons
     */
    public static void attemptReturn(Patron[] patrons) {
        //declare boolean of if the attempted return is a success
        boolean returnSuccess = false;
        //declare and initilize the patron ID
        int patronID = 0;
        boolean inputValid = false;//to ensure user provides a good id number
        while (inputValid == false) {
            //get the patrons id number
            String sPatronID = (JOptionPane.showInputDialog("Enter the id number of the patron"));
            //try to parse input
            try {
                patronID = Integer.parseInt(sPatronID);
                //if successful inputValid is true
                inputValid = true;
            } catch (NumberFormatException e) {
                //if the user entered bad input tell them
                JOptionPane.showMessageDialog(null, "Enter a valid id number", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        //get the title of the book to be signed out
        String bookTitle = JOptionPane.showInputDialog("Enter the title of the book");

        //use id number to find the patron object
        for (int i = 0; i < patrons.length; i++) {
            //use if statment to see if id number matches 
            if (patrons[i].getIDNum() == patronID) {
//all patrons will have unique id numbers so we can invoke method here
//invoke return book method from the patron class and store if it is a success in the return success variable
                returnSuccess = patrons[i].returnBook(bookTitle);
            }
        }

        //use if statment to determine what output to show user
        if (returnSuccess) {
            //if it was a success
            JOptionPane.showMessageDialog(null, "Patron number " + patronID + " successfuly returned " + bookTitle);
        } else {
            //if it was a faliure
            JOptionPane.showMessageDialog(null, "Patron number " + patronID + " does not have " + bookTitle + " signed out");
        }
    }

    /**
     * This method will add a patron to the array of patrons
     *
     * @param patrons doesn't work yet
     * @return we return the new array with the new patron
     */
    public static Patron[] addPatron(Patron[] patrons) {
//ask user information about the patron
        String patronName = JOptionPane.showInputDialog("Enter the new patrons name");
        int idNum = 0;//create a id number for patron
        boolean idNumUnique = false;
//make sure the id number is unique
        while (idNumUnique == false) {
            idNum = (int) (Math.random() * 899999) + 100000;//create  a new randome 6 digit number
            for (int i = 0; i < patrons.length; i++) {
                idNumUnique = true; //assume it is unique
                //use if statment to determine if the id number is unique
                if (patrons[i].getIDNum() == idNum) {
                    idNumUnique = false;//if there is a duplicate then it is not unique
                }
            }
        }
//tell user the id number for the new patron
        JOptionPane.showMessageDialog(null, "The id number for " + patronName + " is " + idNum);

//create new array including the new patron
        Patron[] newPatrons = new Patron[patrons.length + 1];
//loop through array set values equal to old array
        for (int i = 0; i < newPatrons.length; i++) {
            newPatrons[i] = patrons[i];

        }
//add the new patron to new array
        newPatrons[newPatrons.length] = new Patron(patronName, idNum);
//encourage new patron to borrow books
        JOptionPane.showMessageDialog(null, patronName + " is now a patron, start signing out books!");

        //return the new array with new patron
        return newPatrons;
    }

}
