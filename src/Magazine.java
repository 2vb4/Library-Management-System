    public class Magazine extends LibraryItem{
    private int issueNumber;
    private String publicationMonth;

    public Magazine() {
        this.issueNumber = 0;
        this.publicationMonth = "";
    }

    public Magazine(int issueNumber, String publicationMonth, String itemId, String title,boolean isAvailable){
        super( itemId, title, isAvailable);
        setIssueNumber(issueNumber);
        setPublicationMonth(publicationMonth);
    }

// ذي حذفتها وحطيت الي فوق افضل
    //public Magazine(int issueNumber, String publicationMonth) {
      //  this.issueNumber = issueNumber;
        //this.publicationMonth = publicationMonth;
    //}
    // get and set for magazine

    public int getIssueNumber() {
        return issueNumber;
    }

    //chick if the set issue number is bigger than 0

    public void setIssueNumber(int issueNumber) {
        if (issueNumber > 0) {
            this.issueNumber = issueNumber;
        } else {
            System.out.println(" Issue number must be bigger than 0.");
        }
    } //ذي عشان يضبط اللوب حق الاشهر
    public String getPublicationMonth() {
        return publicationMonth;
    }
    private static final String[] months ={
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"};


    public void setPublicationMonth(String publicationMonth) {
        for (String month : months) { // نفس اللوب العادي بس تعلمته وقلت اجربه.. اسرع من استخدام int i = 0
            if (month.equalsIgnoreCase(publicationMonth)) {
                this.publicationMonth = month;
                return;
            }
        }
        System.out.println("Invalid publication month...");
    }



    //احس كذا افضل بحيث ما تقبل الا اشهر محدده



@Override
    public void displayInfo(){
    System.out.println("Title: "+ getTitle());
    System.out.println("Issue: "+ getIssueNumber());
    System.out.println("Month of the publication: "+ getPublicationMonth());
  }
}