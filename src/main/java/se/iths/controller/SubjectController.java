package se.iths.controller;

import se.iths.entity.Subject;
import se.iths.exceptions.SubjectNotFoundException;
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

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectController {


    @Inject
    SubjectService subjectService;

    @Path("")
    @POST
    public Response createSubject(Subject subject) {
        subjectService.createSubject(subject);
        return Response.ok(subject)
                .status(Response.Status.CREATED)
                .build();
    }

    @Path("{id}")
    @GET
    public Response getSubject(@PathParam("id") Long id) {
        if (!findDuplicate(id))
            throw new SubjectNotFoundException();
        Subject subject = subjectService.getSubject(id);
        return Response.ok(subject)
                .header("Students", subject.getStudents())
                .build();
    }

    @Path("")
    @GET
    public Response getSubjects() {
        List<Subject> subjects = subjectService.getSubjects();
        return Response.ok(subjects)
                .build();
    }

    @Path("points")
    @GET
    public Response getSubject(@QueryParam("points") String points) {
        List<Subject> subjects = subjectService.getSubjects(points);
        if (subjects.isEmpty())
            throw new SubjectNotFoundException();
        return Response.ok(subjects)
                .build();
    }

    @Path("name/{id}")
    @PATCH
    public Response updateName(@PathParam("id") Long id, @QueryParam("name") String name) {
        if (!findDuplicate(id))
            throw new SubjectNotFoundException();
        Subject subject = subjectService.updateName(id, name);
        return Response.ok(subject)
                .lastModified(Date.from(Instant.now()))
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @Path("{id}")
    @DELETE
    public Response removeStudent(@PathParam("id") Long id) {
        if (!findDuplicate(id))
            throw new SubjectNotFoundException();
        subjectService.removeSubject(id);
        return Response.ok()
                .status(Response.Status.NO_CONTENT)
                .expires(Date.from(Instant.now()))
                .build();
    }

    @Path("{id}/students")
    @GET
    public Response getStudents(@PathParam("id") Long id) {
        if (!findDuplicate(id))
            throw new SubjectNotFoundException();
        Subject subject = subjectService.getSubject(id);
        return Response.ok(subject.getStudents())
                .build();
    }

    private boolean findDuplicate(Long id) {
        List<Subject> existingSubjects = subjectService.getSubjects();
        boolean conflict = false;
        for (Subject s: existingSubjects) {
            if (s.getId().equals(id)) {
                conflict = true;
                break;
            }
        }
        return conflict;
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
    @Path("")
    @PATCH
    public void illegalPathUpdate() throws MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }
}
