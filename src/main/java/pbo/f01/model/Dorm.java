package pbo.f01.model;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import java.util.Collection;
import java.util.List;

/**
 * 12S22029 - Jeremy Samosir
 * 12S22019 - Liony Tamara Lewinsky
 */

 @Entity
 @Table (name = "dorms")
public class Dorm {
    @Column (name = "name", nullable = false, length = 200)
    private String name;
    @Column (name = "gender", nullable = false, length = 10)
    private String gender;
    @Column (name = "capacity", nullable = false, length = 10)
    private Integer capacity;
    
    @OneToMany(mappedBy = "dorm")
    private List<Student> students;

    public Dorm(){
        //empty
    }

    public Dorm(String name, String gender, Integer capacity, List<Student> students) {
        this.name = name;
        this.gender = gender;
        this.capacity = capacity;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Integer getCapacity(){
        return capacity;
    }

    public List<Student> getStudent(){
        return students;
    }

    public void setName(String Name) {
        this.name = name;
    }
    
    public void setGender(String gender){
        this.gender = gender;
    }

    public void setCapacity(Integer capacity){
        this.capacity = capacity;
    }

    public void setStudents(List <Student> students){
        this.students = students;
    }
    @Override
    public String toString() {
        return this.name + " | " + this.gender + " | " + this.capacity + "|" + this.students;
    }
}