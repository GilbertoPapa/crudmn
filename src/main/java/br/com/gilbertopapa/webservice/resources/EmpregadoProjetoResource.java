package br.com.gilbertopapa.webservice.resources;

import br.com.gilbertopapa.webservice.model.domain.Empregado;
import br.com.gilbertopapa.webservice.service.RelationshipService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class EmpregadoProjetoResource {

    private RelationshipService service = new RelationshipService();

    @GET
    public List<Empregado> getEmpregados(@PathParam("projetoId")
                                                 long projetoId) {
        return service.getEmpregados(projetoId);
    }


    @DELETE
    @Path("{empregadoId}")
    public Response delete(@PathParam("projetoId") long projetoId,
                           @PathParam("empregadoId") long empregadoId) {
        service.deleteRelationshipProjetoEmpregado(projetoId, empregadoId);
        return Response.noContent().build();
    }
}
