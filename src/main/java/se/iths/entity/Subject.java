package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class Subject {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name="SUBJ_ID")
    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String points;
    @ManyToOne
    private Teacher teacher;
    @ManyToMany
    private Collection<Student> students;

    public Subject(String name, String points) {
        this.name = name;
        this.points = points;
    }
    public Subject() {}

    public void addStudent(Student student) {
        this.students.add(student);
    }
    public Collection<Student> getStudents() {
        return students;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id; }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPoints() {
        return points;
    }
    public void setPoints(String points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id= " + id +
                ", name'" + name + '\'' +
                ", points='" + points +
                '}';
    }
}
