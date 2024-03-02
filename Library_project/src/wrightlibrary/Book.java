/*Silas Wright
  November 30 2021

A new Book must have a title, author, and number of pages to exist
A Book’s title, author, and number of pages can be examined, but never modified
A new Patron must have a name and ID number to exist/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrightlibrary;

/**
 *
 * @author silas wright
 */
public class Book {
    //declare feilds 

    private String title;
    private String author;
    private int numPages;

    /**
     * This will be our only constructor
     *
     * @param title the title of book
     * @param author the author of book
     * @param numPages the number of pages in the book
     */
    public Book(String title, String author, int numPages) {
        //set global variables to the user input
        this.title = title;
        this.author = author;
        this.numPages = numPages;
    }

//accessors
    /**
     * This method will return the title of the book
     *
     * @return the title of the book as a string
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method will return the author of the book
     *
     * @return the author of the book as a string
     */
    public String getAuthor() {
        return author;
    }

    /**
     * This method will return the number of pages in a book
     *
     * @return the number of pages in the book as a string
     */
    public int getNumPages() {
        return numPages;
    }

    @Override
    /**
     * This method will return the book as a string
     *
     * @return the book as a string
     */
    public String toString() {

        String s = "Title:  " + title + "\nAuthor:  " + author + "\nPages:  " + numPages;
        return s;
    }
}
