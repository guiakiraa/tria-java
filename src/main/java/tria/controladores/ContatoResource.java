package tria.controladores;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tria.entidades.Contato;
import tria.servicos.ContatoServico;

import java.util.List;
import java.util.Optional;

@Path("contato")
public class ContatoResource {

    private final ContatoServico contatoServico = new ContatoServico();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contato> getContatos() {
        return contatoServico.Listar();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContatoById(@PathParam("id") int id) {
        Optional<Contato> contato = contatoServico.BuscarPorId(id);
        return contato.map(value -> Response.ok(value).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).entity("Contato não encontrado").build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addContato(Contato contato) {
        contatoServico.Cadastrar(contato);
        return Response.status(Response.Status.CREATED).entity(contato).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateContato(@PathParam("id") int id, Contato contato) {
        Optional<Contato> _contato = contatoServico.BuscarPorId(id);
        if (_contato.isPresent()) {
            contatoServico.Atualizar(contato, id);
            return Response.ok(contato).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteContato(@PathParam("id") int id) {
        Optional<Contato> contato = contatoServico.BuscarPorId(id);
        if (contato.isPresent()) {
            contatoServico.Deletar(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
