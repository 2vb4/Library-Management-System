public class Library {
     //اضافه المتغيرات المطلوبه
private Book[] books;
private int numOfBooks;
private Member[] members;
private int numOfMembers;
    //عدد الاعضاء و عدد الكتب متغيرات لاجل تتبع العدد الفعلي لهم
public Library(int capacity){
    this.books= new Book[capacity];
    this.numOfBooks =0;
    this.members= new Member[capacity];
    this.numOfMembers=0; 
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

}