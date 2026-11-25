    public interface Searchable {
        Book searchBookByTitle(String title);
        Book searchBookByISBN(String isbn);
        Member searchMemberByName(String name);
        Member searchMemberByID(String id);
    }