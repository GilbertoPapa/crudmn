package br.com.gilbertopapa.webservice.model.dao;

import br.com.gilbertopapa.webservice.exceptions.DAOException;
import br.com.gilbertopapa.webservice.exceptions.ErrorCode;

public class ProjetoEmpregadoDAO {

    public void saveRelationship(long projetoId , long empregadoId){
        if ((projetoId <= 0)||(empregadoId <=0)){
            throw new DAOException("O id do projeto e o id do empregado precisam ser maiores do que 0.", ErrorCode.BAD_REQUEST.getCode());
        }

    }
}
