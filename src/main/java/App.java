import com.example.entity.Student;
import com.example.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by ronnen on 18-Jan-2021
 */


public class App {

    public static void main(String[] args) {

        // try-with-resources to close the session at the end
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // start a transaction
            session.beginTransaction();

            // creates two student Objects
            Student student = new Student();
            student.setFirstName("John");
            student.setLastName("Bloch");
            student.setContactNo("+1-408-575-1317");

            Student student2 = new Student();
            student2.setFirstName("James");
            student2.setLastName("Thompson");
            student2.setContactNo("+1-416-575-1255");

            // save the students Objects
            session.save(student);
            session.save(student2);

            // commit the transaction
            session.getTransaction().commit();

            // Query the table for all Students
            Query<Student> q = session.createQuery("From Student", Student.class);

            // prints the Student table size
            List<Student> resultList = q.list();
            System.out.println("total students: " + resultList.size());

            // prints the content of 'Students'
            for (Student s : resultList) {
                System.out.println("student : " + s);
            }

        } catch (Exception e) {
            System.out.println("[ERROR] error while opening the session: " + e);
        }
    }
}

// Taken From: https://is.gd/CAOUGF