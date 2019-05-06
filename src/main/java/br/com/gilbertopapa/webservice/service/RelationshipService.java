package br.com.gilbertopapa.webservice.service;

import br.com.gilbertopapa.webservice.model.dao.ProjetoEmpregadoDAO;
import br.com.gilbertopapa.webservice.model.domain.Empregado;
import br.com.gilbertopapa.webservice.model.domain.Projeto;

import java.util.List;

public class RelationshipService {

    private ProjetoEmpregadoDAO dao = new ProjetoEmpregadoDAO();


    public void saveRelationshipProjetoEmpregado(long projetoId, long empregadoId) {

        dao.saveRelationship(projetoId, empregadoId);
    }

    public List<Empregado> getEmpregados(long projetoId) {
        return dao.getEmpregados(projetoId);
    }

    public List<Projeto> getProjetos(long empregadoId) {
        return dao.getProjetos(empregadoId);
    }

    public void deleteRelationshipProjetoEmpregado(long projetoId, long empregadoId) {
        dao.deleteRelationship(projetoId, empregadoId);
    }

}
