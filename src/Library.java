public class Library {
     //اضافه المتغيرات المطلوبه
private Book[] books;
private int numOfBooks;
private Member[] members;
private int numOfMembers;
private Borrowing[] borrowings;
private int numOfBorrowings;
    //عدد الاعضاء و عدد الكتب متغيرات لاجل تتبع العدد الفعلي لهم
public Library(int capacity){
    this.books= new Book[capacity];
    this.numOfBooks =0;
    this.members= new Member[capacity];
    this.numOfMembers=0; 
    this.borrowings = new Borrowing[capacity];
    this.numOfBorrowings = 0;
}

public boolean addBook(Book book){
    // داله اضافه الكتب
if(numOfBooks < books.length) {
    books[numOfBooks]= book;
    numOfBooks++;
    return true;
    //في الشرط قارنت عدد الكتب في اقصى سعه للكتب
}
return false;
}
public boolean addMember(Member member){
    //داله اضافه العضو
if(numOfMembers < members.length) {
    members[numOfMembers]= member;
    numOfMembers++;
    return true;
    //نجاح عملية الاضافه
}
return false;
//فشل عملية الاضافه
}
//داله البحث عن الكتب
public Book searchBookByTitle(String title){
for (int i=0; i < numOfBooks; i++){ 
if(books[i] != null && books[i].getTitle().equalsIgnoreCase(title)){
    //استخدمت equalsIgnoreCase لتجاهل الفروق بين الاحرف الصغيره والكبيره 
    return books[i];
}   
}
return null;
}

public Book searchBookByISBN(String isbn){
    //داله البحث عن الكتب بستخدام ISBN 
    for(int i=0;i<numOfBooks;i++){
        if(books[i] != null && books[i].getIsbn().equals(isbn)){
            return books[i];
        }
    }
    return null;
}
//نسوي دوال البحث عن الاعضاء بنفس طريقه دوال البحث عن الكتب
public Member searchMemberByName(String name){
for (int i=0; i < numOfMembers; i++){ 
if(members[i] != null && members[i].getName().equalsIgnoreCase(name)){
    return members[i];
}  
}
return null;
}
public Member searchMemberByID(String id){
for(int i=0; i < numOfMembers; i++){
    if(members[i] != null && members[i].getMemberId().equals(id)){
        return members[i];
    }
}
return null;
}
public boolean borrowBook(String isbn, String memberId){
Book book = searchBookByISBN(isbn);
Member member = searchMemberByID(memberId);
//نسوي شروط حمايه اذا الكتاب غير موجود نوقف الداله
if (book == null) {
System.out.println("Error: Book not found.");
return false;
    } if (member == null) {
System.out.println("Error: Member not found.");
return false;
    }
//نتاكد بان مصفوفه الاستعاره غير ممتلئه عن طريق المقارنه
if (numOfBorrowings >= borrowings.length) {
System.out.println("Error: Borrowing records array is full.");
return false;
    }

//ايضا نتاكد بوجود نسخه واحده على الاقل متاحه للاستعاره
//التاكد يكون من خلال القيتر من كلاس الكتاب
if (book.getNumberOfCopies() <= 0) {
System.out.println("Error: No available copies for this book.");
return false;
    }
//بعد ذلك ننقص عدد النسخه بواحد ومن ثم نقارنها بصفر
//اذا كانت اقل من صفر نغير حاله الكتاب في كلاس الاب الى غير متاح
book.setNumberOfCopies(book.getNumberOfCopies() - 1);
if (book.getNumberOfCopies() == 0) {
book.setAvailable(false);
    }
Borrowing record = new Borrowing(book, member);
borrowings[numOfBorrowings] = record;
numOfBorrowings++;
System.out.println("Book borrowed successfully.");
return true;
}
// داله ارجاع الكتاب
public boolean returnBook(String isbn, String memberId) {
    // اولا نكتب حلقه تمر على سجلات الاستعاره 
for (int i = 0; i < numOfBorrowings; i++) {
    //يلزم تتحق جميع الشروط الاربعه ولاجل ذلك استخدمنا اند
if (borrowings[i] != null
&& !borrowings[i].isReturned() && borrowings[i].getBook().getIsbn().equals(isbn)
        && borrowings[i].getMember().getMemberId().equals(memberId)) {
borrowings[i].setReturned(true);

Book book = borrowings[i].getBook();
//نزيد عدد النسخ بمقدار واحد
book.setNumberOfCopies(book.getNumberOfCopies() + 1);
//بما ان الكتاب استرجع نجعله متاح
book.setAvailable(true);

System.out.println("Book returned successfully.");
return true;
        }
    }
System.out.println("Error: No active borrowing record found.");
return false;
}
}
