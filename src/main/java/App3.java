import com.example.entity.Student;
import com.example.hibernate.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by ronnen on 18-Jan-2021
 */


public class App3 {

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

            // start the update part
            Long studentId = 1L;

            System.out.println("\nGet Student with StudentId: " + studentId);

            Student myStudent = session.get(Student.class, studentId);
            System.out.println("\nfirst name before update: " + myStudent);

            myStudent.setFirstName("Saby");
            System.out.println("\nfirst name after update: " + myStudent +"\n");

            // commit the transactions
            session.getTransaction().commit();

            // close the session that created two students in the DB
            session.close();

            // NEW SESSION INCLUDES: get-session, begin-transaction, commit
            Session session2 = HibernateUtil.getSessionFactory().openSession();

            // start a transaction
            session2.beginTransaction();

            // update contactNo for all Students
            session2.createQuery("update Student set contactNo='123456'")
                    .executeUpdate();

            // commit the transactions
            session2.getTransaction().commit();

            // close the session that created two students in the DB
            session2.close();

        } catch (Exception e) {
            System.out.println("[ERROR] error while opening the session: " + e);
        }
    }
}
