package br.com.gilbertopapa.webservice.resources;

import br.com.gilbertopapa.webservice.model.domain.Projeto;
import br.com.gilbertopapa.webservice.service.RelationshipService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class EmpregadoProjetoResource {
    private RelationshipService service = new RelationshipService();

    @POST
    @Path("{projetoId}")
    public Response save(@PathParam("projetoId") long projetoId, @PathParam("empregadoId") long empregadoId) {
        service.saveRelationshipProjetoEmpregado(projetoId, empregadoId);
        return Response.noContent().build();
    }

    @GET
    public List<Projeto> getProjetos(@PathParam("empregadoId") long empregadoId) {
        return service.getProjetos(empregadoId);
    }

    @DELETE
    @Path("{projetoId}")
    public Response delete(@PathParam("projetoId") long projetoId, @PathParam("empregadoId") long empregadoId) {
        service.deleteRelationshipProjetoEmpregado(projetoId, empregadoId);
        return Response.noContent().build();
    }
}
