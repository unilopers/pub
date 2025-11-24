package com.grupo_5.pub.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Mesa {

    @Id
    @Column(nullable=false, unique=true)
    private Integer id;

    @Column(nullable=false)
    private Integer capacidade;

    @Column(nullable=false)
    private String status;



    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public Integer getCapacidade() {return capacidade;}
    public void setCapacidade(Integer capacidade) {this.capacidade = capacidade;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
}
