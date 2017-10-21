package model;

import java.util.ArrayList;
import java.util.List;

import studentmanagement.Class;
import studentmanagement.Student;

/**
 *
 * @author ducanhnguyen
 */
public class DataInitilization {

    public static List<Student> students = new ArrayList<>();

    public static List<Class> classes = new ArrayList<>();

    public DataInitilization() {
        students.add(new Student(101, "William Shakespeare"));
        students.add(new Student(102, "Jane Austen"));
        students.add(new Student(103, "William Faulkner"));
        students.add(new Student(104, "Charles Dickens"));
        students.add(new Student(105, "Joseph Conrad"));
        students.add(new Student(106, "Homer"));
        students.add(new Student(107, "Geoffrey Chaucer"));

        classes.add(new Class("Math", 30));
        classes.add(new Class("Literature", 10));
        classes.add(new Class("History", 43));
    }

}
