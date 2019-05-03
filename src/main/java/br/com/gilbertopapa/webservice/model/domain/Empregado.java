package br.com.gilbertopapa.webservice.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ManyToMany;
import java.util.List;

public class Empregado {


    @ManyToMany(mappedBy = "empregados")
    @JsonIgnore
    private List<Projeto> projetos;
}
