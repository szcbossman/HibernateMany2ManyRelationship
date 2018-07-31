package com.troyszc.firstHibernate;

import com.troyszc.firstHibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudents {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        //use session obj to save java obj
        try {
            //start transaction
            session.beginTransaction();

            /*
            //create course and reviews
            Course theCourse = new Course("How to stick shift 101");

            //save course
            session.save(theCourse);
            System.out.println("the course: " + theCourse);

            //create students
            Student theStudent1 = new Student("troy", "song", "troysong33@gmail.com");
            Student theStudent2 = new Student("trey", "songz", "treysongz@gmail.com");

            //add students
            theCourse.addStudent(theStudent1);
            theCourse.addStudent(theStudent2);

            //save students
            session.save(theStudent1);
            session.save(theStudent2);
            System.out.println("the students: " + theCourse.getStudents());
            */

            //more work to test out join table
            int studentId = 1;

            Student theStudentWhoLikesToLearnMore = session.get(Student.class, studentId);
            Course theCourse1 = new Course("how to heel and toe 102");
            Course theCourse2 = new Course("how to left foot braking");
            theCourse1.addStudent(theStudentWhoLikesToLearnMore);
            theCourse2.addStudent(theStudentWhoLikesToLearnMore);
            session.save(theCourse1);
            session.save(theCourse2);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done: data saved to db.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }

    }
}
