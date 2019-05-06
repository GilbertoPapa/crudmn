package br.com.gilbertopapa.webservice.resources;

import br.com.gilbertopapa.webservice.model.domain.Projeto;
import br.com.gilbertopapa.webservice.resources.beans.FilterBean;
import br.com.gilbertopapa.webservice.service.ProjetoService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

public class ProjetoResource {

    private ProjetoService service = new ProjetoService();

    @GET
    public List<Projeto> getProjetos(@BeanParam FilterBean projetoFilter) {
        if ((projetoFilter.getOffset() >= 0) && (projetoFilter.getLimit() > 0)) {
            return service.getProjetosByPagination(projetoFilter.getOffset(), projetoFilter.getLimit());
        }
        if (projetoFilter.getNome() != null) {
            return service.getProjetosByName(projetoFilter.getNome());
        }

        return service.getProjetos();
    }

    @GET
    @Path("{projetoId}")
    public Projeto getProjeto(@PathParam("projetoId") long id, @Context UriInfo uriInfo) {
        return service.getProjeto(id);
    }

    @DELETE
    @Path("{projetoId}")
    public Response delete(@PathParam("projetoId") long id) {
        service.deleteProjeto(id);
        return Response.noContent().build();
    }

    @POST
    public Response save(Projeto projeto) {
        projeto = service.saveProjeto(projeto);
        return Response.status(Response.Status.CREATED)
                .entity(projeto)
                .build();
    }

    @PUT
    @Path("{projetoId}")
    public Response update(@PathParam("projetoId") long id, Projeto projeto) {
        projeto.setId(id);
        service.updateProjeto(projeto);
        return Response.noContent().build();
    }

    @Path("{projetoId}/empregados")
    public ProjetoEmpregadoResource getProjetoEmpregadoResource() {
        return new ProjetoEmpregadoResource();
    }


}
