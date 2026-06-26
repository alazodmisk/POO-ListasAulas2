package com.example.crudproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudproject.model.Cliente;
import com.example.crudproject.service.ClienteService;

@RestController
@RequestMapping("/Cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Cliente cadastraCliente(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @GetMapping("/{id}")
    public Cliente pesquisaCliente(int id){
        return clienteService.findById(id);
    }

    @GetMapping
    public List<Cliente> pesquisaClientes(){
        return clienteService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deletarCliente(int id){
        clienteService.deleteById(id);
    }
}
