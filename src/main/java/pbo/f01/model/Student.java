package pbo.f01.model;


/**
 * 12S22029 - Jeremy Samosir
 * 12S22019 - Liony Tamara Lewinsky
 */


 
public class Student {
    private String id;
    private String name;
    private int entranceYear;
    private String gender;

    public Student(String id, String name, int entranceYear, String gender) {
        this.id = id;
        this.name = name;
        this.entranceYear = entranceYear;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getEntranceYear() {
        return entranceYear;
    }

    public String getGender() {
        return gender;
    }
}

