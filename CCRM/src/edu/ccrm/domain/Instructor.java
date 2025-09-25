package edu.ccrm.domain;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {
    private String department;
    private List<Course> coursesTaught;
    private boolean active;   // <-- NEW field to track active status

    public Instructor(int id, String fullName, String email, String department) {
        super(id, fullName, email);   // Call Person constructor
        this.department = department;
        this.coursesTaught = new ArrayList<>();
        this.active = true;  // By default, an instructor is active
    }

    // Getters and setters
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public List<Course> getCoursesTaught() { return coursesTaught; }

    // Assign course
    public void assignCourse(Course c) {
        coursesTaught.add(c);
    }

    // ------------------ Active/Inactive Handling ------------------
    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    // Override abstract method
    @Override
    public void printProfile() {
        System.out.println("Instructor Profile:");
        System.out.println("Name: " + fullName + ", Email: " + email);
        System.out.println("Department: " + department);
        System.out.println("Active: " + active);
        System.out.println("Teaching " + coursesTaught.size() + " course(s).");
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "name='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", active=" + active +
                ", createdAt=" + createdAt +
                '}';
    }
}
