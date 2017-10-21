package model;

/**
 *
 * @author ducanhnguyen
 */
public class Course {

    private String name;
    private String description;

    public Course(String name, String numStudent) {
        this.name = name;
        this.description = numStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
