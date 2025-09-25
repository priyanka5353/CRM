package edu.ccrm.domain;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String regNo;
    private boolean active;
    private List<Enrollment> enrollments;

    public Student(int id, String fullName, String email, String regNo) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.active = true;
        this.enrollments = new ArrayList<>();
    }

    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }

    public boolean isActive() { return active; }
    public void deactivate() { this.active = false; }
    public void activate() { this.active = true; }


    public List<Enrollment> getEnrollments() { return enrollments; }

    // Enroll in a course with Grade enum
    public void enrollCourse(Course c, Grade grade) {
        Enrollment e = new Enrollment(this, c, grade);
        enrollments.add(e);
    }

    // 🔹 Calculate GPA
    public double calculateGPA() {
        if (enrollments.isEmpty()) return 0.0;

        int totalPoints = 0;
        int totalCourses = 0;

        for (Enrollment e : enrollments) {
            totalPoints += e.getGrade().getPoints();
            totalCourses++;
        }

        return (double) totalPoints / totalCourses;
    }

    // 🔹 Print Transcript
    public void printTranscript() {
        System.out.println("\nTranscript for " + fullName + " (" + regNo + ")");
        for (Enrollment e : enrollments) {
            System.out.println(" - " + e.getCourse().getCode() + " | "
                    + e.getCourse().getTitle() + " | Grade: " + e.getGrade());
        }
        System.out.printf("GPA: %.2f%n", calculateGPA());
    }

    @Override
    public void printProfile() {
        System.out.println("Student Profile:");
        System.out.println("RegNo: " + regNo + ", Name: " + fullName + ", Email: " + email);
        System.out.println("Status: " + (active ? "Active" : "Inactive"));
        System.out.println("Enrolled in " + enrollments.size() + " course(s):");
        for (Enrollment e : enrollments) {
            System.out.println("  - " + e);
        }
    }

    @Override
    public String toString() {
        return "Student{regNo='" + regNo + "', name='" + fullName + "', email='" + email +
                "', active=" + active + ", courses=" + enrollments + "}";
    }
}
