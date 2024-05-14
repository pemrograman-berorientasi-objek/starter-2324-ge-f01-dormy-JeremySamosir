package pbo.f01;
import pbo.f01.model.Student;
import pbo.f01.model.Dorm;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;
import javax.persistence.*;
import java.util.*;
import java.util.stream.*;
/**
 * 12S22029 - Jeremy Samosir
 * 12S22019 - Liony Tamara Lewinsky
 */


public class App {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit");

    public static void main(String[] args) {
        App app = new App();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("---")) {
                break;
            }

            String[] parts = input.split("#");
            switch (parts[0]) {
                case "dorm-add":
                    app.addDorm(parts[1], Integer.parseInt(parts[2]), parts[3]);
                    break;
                case "student-add":
                    app.addStudent(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4]);
                    break;
                case "assign":
                    app.assignStudentToDorm(parts[1], parts[2]);
                    break;
                case "display-all":
                    app.displayAll();
                    break;
            }
        }

        scanner.close();
    }

    public void addDorm(String name, int capacity, String gender) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Dorm dorm = new Dorm(name, capacity, gender);
        em.persist(dorm);
        em.getTransaction().commit();
        em.close();
    }

    public void addStudent(String id, String name, int year, String gender) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student student = new Student(id, name, year, gender);
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    public void assignStudentToDorm(String studentId, String dormName) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Student student = em.find(Student.class, studentId);
        Dorm dorm = em.find(Dorm.class, dormName);

        if (student != null && dorm != null && dorm.getStudents().size() < dorm.getCapacity()) {
            student.setDorm(dorm);
            dorm.getStudents().add(student);
            em.persist(student);
            em.persist(dorm);
        }

        em.getTransaction().commit();
        em.close();
    }

    public void displayAll() {
        EntityManager em = emf.createEntityManager();
        List<Dorm> dorms = em.createQuery("SELECT d FROM Dorm d ORDER BY d.name", Dorm.class).getResultList();

        for (Dorm dorm : dorms) {
            System.out.println(dorm);
            dorm.getStudents().stream()
                .sorted(Comparator.comparing(Student::getName))
                .forEach(student -> System.out.println(student));
        }
        em.close();
    }
}