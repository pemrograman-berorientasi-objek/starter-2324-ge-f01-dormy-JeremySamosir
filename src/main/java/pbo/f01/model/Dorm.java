package pbo.f01.model;

import java.util.ArrayList;
import java.util.List;


/**
 * 12S22029 - Jeremy Samosir
 * 12S22019 - Liony Tamara Lewinsky
 */


public class Dorm {
    private String name;
    private int capacity;
    private String gender;
    private List<Student> students;

    public Dorm(String name, int capacity, String gender) {
        this.name = name;
        this.capacity = capacity;
        this.gender = gender;
        this.students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getGender() {
        return gender;
    }

    public List<Student> getStudents() {
        return students;
    }

    public boolean addStudent(Student student) {
        if (students.size() < capacity && student.getGender().equals(gender)) {
            students.add(student);
            return true;
        }
        return false;
    }
}