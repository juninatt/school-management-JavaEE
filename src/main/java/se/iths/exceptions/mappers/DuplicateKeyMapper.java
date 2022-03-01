package se.iths.exceptions.mappers;

import se.iths.entity.ExceptionMessage;

import javax.ejb.DuplicateKeyException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DuplicateKeyMapper implements ExceptionMapper<DuplicateKeyException> {

    ExceptionMessage message = new ExceptionMessage("409", "Student-id already exists", "Have a nice day!");

    @Override
    public Response toResponse(DuplicateKeyException e) {
        return Response.ok(message)
                .status(409, "Conflict")
                .build();
    }
}
