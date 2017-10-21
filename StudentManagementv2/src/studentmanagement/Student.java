package studentmanagement;

/**
 *
 * @author ducanhnguyen
 */
public class Student {

    private int studentCode;
    private String name;
    private float grade;

    public Student(int studentCode, String name, float grade) {
        this.studentCode = studentCode;
        this.name = name;
        this.grade = grade;
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

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return getStudentCode() + " " + getName() + " " + getGrade();
    }

}
