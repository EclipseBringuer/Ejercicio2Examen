package com.gabriel.examenAPI.model.entity;

import jakarta.persistence.*;

/**
 * Entidad que representa a un cliente en la aplicación.
 */
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

    /**
     * Constructor para crear un objeto Cliente con parámetros proporcionados.
     *
     * @param nombre Nombre del cliente.
     * @param total  Total de ventas del cliente.
     * @param estado Estado del cliente.
     */
    public Cliente(String nombre, Double total, String estado) {
        this.nombre = nombre;
        this.total = total;
        this.estado = estado;
    }

    /**
     * Constructor por defecto que crea un Cliente con valores predeterminados.
     * - Nombre: "Unknown"
     * - Total: 0.0
     * - Estado: "activo"
     */
    public Cliente(){
        this.nombre = "Unknown";
        this.total = (double) 0;
        this.estado = "activo";
    }

    /**
     * Obtiene el ID del cliente.
     *
     * @return ID del cliente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del cliente.
     *
     * @param id Nuevo ID del cliente.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param nombre Nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el total de ventas del cliente.
     *
     * @return Total de ventas del cliente.
     */
    public Double getTotal() {
        return total;
    }

    /**
     * Establece el total de ventas del cliente.
     *
     * @param total Nuevo total de ventas del cliente.
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * Obtiene el estado del cliente.
     *
     * @return Estado del cliente.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del cliente.
     *
     * @param estado Nuevo estado del cliente.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Representación en cadena del objeto Cliente.
     *
     * @return Cadena que representa el objeto Cliente.
     */
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