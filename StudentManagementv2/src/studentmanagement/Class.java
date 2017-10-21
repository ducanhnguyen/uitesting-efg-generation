package studentmanagement;

/**
 *
 * @author ducanhnguyen
 */
public class Class {

    private String name;
    private int numStudent;

    public Class(String name, int numStudent) {
        this.name = name;
        this.numStudent = numStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(int numStudent) {
        this.numStudent = numStudent;
    }
}
