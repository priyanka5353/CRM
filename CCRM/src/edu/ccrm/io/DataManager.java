package edu.ccrm.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Instructor;

public class DataManager {

    // ---------------- STUDENTS ----------------
    public static void saveStudents(List<Student> students, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
            System.out.println("✅ Students saved successfully to " + filename);
        } catch (Exception e) {
            System.out.println("❌ Error saving students: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Student> loadStudents(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Student>) ois.readObject();
        } catch (Exception e) {
            System.out.println("⚠️ No saved students found. Starting fresh.");
            return new ArrayList<>();
        }
    }

    // ---------------- COURSES ----------------
    public static void saveCourses(List<Course> courses, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(courses);
            System.out.println("✅ Courses saved successfully to " + filename);
        } catch (Exception e) {
            System.out.println("❌ Error saving courses: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Course> loadCourses(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Course>) ois.readObject();
        } catch (Exception e) {
            System.out.println("⚠️ No saved courses found. Starting fresh.");
            return new ArrayList<>();
        }
    }

    // ---------------- ENROLLMENTS ----------------
    public static void saveEnrollments(List<Enrollment> enrollments, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(enrollments);
            System.out.println("✅ Enrollments saved successfully to " + filename);
        } catch (Exception e) {
            System.out.println("❌ Error saving enrollments: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Enrollment> loadEnrollments(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Enrollment>) ois.readObject();
        } catch (Exception e) {
            System.out.println("⚠️ No saved enrollments found. Starting fresh.");
            return new ArrayList<>();
        }
    }

    // ---------------- INSTRUCTORS (NEW) ----------------
    public static void saveInstructors(List<Instructor> instructors, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(instructors);
            System.out.println("✅ Instructors saved successfully to " + filename);
        } catch (Exception e) {
            System.out.println("❌ Error saving instructors: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Instructor> loadInstructors(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Instructor>) ois.readObject();
        } catch (Exception e) {
            System.out.println("⚠️ No saved instructors found. Starting fresh.");
            return new ArrayList<>();
        }
    }
}
