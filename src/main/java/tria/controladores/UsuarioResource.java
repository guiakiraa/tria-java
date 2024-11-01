package tria.controladores;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tria.entidades.Usuario;
import tria.servicos.UsuarioServico;

import java.util.List;
import java.util.Optional;

@Path("usuario")
public class UsuarioResource {

    private final UsuarioServico usuarioServico = new UsuarioServico();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getUsuarios() {
        return usuarioServico.Listar();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioById(@PathParam("id") int id) {
        Optional<Usuario> usuario = usuarioServico.BuscarPorId(id);
        return usuario.map(value -> Response.ok(value).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado").build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUsuario(Usuario usuario) {
        usuarioServico.Cadastrar(usuario);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUsuario(@PathParam("id") int id, Usuario usuario) {
        Optional<Usuario> _usuario = usuarioServico.BuscarPorId(id);
        if (_usuario.isPresent()) {
            usuarioServico.Atualizar(usuario, id);
            return Response.ok(usuario).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUsuario(@PathParam("id") int id) {
        Optional<Usuario> usuario = usuarioServico.BuscarPorId(id);
        if (usuario.isPresent()) {
            usuarioServico.Deletar(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
