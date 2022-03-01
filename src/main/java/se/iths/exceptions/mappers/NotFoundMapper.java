package se.iths.exceptions.mappers;

import se.iths.entity.ExceptionMessage;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundMapper implements ExceptionMapper<NotFoundException> {

    ExceptionMessage message = new ExceptionMessage("404", "Not Found", "Have a nice day!");

    @Override
    public Response toResponse(NotFoundException e) {
        return Response.ok(message)
                .status(404, "Not found")
                .build();
    }
}