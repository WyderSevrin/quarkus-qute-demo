package org.acme.qute.teams.boundary;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.qute.teams.entity.ObjTemplateData;

@Path("/teams")
public class TeamsResource {

    @CheckedTemplate(requireTypeSafeExpressions = false)
    public static class Templates {
        public static native TemplateInstance page2(String message, ObjTemplateData objTemplateData);
    }

    @GET
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getPage2() {
        return TeamsResource.Templates.page2("page 2", new ObjTemplateData());
    }
}
