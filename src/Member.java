public class Member {
    // اضافه المتغيرات المطلوبه لكلاس العضو
    private String memberId;
    private String name;
    private int age;
    private String email;

    // الكونستركتور الفاضي يحط قيم مبدئيه
    public Member() {
        this.memberId = "";
        this.name = "";
        this.age = 0;
        this.email = "";
    }

    // كونستركتور ياخذ بيانات العضو ويمررها لدوال السيتر عشان يتاكد من الشروط
    public Member(String memberId, String name, int age, String email) {
        setMemberId(memberId);
        setName(name);
        setAge(age);
        setEmail(email);
    }

    // دوال القيتر والسيتر للاسم
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // دوال القيتر والسيتر لرقم العضو
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        // نتاكد من صيغه رقم العضو لازم يبدا بحرف واحد وبعده من 3 الى 5 ارقام مثل M001
        String idRegex = "^[A-Za-z]\\d{3,5}$";

        // نتاكد ان القيمه مو فاضيه وتطابق الشرط
        if (memberId != null && memberId.matches(idRegex)) {
            this.memberId = memberId;
        } else {
            System.out.println("Invalid ID: must start with 1 letter followed by 3 to 5 digits.");
        }
    }

    // دوال القيتر والسيتر للعمر
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        // في الشرط نتاكد ان عمر العضو 12 سنه او اكبر
        if (age >= 12) {
            this.age = age;
        } else {
            System.out.println("Invalid age: member must be at least 12 years old.");
        }
    }

    // دوال القيتر والسيتر للايميل
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // نتاكد من صيغه الايميل باستخدام Regex انه فيه علامه @ واسم دومين صحيح
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (email != null && email.matches(emailRegex)) {
            this.email = email;
        } else {
            System.out.println("Error: Email must contain '@' and a domain name.");
        }
    }

    // داله طباعه بيانات العضو
    public void displayInfo() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
    }
}
