package br.com.gilbertopapa.webservice.resources;

import br.com.gilbertopapa.webservice.model.domain.Empregado;
import br.com.gilbertopapa.webservice.service.RelationshipService;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class EmpregadoProjetoResource {

    private RelationshipService service = new RelationshipService();
    @GET
    public List<Empregado> getEmpregados(@PathParam("projetoId")
                                                 long projetoId) {
        return service.getEmpregados(projetoId);
    }
}
