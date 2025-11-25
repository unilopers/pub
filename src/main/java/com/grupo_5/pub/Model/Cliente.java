package com.grupo_5.pub.Model;

import jakarta.persistence.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;


@JsonPropertyOrder({"id", "nome", "contato"})
@JacksonXmlRootElement(localName = "cliente")
@Entity
public class Cliente {


    public Cliente(String contato, String nome, Integer id) {
        this.contato = contato;
        this.nome = nome;
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String nome;

    @Column(nullable=false)
    private String contato;

    public Cliente(List<Cliente> all) {}

    public Cliente() {

    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getContato() {return contato;}
    public void setContato(String contato) {this.contato = contato;}
}
