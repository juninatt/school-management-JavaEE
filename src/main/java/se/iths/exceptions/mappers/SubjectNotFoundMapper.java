package se.iths.exceptions.mappers;

import se.iths.entity.ExceptionMessage;
import se.iths.exceptions.exception.SubjectNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class SubjectNotFoundMapper implements ExceptionMapper<SubjectNotFoundException> {

    ExceptionMessage message = new ExceptionMessage("404", "Could not find subject", "Additional information");

    @Override
    public Response toResponse(SubjectNotFoundException e) {
        return Response.ok(message)
                .status(404, "Subject not found")
                .build();
    }
}
