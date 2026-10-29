public abstract class LibraryItem {
// المتغيرات المشتركه
    private String itemId;
    private String title;
    private boolean isAvailable;

    public LibraryItem(){
        this.itemId = "";
        this.title ="";
        this.isAvailable = true;// Jehad please chick this one is it better to be true or false
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
    //غيرت الاسم هنا كان setAvailable بس ذا اوضح
    public boolean isAvailable(){
        return isAvailable;
    }

    //هنا فيه مشكله اذا كان عدد المتغير او الكتب صفر بيرجع true بالحالتين بس ممكن نحتاجه بعدين

    //ادري لازم يكون فوق الصفر في ملف الbook بس افرض الدكتور وصلها للصفر؟

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
   public abstract void displayInfo();

}