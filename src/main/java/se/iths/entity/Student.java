package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;


/**
 * The student class that represents a student.
 * Student.getAll-query fetches all students currently stored in database.
 * Student.getByLastName-query fetches all students with given last name.
 */
@Entity
@NamedQueries(value= {
        @NamedQuery(name="Student.getAll", query="SELECT s FROM Student s ORDER BY s.lastName"),
        @NamedQuery(name="Student.getByLastName", query="SELECT s FROM Student s WHERE s.lastName = :name")
})
public class Student {

    /**
     * The id number of the student. Value generated automatically with each new instance of the class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUD_ID")
    private Long id;

    /**
     * Student first name.
     */
    @NotEmpty
    private String firstName;

    /**
     * Student last name.
     */
    @NotEmpty
    private String lastName;

    /**
     * Student email.
     */
    @NotEmpty
    private String email;

    /**
     * Student phone number.
     */
    private String phoneNumber;

    /**
     * A collection of subjects{@link Subject} belonging to the student.
     * It is a many-to-many relationship, owned by the student side.
     */
    @ManyToMany(mappedBy = "students")
    @JoinTable(name="STUD_SUBJ",
    joinColumns = @JoinColumn(name="STUD_ID"),
    inverseJoinColumns = @JoinColumn(name="SUBJ_ID"))
    private Collection<Subject> subjects;

    /**
     * Constructor with 'firstName', 'lastName' and 'email' as parameters.
     */
    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * No-args constructor
     */
    public Student() {
    }

    /**
     * Method that adds a subject to the student collection of subjects,
     * and adds the student to the subject collection of students.
     */
    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        subject.addStudent(this);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
