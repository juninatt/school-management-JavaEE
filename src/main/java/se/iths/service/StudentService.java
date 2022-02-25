package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public Student createStudent(Student student) {
        entityManager.persist(student);
        return  student;
    }

    public Student getStudent(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> getStudents() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    public void removeStudent(Long id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }
}
