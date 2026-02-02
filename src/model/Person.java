package model;

public abstract class Person {
    private int id;
    private String name;
    private int age;

    public Person(int id, String name, int age) {
        setId(id);
        setName(name);
        setAge(age);
    }

    public Person(String name, int age) {
        this(0, name, age); // ID = 0 означает авто-генерацию
    }

    public abstract String getRole();
    public abstract void performDuties();

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative!");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().length() < 2) {
            throw new IllegalArgumentException("Name must be at least 2 characters!");
        }
        this.name = name.trim();
    }

    public void setAge(int age) {
        if (age <= 0 || age > 120) {
            throw new IllegalArgumentException("Age must be between 1 and 120!");
        }
        this.age = age;
    }

    @Override
    public String toString() {
        return getRole() + " [ID: " + id + ", Name: " + name + ", Age: " + age + "]";
    }
}