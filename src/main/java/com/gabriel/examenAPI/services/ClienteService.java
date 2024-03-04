package com.gabriel.examenAPI.services;

import com.gabriel.examenAPI.model.dto.ClienteDTO;
import com.gabriel.examenAPI.model.dto.EstadisticasDTO;
import com.gabriel.examenAPI.model.entity.Cliente;
import com.gabriel.examenAPI.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona las operaciones relacionadas con los clientes.
 */
@Service
public class ClienteService {
    private final ClienteRepository repository;

    /**
     * Constructor del servicio ClienteService.
     *
     * @param clienteRepository Repositorio de clientes utilizado por el servicio.
     */
    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.repository = clienteRepository;
    }

    /**
     * Crea un nuevo cliente a partir de un objeto ClienteDTO.
     *
     * @param cliente Datos del cliente a registrar.
     * @return Cliente creado y almacenado en la base de datos.
     */
    public Cliente createCliente(ClienteDTO cliente) {
        return repository.save(convertClienteDTOToCliente(cliente));
    }

    /**
     * Obtiene un cliente por su ID y lo convierte a un objeto ClienteDTO.
     *
     * @param id ID del cliente a obtener.
     * @return ClienteDTO correspondiente al ID proporcionado, o null si no se encuentra.
     */
    public ClienteDTO getById(Long id) {
        ClienteDTO output = null;
        if (repository.existsById(id)) {
            output = convertClienteToClienteDTO(repository.findById(id).get());
        }
        return output;
    }

    /**
     * Obtiene una lista de clientes cuyo total de ventas sea mayor que la cantidad proporcionada.
     *
     * @param cantidad Cantidad mínima de ventas para filtrar los clientes.
     * @return Lista de clientes que cumplen con el criterio, convertidos a objetos ClienteDTO.
     */
    public List<ClienteDTO> getMejoresClientes(Double cantidad) {
        return repository.getMejoresClientes(cantidad).stream()
                .map(c -> new ClienteDTO(c.getNombre(), c.getTotal(), c.getEstado()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene estadísticas de los clientes, como el total de ventas, el promedio de ventas de los activos
     * y la cantidad de clientes inactivos con ventas mayores a cero.
     *
     * @return Estadísticas de los clientes en un objeto EstadisticasDTO.
     */
    public EstadisticasDTO getEstadisticas() {
        return new EstadisticasDTO(repository.getTotalVentas(), repository.getPromedioVentaActivos(), repository.getInactivos());
    }

    /**
     * Convierte un objeto de la clase Cliente a un objeto ClienteDTO.
     *
     * @param cliente Objeto de la clase Cliente a convertir.
     * @return Objeto ClienteDTO creado a partir del Cliente proporcionado.
     */
    private ClienteDTO convertClienteToClienteDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getNombre(), cliente.getTotal(), cliente.getEstado());
    }

    /**
     * Convierte un objeto de la clase ClienteDTO a un objeto Cliente.
     *
     * @param clienteDTO Objeto de la clase ClienteDTO a convertir.
     * @return Objeto Cliente creado a partir del ClienteDTO proporcionado.
     */
    private Cliente convertClienteDTOToCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.nombre());
        cliente.setTotal(clienteDTO.total());
        cliente.setEstado(clienteDTO.estado());
        return cliente;
    }
}