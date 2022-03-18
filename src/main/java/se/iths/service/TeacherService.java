package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public Teacher createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
        return teacher;
    }
    public Teacher getTeacher(Long id) {
        return entityManager.find(Teacher.class, id);
    }
    public List<Teacher> getTeachers() {
        return entityManager.createNamedQuery("Teacher.getAll", Teacher.class).getResultList();
    }
    public List<Teacher> getTeachers(String lastName) {
        return entityManager.createNamedQuery("Teacher.getByLastName", Teacher.class)
                .setParameter("name", lastName)
                .getResultList();
    }
    public Teacher addSubject(Long teacherId, Long subjectId) {
        Teacher teacher = entityManager.find(Teacher.class, teacherId);
        Subject subject = entityManager.find(Subject.class, subjectId);
        teacher.addSubject(subject);
        return entityManager.merge(teacher);
    }
    public Teacher updateName(Long id, String firstName, String lastName) {
        Teacher teacher = entityManager.find(Teacher.class, id);
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        return entityManager.merge(teacher);
    }
    public void removeTeacher(Long id) {
        Teacher teacher = entityManager.find(Teacher.class, id);
        entityManager.remove(teacher);
    }
}
