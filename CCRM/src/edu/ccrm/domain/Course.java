package edu.ccrm.domain;

import java.io.Serializable;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;  // for serialization

    private String code;
    private String title;
    private Semester semester;
    private boolean active;  // NEW field

    public Course(String code, String title, Semester semester) {
        this.code = code;
        this.title = title;
        this.semester = semester;
        this.active = true; // default active
    }

    // Getters
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public Semester getSemester() { return semester; }
    public boolean isActive() { return active; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setSemester(Semester semester) { this.semester = semester; }

    // Deactivate method
    public void deactivate() { this.active = false; }
    
    public void reactivate() { 
        this.active = true; 
    }

    @Override
    public String toString() {
        return code + " - " + title + " (" + semester + ")" +
               (active ? "" : " [Inactive]");
    }
}
