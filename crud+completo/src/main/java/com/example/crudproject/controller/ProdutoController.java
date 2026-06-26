package com.example.crudproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudproject.model.Produto;
import com.example.crudproject.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> getAllProducts() {
        return produtoService.findAll();
    }

    @GetMapping("/{id}")
    public Produto findById(long id){
        return produtoService.findById(id);
    }

    @PostMapping
    public Produto createProduct(@RequestBody Produto produto) {
        return produtoService.save(produto);
    }

    @DeleteMapping
    public void deleteProduto(long id){
        produtoService.deleteById(id);
    }
}
