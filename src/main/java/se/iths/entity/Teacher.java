package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * The teacher class that represents a teacher.
 * Teacher.getAll-query fetches all teachers currently stored in database.
 * Teacher.getByLastName-query fetches all teachers with given last name.
 */
@Entity
@NamedQueries(value= {
        @NamedQuery(name="Teacher.getAll", query="SELECT t FROM Teacher t ORDER BY t.lastName"),
        @NamedQuery(name="Teacher.getByLastName", query="SELECT t FROM Teacher t WHERE t.lastName = :name")
})
public class Teacher {

    /**
     * The id number of the teacher. Value generated automatically with each new instance of the class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Teacher first name.
     */
    @NotEmpty
    private String firstName;

    /**
     * Teacher last name.
     */
    @NotEmpty
    private String lastName;

    /**
     * Teacher email.
     */
    @NotEmpty
    private String email;

    /**
     * Teacher phone number.
     */
    private String phoneNumber;


    /**
     * A List of subjects{@link Subject} belonging to the teacher.
     * Each subject can only have one teacher.
     */
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Subject> subjects;

    /**
     * A constructor with 'firstName', 'lastName' and 'email' as parameters.
     */
    public Teacher(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * No-args constructor.
     */
    public Teacher() {
    }

    /**
     * Method that adds a student to the list of students belonging to the teacher.
     */
    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        subject.setTeacher(this);
    }

    /**
     * Method that returns all subjects belonging to the teacher.
     */
    @JsonbTransient
    public List<Subject> getSubjects() {
        return subjects;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
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
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
