
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
    private String name;
    private String studentID;
    private int[] assessmentMarks;

    public Student(String name, String studentID, int[] assessmentMarks) {
        this.name = name;
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
        return "Name: " + name + " || Student ID: " + studentID + " || Marks: " +
                assessmentMarks[0] + ", " + assessmentMarks[1] + ", " + assessmentMarks[2] + " || Total: " + getTotalMarks();
    }
}
public class StudentStatistics
{
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String filename;
        
        // Use while loop with true for iteration until user stop the loop 
        while (true) {
            System.out.print("Enter student info file name: ");
            filename = scanner.next();
        
            // Read data from the user given file
            try (BufferedReader br = new BufferedReader(new FileReader(filename + ".txt"))) {
                String line;
                while ((line = br.readLine()) != null) {    
                    String[] item = line.split(",");
                    String name = item[0].trim();
                    String studentID = item[1].trim();
                    int[] assessmentMarks = {
                        Integer.parseInt(item[2].trim()),
                        Integer.parseInt(item[3].trim()),
                        Integer.parseInt(item[4].trim())
                    };
                    
                    // Add student information into the students list
                    Student student = new Student(name, studentID, assessmentMarks);
                    students.add(student);
                }
                
                System.out.println("The student info successfully loaded.\n");
                break;
                
            } catch (IOException e) {
                System.out.println("This file not exist.");
            }
        }
        
         // Use while loop with true for iteration until user stop the loop 
        while (true) {
            // Display menu to the user for choose an option
            System.out.println("===== Student Statistics - Menu =====\n" +
                                "Press 1. Calculate and display total mark for each student\n" +
                                "Press 2. Display students with total marks less than a certain threshold\n" +
                                "Press 3. Display top 5 students with highest and lowest marks\n" +
                                "Press 4. Quit\n");
                                
            int choice = scanner.nextInt();
            // The user chocie is equal to 1, then goto inside the if statement
            if (choice == 1) {
                display_students_info(students);
            }
            
            else if (choice ==2){
                
            }
            else if (choice ==3){
                
            }
            else if (choice ==4){
                 System.out.println("Thank you for using Student Statistics!");
                System.exit(0);
            }
          
            // The user chocie is other than 1-4, then goto inside the else statement
          
            else {
                 System.out.println("WARNING! Please choose between 1 - 4.");
      
            }
        }
    }
    
    // This method shows complete details of the student
    private static void display_students_info(List<Student> students) {
        System.out.println();
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();
    }
}
