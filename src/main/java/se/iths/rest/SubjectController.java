package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
}
