package com.grupo_5.pub.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private LocalDate data;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "atracoes_musicais", columnDefinition = "TEXT")
    private String atracoesMusicais;

    public Evento() {}

    public Evento(Integer id, String nome, LocalDate data, LocalTime horaInicio, String atracoesMusicais) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.horaInicio = horaInicio;
        this.atracoesMusicais = atracoesMusicais;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public String getAtracoesMusicais() { return atracoesMusicais; }
    public void setAtracoesMusicais(String atracoesMusicais) { this.atracoesMusicais = atracoesMusicais; }
}
