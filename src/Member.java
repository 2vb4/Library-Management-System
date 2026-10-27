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

}
