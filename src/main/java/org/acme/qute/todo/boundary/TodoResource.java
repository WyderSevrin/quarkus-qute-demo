package org.acme.qute.todo.boundary;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.core.Response;
import org.acme.qute.todo.entity.Todo;
import org.acme.qute.todo.entity.TodoForm;
import org.acme.qute.todo.control.TodoService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Path("/todo")
public class TodoResource {

    @Inject
    TodoService todoService;


    @CheckedTemplate(requireTypeSafeExpressions = false)
    public static class Templates {
        public static native TemplateInstance error(String message);
        public static native TemplateInstance todo(Todo todo, List<Integer> priorities, boolean update);
        public static native TemplateInstance todos(List<Todo> todos, long totalCount, List<Integer> priorities, String filter, boolean filtered,  boolean update);
    }

    final List<Integer> priorities = IntStream.range(1, 6).boxed().collect(Collectors.toList());

    @GET
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance listTodos(@QueryParam("filter") String filter) {

        return Templates.todos(find(filter), Todo.count(), priorities, filter, filter != null && !filter.isEmpty(), false);
    }



    private List<Todo> find(String filter) {
        if(todoService.read().isEmpty()) {
            todoService.add(new Todo(1,"title 1",1, false));
            todoService.add(new Todo(2,"title 2",2, false));
            todoService.add(new Todo(3,"title 3",3, false));
        }
        return todoService.read();
    }



    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/new")
    public Response addTodo(TodoForm todoForm) {
        Todo todo = todoForm.convertIntoTodo();
        todoService.add(todo);

        return Response.status(Response.Status.SEE_OTHER)
            .location(URI.create("/todo"))
            .build();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/{id}/edit")
    public TemplateInstance updateForm(@PathParam("id") long id) {
        Todo loaded = todoService.find((int)id);

        if (loaded == null) {
            return Templates.error("Todo with id " + id + " does not exist.");
        }

        return Templates.todo(loaded, priorities, true);
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/{id}/edit")
    public Object updateTodo(
        @PathParam("id") long id,
        TodoForm todoForm) {

        Todo loaded = todoService.find((int)id);

        if (loaded == null) {
            return Templates.error("Todo with id " + id + " has been deleted after loading this form.");
        }

        loaded = todoForm.updateTodo(loaded);

        return Response.status(301)
            .location(URI.create("/todo"))
            .build();
    }

    @POST
    @Path("/{id}/delete")
    public Response deleteTodo(@PathParam("id") int id) {
        Todo todo = todoService.find(id);
        todoService.delete(todo);

        return Response.status(301)
            .location(URI.create("/todo"))
            .build();
    }
}
