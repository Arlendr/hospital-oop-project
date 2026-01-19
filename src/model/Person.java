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

    public abstract String getRole();
    public abstract void performDuties();

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

      public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive!");
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