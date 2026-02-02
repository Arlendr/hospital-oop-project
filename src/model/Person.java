package model;

public abstract class Person {
    private int id;
    private String name;
    private int age;

    // Конструктор с ID
    public Person(int id, String name, int age) {
        setId(id);
        setName(name);
        setAge(age);
    }

    // Конструктор без ID (для авто-генерации)
    public Person(String name, int age) {
        this(0, name, age); // ID = 0 означает авто-генерацию
    }

    // Абстрактные методы
    public abstract String getRole();
    public abstract void performDuties();

    // Геттеры
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    // Сеттеры с валидацией
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