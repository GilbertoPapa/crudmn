package br.com.gilbertopapa.webservice.resources;

import br.com.gilbertopapa.webservice.service.RelationshipService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/projetos")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ProjetoResource {



    @POST
    @Path("{projetoId}")
    public Response save( @PathParam("projetoId") long projetoId,
                          @PathParam("empregadoId") long empregadoId) {
        service.saveRelationshipProjetoEmpregado(projetoId, empregadoId);
        return Response.noContent().build();
    }


    @Path("{empregadoId}/projetos")
    public EmpregadoProjetoResource getEmpregadoProjetoResource() {
        return new EmpregadoProjetoResource();
    }

    private RelationshipService service = new RelationshipService();

    @GET
    public void testeSubrecurso() {
        System.out.println("Testando subrecurso!");
    }

    @Path("{projetoId}/empregados")
    public ProjetoEmpregadoResource getProjetoEmpregadoResource() {
        return new ProjetoEmpregadoResource();
    }


}
