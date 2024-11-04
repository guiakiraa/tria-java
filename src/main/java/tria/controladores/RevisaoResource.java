package tria.controladores;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tria.entidades.Revisao;
import tria.servicos.RevisaoServico;

import java.util.List;
import java.util.Optional;

@Path("revisao")
public class RevisaoResource {
    private final RevisaoServico revisaoServico = new RevisaoServico();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Revisao> getLogins() {
        return revisaoServico.Listar();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoginById(@PathParam("id") int id) {
        Optional<Revisao> revisao = revisaoServico.BuscarPorId(id);
        if (revisao.isPresent()) {
            return Response.ok(revisao.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Revisão não encontrado").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLogin(Revisao revisao) {
        revisaoServico.Cadastrar(revisao);
        return Response.status(Response.Status.CREATED)
                .entity(revisaoServico)
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogin(@PathParam("id") int id, Revisao revisao) {
        Optional<Revisao> _revisao = revisaoServico.BuscarPorId(id);
        if (_revisao.isPresent()) {
            revisaoServico.Atualizar(revisao, id);
            return Response.ok(revisao).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteLogin(@PathParam("id") int id) {
        Optional<Revisao> revisao = revisaoServico.BuscarPorId(id);
        if (revisao.isPresent()) {
            revisaoServico.Deletar(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
