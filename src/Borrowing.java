public class Borrowing {
    private String borrowingId;
    private Book book; //Aggregation
    private Member member; //Aggregation
    private boolean isReturned;
    private static int totalBorrowedBooks = 0; //يحسب إجمالي الكتب المستعارة في المكتبة

    //defult
    public Borrowing() {
        this.borrowingId = "";
        this.book = null;
        this.member = null;
        this.isReturned = false;
    }
    public Borrowing(String borrowingId, Book book, Member member) {
        this.borrowingId = borrowingId;
        this.book = book;
        this.member = member;
        this.isReturned = false;
        totalBorrowedBooks++; // add +1 to the tottal borrowed books
    }
    //setter and getter

    public String getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingId(String borrowingId) {
        this.borrowingId = borrowingId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }
    public static int getTotalBorrowedBooks() {//نستدعيها باسم الكلاس
        return totalBorrowedBooks;
    }
    public void returnBook() {
        // نتأكد أول إن الكتاب ما قد رجع من قبل
        if (!isReturned) {// نغير الحالة إلى تم الترجيع
            isReturned = true;
            // نزيد عدد نسخ الكتاب 1 باستخدام دوال كلاس Book
            book.setNumberOfCopies(book.getNumberOfCopies() + 1);
            // نغير الحالة إلى تم الترجيع
            book.setAvailable(true);
            System.out.println("Book returned successfully.");
        }
        else {
            System.out.println("This book is already returned.");
        }
    }

}
