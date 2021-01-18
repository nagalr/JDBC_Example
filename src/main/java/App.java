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


        try  {
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

            // save the students Objects
            session.save(student1);
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

            // close the session that created two students in the DB
            session.close();

            /*
             Starring the extracting student part (reading from the DB)
             get a new session to retrieve the student
            */
            Session session2 = HibernateUtil.getSessionFactory().openSession();

            // start a transaction
            session2.beginTransaction();

            // retrieve student based on its ID
            Student myStudent = session2.get(Student.class, student1.getId());

            // prints the new student that retrieved from the DB
            System.out.println("\n************ New Student: *****\n" + myStudent);

            // commit the action
            session2.getTransaction().commit();

            // close the session that read a student from the DB
            session2.close();

        } catch (Exception e) {
            System.out.println("[ERROR] error while opening the session: " + e);
        }
    }
}

// Taken From: https://is.gd/CAOUGF