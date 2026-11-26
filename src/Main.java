public class Main {
    public static void main(String[] args) {

        // 1. إنشاء المكتبة بسعة 50 عنصر
        Library library = new Library(50);

        try {
            // 2. تحميل البيانات المحفوظة من الملفات أول ما يشتغل البرنامج
            System.out.println("----- 1. Loading Data From Files -----");
            library.loadFromFile();

            // 3. إضافة كتب جديدة واختبار الـ RegEx على رقم ISBN
            System.out.println("\n----- 2. Adding Books & Testing RegEx -----");
            Book b1 = new Book("C101", "Java Programming", "Abdulaziz harbi", "9780134685991", 2);
            Book b2 = new Book("C102", "Java Advance Programming", "Abdullah Albluwe", "1234567890", 1);

            // تجربة رقم ISBN غلط (أقل من 10 أرقام) للتأكد إن الـ RegEx يرفضه
            Book invalidBook = new Book("C103", "Wrong ISBN Book", "Unknown", "123", 1);

            if (library.searchBookByISBN("9780134685991") == null) {
                library.addBook(b1);
            }
            if (library.searchBookByISBN("1234567890") == null) {
                library.addBook(b2);
            }

            // 4. إثبات الوراثة (Inheritance) وتعدد الأشكال (Polymorphism) بين Book و Magazine
            System.out.println("\n----- 3. Testing Inheritance & Polymorphism -----");
            LibraryItem item1 = b1;
            LibraryItem item2 = new Magazine(15, "November", "M201", "Tech Monthly", true);
            item1.displayInfo();
            System.out.println("--------------------");
            item2.displayInfo();

            // 5. تسجيل الأعضاء واختبار الـ RegEx على رقم العضو والإيميل
            System.out.println("\n----- 4. Registering Members & Testing RegEx -----");
            Member m1 = new Member("A001", "Abdulaziz", 19, "abz.harb2@gmail.com");
            Member m2 = new Member("A002", "Firas", 19, "firas@gmail.com");

            // تجربة بيانات غلط (رقم عضو بدون حرف + عمر أصغر من 12 + إيميل بدون @)
            Member invalidMember = new Member("99999", "Test", 10, "wrong-email");

            if (library.searchMemberByID("A001") == null) {
                library.addMember(m1);
            }
            if (library.searchMemberByID("A002") == null) {
                library.addMember(m2);
            }

            // 6. اختبار البحث بالاسم والعنوان ورقم ISBN ورقم العضوية
            System.out.println("\n----- 5. Searching Books & Members -----");
            Book foundBook = library.searchBookByTitle("Java Programming");
            if (foundBook != null) {
                System.out.println("Found Book: " + foundBook.getTitle() + " | ISBN: " + foundBook.getIsbn());
            }

            Member foundMember = library.searchMemberByName("Firas");
            if (foundMember != null) {
                System.out.println("Found Member: " + foundMember.getName() + " | ID: " + foundMember.getMemberId());
            }

            // 7. اختبار الاستعارة وعرض إجمالي الكتب المستعارة بالمتغير المشترك static
            System.out.println("\n----- 6. Borrowing Books & Static Counter -----");
            library.borrowBook("1234567890", "A001"); // استعارة صحيحة (تقلل النسخ من 1 إلى 0)
            System.out.println("Total Borrowed Books (Static): " + Borrowing.getTotalBorrowedBooks());

            // 8. اختبار معالجة أخطاء الاستعارة (كتاب خلصت نسخه + عضو غير موجود)
            System.out.println("\n----- 7. Testing Borrowing Error Handling -----");
            library.borrowBook("1234567890", "A002"); // يفشل لأن نسخ الكتاب صارت 0
            library.borrowBook("9780134685991", "A999"); // يفشل لأن العضو A999 غير موجود

            // 9. عرض الكتب المتوفرة والمستعارة والأعضاء المسجلين
            System.out.println("\n----- 8. Displaying Available Books, Borrowed Books & Members -----");
            System.out.println("--- Available Books ---");
            for (int i = 0; i < library.getNumOfBooks(); i++) {
                Book b = library.getBooks()[i];
                if (b != null && b.isAvailable()) {
                    b.displayInfo();
                    System.out.println("--------------------");
                }
            }

            System.out.println("--- Active Borrowed Books ---");
            for (int i = 0; i < library.getNumOfBorrowings(); i++) {
                Borrowing br = library.getBorrowings()[i];
                if (br != null && !br.isReturned()) {
                    br.displayInfo();
                    System.out.println("--------------------");
                }
            }

            System.out.println("--- Registered Members ---");
            for (int i = 0; i < library.getNumOfMembers(); i++) {
                Member m = library.getMembers()[i];
                if (m != null) {
                    m.displayInfo();
                    System.out.println("--------------------");
                }
            }

            // 10. ترجيع الكتاب وتحديث نسخه + اختبار خطأ الترجيع مرتين (throw / catch)
            System.out.println("\n----- 9. Returning Book & Exception Handling -----");
            library.returnBook("1234567890", "A001"); // ترجيع صحيح (ترجع النسخ من 0 إلى 1)
            library.returnBook("1234567890", "A001"); // محاولة ترجيع ثانية تطبع رسالة خطأ

            // اختبار الـ throw/catch المباشر من كلاس Borrowing عند الترجيع مرتين
            Borrowing directRecord = new Borrowing("BR-TEST", b1, m1);
            directRecord.returnBook(); // أول مرة ينجح
            directRecord.returnBook(); // ثاني مرة يرمي IllegalArgumentException ويمسكه catch تحت

        } catch (IllegalArgumentException e) {
            System.out.println("Caught Exception in Main: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());

        } finally {
            // 11. حفظ جميع البيانات في الملفات النصية في النهاية دائماً
            System.out.println("\n----- 10. Saving Data to Files -----");
            library.saveToFile();
            System.out.println("Program finished successfully.");
        }
    }
}