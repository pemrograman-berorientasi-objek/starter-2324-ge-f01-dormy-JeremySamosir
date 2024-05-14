package pbo.f01.model;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;


/**
 * 12S22029 - Jeremy Samosir
 * 12S22019 - Liony Tamara Lewinsky
 */



@Entity
public class Dorm {
    @Id
    private String name;
    private int capacity;
    private String gender;

    @OneToMany(mappedBy = "dorm", cascade = CascadeType.ALL)
    private List<Student> students;


    public Dorm() {

    }

    public Dorm(String name, int capacity, String gender) {
        this.name = name;
        this.capacity = capacity;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return name + "|" + gender + "|" + capacity + "|" + (students != null ? students.size() : 0);
    }
}