package pbo.f01;
import pbo.f01.model.Student;
import pbo.f01.model.Dorm;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

/**
 * 12S22029 - Jeremy Samosir
 * 12S22019 - Liony Tamara Lewinsky
 */

public class App {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pbo_f01");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("---")) {
                break;
            }
            processInput(input, em);
        }

        em.close();
        emf.close();
    }

    private static void processInput(String input, EntityManager em) {
        if (input.startsWith("display-all")) {
            displayAll(em);
        } else if (input.startsWith("student-add#")) {
            addStudent(input, em);
        } else if (input.startsWith("dorm-add#")) {
            addDorm(input, em);
        } else if (input.startsWith("assign#")) {
            assignStudent(input, em);
        }
    }

    private static void displayAll(EntityManager em) {
        TypedQuery<Dorm> query = em.createQuery("SELECT d FROM Dorm d ORDER BY d.name", Dorm.class);
        List<Dorm> dorms = query.getResultList();

        for (Dorm dorm : dorms) {
            System.out.printf("%s|%s|%d|%d\n", dorm.getName(), dorm.getGender(), dorm.getCapacity(), dorm.getStudent().size());
            dorm.getStudent().stream()
                .sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
                .forEach(student -> System.out.printf("%s|%s|%d\n", student.getId(), student.getName(), student.getYear()));
        }
    }

    private static void addStudent(String input, EntityManager em) {
        String[] parts = input.split("#");
        Student student = new Student();
        student.setId(parts[1]);
        student.setName(parts[2]);
        student.setYear(Integer.parseInt(parts[3]));
        student.setGender(parts[4]);

        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
    }

    private static void addDorm(String input, EntityManager em) {
        String[] parts = input.split("#");
        Dorm dorm = new Dorm();
        dorm.setName(parts[1]);
        dorm.setCapacity(Integer.parseInt(parts[2]));
        dorm.setGender(parts[3]);

        em.getTransaction().begin();
        em.persist(dorm);
        em.getTransaction().commit();
    }

    private static void assignStudent(String input, EntityManager em) {
        String[] parts = input.split("#");
        String studentId = parts[1];
        String dormName = parts[2];

        Student student = em.find(Student.class, studentId);
        Dorm dorm = em.find(Dorm.class, dormName);

        if (student != null && dorm != null && dorm.getStudent().size() < dorm.getCapacity() && student.getGender().equals(dorm.getGender())) {
            em.getTransaction().begin();
            student.setDorm(dorm);
            em.getTransaction().commit();
        }
    }
}