import com.example.entity.Student;
import com.example.hibernate.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by ronnen on 18-Jan-2021
 */


public class App4 {

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

            // retrieving the object to be deleted
            Student myStudent = session.get(Student.class, studentId);

            // deleting the object (can verify deletion in Sequel pro)
            session.delete(myStudent);

            // delete another student with 'executeUpdate()'
            // here, not retrieving the object, deleting 'on-the-fly'
            session.createQuery("delete from Student where id=2")
                    .executeUpdate();

            // commit the transactions
            session.getTransaction().commit();

            // close the session that created two students in the DB
            session.close();

        } catch (Exception e) {
            System.out.println("[ERROR] error while opening the session: " + e);
        }
    }
}
