package model;

public class MedicalProcedure implements Treatable {
    private int id;
    private String name;
    private double cost;

    public MedicalProcedure(int id, String name, double cost) {
        setId(id);
        setName(name);
        setCost(cost);
    }

    @Override
    public void performTreatment() {
        System.out.println("Performing procedure: " + name);
    }

    @Override
    public String getTreatmentDetails() {
        return "Procedure [ID: " + id + ", Name: " + name + ", Cost: $" + cost + "]";
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getCost() { return cost; }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Procedure ID must be positive!");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Procedure name cannot be empty!");
        }
        this.name = name.trim();
    }

    public void setCost(double cost) {
        if (cost <= 0) {
            throw new IllegalArgumentException("Procedure cost must be positive!");
        }
        this.cost = cost;
    }
}