package com.safework.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "checkin")
public class Checkin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    // Quando serializar o Usuario dentro do Checkin, ignora o campo "time"
    @JsonIgnoreProperties({"time"})
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private int humor;

    @Column(nullable = false)
    private int sono;

    @Column(nullable = false)
    private int carga;

    // GETTERS e SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getHumor() {
        return humor;
    }

    public void setHumor(int humor) {
        this.humor = humor;
    }

    public int getSono() {
        return sono;
    }

    public void setSono(int sono) {
        this.sono = sono;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }
}
