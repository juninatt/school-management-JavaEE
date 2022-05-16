package se.iths.controller;

import se.iths.entity.Student;
import se.iths.exceptions.exception.StudentNotFoundException;
import se.iths.service.StudentService;

import javax.ejb.DuplicateKeyException;
import javax.inject.Inject;
import javax.mail.MethodNotSupportedException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * The student controller class.
 */
@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentController {

    /**
     * {@link StudentService}
     */
    @Inject
    StudentService studentService;

    /**
     * Method that creates new student
     */
    @Path("")
    @POST
    public Response createStudent(Student student) {
        studentService.createStudent(student);
        return Response.ok(student)
                .lastModified(Date.from(Instant.now()))
                .status(Response.Status.CREATED)
                .build();
    }

    /**
     * Method that returns student with matching id if exists.
     * Otherwise, throws exception.
     * {@link StudentNotFoundException}
     */
    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        if (!exists(id))
            throw new StudentNotFoundException();
        Student student = studentService.getStudent(id);
        return Response.ok(student)
                .build();
    }

    /**
     * Method that returns a list of all students.
     */
    @Path("")
    @GET
    public Response getStudents() {
        List<Student> students = studentService.getStudents();
        return Response.ok(students)
                .build();
    }

    /**
     * Method that return a list of students with matching last name.
     * If list is empty throws exception.
     * {@link StudentNotFoundException}
     */
    @Path("last-name")
    @GET
    public Response getStudents(@QueryParam("last-name") String name) {
        List<Student> students = studentService.getStudents(name);
        if (students.isEmpty())
            throw new StudentNotFoundException();
        return Response.ok(students)
                .build();
    }

    /**
     * Method that updates first- and last name of a student with matching id, if exists.
     * Otherwise, throws exception.
     * {@link StudentNotFoundException}
     */
    @Path("name/{id}")
    @PATCH
    public Response updateName(@PathParam("id") Long id, @QueryParam("first-name") String firstName,@QueryParam("last-name") String lastName) {
        if (!exists(id))
            throw new StudentNotFoundException();
        Student student = studentService.updateName(id, firstName, lastName);
        return Response.ok(student)
                .lastModified(Date.from(Instant.now()))
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    /**
     * Method that deletes student with matching id, if exists.
     * Otherwise, throws exception.
     * {@link StudentNotFoundException}
     */
    @Path("{id}")
    @DELETE
    public Response removeStudent(@PathParam("id") Long id) {
        if (!exists(id))
            throw new StudentNotFoundException();
        studentService.removeStudent(id);
        return Response.ok()
                .status(Response.Status.NO_CONTENT)
                .expires(Date.from(Instant.now()))
                .build();
    }

    /**
     * Method that adds a subject to a student. If student doesn't exist, throws exception.
     * {@link StudentNotFoundException}
     */
    @Path("addsubject/{id}")
    @PATCH
    public Response addSubject(@PathParam("id") Long studentId, @QueryParam("subject") Long subjectId) {
        if (!exists(studentId))
            throw new StudentNotFoundException();
        studentService.addSubject(studentId, subjectId);
        return Response.ok()
                .lastModified(Date.from(Instant.now()))
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    /**
     * Method that checks if a student with given id-value exists in database.
     * 'isPresent' returns true if student exists in database.
     */
    private boolean exists(Long id) {
        boolean isPresent = false;
        List<Student> existingStudents = studentService.getStudents();
        for (Student s: existingStudents) {
            if (s.getId().equals(id)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    /**
     * Method that throws exception for unsupported create requests.
     * @see DuplicateKeyException
     */
    @Path("{id}")
    @POST
    public void illegalPathCreate(@PathParam("id") Long id) throws DuplicateKeyException {
        if (exists(id))
            throw new DuplicateKeyException();
        throw new NotFoundException();
    }

    /**
     * Method that throws exception for unsupported delete requests.
     * @see MethodNotSupportedException
     */
    @Path("")
    @DELETE
    public void illegalPathDelete() throws MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }

    /**
     * Method that throws exception for unsupported update requests.
     * @see MethodNotSupportedException
     */
    @Path("")
    @PATCH
    public void illegalPathUpdate() throws MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }
}

