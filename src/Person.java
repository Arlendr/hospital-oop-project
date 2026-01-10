public class Person {
    protected int id;
    protected String name;
    protected int age;
    protected String contactInfo;

    public Person(int id, String name, int age, String contactInfo) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.contactInfo = contactInfo;
    }

    public Person() {
        this(0, "Unknown", 0, "No contact");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void work() {
        System.out.println(name + " is working");
    }

    public void introduce() {
        System.out.println("Hello, I am " + name);
    }

    public boolean isChild() {
        return age < 18;
    }

    public boolean isSenior() {
        return age >= 65;
    }

    @Override
    public String toString() {
        return "Person #" + id + ": " + name + " (Age: " + age + ", Contact: " + contactInfo + ")";
    }
}