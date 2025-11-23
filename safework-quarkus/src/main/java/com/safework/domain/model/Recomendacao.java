package com.safework.domain.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "recomendacao")
public class Recomendacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "time_id")
    private Time time;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false, length = 80)
    private String tipo;

    @Column(nullable = false, length = 20)
    private String impacto;

    @Column(nullable = false, length = 20)
    private String esforco;

    @Column(nullable = false, length = 20)
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(String impacto) {
        this.impacto = impacto;
    }

    public String getEsforco() {
        return esforco;
    }

    public void setEsforco(String esforco) {
        this.esforco = esforco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
