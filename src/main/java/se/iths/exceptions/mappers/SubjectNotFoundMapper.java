package se.iths.exceptions.mappers;

import se.iths.entity.ExceptionMessage;
import se.iths.exceptions.exception.SubjectNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Mapper class that handles customized logic for SubjectNotFoundException.
 * {@link SubjectNotFoundMapper}
 */
public class SubjectNotFoundMapper implements ExceptionMapper<SubjectNotFoundException> {

    /**
     * Instance of{@link ExceptionMessage}
     */
    ExceptionMessage message = new ExceptionMessage("404", "Could not find subject", "Additional information");

    /**
     * Method that sends the message object back with the response to the exception.
     */
    @Override
    public Response toResponse(SubjectNotFoundException e) {
        return Response.ok(message)
                .status(404, "Subject not found")
                .build();
    }
}
