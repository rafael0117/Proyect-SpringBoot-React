package com.rafael0117.SpringBoot_react;

import com.rafael0117.SpringBoot_react.entity.Cliente;
import com.rafael0117.SpringBoot_react.exception.ResourceNotFoundException;
import com.rafael0117.SpringBoot_react.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    @Autowired
    private IClienteRepository repository;

    @GetMapping
    public List<Cliente>  listarClientes(){
        return repository.findAll();
    }
    @PostMapping
    public Cliente guardar(@RequestBody Cliente cliente){
        return repository.save(cliente);
    }
    @PutMapping("/{id}")
    public Cliente actualizar (@PathVariable Long id,@RequestBody Cliente cliente){
        Cliente cliente1=repository.findById(id).orElseThrow();
        cliente1.setNombre(cliente.getNombre());
        cliente1.setApellido(cliente.getApellido());
        cliente1.setEmail(cliente.getEmail());
        Cliente actualizado = repository.save(cliente1);
        return actualizado;
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("No existe el id");
        }
        repository.deleteById(id);
    }
    @GetMapping("/{id}")
    public Cliente listarPorId(@PathVariable Long id){
        Cliente cliente = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El cliente con ese id no existe"+id));
        return cliente;
    }





}
