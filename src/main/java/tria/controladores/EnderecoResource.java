package tria.controladores;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tria.entidades.Endereco;
import tria.servicos.EnderecoServico;

import java.util.List;
import java.util.Optional;

@Path("endereco")
public class EnderecoResource {

    private final EnderecoServico enderecoServico = new EnderecoServico();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Endereco> getEnderecos() {
        return enderecoServico.Listar();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnderecoById(@PathParam("id") int id) {
        Optional<Endereco> endereco = enderecoServico.BuscarPorId(id);
        return endereco.map(value -> Response.ok(value).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).entity("Endereço não encontrado").build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEndereco(Endereco endereco) {
        enderecoServico.Cadastrar(endereco);
        return Response.status(Response.Status.CREATED).entity(endereco).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEndereco(@PathParam("id") int id, Endereco endereco) {
        Optional<Endereco> _endereco = enderecoServico.BuscarPorId(id);
        if (_endereco.isPresent()) {
            enderecoServico.Atualizar(endereco, id);
            return Response.ok(endereco).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteEndereco(@PathParam("id") int id) {
        Optional<Endereco> endereco = enderecoServico.BuscarPorId(id);
        if (endereco.isPresent()) {
            enderecoServico.Deletar(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
