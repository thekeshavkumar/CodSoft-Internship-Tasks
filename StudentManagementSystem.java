import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        if (!name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void setRollNumber(String rollNumber) {
        if (!rollNumber.trim().isEmpty()) {
            this.rollNumber = rollNumber;
        }
    }

    public void setGrade(String grade) {
        if (!grade.trim().isEmpty()) {
            this.grade = grade;
        }
    }

    @Override
    public String toString() {
        return String.format("Roll No: %s, Name: %s, Grade: %s", rollNumber, name, grade);
    }
}

public class StudentManagementSystem {
    private static final String DATA_FILE = "students.dat";
    private List<Student> students;
    private Scanner scanner;

    public StudentManagementSystem() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadData();
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    editStudent();
                    break;
                case "3":
                    removeStudent();
                    break;
                case "4":
                    searchStudent();
                    break;
                case "5":
                    displayAllStudents();
                    break;
                case "6":
                    running = false;
                    saveData();
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }

    private void addStudent() {
        System.out.println("\nAdd New Student");
        String rollNumber = inputRollNumber(true);
        if (rollNumber == null) return;

        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter grade: ");
        String grade = scanner.nextLine().trim();
        if (grade.isEmpty()) {
            System.out.println("Grade cannot be empty.");
            return;
        }

        students.add(new Student(name, rollNumber, grade));
        System.out.println("Student added successfully.");
    }

    private void editStudent() {
        System.out.println("\nEdit Student");
        System.out.print("Enter roll number of student to edit: ");
        String rollNumber = scanner.nextLine().trim();

        Student student = findStudentByRoll(rollNumber);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name (leave blank to keep '" + student.getName() + "'): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            student.setName(name);
        }

        System.out.print("Enter new grade (leave blank to keep '" + student.getGrade() + "'): ");
        String grade = scanner.nextLine().trim();
        if (!grade.isEmpty()) {
            student.setGrade(grade);
        }

        System.out.println("Student information updated.");
    }

    private void removeStudent() {
        System.out.println("\nRemove Student");
        System.out.print("Enter roll number of student to remove: ");
        String rollNumber = scanner.nextLine().trim();

        Student student = findStudentByRoll(rollNumber);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(student);
        System.out.println("Student removed successfully.");
    }

    private void searchStudent() {
        System.out.println("\nSearch Student");
        System.out.print("Enter roll number or name keyword: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        List<Student> found = new ArrayList<>();
        for (Student s : students) {
            if (s.getRollNumber().toLowerCase().contains(keyword) || s.getName().toLowerCase().contains(keyword)) {
                found.add(s);
            }
        }

        if (found.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Found students:");
            found.forEach(System.out::println);
        }
    }

    private void displayAllStudents() {
        System.out.println("\nAll Students:");
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private Student findStudentByRoll(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                return s;
            }
        }
        return null;
    }

    private String inputRollNumber(boolean checkUnique) {
        System.out.print("Enter roll number: ");
        String rollNumber = scanner.nextLine().trim();
        if (rollNumber.isEmpty()) {
            System.out.println("Roll number cannot be empty.");
            return null;
        }
        if (checkUnique && findStudentByRoll(rollNumber) != null) {
            System.out.println("Roll number already exists.");
            return null;
        }
        return rollNumber;
    }

    private void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Failed to save data.");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            students = (List<Student>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load data.");
        }
    }

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.start();
    }
}
