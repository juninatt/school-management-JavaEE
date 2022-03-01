package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exceptions.StudentNotFoundException;
import se.iths.service.StudentService;


import javax.ejb.DuplicateKeyException;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Path("student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentController {

    @Inject
    StudentService studentService;

    @Path("")
    @POST
    public Response createStudent(Student student) {
        studentService.createStudent(student);
        return Response.ok(student)
                .lastModified(Date.from(Instant.now()))
                .status(Response.Status.CREATED)
                .build();
    }
    @Path("{id}")
    @POST
    public void illegalPathCreate(@PathParam("id") Long id) throws DuplicateKeyException {
        if (findDuplicate(id))
            throw new DuplicateKeyException();
        throw new NotFoundException();
    }

    private boolean findDuplicate(Long id) {
        List<Student> existingStudents = studentService.getStudents();
        boolean conflict = false;
        for (Student s: existingStudents) {
            if (s.getId().equals(id)) {
                conflict = true;
                break;
            }
        }
        return conflict;
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        if (!findDuplicate(id))
            throw new StudentNotFoundException();
        Student student = studentService.getStudent(id);
        return Response.ok(student)
                .build();
    }
    @Path("")
    @GET
    public Response getStudents() {
        List<Student> students = studentService.getStudents();
        return Response.ok(students)
                .build();
    }
    @Path("findbyname")
    @GET
    public Response getStudents(@QueryParam("last-name") String name) {
        List<Student> students = studentService.getStudents(name);
        return Response.ok(students)
                .build();
    }
    @Path("name/{id}")
    @PATCH
    public Response updateName(@PathParam("id") Long id, @QueryParam("first-name") String firstName,@QueryParam("last-name") String lastName) {
        Student student = studentService.updateName(id, firstName, lastName);
        return Response.ok(student)
                .lastModified(Date.from(Instant.now()))
                .status(Response.Status.NO_CONTENT)
                .build();
    }
    @Path("{id}")
    @DELETE
    public Response removeStudent(@PathParam("id") Long id) {
        studentService.removeStudent(id);
        return Response.ok()
                .status(Response.Status.NO_CONTENT)
                .expires(Date.from(Instant.now()))
                .build();
    }
}

