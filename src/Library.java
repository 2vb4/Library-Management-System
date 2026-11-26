public class Library implements Searchable {
    // اضافه المتغيرات المطلوبه
    private Book[] books;
    private int numOfBooks;
    private Member[] members;
    private int numOfMembers;
    private Borrowing[] borrowings;
    private int numOfBorrowings;

    // عدد الاعضاء وعدد الكتب متغيرات لاجل تتبع العدد الفعلي لهم
    public Library(int capacity){
        this.books = new Book[capacity];
        this.numOfBooks = 0;
        this.members = new Member[capacity];
        this.numOfMembers = 0;
        this.borrowings = new Borrowing[capacity];
        this.numOfBorrowings = 0;
    }

    public boolean addBook(Book book) {
        // داله اضافه الكتب
        if (numOfBooks < books.length) {
            books[numOfBooks] = book;
            numOfBooks++;
            return true;
            // في الشرط قارنت عدد الكتب في اقصى سعه للكتب
        }
        return false;
    }

    public boolean addMember(Member member) {
        // داله اضافه العضو
        if (numOfMembers < members.length) {
            members[numOfMembers] = member;
            numOfMembers++;
            return true;
            // نجاح عملية الاضافه
        }
        return false;
        // فشل عملية الاضافه
    }

    @Override
    public Book searchBookByTitle(String title) {
        // داله البحث عن الكتب بالعنوان
        for (int i = 0; i < numOfBooks; i++) {
            if (books[i] != null && books[i].getTitle() != null && books[i].getTitle().equalsIgnoreCase(title)) {
                // استخدمت equalsIgnoreCase لتجاهل الفروق بين الاحرف الصغيره والكبيره
                return books[i];
            }
        }
        return null;
    }

    @Override
    public Book searchBookByISBN(String isbn) {
        // داله البحث عن الكتب باستخدام ISBN
        for (int i = 0; i < numOfBooks; i++) {
            if (books[i] != null && books[i].getIsbn() != null && books[i].getIsbn().equals(isbn)) {
                return books[i];
            }
        }
        return null;
    }

    // نسوي دوال البحث عن الاعضاء بنفس طريقه دوال البحث عن الكتب
    @Override
    public Member searchMemberByName(String name) {
        for (int i = 0; i < numOfMembers; i++) {
            if (members[i] != null && members[i].getName() != null && members[i].getName().equalsIgnoreCase(name)) {
                return members[i];
            }
        }
        return null;
    }

    @Override
    public Member searchMemberByID(String id) {
        for (int i = 0; i < numOfMembers; i++) {
            if (members[i] != null && members[i].getMemberId() != null && members[i].getMemberId().equals(id)) {
                return members[i];
            }
        }
        return null;
    }

    public boolean borrowBook(String isbn, String memberId) {
        Book book = searchBookByISBN(isbn);
        Member member = searchMemberByID(memberId);

        // نسوي شروط حمايه اذا الكتاب او العضو غير موجود نوقف الداله
        if (book == null) {
            System.out.println("Error: Book not found.");
            return false;
        }
        if (member == null) {
            System.out.println("Error: Member not found.");
            return false;
        }

        // نتاكد بان مصفوفه الاستعاره غير ممتلئه عن طريق المقارنه
        if (numOfBorrowings >= borrowings.length) {
            System.out.println("Error: Borrowing records array is full.");
            return false;
        }

        // نتاكد بوجود نسخه متاحه للاستعاره قبل تنفيذ العمليه
        if (!book.isAvailable() || book.getNumberOfCopies() <= 0) {
            System.out.println("Error: No available copies for this book.");
            return false;
        }

        // نستدعي داله الاستعاره من كلاس الكتاب لتقليل النسخ وتحديث الحاله تلقائيا
        book.borrow();

        String borrowingId = "BR" + (numOfBorrowings + 1);
        Borrowing record = new Borrowing(borrowingId, book, member);
        borrowings[numOfBorrowings] = record;
        numOfBorrowings++;
        System.out.println("Book borrowed successfully.");
        return true;
    }

    // داله ارجاع الكتاب
    public boolean returnBook(String isbn, String memberId) {
        // اولا نكتب حلقه تمر على سجلات الاستعاره
        for (int i = 0; i < numOfBorrowings; i++) {
            // نتاكد ان السجل موجود والكتاب ما رجع ونطابق رقم الكتاب ورقم العضو
            if (borrowings[i] != null
                    && !borrowings[i].isReturned()
                    && borrowings[i].getBook() != null
                    && borrowings[i].getBook().getIsbn() != null
                    && borrowings[i].getBook().getIsbn().equals(isbn)
                    && borrowings[i].getMember() != null
                    && borrowings[i].getMember().getMemberId() != null
                    && borrowings[i].getMember().getMemberId().equals(memberId)) {

                try {
                    // ننادي دالة الارجاع الجاهزة من كلاس Borrowing
                    borrowings[i].returnBook();
                    return true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        }
        System.out.println("Error: No active borrowing record found.");
        return false;
    }

    // داله حفظ البيانات عن طريق كلاس FileHandler
    public void saveToFile() {
        FileHandler.saveAll(this);
    }

    // داله قراءه البيانات عن طريق كلاس FileHandler
    public void loadFromFile() {
        FileHandler.loadAll(this);
    }

    // دوال القيتر للمصفوفات والعدادات
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
