package com.example.crudproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudproject.model.Cliente;
import com.example.crudproject.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(int id){
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente save(Cliente cliente){
        if (cliente.getNome() == null || cliente.getEmail() == null){
            throw new RuntimeException("Erro, nome ou email vazio");
        }
        return clienteRepository.save(cliente);
    }

    public void deleteById(int id){
        clienteRepository.deleteById(id);
    }

    public Cliente findByEmail(String email){
        return clienteRepository.findByEmail(email);
    }
}