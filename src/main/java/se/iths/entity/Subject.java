package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

/**
 * The subject class that represents a subject.
 * Subject.getAll-query fetches all subjects currently stored in database.
 * Subject.getByLastName-query fetches all subjects with given last name.
 */
@Entity
@NamedQueries(value= {
        @NamedQuery(name="Subject.getAll", query="SELECT s FROM Subject s ORDER BY s.name"),
        @NamedQuery(name="Subject.getByPoints", query="SELECT s FROM Subject s WHERE s.points = :points")
})
public class Subject {

    /**
     * The id number of the subject. Value generated automatically with each new instance of the class.
     */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name="SUBJ_ID")
    private Long id;

    /**
     * Subject name
     */
    @NotEmpty
    private String name;
    /**
     * Number of points a student gets for completing the subject.
     */
    @NotEmpty
    private String points;

    /**
     * Each subject is held by one teacher {@link Teacher}
     * One teacher can have many subjects.
     */
    @ManyToOne
    private Teacher teacher;

    /**
     * A collection of students belonging to the teacher.
     * It is a many-to-many relationship, owned by the student side.
     */
    @ManyToMany
    private Collection<Student> students;

    /**
     * Constructor with 'name' and 'points' as parameters
     */
    public Subject(String name, String points) {
        this.name = name;
        this.points = points;
    }

    /**
     * No-args constructor.
     */
    public Subject() {}

    /**
     * Method that adds a student to the collection of students belonging to the subject.
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Method that returns all students belonging to the subject.
     */
    @JsonbTransient
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
