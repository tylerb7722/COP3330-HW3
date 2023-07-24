// Tyler Boudreau
// COP 3330 - Object Oriented Programming
// HW3
// 07/06/2023

import java.util.*;

public class HW3 {
 public static void main(String[] args) {
     System.out.println("\t\tWelcome to the book program!\n\n");
     Scanner sc = new Scanner(System.in);
     int program = 1;
     int i = 0;
     Book book;
     Book[] books = new Book[100];
     while(program == 1){
     System.out.println("Would you like to create a book object?\n");
     String choice = sc.nextLine().toLowerCase();
     if (choice.equals("yes")){
         System.out.print("Enter the author, title and the isbn of the book separated by '/'\n");
         String bookdetails = sc.nextLine();
         String strArr1[] = bookdetails.split("/");
         String author = strArr1[0], title = strArr1[1].toUpperCase(), isbn = strArr1[2];

         System.out.println("Got It!\nNow, tell me if it is a bookstore book or a library book (enter BB for bookstore book or LB for library book):\n");
         int inputbooktype = 0;
         while (inputbooktype == 0){
         String booktype = sc.nextLine().toUpperCase();
         if(booktype.equals("BB")){
             System.out.println("Got it!\nEnter the listed price of " + title + " by " + author+"\n");
             double price = sc.nextDouble();
             sc.nextLine();
             System.out.println("Is it on sale? (yes/no)\n");
             String isonSale = sc.nextLine().toLowerCase();
             if(isonSale.equals("yes")){
                 Boolean onSale = true;
                 System.out.println("Deduction Percentage?\n");
                 String inputv = sc.nextLine();
                 inputv = inputv.replaceAll("[^0-9]", "");
                 int saleRate = Integer.parseInt(inputv);
                 System.out.println("Got it!\n\nHere is your bookstore book information:");
                 book = new BookstoreBook(author,title,isbn,price,onSale,saleRate);
                 books[i] = book;
                 System.out.println(books[i]);
                 i +=1;
                 inputbooktype = 1;
             }
             else if (isonSale.equals("no")){
                 Boolean onSale = false;
                 Double saleRate = 0.0;
                 System.out.println("Got it!\n\nHere is your bookstore book information:");
                 book = new BookstoreBook(author,title,isbn,price,onSale,saleRate);
                 books[i] = book;
                 System.out.println(books[i]);
                 i +=1;
                 inputbooktype = 1;
             }
         }
         else if(booktype.equals("LB")){
             System.out.println("Whats the subject of the book?");
             int case1 = 1;
             while(case1==1){
             String subject = sc.nextLine().toUpperCase();
             LibraryBook lbook = new LibraryBook(author,title,isbn,subject);
             if (!lbook.getSubject().equals("false")){
                 book = new LibraryBook(author,title,isbn,subject);
                 books[i] = book;
                 System.out.println("Got it!\n\nHere is your library book information:\n");
                 System.out.println(books[i] + "\n");
                 i += 1;
                 case1 = 0;
                 inputbooktype = 1;
             }
             else {
                 System.out.println("Oops! That is not a valid entry. Please try again.");
             }}
         }
         else {
             System.out.println("Opps! That is not a valid entry. Please try again.");
         }
     }
 }
     else if(choice.equals("no")){
         int libbookcount = 0;
         int bookstorebookcount = 0;
         System.out.println("Sure!\n\nHere are all the books you entered...\n");
         for (int num=0;num<=i-1;num++){
             String booktype = books[num].getBookType();
             if (booktype.equals("Library Book")){
                 libbookcount +=1;
             }
             else if (booktype.equals("Bookstore Book")){
                 bookstorebookcount += 1;
             }
         }
         System.out.println("Library books (" + libbookcount + ")\n");
         for (int num=0;num<=i-1;num++){
             String booktype = books[num].getBookType();
             if (booktype.equals("Library Book")){
                 System.out.println("\t" + books[num] + "\n");
             }
         }
         System.out.println("----\nBookstore Books (" + bookstorebookcount + ")\n");
         for (int num=0;num<=i-1;num++){
             String booktype = books[num].getBookType();
             if (booktype.equals("Bookstore Book")){
                 System.out.println("\t" + books[num] + "\n");
             }
         }
         System.out.println("----\n");
         System.out.println("Would you like to search for a book?\n");
         String search = sc.nextLine();
         if (search.equals("yes")){
             System.out.println("Search by isbn, author, or title?\n");
             String searchby = sc.nextLine().toLowerCase();
             if (searchby.equals("author")){
                 System.out.println("Enter the first three letters of the author:\n");
                 String authorsearch = sc.nextLine().toLowerCase();
                 libbookcount = 0;
                 bookstorebookcount = 0;
                 for (int num=0;num<=i-1;num++){
                     if (books[num].getAuthor().toLowerCase().contains(authorsearch)){
                         if(books[num].getBookType().equals("Library Book")){
                             libbookcount +=1;
                         }
                         else if(books[num].getBookType().equals("Bookstore Book")){
                             bookstorebookcount +=1;
                         }
                     }
                 }
                 System.out.println("We found " + libbookcount + " Library Book(s) and " + bookstorebookcount + " Book Store Book(s):\n");
                 for (int num=0;num<=i-1;num++){
                     if (books[num].getAuthor().toLowerCase().contains(authorsearch)){
                         System.out.println(books[num]);
                     }
                 }
             }
             else if (searchby.equals("title")){
                 System.out.println("Enter the first three letters of the book title:\n");
                 String titlesearch = sc.nextLine().toUpperCase();
                 libbookcount = 0;
                 bookstorebookcount = 0;
                 for (int num=0;num<=i-1;num++){
                     if (books[num].getTitle().toUpperCase().contains(titlesearch)){
                         if(books[num].getBookType().equals("Library Book")){
                             libbookcount +=1;
                         }
                         else if(books[num].getBookType().equals("Bookstore Book")){
                             bookstorebookcount +=1;
                         }
                     }
                 }
                 System.out.println("We found " + libbookcount + " Library Book(s) and " + bookstorebookcount + " Book Store Book(s):\n");
                 for (int num=0;num<=i-1;num++){
                     if (books[num].getTitle().toUpperCase().contains(titlesearch)){
                         System.out.println(books[num]);
                     }
                 }
             }
             else if (searchby.equals("isbn")){
                 System.out.println("Enter the first three numbers of the book isbn:\n");
                 String isbnsearch = sc.nextLine();
                 libbookcount = 0;
                 bookstorebookcount = 0;
                 for (int num=0;num<=i-1;num++){
                     if (books[num].getIsbn().contains(isbnsearch)){
                         if(books[num].getBookType().equals("Library Book")){
                             libbookcount +=1;
                         }
                         else if(books[num].getBookType().equals("BookStore Book")){
                             bookstorebookcount +=1;
                         }
                     }
                 }
                 System.out.println("We found " + libbookcount + " Library Book(s) and " + bookstorebookcount + " Book Store Book(s):\n");
                 for (int num=0;num<=i-1;num++){
                     if (books[num].getIsbn().contains(isbnsearch)){
                         System.out.println(books[num]);
                     }
                 }
             }
         }
         program = 0;
     }
     else {
         System.out.println("Sorry! that's not a valid entry. Please try again.\n");
     }

     }
     System.out.println("Goodbye!");
 }  
}

