public class Member {
    private String  memberId;
    private String name;
    private int age;
    private String email;


public Member (){
    this.memberId= "";
    this.name = "";
    this.age = 0;
    this.email = "";
    }

public Member (String memberId, String name, int age, String email){
    this.memberId = memberId;
    this.name = name;
    this.age = age;
    this.email = email;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getAge() {
        return age;
    }
//chick if age is bigger or equal 12
    public void setAge(int age) {// maybe there was no need for age chick please change if u agree
        if(age >= 12){
            this.age = age;
        }
        else{
            System.out.println("you must be 12 at least ");
        }
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        //regex checking for: text + @ + domain + .extension
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (email != null && email.matches(emailRegex)) {
            this.email = email;
        } else {
            System.out.println("error Email must contain '@' and a domain name.");
        }
    }
}
