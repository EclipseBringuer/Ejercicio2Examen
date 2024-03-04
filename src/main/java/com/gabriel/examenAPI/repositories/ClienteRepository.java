package com.gabriel.examenAPI.repositories;

import com.gabriel.examenAPI.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio que define las operaciones de base de datos para la entidad Cliente.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    /**
     * Obtiene una lista de clientes cuyo total de ventas sea mayor que la cantidad proporcionada.
     *
     * @param cantidad Cantidad mínima de ventas para filtrar los clientes.
     * @return Lista de clientes que cumplen con el criterio.
     */
    @Query("SELECT c FROM Cliente c WHERE c.total>:cantidad")
    List<Cliente> getMejoresClientes(@Param("cantidad") Double cantidad);

    /**
     * Obtiene el total de ventas de todos los clientes.
     *
     * @return Total de ventas de todos los clientes.
     */
    @Query("SELECT sum(c.total) FROM Cliente c")
    Long getTotalVentas();

    /**
     * Obtiene el promedio de ventas de los clientes activos.
     *
     * @return Promedio de ventas de los clientes activos.
     */
    @Query("SELECT avg(c.total) FROM Cliente c WHERE c.estado='activo'")
    Double getPromedioVentaActivos();

    /**
     * Obtiene la cantidad de clientes inactivos con ventas mayores a cero.
     *
     * @return Cantidad de clientes inactivos con ventas mayores a cero.
     */
    @Query("SELECT count(c) FROM Cliente c WHERE c.estado='inactivo' AND c.total>0")
    Long getInactivos();
}