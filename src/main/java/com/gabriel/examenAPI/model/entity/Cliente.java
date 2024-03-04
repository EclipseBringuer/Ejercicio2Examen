package com.gabriel.examenAPI.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "total")
    private Double total;
    @Column(name = "estado")
    private String estado;

    public Cliente(String nombre, Double total, String estado) {
        this.nombre = nombre;
        this.total = total;
        this.estado = estado;
    }

    public Cliente(){
        this.nombre = "Unknown";
        this.total = (double) 0;
        this.estado = "activo";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", total=" + total +
                ", estado='" + estado + '\'' +
                '}';
    }
}
