import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Student Grade Calculator");
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        scanner.nextLine();

        if (numSubjects <= 0) {
            System.out.println("Invalid number of subjects.");
            return;
        }

        String[] subjectNames = new String[numSubjects];
        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter the name of subject " + (i + 1) + ": ");
            subjectNames[i] = scanner.nextLine();

            while (true) {
                System.out.print("Enter marks for " + subjectNames[i] + " (out of 100): ");
                int input = scanner.nextInt();
                if (input >= 0 && input <= 100) {
                    marks[i] = input;
                    totalMarks += input;
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Invalid input. Marks should be between 0 and 100.");
                }
            }
        }

        double averagePercentage = (double) totalMarks / numSubjects;
        String grade = calculateGrade(averagePercentage);

        System.out.println("\nResult Summary:");
        for (int i = 0; i < numSubjects; i++) {
            System.out.println(subjectNames[i] + ": " + marks[i] + "/100");
        }

        System.out.println("Total Marks: " + totalMarks + " out of " + (numSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }

    public static String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F (Fail)";
        }
    }
}
