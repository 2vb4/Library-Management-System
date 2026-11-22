package librarymanagementsystem;

public class Member {

    private int memberID;
    private String name;
    private int age;

    public Member(int memberID, String name, int age) {
        this.memberID = memberID;
        this.name = name;
        setAge(age);
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 12) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Invalid age");
        }
    }
}
