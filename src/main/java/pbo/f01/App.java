package pbo.f01;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import pbo.f01.model.Student;
import pbo.f01.model.Dorm;

/**
 * 12S22029 - Jeremy Samosir
 * 12S22019 - Liony Tamara Lewinsky
 */


public class App {
    List<Student> students = new ArrayList<>();
    List<Dorm> dorms = new ArrayList<>();

    public void addStudent(String id, String name, int entranceYear, String gender) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return;
            }
        }
        students.add(new Student(id, name, entranceYear, gender));
    }

    public void addDorm(String name, int capacity, String gender) {
        for (Dorm dorm : dorms) {
            if (dorm.getName().equals(name)) {
              
                return;
            }
        }
        dorms.add(new Dorm(name, capacity, gender));
    }

    public void assign(String studentId, String dormName) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                for (Dorm dorm : dorms) {
                    if (dorm.getName().equals(dormName)) {
                        dorm.addStudent(student);
                        return;
                    }
                }
              
            }
        }
      
    }

    public void displayAll() {
        Collections.sort(dorms, Comparator.comparing(Dorm::getName));
        for (Dorm dorm : dorms) {
            System.out.println(dorm.getName() + "|" + dorm.getGender() + "|" + dorm.getCapacity() + "|" + dorm.getStudents().size());
            Collections.sort(dorm.getStudents(), Comparator.comparing(Student::getName));
            for (Student student : dorm.getStudents()) {
                System.out.println(student.getId() + "|" + student.getName() + "|" + student.getEntranceYear());
            }
        }
    }

    public static void main(String[] args) {
        App app = new App();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (!(input = scanner.nextLine()).equals("---")) {
            try {
                String[] parts = input.split("#");
                switch (parts[0]) {
                    case "student-add":
                        app.addStudent(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4]);
                        break;
                    case "dorm-add":
                        app.addDorm(parts[1], Integer.parseInt(parts[2]), parts[3]);
                        break;
                    case "assign":
                        app.assign(parts[1], parts[2]);
                        break;
                    case "display-all":
                        app.displayAll();
                        break;
                    default:
                }
            } catch (Exception e) {
               
            }
        }

        scanner.close();
    }
}