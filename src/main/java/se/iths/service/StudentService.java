package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;

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
        return entityManager.createNamedQuery("Student.getAll", Student.class).getResultList();
    }
    public List<Student> getStudents(String lastName) {
        return entityManager.createNamedQuery("Student.getByLastName", Student.class)
                .setParameter("name", lastName)
                .getResultList();
    }
    public Student updateName(Long id, String firstName, String lastName) {
        Student student = entityManager.find(Student.class, id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        return entityManager.merge(student);
    }
    public void removeStudent(Long id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }

    public Student addSubject(Long studentId, Long subjectId) {
        Student student = entityManager.find(Student.class, studentId);
        Subject subject = entityManager.find(Subject.class, subjectId);
        student.addSubject(subject);
        return entityManager.merge(student);
    }
}
