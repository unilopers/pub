package com.grupo_5.pub.Model;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String nome;

    @Column(nullable=false)
    private String contato;

    public Cliente() {}

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getContato() {return contato;}
    public void setContato(String contato) {this.contato = contato;}
}
