package entities;

import java.io.Serializable;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 20:33
 */
public class Student implements Serializable {


    private String name = null;
    private int age = 0;
    private String email = null;
    private boolean checked = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Student() {
    }

    public Student(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Student(String name, int age, String email, boolean checked) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
