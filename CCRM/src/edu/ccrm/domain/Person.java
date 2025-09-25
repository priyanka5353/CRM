package edu.ccrm.domain;

import java.io.Serializable;
import java.time.LocalDate;

// Abstract class to demonstrate Abstraction & Inheritance
public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int id;              // unique id
    protected String fullName;
    protected String email;
    protected LocalDate createdAt; // using Date/Time API

    public Person(int id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = LocalDate.now();
    }

    // Abstract method to force subclasses to implement
    public abstract void printProfile();

    // Encapsulation with getters/setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "Person{id=" + id + ", name='" + fullName + "', email='" + email + "'}";
    }
}
