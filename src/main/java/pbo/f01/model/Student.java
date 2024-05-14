package pbo.f01.model;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
/**
 * 12S22029 - Jeremy Samosir
 * 12S22019 - Liony Tamara Lewinsky
 */

@Entity
@Table (name = "students")
public class Student {
    @Column(name = "id", nullable = false, length = 10)
    private String id;
    @Column(name = "name", nullable = false, length = 200)
    private String name;
    @Column(name = "gender", nullable = false, length = 10)
    private String gender;
    @Column(name = "year", nullable = false, length = 7)
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "dorm_name")
    private Dorm dorm;

    public Student(){
        //empty
    }


    public Student(String id, String name, String gender, Integer year) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Integer getYear(){
        return year;
    }

    public Dorm dorm(){
        return dorm;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String Name) {
        this.name = name;
    }
    
    public void setGender(String gender){
        this.gender = gender;
    }

    public void setYear(Integer year){
        this.year = year;
    }

    
    public void setDorm(Dorm dorm) {
        this.dorm = dorm;
    }

    @Override
    public String toString() {
        return this.id + " | " + this.name + " | " + this.gender + " | " + this.year;
    }

}
