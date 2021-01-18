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

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Bloch");
        student.setContactNo("+1-408-575-1317");

        session.save(student);
        session.getTransaction().commit();

        Query<Student> q = session.createQuery("From Student", Student.class);

        List<Student> resultList = q.list();
        System.out.println("total students:" + resultList.size());

        for (Student s : resultList) {
            System.out.println("student : " + s);
        }
    }
}
