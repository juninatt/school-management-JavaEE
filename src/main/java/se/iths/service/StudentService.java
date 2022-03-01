package se.iths.service;

import se.iths.entity.Student;
import se.iths.exceptions.StudentNotFoundException;

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

    public List<Student> getStudents(String lastName) {
        return entityManager.createQuery(
                        "SELECT s FROM Student s WHERE s.lastName LIKE :name", Student.class)
                .setParameter("name", lastName)
                .getResultList();
    }

    public Student updateName(Long id, String firstName, String lastName) {
        Student student = entityManager.find(Student.class, id);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        student.setFirstName(firstName);
        student.setLastName(lastName);
        return entityManager.merge(student);
    }
    public void removeStudent(Long id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }
}
