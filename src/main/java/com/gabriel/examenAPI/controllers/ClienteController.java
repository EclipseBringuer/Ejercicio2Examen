package com.gabriel.examenAPI.controllers;

import com.gabriel.examenAPI.model.dto.ClienteDTO;
import com.gabriel.examenAPI.model.dto.EstadisticasDTO;
import com.gabriel.examenAPI.model.entity.Cliente;
import com.gabriel.examenAPI.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Registra un nuevo cliente")
    @PostMapping("/create")
    public ResponseEntity<Cliente> insertarCliente(@RequestBody ClienteDTO cliente) {
        return new ResponseEntity<>(clienteService.createCliente(cliente), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene un cliente por su id")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getCliente(@PathVariable Long id) {
        var output = clienteService.getById(id);
        if (output != null) {
            return new ResponseEntity<>(output, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Lista los clientes que tengan unas ventas totales superiores al parametro")
    @GetMapping("")
    public ResponseEntity<List<ClienteDTO>> listarMejoresClientes(@RequestParam("ventasMayorQue") Double cantidad){
        return new ResponseEntity<>(clienteService.getMejoresClientes(cantidad), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene las estadisticas de los clientes")
    @GetMapping("/estadisticas")
    public ResponseEntity<EstadisticasDTO> estadisticas(){
        return new ResponseEntity<>(clienteService.getEstadisticas(), HttpStatus.OK);
    }
}
