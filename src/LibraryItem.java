public class LibraryItem {

    private String itemId;
    private String title;
    private boolean isAvailable;

    public LibraryItem(){
        this.itemId = "";
        this.title ="";
        this.isAvailable = true;
    }
    public LibraryItem (String itemId, String title, boolean isAvailable){
        this.itemId = itemId;
        this.title = title;
        this.isAvailable = isAvailable;
    }

}

