public class Book extends LibraryItem {
    // اضافه المتغيرات المطلوبه لكلاس الكتاب
    private String author;
    private String isbn;
    private int numberOfCopies;

    // الكونستركتور الفاضي يحط قيم مبدئيه
    public Book() {
        super();
        this.author = "";
        this.isbn = "";
        this.numberOfCopies = 0;
        setAvailable(false);
    }

    // كونستركتور ياخذ المؤلف والرقم وعدد النسخ
    public Book(String author, String isbn, int numberOfCopies) {
        super();
        setAuthor(author);
        setIsbn(isbn);
        setNumberOfCopies(numberOfCopies);
    }

    // كونستركتور كامل ياخذ كل البيانات ويمرر رقم الكتاب والعنوان لكلاس الاب عن طريق super
    public Book(String itemId, String title, String author, String isbn, int numberOfCopies) {
        super(itemId, title, numberOfCopies > 0);
        setAuthor(author);
        setIsbn(isbn);
        setNumberOfCopies(numberOfCopies);
    }

    // دوال القيتر والسيتر للمؤلف
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // دوال القيتر والسيتر لرقم ISBN
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        // نتاكد من صيغه ISBN باستخدام Regex لازم يكون 10 او 13 رقم فقط
        String isbnRegex = "^(\\d{10}|\\d{13})$";

        // نتاكد ان القيمه مو فاضيه وتطابق الشرط
        if (isbn != null && isbn.matches(isbnRegex)) {
            this.isbn = isbn;
        } else {
            System.out.println("Invalid ISBN: must be 10 or 13 digits.");
        }
    }

    // دوال القيتر والسيتر لعدد النسخ
    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        // في الشرط نتاكد ان عدد النسخ صفر او اكثر عشان ما يصير بالسالب
        if (numberOfCopies >= 0) {
            this.numberOfCopies = numberOfCopies;
            // نحدث حاله الكتاب في كلاس الاب اذا النسخ اكبر من صفر يكون متاح واذا صفر غير متاح
            setAvailable(numberOfCopies > 0);
        } else {
            System.out.println("Number of copies must be 0 or more.");
        }
    }

    // داله طباعه بيانات الكتاب كامله
    @Override
    public void displayInfo() {
        System.out.println("Item ID: " + getItemId());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Number of Copies: " + numberOfCopies);
        System.out.println("Available: " + isAvailable());
    }
}
