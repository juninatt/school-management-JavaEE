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

/**
 * The teacher controller class.
 */
@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherController {

    /**
     * {@link TeacherService}
     */
    @Inject
    TeacherService teacherService;

    /**
     * Method that creates new teacher.
     */
    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {
        teacherService.createTeacher(teacher);
        return Response.ok(teacher)
                .lastModified(Date.from(Instant.now()))
                .status(Response.Status.CREATED)
                .build();
    }

    /**
     * Method that returns teacher with matching id, if exists.
     * Otherwise, throws exception.
     * {@link TeacherNotFoundException}
     */
    @Path("{id}")
    @GET
    public Response getTeacher(@PathParam("id") Long id) {
        if (!exists(id))
            throw new TeacherNotFoundException();
        Teacher teacher = teacherService.getTeacher(id);
        return Response.ok(teacher)
                .build();
    }

    /**
     * Mathod that returns a list of all teachers.
     */
    @Path("")
    @GET
    public Response getTeachers() {
        List<Teacher> teachers = teacherService.getTeachers();
        return Response.ok(teachers)
                .build();
    }

    /**
     * Method that returns a list of teachers with matching last name.
     * If list is empty throws exception.
     * {@link TeacherNotFoundException}
     */
    @Path("last-name")
    @GET
    public Response getTeachers(@QueryParam("last-name") String name) {
        List<Teacher> teachers = teacherService.getTeachers(name);
        if (teachers.isEmpty())
            throw new TeacherNotFoundException();
        return Response.ok(teachers)
                .build();
    }

    /**
     * Method that updates first- and last name of teacher with matching id, if exists.
     * Otherwise, throws exception.
     * {@link TeacherNotFoundException}
     */
    @Path("name/{id}")
    @PATCH
    public Response updateName(@PathParam("id") Long id, @QueryParam("first-name") String firstName,@QueryParam("last-name") String lastName) {
        if (!exists(id))
            throw new TeacherNotFoundException();
        Teacher teacher = teacherService.updateName(id, firstName, lastName);
        return Response.ok(teacher)
                .lastModified(Date.from(Instant.now()))
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    /**
     * Method that deletes teacher with matching id, if exists.
     * Otherwise, throws exception.
     * {@link TeacherNotFoundException}
     */
    @Path("{id}")
    @DELETE
    public Response removeTeacher(@PathParam("id") Long id) {
        if (!exists(id))
            throw new TeacherNotFoundException();
        teacherService.removeTeacher(id);
        return Response.ok()
                .status(Response.Status.NO_CONTENT)
                .expires(Date.from(Instant.now()))
                .build();
    }

    /**
     * Method that adds a subject to a teacher. If teacher doesn't exist, throws exception.
     * {@link TeacherNotFoundException}
     */
    @Path("addsubject/{id}")
    @PATCH
    public Response addSubject(@PathParam("id") Long teacherId, @QueryParam("subject") Long subjectId) {
        if (!exists(teacherId))
            throw new TeacherNotFoundException();
        teacherService.addSubject(teacherId, subjectId);
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
        List<Teacher> existingTeachers = teacherService.getTeachers();
        boolean isPresent = false;
        for (Teacher s: existingTeachers) {
            if (s.getId() == id) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
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
}
