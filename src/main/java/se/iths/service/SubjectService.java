package se.iths.service;

import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public Subject createSubject(Subject subject) {
        entityManager.persist(subject);
        return subject;
    }
    public Subject getSubject(Long id) { return entityManager.find(Subject.class, id); }
    public List<Subject> getSubjects() {
        return entityManager.createNamedQuery("Subject.getAll", Subject.class).getResultList();
    }
    public List<Subject> getSubjects(String points) {
        return entityManager.createNamedQuery("Subject.getByPoints", Subject.class)
                .setParameter("points", points)
                .getResultList();
    }
    public Subject updateName(Long id, String name) {
        Subject subject = entityManager.find(Subject.class, id);
        subject.setName(name);
        return entityManager.merge(subject);
    }
    public void removeSubject(Long id) {
        Subject subject = entityManager.find(Subject.class, id);
        entityManager.remove(subject);
    }
}
