package com.example.crudproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crudproject.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
