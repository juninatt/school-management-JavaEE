package se.iths.service;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * The teacher service class that is responsible for the persistence logic of the teacher-class.
 */
@Transactional
public class TeacherService {

    /**
     * Used to interact with the persistence context.
     * @see EntityManager
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Method that saves a new teacher to the database.
     */
    public Teacher createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
        return teacher;
    }

    /**
     * Method that returns a teacher with matching id.
     */
    public Teacher getTeacher(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    /**
     * Method that returns a list of all teachers in the database.
     */
    public List<Teacher> getTeachers() {
        return entityManager.createNamedQuery("Teacher.getAll", Teacher.class).getResultList();
    }

    /**
     * Method that returns all teachers with matching last name.
     */
    public List<Teacher> getTeachers(String lastName) {
        return entityManager.createNamedQuery("Teacher.getByLastName", Teacher.class)
                .setParameter("name", lastName)
                .getResultList();
    }

    /**
     * Method that updates the first- and last name of a teacher with matching id.
     */
    public Teacher updateName(Long id, String firstName, String lastName) {
        Teacher teacher = entityManager.find(Teacher.class, id);
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        return entityManager.merge(teacher);
    }

    /**
     * Method that removes teacher with matching id from database.
     */
    public void removeTeacher(Long id) {
        Teacher teacher = entityManager.find(Teacher.class, id);
        entityManager.remove(teacher);
    }

    /**
     * Method that adds a subject to a teacher.
     */
    public Teacher addSubject(Long teacherId, Long subjectId) {
        Teacher teacher = entityManager.find(Teacher.class, teacherId);
        Subject subject = entityManager.find(Subject.class, subjectId);
        teacher.addSubject(subject);
        return entityManager.merge(teacher);
    }
}
