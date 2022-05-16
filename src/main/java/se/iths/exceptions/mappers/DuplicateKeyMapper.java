package se.iths.exceptions.mappers;

import se.iths.entity.ExceptionMessage;

import javax.ejb.DuplicateKeyException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Mapper class that handles customized logic for DuplicateKeyException.
 * @see ExceptionMapper
 * @see DuplicateKeyException
 */
@Provider
public class DuplicateKeyMapper implements ExceptionMapper<DuplicateKeyException> {

    /**
     * Instance of{@link ExceptionMessage}.
     */
    ExceptionMessage message = new ExceptionMessage("409", "Id already exists", "Have a nice day!");

    /**
     * Method that sends the message object back with the response to the exception.
     */
    @Override
    public Response toResponse(DuplicateKeyException e) {
        return Response.ok(message)
                .status(409, "Conflict")
                .build();
    }
}
