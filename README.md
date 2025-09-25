# Campus Course & Records Manager (CCRM)

## Project Overview
The Campus Course & Records Manager (CCRM) is a **Java SE console-based application** that helps institutes manage:  
- Students (add, list, update, deactivate/reactivate, transcript)  
- Courses (add, list, update, deactivate/reactivate, assign instructors)  
- Enrollment & Grades (enroll, record grades, compute GPA, print transcripts)  
- File Utilities (import/export, backup & restore data)  

It demonstrates core Java concepts (OOP, exceptions, design patterns, I/O, streams, etc.) and follows a menu-driven CLI workflow.  

---

## Features
### Student Management
- Add/list/update students  
- Activate/deactivate students  
- Print student profile & transcript  

### Course Management
- Add/list/update courses  
- Activate/deactivate courses  
- Assign instructors to courses  

### Enrollment & Grading
- Enroll students into active courses  
- Record letter grades (S, A, B, C, D, F)  
- Compute GPA automatically  
- Print transcript  

### File Operations
- Save/load students, courses, enrollments, instructors  
- Backup and restore data  
- Export to `.dat` files  

### Reports (Planned/Partially Implemented)
- GPA distribution using Streams  
- Top students report  
- Course enrollment summary  

---

## Tech Highlights
- Java SE 17+  
- OOP Pillars: Encapsulation, Inheritance, Abstraction, Polymorphism  
- Design Patterns: Singleton (AppConfig), Builder (planned for Course)  
- Enums: Semester, Grade  
- Collections API: List, ArrayList  
- I/O & Persistence: ObjectInputStream, ObjectOutputStream  
- Backup: Timestamped backup with restore  
- Date/Time API: LocalDate for student/instructor creation  

---

## Project Structure
```
edu.ccrm
├─ cli/         # CLI Main menu
├─ domain/      # Domain classes: Person, Student, Instructor, Course, Enrollment, Grade, Semester
├─ io/          # DataManager (I/O), BackupService
├─ config/      # AppConfig (Singleton)
```

---

## Evolution of Java (short timeline)
- 1995: Java 1.0 released by Sun Microsystems  
- 2004: Java 5 introduced generics, annotations  
- 2011: Java 7 with NIO.2 File API  
- 2014: Java 8 with lambdas, streams  
- 2017: Java 9 modules introduced  
- 2021+: Modern Java (17 LTS, sealed classes, records)  

---

## Java Editions
| Edition | Purpose |
|---------|---------|
| Java ME | Mobile/embedded devices |
| Java SE | Standard Edition for desktop & console apps |
| Java EE (Jakarta EE) | Enterprise Edition for web & enterprise servers |

---

## Java Architecture
- JDK (Java Development Kit) – tools for compiling/running Java code  
- JRE (Java Runtime Environment) – libraries + JVM to run code  
- JVM (Java Virtual Machine) – executes bytecode on any platform  

**Flow:** Source Code → Compiler → Bytecode → JVM → Machine  

---

## Setup
### Install Java (Windows)
1. Download JDK from Oracle/AdoptOpenJDK  
2. Install & set `JAVA_HOME` in environment variables  
3. Verify installation:
   ```bash
   java -version
   ```

### Eclipse IDE
1. Open Eclipse → File → New → Java Project  
2. Add packages (`edu.ccrm.domain`, `edu.ccrm.cli`, etc.)  
3. Run `Main.java`  

---

## Sample Usage
### Start Program
```
Welcome to Campus Course & Records Manager (CCRM)
===== MAIN MENU =====
1. Manage Students
2. Manage Courses
3. Enrollment & Grades
4. Import/Export Data
5. Print Transcript
6. Backup & Reports
7. Manage Instructors
8. Exit
```

### Example Flow
1. Add student → Manage Students → Add Student  
2. Add course → Manage Courses → Add Course  
3. Enroll student → Enrollment & Grades  
4. Assign instructor → Manage Instructors  
5. Print transcript → Print Transcript  

---

## Mapping Table (syllabus → code)
| Requirement | Implementation |
|-------------|----------------|
| Encapsulation | Private fields + getters/setters in Student, Course |
| Inheritance | Person → Student, Instructor |
| Abstraction | Person abstract class |
| Polymorphism | printProfile() overridden in Student, Instructor |
| Enums | Grade, Semester |
| Singleton | AppConfig |
| File I/O | DataManager, BackupService |
| Date/Time API | createdAt in Person |
| Streams | Planned for reports/search |
| Exceptions | Basic try/catch (custom exceptions pending) |
| Nested Classes | Pending |
| Builder Pattern | Pending |
| Recursion | Pending (backup utility) |

---

## Screenshots (to include)
- Java install (`java -version`)  
- Eclipse setup  
- Running program (main menu, student/course creation)  
- Backup folder created  

---

## Deliverables
- Source code (this repo)  
- `README.md` (this file)  
- Screenshots folder  
- Optional demo video link  

---

## Future Improvements
- CSV/JSON import-export  
- Recursive backup size utility  
- GPA reports with Streams  
- Custom exceptions (DuplicateEnrollmentException, MaxCreditLimitExceededException)  
- Immutable value objects (CourseCode)  
- Builder & Interfaces  
