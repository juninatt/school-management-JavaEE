package se.iths.service;

import se.iths.entity.Student;
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
        return entityManager.createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList();
    }
    public List<Teacher> getTeachers(String lastName) {
        return entityManager.createQuery(
                        "SELECT t FROM Teacher t WHERE t.lastName LIKE :name", Teacher.class)
                .setParameter("name", lastName)
                .getResultList();
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
