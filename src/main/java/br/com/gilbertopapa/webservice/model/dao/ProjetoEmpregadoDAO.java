package br.com.gilbertopapa.webservice.model.dao;

import br.com.gilbertopapa.webservice.exceptions.DAOException;
import br.com.gilbertopapa.webservice.exceptions.ErrorCode;
import br.com.gilbertopapa.webservice.model.domain.Empregado;
import br.com.gilbertopapa.webservice.model.domain.Projeto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

public class ProjetoEmpregadoDAO {

    public void deleteRelationship(long projetoId , long empregadoId){
        if ((projetoId <= 0)||(empregadoId <=0)){
            throw new DAOException("O id do projeto e o id do empregado precisam ser maiores do que 0.", ErrorCode.BAD_REQUEST.getCode());
        }

        EntityManager entityManager = JPAUtil.getEntityManager();

        if (relationshipExist(projetoId,projetoId,entityManager)){
            throw new DAOException("Relacionamento informado para remoção não existe.",ErrorCode.NOT_FOUND.getCode());

        }

        try {
            Projeto projeto = entityManager.find(Projeto.class,projetoId);
            Empregado empregado = entityManager.find(Empregado.class,empregadoId);

            entityManager.getTransaction().begin();
            projeto.getEmpregados().remove(empregado);
            entityManager.persist(projeto);
            entityManager.getTransaction().commit();

        }catch (RuntimeException e){
            throw new DAOException("Erro ao remover relacionamento entre projeto e empregado no banco de dados: "+e.getMessage(),ErrorCode.SERVER_ERROR.getCode());

        }finally {
            entityManager.close();
        }
    }

    public void saveRelationship(long projetoId , long empregadoId){
        if ((projetoId <= 0)||(empregadoId <=0)){
            throw new DAOException("O id do projeto e o id do empregado precisam ser maiores do que 0.", ErrorCode.BAD_REQUEST.getCode());
        }

    }

    private boolean relationshipExist(long projetoId, long empregadoId, EntityManager em) {
        try {
            em.createQuery("select e from Empregado e join e.projetos p where p.id = :projetoId and e.id = :empregadoId", Empregado.class)
                    .setParameter("projetoId", projetoId)
                    .setParameter("empregadoId", empregadoId)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return false;
        }
        return true;
    }

}
