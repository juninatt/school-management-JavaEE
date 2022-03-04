package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Path("teacher")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherController {

    @Inject
    TeacherService teacherService;

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {
        teacherService.createTeacher(teacher);
        return Response.ok(teacher)
                .lastModified(Date.from(Instant.now()))
                .status(Response.Status.CREATED)
                .build();
    }
    @Path("{id}")
    @GET
    public Response getTeacher(@PathParam("id") Long id) {
        Teacher teacher = teacherService.getTeacher(id);
        return Response.ok(teacher)
                .build();
    }
    @Path("")
    @GET
    public Response getTeachers() {
        List<Teacher> teachers = teacherService.getTeachers();
        return Response.ok(teachers)
                .build();
    }
    @Path("last-name")
    @GET
    public Response getTeachers(@QueryParam("last-name") String name) {
        List<Teacher> teachers = teacherService.getTeachers(name);
        return Response.ok(teachers)
                .build();
    }
    @Path("name/{id}")
    @PATCH
    public Response updateName(@PathParam("id") Long id, @QueryParam("first-name") String firstName,@QueryParam("last-name") String lastName) {
        Teacher teacher = teacherService.updateName(id, firstName, lastName);
        return Response.ok(teacher)
                .lastModified(Date.from(Instant.now()))
                .status(Response.Status.NO_CONTENT)
                .build();
    }
    @Path("{id}")
    @DELETE
    public Response removeTeacher(@PathParam("id") Long id) {
        teacherService.removeTeacher(id);
        return Response.ok()
                .status(Response.Status.NO_CONTENT)
                .expires(Date.from(Instant.now()))
                .build();
    }
}
