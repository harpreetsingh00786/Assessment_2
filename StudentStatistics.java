
/**
 * Write a description of class Assignment2 here.
 *      This is the student statistics system, the user can load a txt file, calculate the total marks of each student, and 
 *  display below the user threshold, the 5 highest and 5 lowest marks student details.
 *
 * @author (Harpreet Singh)
 * @version (25/09/2023)
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;


class Student {
    private String lastName;
    private String firstName;
    private String studentID;
    private int[] assessmentMarks;

    public Student(String lastName, String firstName, String studentID, int[] assessmentMarks) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentID = studentID;
        this.assessmentMarks = assessmentMarks;
    }

    public int getTotalMarks() {
        int total = 0;
        for (int mark : assessmentMarks) {
            total = total + mark;
        }
        return total;
    }

    @Override
    public String toString() {
        return "Last Name: " + lastName + " || First Name: " + firstName + " || Student ID: " + studentID + " || Marks: " +
                assessmentMarks[0] + ", " + assessmentMarks[1] + ", " + assessmentMarks[2] + " || Total: " + getTotalMarks();
    }
}

public class StudentStatistics {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String filename;

        while (true) {
            System.out.print("Enter student info file name: ");
            filename = scanner.next();

            try (BufferedReader br = new BufferedReader(new FileReader(filename + ".txt"))) {
                String line;
                br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] item = line.split(",");
                    String lastName = item[0].trim();
                    String firstName = item[1].trim();
                    String studentID = item[2].trim();
                    int[] assessmentMarks = {
                            Integer.parseInt(item[3].trim()),
                            Integer.parseInt(item[4].trim()),
                            Integer.parseInt(item[5].trim())
                    };

                    Student student = new Student(lastName, firstName, studentID, assessmentMarks);
                    students.add(student);
                }

                System.out.println("The student info successfully loaded.\n");
                break;

            } catch (IOException e) {
                System.out.println("This file does not exist.");
            }
        }

        while (true) {
            System.out.println("===== Student Statistics - Menu =====\n" +
                    "Press 1. Calculate and display total mark for each student\n" +
                    "Press 2. Display students with total marks less than a certain threshold\n" +
                    "Press 3. Display top 5 students with highest and lowest marks\n" +
                    "Press 4. Quit\n");

            int choice = scanner.nextInt();

            if (choice == 1) {
                display_students_info(students);
            } else if (choice == 2) {
                System.out.print("Enter the threshold: ");
                int threshold = scanner.nextInt();
                System.out.println();
                display_students_below_threshold(students, threshold);
                System.out.println();
            } else if (choice == 3) {
                display_topN_students(students, 5, true);
                display_topN_students(students, 5, false);
                System.out.println();
            } else if (choice == 4) {
                System.out.println("Thank you for using Student Statistics!");
                System.exit(0);
            } else {
                System.out.println("WARNING! Please choose between 1 - 4.");
            }
        }
    }

    private static void display_students_info(List<Student> students) {
        System.out.println();
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

    private static void display_students_below_threshold(List<Student> students, int threshold) {
        for (Student student : students) {
            if (student.getTotalMarks() < threshold) {
                System.out.println(student);
            }
        }
    }

    private static void display_topN_students(List<Student> students, int n, boolean highest) {
        int size = students.size();
        boolean swap = false;

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                swap = false;

                if (highest) {
                    if (students.get(j).getTotalMarks() < students.get(j + 1).getTotalMarks()) {
                        swap = true;
                    }

                } else {
                    if (students.get(j).getTotalMarks() > students.get(j + 1).getTotalMarks()) {
                        swap = true;
                    }
                }

                if (swap) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }

        if (highest) {
            System.out.println("\n              Top " + n + " highest students");
        } else {
            System.out.println("\n              Top " + n + " lowest students");
        }

        for (int i = 0; i < n && i < students.size(); i++) {
            System.out.println(students.get(i));
        }
    }
}

