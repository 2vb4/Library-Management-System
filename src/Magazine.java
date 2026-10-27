public class Magazine {
    private int issueNumber;
    private String publicationMonth;

    public Magazine() {
        this.issueNumber = 0;
        this.publicationMonth = "";
    }
    public Magazine(int issueNumber, String publicationMonth) {
        this.issueNumber = issueNumber;
        this.publicationMonth = publicationMonth;
    }
    // get and set for magazine

    public int getIssueNumber() {
        return issueNumber;
    }
    public void setIssueNumber(int issueNumber) {
        if (issueNumber > 0) {
            this.issueNumber = issueNumber;
        } else {
            System.out.println(" Issue number must be bigger than 0.");
        }
    }
    public String getPublicationMonth() {
        return publicationMonth;
    }
    // public void setPublicationMonth(String publicationMonth) {}
    //i dont know how to put getter and sitter in this one so please fix it



}