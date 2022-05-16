package se.iths.exceptions.mappers;

import se.iths.entity.ExceptionMessage;
import se.iths.exceptions.exception.TeacherNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Mapper class that handles customized logic for TeacherNotFoundException.
 * {@link TeacherNotFoundException}
 */
public class TeacherNotFoundMapper implements ExceptionMapper<TeacherNotFoundException> {

    ExceptionMessage message = new ExceptionMessage("404", "Could not find teacher", "Additional information");

    @Override
    public Response toResponse(TeacherNotFoundException e) {
        return Response.ok(message)
                .status(404, "Teacher not found")
                .build();
    }
}
