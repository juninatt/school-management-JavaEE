package se.iths.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnknownLocationMapper implements ExceptionMapper<UnknownLocationException> {

    @Override
    public Response toResponse(UnknownLocationException e) {
        return Response.status(418, "Where").build();
    }
}
