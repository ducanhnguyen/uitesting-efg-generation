package studentmanagement;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ducanhnguyen
 */
public class DataInitilization {

    public static List<Student> students = new ArrayList<>();

    public static List<Class> classes = new ArrayList<>();

    public static List<Course> courses = new ArrayList<>();

    public DataInitilization() {
        students.add(new Student(101, "William Shakespeare", 7.6f));
        students.add(new Student(102, "Jane Austen", 7.7f));
        students.add(new Student(103, "William Faulkner", 8.7f));
        students.add(new Student(104, "Charles Dickens", 8.9f));
        students.add(new Student(105, "Joseph Conrad", 1.2f));
        students.add(new Student(106, "Homer", 8.0f));
        students.add(new Student(107, "Geoffrey Chaucer", 5.6f));

        classes.add(new Class("10A11", 30));
        classes.add(new Class("10A10", 10));
        classes.add(new Class("10A1", 43));

        courses.add(new Course("Math", "..."));
        courses.add(new Course("Literature", "..."));
        courses.add(new Course("History", ".."));
    }

}
