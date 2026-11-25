import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Library {
    // اضافه المتغيرات المطلوبه
    private Book[] books;
    private int numOfBooks;
    private Member[] members;
    private int numOfMembers;
    private Borrowing[] borrowings;
    private int numOfBorrowings;

    // عدد الاعضاء و عدد الكتب متغيرات لاجل تتبع العدد الفعلي لهم
public Library(int capacity){
this.books=new Book[capacity];
this.numOfBooks =0;
this.members =new Member[capacity];
this.numOfMembers =0;
this.borrowings =new Borrowing[capacity];
this.numOfBorrowings =0;
    }
public boolean addBook(Book book){
        // داله اضافه الكتب
if (numOfBooks < books.length){
books[numOfBooks] = book;
numOfBooks++;
return true;
// في الشرط قارنت عدد الكتب في اقصى سعه للكتب
        }
return false;
    }

public boolean addMember(Member member) {
        // داله اضافه العضو
if (numOfMembers < members.length){
members[numOfMembers] =member;
numOfMembers++;
return true;
            // نجاح عملية الاضافه
        }
return false;
        // فشل عملية الاضافه
    }

    // داله البحث عن الكتب
public Book searchBookByTitle(String title){
for (int i = 0; i < numOfBooks; i++) {
if (books[i] != null && books[i].getTitle() != null && books[i].getTitle().equalsIgnoreCase(title)){
     // استخدمت equalsIgnoreCase لتجاهل الفروق بين الاحرف الصغيره والكبيره
return books[i];
            }
        }
return null;
    }

public Book searchBookByISBN(String isbn){
     // داله البحث عن الكتب بستخدام ISBN
 for (int i = 0; i < numOfBooks;i++){
     if (books[i] != null && books[i].getIsbn() != null && books[i].getIsbn().equals(isbn)){
          return books[i];
            }
        }
        return null;
    }

    // نسوي دوال البحث عن الاعضاء بنفس طريقه دوال البحث عن الكتب
     public Member searchMemberByName(String name) {
     for (int i = 0; i < numOfMembers; i++) {
      if (members[i] != null && members[i].getName() != null && members[i].getName().equalsIgnoreCase(name)) {
        return members[i];
            }
        }
        return null;
    }
public Member searchMemberByID(String id){
for (int i = 0; i < numOfMembers; i++) {
if (members[i] != null && members[i].getMemberId() !=null && members[i].getMemberId().equals(id)) {
return members[i];
 }
}
return null;
    }

public boolean borrowBook(String isbn, String memberId){
Book book = searchBookByISBN(isbn);
Member member = searchMemberByID(memberId);
// نسوي شروط حمايه اذا الكتاب غير موجود نوقف الداله
if (book == null){
System.out.println("Error: Book not found.");
return false;
        }
if (member == null){
     System.out.println("Error: Member not found.");
           return false;
}
// نتاكد بان مصفوفه الاستعاره غير ممتلئه عن طريق المقارنه
if (numOfBorrowings >= borrowings.length){
  System.out.println("Error: Borrowing records array is full.");
      return false;
        }

 // ايضا نتاكد بوجود نسخه واحده على الاقل متاحه للاستعاره
// التاكد يكون من خلال القيتر من كلاس الكتاب
 if (book.getNumberOfCopies() <= 0){
      System.out.println("Error: No available copies for this book.");
           return false;
        }
// بعد ذلك ننقص عدد النسخه بواحد ومن ثم نقارنها بصفر
 // اذا كانت تساوي صفر نغير حاله الكتاب في كلاس الاب الى غير متاح
book.setNumberOfCopies(book.getNumberOfCopies() - 1);
if (book.getNumberOfCopies() == 0){
book.setAvailable(false);
        }

String borrowingId = "BR" + (numOfBorrowings + 1);
Borrowing record = new Borrowing(borrowingId, book, member);
borrowings[numOfBorrowings]= record;
 numOfBorrowings++;
  System.out.println("Book borrowed successfully.");
      return true;
    }

    // داله ارجاع الكتاب
    public boolean returnBook(String isbn, String memberId){
        // اولا نكتب حلقه تمر على سجلات الاستعاره
        for (int i = 0; i <numOfBorrowings;i++){
            // نتاكد ان السجل موجود والكتاب ما رجع ونطابق رقم الكتاب ورقم العضو
            if (borrowings[i] != null
                    && !borrowings[i].isReturned()
                    && borrowings[i].getBook() != null
                    && borrowings[i].getBook().getIsbn() != null
                    && borrowings[i].getBook().getIsbn().equals(isbn)
                    && borrowings[i].getMember() !=null
                    && borrowings[i].getMember().getMemberId() !=null
                    && borrowings[i].getMember().getMemberId().equals(memberId)){

                try {
                    // ننادي دالة الإرجاع الجاهزة من كلاس Borrowing
                    borrowings[i].returnBook();
                    return true;
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        }
 System.out.println("Error: No active borrowing record found.");
 return false;
    }

    // داله حفظ البيانات في ملف نصي
    public void saveToFile(){
        try {
            FileWriter writer = new FileWriter("library.txt");

            // حفظ الكتب في الملف
            for (int i = 0; i < numOfBooks; i++){
                if (books[i] != null){
                    writer.write("BOOK," + books[i].getItemId() + "," + books[i].getTitle() + ","
                            + books[i].getAuthor() + "," + books[i].getIsbn() + ","
                            + books[i].getNumberOfCopies() + "\n");
                }
            }

            // حفظ الاعضاء في الملف
            for (int i = 0; i < numOfMembers; i++){
                if (members[i] != null){
                    writer.write("MEMBER," + members[i].getMemberId() + "," + members[i].getName() + ","
                            + members[i].getAge() + "," + members[i].getEmail() + "\n");
                }
            }

            // حفظ سجلات الاستعاره في الملف
            for (int i = 0; i < numOfBorrowings; i++){
                if (borrowings[i] != null && borrowings[i].getBook() != null && borrowings[i].getMember() != null){
                    writer.write("BORROW," + borrowings[i].getBorrowingId() + ","
                            + borrowings[i].getBook().getIsbn() + ","
                            + borrowings[i].getMember().getMemberId() + ","
                            + borrowings[i].isReturned() + "\n");
                }
            }

            writer.close();
            System.out.println("Data saved to library.txt successfully.");
        } catch (IOException e){
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    // داله قراءه البيانات من الملف النصي
    public void loadFromFile(){
        try {
            File file = new File("library.txt");
            // اذا الملف غير موجود للحين نطلع من الداله
            if (!file.exists()){
                System.out.println("No saved file found yet.");
                return;
            }

            Scanner reader = new Scanner(file);
            //حلقه تستمر طالما فيه سطرجديد وترجع TRUE
            while (reader.hasNextLine()){
                String line = reader.nextLine();
                // نقسم السطر عند كل فاصله الى مصفوفه نصوص
                String[] t = line.split(",");

                if (t[0].equals("BOOK") && t.length == 6){
                    // نتاكد ان الكتاب مو مضاف قبل عشان ما يتكرر
                    if (searchBookByISBN(t[4]) == null){
                        int copies = Integer.parseInt(t[5]);
                        Book b = new Book(t[3], t[4], copies);
                        b.setItemId(t[1]);
                        b.setTitle(t[2]);
                        b.setAvailable(copies > 0);
                        addBook(b);
                    }
                } else if (t[0].equals("MEMBER") && t.length == 5){
                    // نتاكد ان العضو مو مضاف قبل عشان ما يتكرر
                    if (searchMemberByID(t[1]) == null){
                        int age = Integer.parseInt(t[3]);
                        Member m = new Member(t[1], t[2], age, t[4]);
                        addMember(m);
                    }
                } else if (t[0].equals("BORROW") && t.length == 5){
                    Book b = searchBookByISBN(t[2]);
                    Member m = searchMemberByID(t[3]);
                    boolean returned = Boolean.parseBoolean(t[4]);
                    if (b != null && m != null && numOfBorrowings < borrowings.length){
                        Borrowing br = new Borrowing(t[1], b, m);
                        br.setReturned(returned);
                        borrowings[numOfBorrowings] = br;
                        numOfBorrowings++;
                    }
                }
            }

            reader.close();
            System.out.println("Data loaded from library.txt successfully.");
        } catch (FileNotFoundException e){
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error loading data from file: " + e.getMessage());
        }
    }
// all the getter methods for the arrays and their counts >
public Book[] getBooks() {
        return books;
    }

    public int getNumOfBooks() {
        return numOfBooks;
    }

    public Member[] getMembers() {
        return members;
    }

    public int getNumOfMembers() {
        return numOfMembers;
    }

    public Borrowing[] getBorrowings() {
        return borrowings;
    }

    public int getNumOfBorrowings() {
        return numOfBorrowings;
    }

    public boolean addBorrowing(Borrowing borrowing) {
        if (numOfBorrowings < borrowings.length && borrowing != null) {
            borrowings[numOfBorrowings] = borrowing;
            numOfBorrowings++;
            return true;
        }
        return false;
    }

}
