package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentController {

    @Inject
    StudentService studentService;

    @Path("new")
    @POST
    public Response createStudent(Student student) {
        studentService.createStudent(student);
        return Response.ok(student).build();
    }
    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        Student student = studentService.getStudent(id);
        return Response.ok(student).build();
    }
    @Path("")
    @GET
    public Response getStudents() {
        List<Student> students = studentService.getStudents();
        return Response.ok(students).build();
    }
    @Path("{id}")
    @DELETE
    public Response removeStudent(@PathParam("id") Long id) {
        studentService.removeStudent(id);
        return Response.ok().build();
    }
}

