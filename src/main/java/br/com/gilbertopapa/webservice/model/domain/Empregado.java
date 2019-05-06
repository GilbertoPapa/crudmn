package br.com.gilbertopapa.webservice.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

public class Empregado {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cargo;

    @ManyToMany(mappedBy = "empregados")
    @JsonIgnore
    private List<Projeto> projetos;
}
