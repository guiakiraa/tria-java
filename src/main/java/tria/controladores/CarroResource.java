package tria.controladores;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tria.entidades.Carro;
import tria.servicos.CarroServico;

import java.util.List;
import java.util.Optional;

@Path("carro")
public class CarroResource {

    private final CarroServico carroServico = new CarroServico();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Carro> getCarros() {
        return carroServico.Listar();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarroById(@PathParam("id") int id) {
        Optional<Carro> carro = carroServico.BuscarPorId(id);
        return carro.map(value -> Response.ok(value).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).entity("Carro não encontrado").build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCarro(Carro carro) {
        carroServico.Cadastrar(carro);
        return Response.status(Response.Status.CREATED).entity(carro).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCarro(@PathParam("id") int id, Carro carro) {
        Optional<Carro> _carro = carroServico.BuscarPorId(id);
        if (_carro.isPresent()) {
            carroServico.Atualizar(carro, id);
            return Response.ok(carro).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCarro(@PathParam("id") int id) {
        Optional<Carro> carro = carroServico.BuscarPorId(id);
        if (carro.isPresent()) {
            carroServico.Deletar(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
