package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Path("subject")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectController {


    @Inject
    SubjectService subjectService;

    @Path("create")
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
        Subject subject = subjectService.getSubject(id);
        return Response.ok(subject)
                .build();
    }
    public Response getSubjects() {
        List<Subject> subjects = subjectService.getSubjects();
        return Response.ok(subjects)
                .build();
    }
    @Path("points")
    @GET
    public Response getSubject(@QueryParam("points") String points) {
        List<Subject> subjects = subjectService.getSubjects(points);
        return Response.ok(subjects)
                .build();
    }
    @Path("name/{id}")
    @PATCH
    public Response updateName(@PathParam("id") Long id, @QueryParam("name") String name) {
        Subject subject = subjectService.updateName(id, name);
        return Response.ok(subject)
                .lastModified(Date.from(Instant.now()))
                .status(Response.Status.NO_CONTENT)
                .build();
    }
    @Path("{id}")
    @DELETE
    public Response removeStudent(@PathParam("id") Long id) {
        subjectService.removeSubject(id);
        return Response.ok()
                .status(Response.Status.NO_CONTENT)
                .expires(Date.from(Instant.now()))
                .build();
    }
}
