package se.iths.service;

import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * The subject service class that is responsible for the persistence logic of the subject-class.
 */
@Transactional
public class SubjectService {

    /**
     *  Used to interact with the persistence context.
     * @see EntityManager
     */
    @PersistenceContext
    EntityManager entityManager;


    /**
     * Method that saves a new subject to the database.
     */
    public Subject createSubject(Subject subject) {
        entityManager.persist(subject);
        return subject;
    }

    /**
     * Method that returns the subject with matching id.
     */
    public Subject getSubject(Long id) { return entityManager.find(Subject.class, id); }


    /**
     * Method that returns a list of all student in database.
     */
    public List<Subject> getSubjects() {
        return entityManager.createNamedQuery("Subject.getAll", Subject.class).getResultList();
    }

    /**
     * Method that returns all subject with matching points-value.
     */
    public List<Subject> getSubjects(String points) {
        return entityManager.createNamedQuery("Subject.getByPoints", Subject.class)
                .setParameter("points", points)
                .getResultList();
    }

    /**
     * Method that updates the name of a subject with matching id.
     */
    public Subject updateName(Long id, String name) {
        Subject subject = entityManager.find(Subject.class, id);
        subject.setName(name);
        return entityManager.merge(subject);
    }

    /**
     * Method that removes subject with matching id from database.
     */
    public void removeSubject(Long id) {
        Subject subject = entityManager.find(Subject.class, id);
        entityManager.remove(subject);
    }
}
