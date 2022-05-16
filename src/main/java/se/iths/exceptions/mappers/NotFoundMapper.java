package se.iths.exceptions.mappers;

import se.iths.entity.ExceptionMessage;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Mapper class that handles customized logic for NotFoundException.
 * @see NotFoundException
 */
@Provider
public class NotFoundMapper implements ExceptionMapper<NotFoundException> {

    /**
     * Instance of{@link ExceptionMessage}
     */
    ExceptionMessage message = new ExceptionMessage("404", "Not Found", "Have a nice day!");

    /**
     * Method that sends the message object back with the response to the exception.
     */
    @Override
    public Response toResponse(NotFoundException e) {
        return Response.ok(message)
                .status(404, "Not found")
                .build();
    }
}
