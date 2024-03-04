package com.gabriel.examenAPI.services;

import com.gabriel.examenAPI.model.dto.ClienteDTO;
import com.gabriel.examenAPI.model.dto.EstadisticasDTO;
import com.gabriel.examenAPI.model.entity.Cliente;
import com.gabriel.examenAPI.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.repository = clienteRepository;
    }

    public Cliente createCliente(ClienteDTO cliente) {
        return repository.save(convertClienteDTOToCliente(cliente));
    }

    public ClienteDTO getById(Long id) {
        ClienteDTO output = null;
        if (repository.existsById(id)) {
            output = convertClienteToClienteDTO(repository.findById(id).get());
        }
        return output;
    }

    public List<ClienteDTO> getMejoresClientes(Double cantidad) {
        return repository.getMejoresClientes(cantidad).stream()
                .map(c -> new ClienteDTO(c.getNombre(), c.getTotal(), c.getEstado()))
                .collect(Collectors.toList());
    }

    public EstadisticasDTO getEstadisticas() {
        return new EstadisticasDTO(repository.getTotalVentas(), repository.getPromedioVentaActivos(), repository.getInactivos());
    }

    private ClienteDTO convertClienteToClienteDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getNombre(), cliente.getTotal(), cliente.getEstado());
    }

    private Cliente convertClienteDTOToCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.nombre());
        cliente.setTotal(clienteDTO.total());
        cliente.setEstado(clienteDTO.estado());
        return cliente;
    }
}
