package se.iths.util;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class that loads sample data to test the application.
 */
@Singleton
@Startup
public class  SampleData {

    /**
     *  Used to interact with the persistence context.
     * @see EntityManager
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Generates test data.
     */
    @PostConstruct
    public void generateData() {

        Student stud1 = new Student("Johannes", "Döparen", "j_dope@iths.se");
        Student stud2 = new Student("Niels", "Bohr", "bohrium@iths.se");
        Student stud3 = new Student("Margaret", "Sanger", "mychoise@iths.se");
        Student stud4 = new Student("Jane", "Calamity", "cj@iths.se");
        Student stud5 = new Student("Petter", "Bergström", "petter.berstron@iths.se");

        Subject sub1 = new Subject("Survival", "100.0");
        Subject sub2 = new Subject("Cooking", "15.0");
        Subject sub3 = new Subject("Math", "75.0");
        Subject sub4 = new Subject("Programming", "1000.0");
        Subject sub5 = new Subject("Teapotting", "500.0");

        Teacher tea1 = new Teacher("Karl", "Marx", "madmax@iths.se");
        Teacher tea2 = new Teacher("Adam", "Smith", "as_0@iths.se");
        Teacher tea3 = new Teacher("Augustus", "Caesar", "imperator_caesar_divi_filius_augustus@iths.se");
        Teacher tea4 = new Teacher("Екатери́на", "Алексе́евна", "cath_the_great@iths.se");
        Teacher tea5 = new Teacher("Mileva", "Einstein-Maric","steinmar@iths.se");

        entityManager.persist(stud1);
        entityManager.persist(stud2);
        entityManager.persist(stud3);
        entityManager.persist(stud4);
        entityManager.persist(stud5);

        entityManager.persist(sub1);
        entityManager.persist(sub2);
        entityManager.persist(sub3);
        entityManager.persist(sub4);
        entityManager.persist(sub5);

        entityManager.persist(tea1);
        entityManager.persist(tea2);
        entityManager.persist(tea3);
        entityManager.persist(tea4);
        entityManager.persist(tea5);
    }

}
