package se.iths.controller;

import se.iths.entity.Teacher;
import se.iths.exceptions.exception.TeacherNotFoundException;
import se.iths.service.TeacherService;

import javax.ejb.DuplicateKeyException;
import javax.inject.Inject;
import javax.mail.MethodNotSupportedException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Path("teachers")
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
        if (!findDuplicate(id))
            throw new TeacherNotFoundException();
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
        if (teachers.isEmpty())
            throw new TeacherNotFoundException();
        return Response.ok(teachers)
                .build();
    }
    @Path("name/{id}")
    @PATCH
    public Response updateName(@PathParam("id") Long id, @QueryParam("first-name") String firstName,@QueryParam("last-name") String lastName) {
        if (!findDuplicate(id))
            throw new TeacherNotFoundException();
        Teacher teacher = teacherService.updateName(id, firstName, lastName);
        return Response.ok(teacher)
                .lastModified(Date.from(Instant.now()))
                .status(Response.Status.NO_CONTENT)
                .build();
    }
    @Path("{id}")
    @DELETE
    public Response removeTeacher(@PathParam("id") Long id) {
        if (!findDuplicate(id))
            throw new TeacherNotFoundException();
        teacherService.removeTeacher(id);
        return Response.ok()
                .status(Response.Status.NO_CONTENT)
                .expires(Date.from(Instant.now()))
                .build();
    }
    @Path("addsubject/{id}")
    @PATCH
    public Response addSubject(@PathParam("id") Long teacherId, @QueryParam("subject") Long subjectId) {
        if (!findDuplicate(teacherId))
            throw new TeacherNotFoundException();
        teacherService.addSubject(teacherId, subjectId);
        return Response.ok()
                .lastModified(Date.from(Instant.now()))
                .status(Response.Status.NO_CONTENT)
                .build();
    }
    private boolean findDuplicate(Long id) {
        List<Teacher> existingTeachers = teacherService.getTeachers();
        boolean conflict = false;
        for (Teacher s: existingTeachers) {
            if (s.getId() == id) {
                conflict = true;
                break;
            }
        }
        return conflict;
    }
    @Path("")
    @PATCH
    public void illegalPathUpdate() throws MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }
    @Path("{id}")
    @POST
    public void illegalPathCreate(@PathParam("id") Long id) throws DuplicateKeyException {
        if (findDuplicate(id))
            throw new DuplicateKeyException();
        throw new NotFoundException();
    }
    @Path("")
    @DELETE
    public void illegalPathDelete() throws MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }
}
