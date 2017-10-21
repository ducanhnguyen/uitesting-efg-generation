package model;

/**
 *
 * @author ducanhnguyen
 */
public class Student {

    private int studentCode;
    private String name;

    public Student(int studentCode, String name) {
        this.studentCode = studentCode;
        this.name = name;
    }

    public void setStudentCode(int studentCode) {
        this.studentCode = studentCode;
    }

    public int getStudentCode() {
        return studentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getStudentCode() + " " + getName();
    }

}
