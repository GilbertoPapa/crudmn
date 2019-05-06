package br.com.gilbertopapa.webservice.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

public class Projeto {

    @ManyToMany
    @JsonIgnore
    private List<Empregado> empregados;
}