abstract class Book {
 private String author;
 private String title;
 private String isbn;

 public Book(String author, String title, String isbn){
     this.author = author;
     this.title = title;
     this.isbn = isbn;
 }

 public String getAuthor(){
     return author;
 }

 public String getTitle(){
     return title;
 }

 public String getIsbn(){
     return isbn;
 }

 public abstract String getBookType();
 
 public String toString(){
     return "[" + this.isbn + "-" + this.title + " by " + this.author;
 }
}

class BookstoreBook extends Book {
 private double price;
 private boolean onSale;
 private double saleRate;
 private double saleprice;

 public BookstoreBook(String author, String title,String isbn, double price, boolean onSale, double saleRate){
     super(author,title,isbn);
     this.price = price;
     this.onSale = onSale;
     this.saleRate = saleRate;
     if (this.onSale == true){
         saleprice = (this.price)-(this.price*(this.saleRate/100));
     }
 }

 public double getPrice(){
     return price;
 }

 public boolean getOnSale(){
     return onSale;
 }

 public double getSaleRate(){
     return saleRate;
 }
 
 @Override
 public String getBookType(){
     return "Bookstore Book";
 }

 @Override
 public String toString(){
     if(this.onSale == false){
         return super.toString() + " $" + this.price + "\n";
     }
     else {
         return super.toString() + " $" + this.price + " listed for $" + saleprice + "]\n";
     }
 }
}

class LibraryBook extends Book{
 private String subject;
 private String callNumber;

 public LibraryBook(String author, String title, String isbn, String subject){
     super(author,title,isbn);
     if(subject.equals("GENERAL")){
     this.subject = "A";
     }
     else if(subject.equals("PHILOSOPHY")){
     this.subject = "B";
     }
     else if(subject.equals("RELIGION")){
     this.subject = "C";
     }
     else if(subject.equals("WORLD HISTORY")){
     this.subject = "D";
     }
     else if(subject.equals("HISTORY OF THE AMERICAS")){
     this.subject = "E";
     }
     else if(subject.equals("GEOGRAPHY")){
     this.subject = "F";
     }
     else if(subject.equals("ANTHROPOLOGY")){
     this.subject = "G";
     }
     else if(subject.equals("SOCIAL SCIENCES")){
     this.subject = "H";
     }
     else if(subject.equals("INTERNET")){
     this.subject = "I";
     }
     else if(subject.equals("POLITICAL SCIENCE")){
     this.subject = "J";
     }
     else if(subject.equals("LAW")){
     this.subject = "K";
     }
     else if(subject.equals("EDUCATION")){
     this.subject = "L";
     }
     else if(subject.equals("MUSIC")){
     this.subject = "M";
     }
     else if(subject.equals("FINE ARTS")){
     this.subject = "N";
     }
     else if(subject.equals("LANGUAGE")){
     this.subject = "P";
     }
     else if(subject.equals("SCIENCE")){
     this.subject = "Q";
     }
     else if(subject.equals("MEDICINE")){
     this.subject = "R";
     }
     else if(subject.equals("AGRICULTURE")){
     subject = "S";
     }
     else if(subject.equals("TECHNOLOGY")){
     this.subject = "T";
     }
     else if(subject.equals("MILITARY")){
     this.subject = "U";
     }
     else {
         this.subject = "false";
     }
     String author3char = author.substring(0, 3);
     Random rand = new Random();
     int floornum = rand.nextInt(1,15);
     String floorstr = String.format("%02d", floornum);
     String isbnC = isbn.substring(isbn.length()-1).toUpperCase();
     callNumber = this.subject + "." + floorstr + "." + author3char + "." + isbnC;
 }

 public String getSubject(){
     return this.subject;
 }

 @Override
 public String getBookType(){
     return "Library Book";
 }
 @Override
 public String toString(){
     return super.toString() + "-" + callNumber + "]\n";
 }
}
