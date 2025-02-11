package org.acme.qute.users.boundary;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.qute.users.control.UserService;
import org.acme.qute.users.entity.Users;

import java.util.List;

@Path("users")
public class UsersResource {

    @Inject
    UserService userService;

    @CheckedTemplate(requireTypeSafeExpressions = false)
    public static class Templates {
        public static native TemplateInstance users(List<Users> users);
    }

    @GET
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        List<Users> users = userService.getAll();

        return UsersResource.Templates.users(users);
    }




}
