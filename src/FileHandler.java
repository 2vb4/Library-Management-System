import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHandler {
    private static final String BOOKS_FILE = "books.txt";
    private static final String MEMBERS_FILE = "members.txt";
    private static final String BORROWINGS_FILE = "borrowings.txt";

    public static void saveAll(Library library) {
        saveBooks(library);
        saveMembers(library);
        saveBorrowings(library);
    }

    public static void loadAll(Library library) {
        loadBooks(library);
        loadMembers(library);
        loadBorrowings(library);
    }

    public static void saveBooks(Library library) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            Book[] books = library.getBooks();
            int count = library.getNumOfBooks();

            for (int i = 0; i < count; i++) {
                Book b = books[i];
                if (b != null) {
                    writer.println(
                        b.getItemId() + "|" +
                        b.getTitle() + "|" +
                        b.getAuthor() + "|" +
                        b.getIsbn() + "|" +
                        b.getNumberOfCopies()
                    );
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving books to file: " + e.getMessage());
        }
    }

    public static void loadBooks(Library library) {
        File file = new File(BOOKS_FILE);
        if (!file.exists()) {
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                try {
                    String[] parts = line.split("\\|");
                    if (parts.length == 5) {
                        String itemId = parts[0];
                        String title = parts[1];
                        String author = parts[2];
                        String isbn = parts[3];
                        int copies = Integer.parseInt(parts[4]);

                        if (library.searchBookByISBN(isbn) == null) {
                            Book book = new Book(itemId, title, author, isbn, copies);
                            library.addBook(book);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Skipping corrupted book record: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading books from file: " + e.getMessage());
        }
    }

    public static void saveMembers(Library library) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBERS_FILE))) {
            Member[] members = library.getMembers();
            int count = library.getNumOfMembers();

            for (int i = 0; i < count; i++) {
                Member m = members[i];
                if (m != null) {
                    writer.println(
                        m.getMemberId() + "|" +
                        m.getName() + "|" +
                        m.getAge() + "|" +
                        m.getEmail()
                    );
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving members to file: " + e.getMessage());
        }
    }

    public static void loadMembers(Library library) {
        File file = new File(MEMBERS_FILE);
        if (!file.exists()) {
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                try {
                    String[] parts = line.split("\\|");
                    if (parts.length == 4) {
                        String memberId = parts[0];
                        String name = parts[1];
                        int age = Integer.parseInt(parts[2]);
                        String email = parts[3];

                        if (library.searchMemberByID(memberId) == null) {
                            Member member = new Member(memberId, name, age, email);
                            library.addMember(member);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Skipping corrupted member record: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading members from file: " + e.getMessage());
        }
    }

    public static void saveBorrowings(Library library) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BORROWINGS_FILE))) {
            Borrowing[] borrowings = library.getBorrowings();
            int count = library.getNumOfBorrowings();

            for (int i = 0; i < count; i++) {
                Borrowing br = borrowings[i];
                if (br != null && br.getBook() != null && br.getMember() != null) {
                    writer.println(
                        br.getBorrowingId() + "|" +
                        br.getBook().getIsbn() + "|" +
                        br.getMember().getMemberId() + "|" +
                        br.isReturned()
                    );
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving borrowings to file: " + e.getMessage());
        }
    }

    public static void loadBorrowings(Library library) {
        File file = new File(BORROWINGS_FILE);
        if (!file.exists()) {
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                try {
                    String[] parts = line.split("\\|");
                    if (parts.length == 4) {
                        String borrowingId = parts[0];
                        String isbn = parts[1];
                        String memberId = parts[2];
                        boolean isReturned = Boolean.parseBoolean(parts[3]);

                        Book book = library.searchBookByISBN(isbn);
                        Member member = library.searchMemberByID(memberId);

                        if (book != null && member != null) {
                            Borrowing br = new Borrowing(borrowingId, book, member);
                            br.setReturned(isReturned);
                            library.addBorrowing(br);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Skipping corrupted borrowing record: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading borrowings from file: " + e.getMessage());
        }
    }
}