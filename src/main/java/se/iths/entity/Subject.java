package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Subject {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String points;
    @ManyToOne
    private Teacher teacher;

    public Subject(String name, String points) {
        this.name = name;
        this.points = points;
    }
    public Subject() {}

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
