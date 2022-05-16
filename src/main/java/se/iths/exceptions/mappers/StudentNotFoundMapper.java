package se.iths.exceptions.mappers;

import se.iths.entity.ExceptionMessage;
import se.iths.exceptions.exception.StudentNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Mapper class that handles customized logic for StudentNotFoundException.
 * {@link StudentNotFoundException}
 */
@Provider
public class StudentNotFoundMapper implements ExceptionMapper<StudentNotFoundException> {

    /**
     * Instance of{@link ExceptionMessage}
     */
    ExceptionMessage message = new ExceptionMessage("404", "Could not find student", "Additional information");

    /**
     * Method that sends the message object back with the response to the exception.
     */
    @Override
    public Response toResponse(StudentNotFoundException e) {
        return Response.ok(message)
                .status(404, "Student not found")
                .build();
    }
}
