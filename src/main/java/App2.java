import com.example.entity.Student;
import com.example.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by ronnen on 18-Jan-2021
 */


public class App2 {

    public static void main(String[] args) {

        try {
            // define the session for the two students creation
            Session session = HibernateUtil.getSessionFactory().openSession();

            // start a transaction
            session.beginTransaction();

            // creates two student Objects
            Student student1 = new Student();
            student1.setFirstName("John");
            student1.setLastName("Bloch");
            student1.setContactNo("+1-408-575-1317");

            Student student2 = new Student();
            student2.setFirstName("James");
            student2.setLastName("Thompson");
            student2.setContactNo("+1-416-575-1255");

            // save the students Objects into the DB
            session.save(student1);
            session.save(student2);

            // commit the transaction
            session.getTransaction().commit();

            // query a Student with last name 'Thompson'
            // ('s' is an alias, 'lastName' from the Java code, not DB table name)
            Query<Student> q = session
                    .createQuery("From Student s where s.lastName='Thompson'",
                            Student.class);

            List<Student> result = q.getResultList();
            System.out.println("\nStudent with last name 'Thompson' is: " + result + "\n");

            // another query, find students with lastName='Doe' OR firstName='James'
            Query<Student> q2 = session
                                .createQuery("From Student s where s.lastName='Doe' " +
                                        "OR s.firstName='James'", Student.class);

            List<Student> result2 = q2.getResultList();
            System.out.println(result2);

            // close the session that created two students in the DB
            session.close();

        } catch (Exception e) {
            System.out.println("[ERROR] error while opening the session: " + e);
        }
    }
}