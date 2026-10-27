public class LibraryItem {

    private String itemId;
    private String title;
    private boolean isAvailable;

    public LibraryItem(){
        this.itemId = "";
        this.title ="";
        this.isAvailable = true;// jehad please chick this one is it better to be true or false
    }
    public LibraryItem (String itemId, String title, boolean isAvailable){
        this.itemId = itemId;
        this.title = title;
        this.isAvailable = isAvailable;
    }
//get and set for Library
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public boolean getIsAvailable(){
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

