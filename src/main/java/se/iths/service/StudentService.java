package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * The student service class that is responsible for the persistence logic of the student-class.
 */
@Transactional
public class StudentService {

    /**
     *  Used to interact with the persistence context.
     * @see EntityManager
     */
    @PersistenceContext
    EntityManager entityManager;


    /**
     * Method that saves a new student to the database.
     */
    public Student createStudent(Student student) {
        entityManager.persist(student);
        return  student;
    }

    /**
     * Method that returns the student with matching id.
     */
    public Student getStudent(Long id) {
        return entityManager.find(Student.class, id);
    }

    /**
     * Method that returns a list of all students in the database.
     */
    public List<Student> getStudents() {
        return entityManager.createNamedQuery("Student.getAll", Student.class).getResultList();
    }

    /**
     * Method that returns all students with matching last name.
     */
    public List<Student> getStudents(String lastName) {
        return entityManager.createNamedQuery("Student.getByLastName", Student.class)
                .setParameter("name", lastName)
                .getResultList();
    }

    /**
     * Method that updates the first- and last name of a student with matching id.
     */
    public Student updateName(Long id, String firstName, String lastName) {
        Student student = entityManager.find(Student.class, id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        return entityManager.merge(student);
    }

    /**
     * Method that removes student with matching id from database.
     */
    public void removeStudent(Long id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }

    /**
     * Method that add a subject to a student.
     */
    public Student addSubject(Long studentId, Long subjectId) {
        Student student = entityManager.find(Student.class, studentId);
        Subject subject = entityManager.find(Subject.class, subjectId);
        student.addSubject(subject);
        return entityManager.merge(student);
    }
}
