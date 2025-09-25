package edu.ccrm.cli;

import java.util.Scanner;
import edu.ccrm.config.AppConfig;

public class Main {
    public static void main(String[] args) {
        // Load configuration (Singleton)
        AppConfig config = AppConfig.getInstance();
        System.out.println("Welcome to Campus Course & Records Manager (CCRM)");
        System.out.println("Data folder path: " + config.getDataPath());

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Global lists to hold data during program execution
        java.util.List<edu.ccrm.domain.Student> allStudents = new java.util.ArrayList<>();
        java.util.List<edu.ccrm.domain.Course> allCourses = new java.util.ArrayList<>();
        java.util.List<edu.ccrm.domain.Enrollment> allEnrollments = new java.util.ArrayList<>();
        java.util.List<edu.ccrm.domain.Instructor> allInstructors = new java.util.ArrayList<>();
        
        // Load previously saved data at startup
        allStudents = edu.ccrm.io.DataManager.loadStudents("students.dat");
        allCourses = edu.ccrm.io.DataManager.loadCourses("courses.dat");
        allEnrollments = edu.ccrm.io.DataManager.loadEnrollments("enrollments.dat");
        allInstructors = edu.ccrm.io.DataManager.loadInstructors("instructors.dat");

        System.out.println("✅ Previous data loaded from files (if available).");

        while (running) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Enrollment & Grades");
            System.out.println("4. Import/Export Data");
            System.out.println("5. Print Transcript");
            System.out.println("6. Backup & Reports");
            System.out.println("7. Manage Instructors");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {

         // ************************ CASE 1: STUDENTS ************************
            case 1 -> {
                boolean studentMenu = true;
                while (studentMenu) {
                    System.out.println("\n--- Student Management ---");
                    System.out.println("1. Add Student");
                    System.out.println("2. List Students");
                    System.out.println("3. Update Student");
                    System.out.println("4. Deactivate Student");
                    System.out.println("5. Reactivate Student");
                    System.out.println("6. Back to Main Menu");
                    System.out.print("Enter choice: ");

                    int stuChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    switch (stuChoice) {
                        case 1 -> {
                            System.out.print("Enter Student ID: ");
                            int sid = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter Full Name: ");
                            String sname = scanner.nextLine();

                            System.out.print("Enter Email: ");
                            String semail = scanner.nextLine();

                            System.out.print("Enter RegNo: ");
                            String sreg = scanner.nextLine();

                            edu.ccrm.domain.Student s1 =
                                    new edu.ccrm.domain.Student(sid, sname, semail, sreg);
                            allStudents.add(s1);
                            System.out.println("✅ Student added successfully!");
                        }
                        case 2 -> {
                            if (allStudents.isEmpty()) {
                                System.out.println("No students found.");
                            } else {
                                System.out.println("\n--- All Students ---");
                                for (edu.ccrm.domain.Student s : allStudents) {
                                    System.out.println(s);
                                }
                            }
                        }
                        case 3 -> {
                            if (allStudents.isEmpty()) {
                                System.out.println("No students to update.");
                                break;
                            }
                            System.out.println("Select student to update:");
                            for (int i = 0; i < allStudents.size(); i++) {
                                System.out.println((i + 1) + ". " + allStudents.get(i).getFullName());
                            }
                            int idx = scanner.nextInt();
                            scanner.nextLine();

                            edu.ccrm.domain.Student st = allStudents.get(idx - 1);

                            System.out.print("Enter new name (leave blank to keep same): ");
                            String newName = scanner.nextLine();
                            if (!newName.isBlank()) st.setFullName(newName);

                            System.out.print("Enter new email (leave blank to keep same): ");
                            String newEmail = scanner.nextLine();
                            if (!newEmail.isBlank()) st.setEmail(newEmail);

                            System.out.println("✅ Student updated.");
                        }
                        case 4 -> {
                            if (allStudents.isEmpty()) {
                                System.out.println("No students to deactivate.");
                                break;
                            }
                            System.out.println("Select student to deactivate:");
                            for (int i = 0; i < allStudents.size(); i++) {
                                System.out.println((i + 1) + ". " + allStudents.get(i).getFullName());
                            }
                            int idx = scanner.nextInt();
                            scanner.nextLine();

                            edu.ccrm.domain.Student st = allStudents.get(idx - 1);
                            st.deactivate(); // ✅ requires Student class to have this
                            System.out.println("⚠️ Student deactivated: " + st.getFullName());
                        }
                        case 5 -> {
                            java.util.List<edu.ccrm.domain.Student> inactiveStudents = new java.util.ArrayList<>();
                            for (edu.ccrm.domain.Student s : allStudents) {
                                if (!s.isActive()) inactiveStudents.add(s); // ✅ requires Student class to have isActive()
                            }
                            if (inactiveStudents.isEmpty()) {
                                System.out.println("No inactive students to reactivate.");
                                break;
                            }
                            System.out.println("Select student to reactivate:");
                            for (int i = 0; i < inactiveStudents.size(); i++) {
                                System.out.println((i + 1) + ". " + inactiveStudents.get(i).getFullName());
                            }
                            int idx = scanner.nextInt();
                            scanner.nextLine();
                            edu.ccrm.domain.Student st = inactiveStudents.get(idx - 1);
                            st.activate(); // ✅ requires Student class to have this
                            System.out.println("✅ Student reactivated: " + st.getFullName());
                        }
                        case 6 -> studentMenu = false;
                        default -> System.out.println("Invalid choice. Try again.");
                    }
                }
            }


                // ************************ CASE 2: COURSES ************************
                case 2 -> {
                    boolean courseMenu = true;
                    while (courseMenu) {
                        System.out.println("\n--- Course Management ---");
                        System.out.println("1. Add Course");
                        System.out.println("2. List Courses");
                        System.out.println("3. Update Course");
                        System.out.println("4. Deactivate Course");
                        System.out.println("5. Reactivate Course");
                        System.out.println("6. Back to Main Menu");
                        System.out.print("Enter choice: ");

                        int courseChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (courseChoice) {
                            case 1 -> {
                                System.out.print("Enter Course Code: ");
                                String ccode = scanner.nextLine();

                                System.out.print("Enter Course Title: ");
                                String ctitle = scanner.nextLine();

                                System.out.println("Select Semester: 1. SPRING  2. SUMMER  3. FALL");
                                int semChoice = scanner.nextInt();
                                scanner.nextLine();

                                edu.ccrm.domain.Semester sem;
                                switch (semChoice) {
                                    case 1 -> sem = edu.ccrm.domain.Semester.SPRING;
                                    case 2 -> sem = edu.ccrm.domain.Semester.SUMMER;
                                    case 3 -> sem = edu.ccrm.domain.Semester.FALL;
                                    default -> {
                                        System.out.println("Invalid semester, defaulting to SPRING");
                                        sem = edu.ccrm.domain.Semester.SPRING;
                                    }
                                }

                                edu.ccrm.domain.Course c1 = new edu.ccrm.domain.Course(ccode, ctitle, sem);
                                allCourses.add(c1);
                                System.out.println("✅ Course added successfully!");
                            }
                            case 2 -> {
                                if (allCourses.isEmpty()) {
                                    System.out.println("No courses found.");
                                } else {
                                    System.out.println("\n--- All Courses ---");
                                    for (edu.ccrm.domain.Course c : allCourses) {
                                        System.out.println(c);
                                    }
                                }
                            }
                            case 3 -> {
                                if (allCourses.isEmpty()) {
                                    System.out.println("No courses to update.");
                                    break;
                                }
                                System.out.println("Select course to update:");
                                for (int i = 0; i < allCourses.size(); i++) {
                                    System.out.println((i + 1) + ". " + allCourses.get(i).getTitle());
                                }
                                int idx = scanner.nextInt();
                                scanner.nextLine();

                                edu.ccrm.domain.Course course = allCourses.get(idx - 1);
                                System.out.print("Enter new title (leave blank to keep same): ");
                                String newTitle = scanner.nextLine();
                                if (!newTitle.isBlank()) course.setTitle(newTitle);

                                System.out.println("✅ Course updated.");
                            }
                            case 4 -> {
                                if (allCourses.isEmpty()) {
                                    System.out.println("No courses to deactivate.");
                                    break;
                                }
                                System.out.println("Select course to deactivate:");
                                for (int i = 0; i < allCourses.size(); i++) {
                                    System.out.println((i + 1) + ". " + allCourses.get(i).getTitle());
                                }
                                int idx = scanner.nextInt();
                                scanner.nextLine();

                                edu.ccrm.domain.Course course = allCourses.get(idx - 1);
                                course.deactivate();
                                System.out.println("⚠️ Course deactivated: " + course.getTitle());
                            }
                            case 5 -> {
                                java.util.List<edu.ccrm.domain.Course> inactiveCourses = new java.util.ArrayList<>();
                                for (edu.ccrm.domain.Course c : allCourses) {
                                    if (!c.isActive()) inactiveCourses.add(c);
                                }
                                if (inactiveCourses.isEmpty()) {
                                    System.out.println("No inactive courses to reactivate.");
                                    break;
                                }
                                System.out.println("Select course to reactivate:");
                                for (int i = 0; i < inactiveCourses.size(); i++) {
                                    System.out.println((i + 1) + ". " + inactiveCourses.get(i).getTitle());
                                }
                                int idx = scanner.nextInt();
                                scanner.nextLine();
                                edu.ccrm.domain.Course course = inactiveCourses.get(idx - 1);
                                course.reactivate();
                                System.out.println("✅ Course reactivated: " + course.getTitle());
                            }
                            case 6 -> courseMenu = false;
                            default -> System.out.println("Invalid choice. Try again.");
                        }
                    }
                }

             // ************************ CASE 3: ENROLLMENT ************************
                case 3 -> {
                    System.out.println("Enrollment & Grades selected.");

                    if (allStudents.isEmpty()) {
                        System.out.println("No students available. Please add students first.");
                        break;
                    }

                    // Step 1: Select student
                    System.out.println("Available Students:");
                    for (int i = 0; i < allStudents.size(); i++) {
                        System.out.println((i + 1) + ". " + allStudents.get(i).getFullName()
                                + " (" + allStudents.get(i).getRegNo() + ")");
                    }
                    System.out.print("Select Student (number): ");
                    int stuIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (stuIndex < 1 || stuIndex > allStudents.size()) {
                        System.out.println("❌ Invalid student selection.");
                        break;
                    }
                    edu.ccrm.domain.Student selectedStudent = allStudents.get(stuIndex - 1);

                    // Step 2: Select course
                    java.util.List<edu.ccrm.domain.Course> activeCourses = new java.util.ArrayList<>();
                    for (edu.ccrm.domain.Course c : allCourses) {
                        if (c.isActive()) activeCourses.add(c);
                    }
                    if (activeCourses.isEmpty()) {
                        System.out.println("No active courses available.");
                        break;
                    }

                    System.out.println("Available Active Courses:");
                    for (int i = 0; i < activeCourses.size(); i++) {
                        System.out.println((i + 1) + ". " + activeCourses.get(i));
                    }
                    System.out.print("Select Course (number): ");
                    int courseIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (courseIndex < 1 || courseIndex > activeCourses.size()) {
                        System.out.println("❌ Invalid course selection.");
                        break;
                    }
                    edu.ccrm.domain.Course selectedCourse = activeCourses.get(courseIndex - 1);

                    // Step 3: Enter grade
                    System.out.print("Enter Grade (S, A, B, C, D, F): ");
                    String gradeInput = scanner.nextLine().trim();
                    edu.ccrm.domain.Grade grade;
                    try {
                        grade = edu.ccrm.domain.Grade.fromInput(gradeInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ Invalid grade entered.");
                        break;
                    }

                    // Step 4: Enroll student
                    selectedStudent.enrollCourse(selectedCourse, grade);
                    allEnrollments.add(new edu.ccrm.domain.Enrollment(selectedStudent, selectedCourse, grade));

                    // Step 5: Confirmation
                    System.out.println("\n✅ Enrollment completed!");
                    selectedStudent.printProfile();
                }


                // ************************ CASE 4: IMPORT/EXPORT ************************
                case 4 -> {
                    System.out.println("Import/Export selected.");

                    // --- Save Students to file ---
                    edu.ccrm.io.DataManager.saveStudents(allStudents, "students.dat");
                    System.out.println("Students saved successfully to students.dat");

                    // --- Save Courses to file ---
                    edu.ccrm.io.DataManager.saveCourses(allCourses, "courses.dat");
                    System.out.println("Courses saved successfully to courses.dat");

                    // --- Save Enrollments to file ---
                    edu.ccrm.io.DataManager.saveEnrollments(allEnrollments, "enrollments.dat");
                    System.out.println("Enrollments saved successfully to enrollments.dat");

                    // --- Save Instructors to file ---
                    edu.ccrm.io.DataManager.saveInstructors(allInstructors, "instructors.dat");
                    System.out.println("Instructors saved successfully to instructors.dat");

                    // --- Load Students back from file ---
                    java.util.List<edu.ccrm.domain.Student> loadedStudents =
                            edu.ccrm.io.DataManager.loadStudents("students.dat");
                    System.out.println("\nLoaded Students from file:");
                    for (edu.ccrm.domain.Student s : loadedStudents) {
                        s.printProfile();
                    }
                    allStudents = loadedStudents;

                    // --- Load Courses back from file ---
                    java.util.List<edu.ccrm.domain.Course> loadedCourses =
                            edu.ccrm.io.DataManager.loadCourses("courses.dat");
                    System.out.println("\nLoaded Courses from file:");
                    for (edu.ccrm.domain.Course c : loadedCourses) {
                        System.out.println(c);
                    }
                    allCourses = loadedCourses;

                    // --- Load Enrollments back from file ---
                    java.util.List<edu.ccrm.domain.Enrollment> loadedEnrollments =
                            edu.ccrm.io.DataManager.loadEnrollments("enrollments.dat");
                    System.out.println("\nLoaded Enrollments from file:");
                    for (edu.ccrm.domain.Enrollment e : loadedEnrollments) {
                        System.out.println(e);
                    }
                    allEnrollments = loadedEnrollments;

                    // --- Load Instructors back from file ---
                    java.util.List<edu.ccrm.domain.Instructor> loadedInstructors =
                            edu.ccrm.io.DataManager.loadInstructors("instructors.dat");
                    System.out.println("\nLoaded Instructors from file:");
                    for (edu.ccrm.domain.Instructor i : loadedInstructors) {
                        System.out.println(i);
                    }
                    allInstructors = loadedInstructors;
                }

             // ************************ CASE 5: TRANSCRIPT ************************
                case 5 -> {
                    if (allStudents.isEmpty()) {
                        System.out.println("No students available.");
                        break;
                    }

                    System.out.println("Select Student to Print Transcript:");
                    for (int i = 0; i < allStudents.size(); i++) {
                        System.out.println((i + 1) + ". " + allStudents.get(i).getFullName());
                    }

                    System.out.print("Enter choice: ");
                    int idx = scanner.nextInt();
                    scanner.nextLine();

                    if (idx < 1 || idx > allStudents.size()) {
                        System.out.println("❌ Invalid selection. Please try again.");
                        break;
                    }

                    edu.ccrm.domain.Student selectedStudent = allStudents.get(idx - 1);

                    if (selectedStudent.getEnrollments().isEmpty()) {
                        System.out.println("⚠️ This student has no enrollments yet.");
                    } else {
                        selectedStudent.printTranscript();
                    }
                }


             // ************************ CASE 6: BACKUP & REPORTS ************************
                case 6 -> {
                    System.out.println("\n--- Backup & Reports ---");
                    System.out.println("1. Backup Students Data");
                    System.out.println("2. Backup Courses Data");
                    System.out.println("3. Backup Enrollments Data");
                    System.out.println("4. Backup Instructors Data");
                    System.out.println("5. Restore Students Data");
                    System.out.println("6. Restore Courses Data");
                    System.out.println("7. Restore Enrollments Data");
                    System.out.println("8. Restore Instructors Data");
                    System.out.println("9. Back to Main Menu");
                    System.out.print("Enter choice: ");

                    int backupChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (backupChoice) {
                        case 1 -> {
                            edu.ccrm.io.BackupService.backupFile("students.dat");
                            System.out.println("✅ Students backup completed.");
                        }
                        case 2 -> {
                            edu.ccrm.io.BackupService.backupFile("courses.dat");
                            System.out.println("✅ Courses backup completed.");
                        }
                        case 3 -> {
                            edu.ccrm.io.BackupService.backupFile("enrollments.dat");
                            System.out.println("✅ Enrollments backup completed.");
                        }
                        case 4 -> {
                            edu.ccrm.io.BackupService.backupFile("instructors.dat");
                            System.out.println("✅ Instructors backup completed.");
                        }
                        case 5 -> {
                            edu.ccrm.io.BackupService.restoreLatest("students.dat");
                            System.out.println("♻️ Students data restored (latest backup).");
                        }
                        case 6 -> {
                            edu.ccrm.io.BackupService.restoreLatest("courses.dat");
                            System.out.println("♻️ Courses data restored (latest backup).");
                        }
                        case 7 -> {
                            edu.ccrm.io.BackupService.restoreLatest("enrollments.dat");
                            System.out.println("♻️ Enrollments data restored (latest backup).");
                        }
                        case 8 -> {
                            edu.ccrm.io.BackupService.restoreLatest("instructors.dat");
                            System.out.println("♻️ Instructors data restored (latest backup).");
                        }
                        case 9 -> System.out.println("Returning to Main Menu...");
                        default -> System.out.println("❌ Invalid choice.");
                    }
                }


             // ************************ CASE 7: INSTRUCTOR MANAGEMENT ************************
                case 7 -> {
                    boolean instructorMenu = true;
                    while (instructorMenu) {
                        System.out.println("\n--- Instructor Management ---");
                        System.out.println("1. Add Instructor");
                        System.out.println("2. List Instructors");
                        System.out.println("3. Update Instructor");
                        System.out.println("4. Deactivate Instructor");
                        System.out.println("5. Reactivate Instructor");
                        System.out.println("6. Back to Main Menu");
                        System.out.print("Enter choice: ");

                        int instChoice = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        switch (instChoice) {
                            case 1 -> {
                                System.out.print("Enter Instructor ID: ");
                                int iid = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Enter Full Name: ");
                                String iname = scanner.nextLine();

                                System.out.print("Enter Email: ");
                                String iemail = scanner.nextLine();

                                System.out.print("Enter Department: ");
                                String idept = scanner.nextLine();

                                edu.ccrm.domain.Instructor inst =
                                        new edu.ccrm.domain.Instructor(iid, iname, iemail, idept);
                                allInstructors.add(inst);
                                System.out.println("✅ Instructor added successfully!");
                            }
                            case 2 -> {
                                if (allInstructors.isEmpty()) {
                                    System.out.println("No instructors found.");
                                } else {
                                    System.out.println("\n--- All Instructors ---");
                                    for (edu.ccrm.domain.Instructor i : allInstructors) {
                                        System.out.println(i);
                                    }
                                }
                            }
                            case 3 -> {
                                if (allInstructors.isEmpty()) {
                                    System.out.println("No instructors to update.");
                                    break;
                                }
                                System.out.println("Select instructor to update:");
                                for (int i = 0; i < allInstructors.size(); i++) {
                                    System.out.println((i + 1) + ". " + allInstructors.get(i).getFullName());
                                }
                                int idx = scanner.nextInt();
                                scanner.nextLine();

                                edu.ccrm.domain.Instructor inst = allInstructors.get(idx - 1);

                                System.out.print("Enter new name (leave blank to keep same): ");
                                String newName = scanner.nextLine();
                                if (!newName.isBlank()) inst.setFullName(newName);

                                System.out.print("Enter new email (leave blank to keep same): ");
                                String newEmail = scanner.nextLine();
                                if (!newEmail.isBlank()) inst.setEmail(newEmail);

                                System.out.print("Enter new department (leave blank to keep same): ");
                                String newDept = scanner.nextLine();
                                if (!newDept.isBlank()) inst.setDepartment(newDept);

                                System.out.println("✅ Instructor updated.");
                            }
                            case 4 -> {
                                if (allInstructors.isEmpty()) {
                                    System.out.println("No instructors to deactivate.");
                                    break;
                                }
                                System.out.println("Select instructor to deactivate:");
                                for (int i = 0; i < allInstructors.size(); i++) {
                                    System.out.println((i + 1) + ". " + allInstructors.get(i).getFullName());
                                }
                                int idx = scanner.nextInt();
                                scanner.nextLine();

                                edu.ccrm.domain.Instructor inst = allInstructors.get(idx - 1);
                                inst.deactivate();
                                System.out.println("⚠️ Instructor deactivated: " + inst.getFullName());
                            }
                            case 5 -> {
                                java.util.List<edu.ccrm.domain.Instructor> inactiveInstructors = new java.util.ArrayList<>();
                                for (edu.ccrm.domain.Instructor i : allInstructors) {
                                    if (!i.isActive()) inactiveInstructors.add(i);
                                }

                                if (inactiveInstructors.isEmpty()) {
                                    System.out.println("No inactive instructors to reactivate.");
                                    break;
                                }

                                System.out.println("Select instructor to reactivate:");
                                for (int i = 0; i < inactiveInstructors.size(); i++) {
                                    System.out.println((i + 1) + ". " + inactiveInstructors.get(i).getFullName());
                                }
                                int idx = scanner.nextInt();
                                scanner.nextLine();

                                edu.ccrm.domain.Instructor inst = inactiveInstructors.get(idx - 1);
                                inst.activate();
                                System.out.println("✅ Instructor reactivated: " + inst.getFullName());
                            }
                            case 6 -> instructorMenu = false;
                            default -> System.out.println("Invalid choice. Try again.");
                        }
                    }
                }


                // ************************ CASE 8: EXIT ************************
                case 8 -> {
                    running = false;
                    System.out.println("Exiting CCRM. Goodbye!");
                }

                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
