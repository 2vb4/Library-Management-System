 public class Book extends LibraryItem {
     private String author;
     private String isbn;
     private int numberOfCopies;

     public Book() {
         this.author = "";
         this.isbn = "";
         this.numberOfCopies = 0;
     }

     public Book(String author, String isbn, int numberOfCopies) {
         setAuthor(author);
         setIsbn(isbn);
         setNumberOfCopies(numberOfCopies);
     }

     // get and set for book

     public String getAuthor() {
         return author;
     }

     public void setAuthor(String author) {
         this.author = author;
     }

     public String getIsbn() {
         return isbn;
     }

// تراك زدت { هنا يا عزوز

     public void setIsbn(String isbn) {
         // هنا نشيك على صيغة الـ ISBN: يقبل يا 10 أرقام أو 13 رقم بالضبط (زي "1234567890" أو "9781234567890")
         String isbnRegex = "^(\\d{10}|\\d{13})$";

         // نتأكد إن القيمة مو فاضية وتطابق شرط الـ RegEx
         if (isbn != null && isbn.matches(isbnRegex)) {
             this.isbn = isbn;
         } else {
             System.out.println("Invalid ISBN: must be 10 or 13 digits.");
         }
     }

     public int getNumberOfCopies() {
         return numberOfCopies;
     }

     //chick if the number of copies is bigger than 0
     public void setNumberOfCopies(int numberOfCopies) {
         if (numberOfCopies >= 0) {
             this.numberOfCopies = numberOfCopies;
         } else {
             System.out.println("Number of copies most be more  than 0 .");
         }
     }


     @Override
     public void displayInfo() {
         System.out.println("Title: " + getTitle());
         System.out.println("Author: " + author);
         System.out.println("ISBN: " + isbn);
         System.out.println("Copy number: " + numberOfCopies);

     }
 }