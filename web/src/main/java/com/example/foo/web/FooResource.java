package com.example.foo.web;

import com.example.foo.Application;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class FooResource {

    @Inject
    Application app;

    @GET
    @Path("/foo")
    public Response foo() {
        return Response.ok().entity(app.getBarMessage()).build();
    }
}
