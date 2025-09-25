package edu.ccrm.domain;

import java.io.Serializable;

public class Enrollment implements Serializable {
    private static final long serialVersionUID = 1L;  // for serialization

    private Student student;
    private Course course;
    private Grade grade;   // Changed from String to Grade

    public Enrollment(Student student, Course course, Grade grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public Grade getGrade() { return grade; }
    public void setGrade(Grade grade) { this.grade = grade; }

    @Override
    public String toString() {
        return course + " | Grade: " + grade;
    }
}
