package librarymanagementsystem;

public class Book {

    private String title;
    private String author;
    private String ISBN;
    private int numberOfCopies;

    public Book(String title, String author, String ISBN, int numberOfCopies) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        setNumberOfCopies(numberOfCopies);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        if (numberOfCopies >= 0) {
            this.numberOfCopies = numberOfCopies;
        } else {
            throw new IllegalArgumentException("Invalid number of copies");
        }
    }
}
