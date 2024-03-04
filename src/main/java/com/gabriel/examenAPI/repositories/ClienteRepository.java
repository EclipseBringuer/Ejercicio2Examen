package com.gabriel.examenAPI.repositories;

import com.gabriel.examenAPI.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c WHERE c.total>:cantidad")
    List<Cliente> getMejoresClientes(@Param("cantidad") Double cantidad);

    @Query("SELECT sum(c.total) FROM Cliente c")
    Long getTotalVentas();

    @Query("SELECT avg(c.total) FROM Cliente c WHERE c.estado='activo'")
    Double getPromedioVentaActivos();

    @Query("SELECT count(c) FROM Cliente c WHERE c.estado='inactivo' AND c.total>0")
    Long getInactivos();
}
