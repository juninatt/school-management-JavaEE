package se.iths.exceptions.mappers;

import se.iths.entity.ExceptionMessage;
import se.iths.exceptions.StudentNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StudentNotFoundMapper implements ExceptionMapper<StudentNotFoundException> {

    ExceptionMessage message = new ExceptionMessage("404", "Could not find student", "Additional information");

    @Override
    public Response toResponse(StudentNotFoundException e) {
        return Response.ok(message)
                .status(404, "Student not found")
                .build();
    }
}
