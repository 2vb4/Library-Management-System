public class Book {
    private String author;
    private String isbn;
    private int numberOfCopies;


public Book(){
    this.author = "";
    this.isbn = "";
    this.numberOfCopies = 0;

}

public Book(String author, String isbn, int numberOfCopies){
    this.author = author;
    this.isbn = isbn;
    this.numberOfCopies = numberOfCopies;
}
}
