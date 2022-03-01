package se.iths.exceptions.mappers;

import se.iths.entity.ExceptionMessage;

import javax.mail.MethodNotSupportedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MethodNotAllowedMapper implements ExceptionMapper<MethodNotSupportedException> {

    ExceptionMessage message = new ExceptionMessage("405", "Method not allowed", "Have a nice day!");

    @Override
    public Response toResponse(MethodNotSupportedException e) {
        return Response.ok(message)
                .status(405, "Bad method")
                .build();
    }
}
