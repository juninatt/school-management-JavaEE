package se.iths.controller;

import se.iths.entity.Subject;
import se.iths.exceptions.exception.SubjectNotFoundException;
import se.iths.service.SubjectService;

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
 * The subject controller class.
 */
@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectController {


    /**
     * {@link SubjectService}
     */
    @Inject
    SubjectService subjectService;

    /**
     * Method that creates new subject.
     */
    @Path("")
    @POST
    public Response createSubject(Subject subject) {
        subjectService.createSubject(subject);
        return Response.ok(subject)
                .status(Response.Status.CREATED)
                .build();
    }

    /**
     * Method that returns subject with matching id, if exists.
     * Otherwise, throws exception.
     * {@link SubjectNotFoundException}
     */
    @Path("{id}")
    @GET
    public Response getSubject(@PathParam("id") Long id) {
        if (!exists(id))
            throw new SubjectNotFoundException();
        Subject subject = subjectService.getSubject(id);
        return Response.ok(subject)
                .header("Students", subject.getStudents())
                .build();
    }

    /**
     * Method that returns a list of all subjects.
     */
    @Path("")
    @GET
    public Response getSubjects() {
        List<Subject> subjects = subjectService.getSubjects();
        return Response.ok(subjects)
                .build();
    }

    /**
     * Method that returns a list of subjects with the matching points-value.
     * If list is empty throws exception.
     * {@link SubjectNotFoundException}
     */
    @Path("points")
    @GET
    public Response getSubject(@QueryParam("points") String points) {
        List<Subject> subjects = subjectService.getSubjects(points);
        if (subjects.isEmpty())
            throw new SubjectNotFoundException();
        return Response.ok(subjects)
                .build();
    }

    /**
     * Method that updates the name of a subject with matching id, if exists.
     * Otherwise, throws exception.
     * {@link SubjectNotFoundException}
     */
    @Path("name/{id}")
    @PATCH
    public Response updateName(@PathParam("id") Long id, @QueryParam("name") String name) {
        if (!exists(id))
            throw new SubjectNotFoundException();
        Subject subject = subjectService.updateName(id, name);
        return Response.ok(subject)
                .lastModified(Date.from(Instant.now()))
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    /**
     * Method that deletes subject with matching id, if exists.
     * Otherwise, throws exception.
     * {@link SubjectNotFoundException}
     */
    @Path("{id}")
    @DELETE
    public Response removeStudent(@PathParam("id") Long id) {
        if (!exists(id))
            throw new SubjectNotFoundException();
        subjectService.removeSubject(id);
        return Response.ok()
                .status(Response.Status.NO_CONTENT)
                .expires(Date.from(Instant.now()))
                .build();
    }

    /**
     * Method that returns a list of students enrolled in subject with matching id, if exists.
     * Otherwise, throws exception.
     * {@link SubjectNotFoundException}
     */
    @Path("{id}/students")
    @GET
    public Response getStudents(@PathParam("id") Long id) {
        if (!exists(id))
            throw new SubjectNotFoundException();
        Subject subject = subjectService.getSubject(id);
        return Response.ok(subject.getStudents())
                .build();
    }
    /**
     * Method that checks if a subject with given id-value exists in database.
     * 'isPresent' returns true if subject exists in database.
     */
    private boolean exists(Long id) {
        List<Subject> existingSubjects = subjectService.getSubjects();
        boolean isPresent = false;
        for (Subject s: existingSubjects) {
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
