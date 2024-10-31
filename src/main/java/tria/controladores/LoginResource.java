package tria.controladores;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tria.entidades.Login;
import tria.repositorios.LoginRepositorio;

import java.util.List;
import java.util.Optional;

@Path("login")
public class LoginResource {

    private final LoginRepositorio loginRepositorio = new LoginRepositorio();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Login> getLogins() {
        return loginRepositorio.listar();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoginById(@PathParam("id") int id) {
        Optional<Login> login = loginRepositorio.buscarPorId(id);
        if (login != null) {
            return Response.ok(login).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTarefa(Login login) {
        loginRepositorio.cadastrar(login);
        return Response.status(Response.Status.CREATED)
                .entity(loginRepositorio)
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogin(@PathParam("id") int id, Login login) {
        if (loginRepositorio.buscarPorId(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        loginRepositorio.atualizar(login, id);
        return Response.ok(login).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteLogin(@PathParam("id") int id) {
        if (loginRepositorio.buscarPorId(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        loginRepositorio.remover(id);
        return Response.noContent().build();
    }
}
