package br.com.gilbertopapa.webservice.service;

import br.com.gilbertopapa.webservice.model.dao.EmpregadoDAO;
import br.com.gilbertopapa.webservice.model.domain.Empregado;

import java.util.List;

public class EmpregadoService {

    private EmpregadoDAO dao = new EmpregadoDAO();

    public List<Empregado> getEmpregados() {
        return dao.getAll();
    }

    public Empregado getEmpregado(Long id) {
        return dao.getById(id);
    }

    public Empregado saveEmpregado(Empregado empregado) {
        return dao.save(empregado);
    }

    public Empregado updateEmpregado(Empregado empregado) {
        return dao.update(empregado);
    }

    public Empregado deleteEmpregado(Long id) {
        return dao.delete(id);
    }

    public List<Empregado> getEmpregadosByPagination(int firstResult, int maxResults) {
        return dao.getByPagination(firstResult, maxResults);
    }

    public List<Empregado> getEmpregadosByName(String name) {
        return dao.getByName(name);
    }
}
