package MHR.practice;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

import java.net.URI;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class URLResource {

    @Inject
    UriInfo uriInfo;
    @Inject
    URLService service;

    @GET
    @Path("{shortUrl}")
    public Response getUrl(@PathParam("shortUrl") String shortUrl) {
        String rawUrl = service.find(shortUrl);
        if (rawUrl == null) {
            return Response.status(NOT_FOUND).build();
        }
        return Response.ok(rawUrl).build();
    }

    @POST
    public Response convertURL(URL url) {
        URL existingURL = service.check(url);

        if (existingURL != null) {

            return Response.ok(existingURL).build();
        }

        URL newURL = service.generate(url);
        URI createdURI = uriInfo.getAbsolutePathBuilder().path(newURL.getShortURL()).build();

        return Response.created(createdURI).entity(newURL).build();
    }

}
