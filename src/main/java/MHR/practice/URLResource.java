package MHR.practice;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.FOUND;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

import java.io.File;
import java.net.URI;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
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
    @Produces("text/html")
    public File serveStaticFile() {
        return new File("src/main/resources/META-INF/resources/index.html");
    }

    @GET
    @Produces("text/html")
    @Path("{shortUrl}")
    public Response getUrl(@PathParam("shortUrl") String shortUrl) {
        try {
            String rawUrl = service.find(shortUrl);
            return Response.status(FOUND).location(URI.create(rawUrl)).build();
        } catch (Exception e) {
            return Response.status(NOT_FOUND).build();
        }
    }

    @POST
    public Response convertURL(URLTO url) {
        URLTO existingURL = service.check(url);

        if (existingURL != null) {
            return Response.ok(existingURL).build();
        }

        URLTO newURL = service.generate(url);
        URI createdURI = uriInfo.getAbsolutePathBuilder().path(newURL.getShortURL()).build();
        return Response.created(createdURI).entity(newURL).build();
    }

    @DELETE
    @Path("{shortUrl}")
    public Response deleteUrl(@PathParam("shortUrl") String shortUrl) {
        try {
            service.delete(shortUrl);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(NOT_FOUND).build();
        }
    }
}
