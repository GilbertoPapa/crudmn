package br.com.gilbertopapa.webservice.model.dao;

import br.com.gilbertopapa.webservice.exceptions.DAOException;
import br.com.gilbertopapa.webservice.exceptions.ErrorCode;
import br.com.gilbertopapa.webservice.model.domain.Empregado;
import br.com.gilbertopapa.webservice.model.domain.Projeto;

import javax.persistence.EntityManager;
import java.util.List;

public class EmpregadoDAO {


    public List<Empregado> getEmpregados( long projetoId) {

        if (projetoId <= 0){
            throw new DAOException("O id do projeto precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
        }

        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            return entityManager.createQuery("select e from Empregado e JOIN e.projetos p where p.id = :projetoId",Empregado.class)
                    .setParameter("projetoId",projetoId)
                    .getResultList();
        }catch (RuntimeException e){
            throw new DAOException("Erro ao recuperar os empregados "+ projetoId +
            " do banco: " + e.getMessage(),
            ErrorCode.SERVER_ERROR.getCode());
        }finally {
            entityManager.close();
        }

    }

    public List<Projeto> getProjetos(long empregadoId) {
        if(empregadoId <= 0) {
            throw new DAOException("O id do empregado precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
        }

        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery("select p from Projeto p JOIN p.empregados e where e.id = :empregadoId", Projeto.class)
                    .setParameter("empregadoId", empregadoId)
                    .getResultList();
        } catch (RuntimeException ex) {
            throw new DAOException("Erro ao recuperar os projetos em que o empregado de id " + empregadoId + " está trabalhando: " + ex.getMessage(), ErrorCode.SERVER_ERROR.getCode());
        } finally {
            em.close();
        }
    }



}
