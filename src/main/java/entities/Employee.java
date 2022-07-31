package entities;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Siwen Sun
 * @date 2022/07/31/ 12:12
 */
public class Employee {

    private final SimpleStringProperty name;
    private final SimpleStringProperty department;

    public Employee(String name, String department) {
        this.name = new SimpleStringProperty(name);
        this.department = new SimpleStringProperty(department);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String fName) {
        name.set(fName);
    }

    public String getDepartment() {
        return department.get();
    }

    public void setDepartment(String fName) {
        department.set(fName);
    }
}