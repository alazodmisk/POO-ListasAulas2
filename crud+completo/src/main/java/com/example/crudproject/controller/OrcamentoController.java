package com.example.crudproject.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudproject.model.Orcamento;
import com.example.crudproject.model.StatusOrcamento;
import com.example.crudproject.service.OrcamentoService;

@RestController
@RequestMapping("/orcamento")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @PostMapping
    public Orcamento criarOrcamento(@RequestBody Orcamento orcamento){
        return orcamentoService.insertOrcamento(orcamento);
    }

    @GetMapping
    public List<Orcamento> listarOrcamento(){
        return orcamentoService.selectAllOrcamento();
    }

    @PutMapping("/{id}/aprovar")
    public Orcamento aprovarOrcamento(@PathVariable int id){
        return orcamentoService.aprovarOrcamento(id);
    }

    @PutMapping("/{id}/rejeitar")
    public Orcamento rejeitarOrcamento(@PathVariable int id){
        return orcamentoService.rejeitarOrcamento(id);
    }

    @GetMapping("/{id}")
    public Orcamento buscarById(@PathVariable int id){
        return orcamentoService.selectOrcamentoById(id);
    }

    @GetMapping("/{id}/valor")
    public double calcularValorTotal(@PathVariable int id){
        return orcamentoService.valorTotal(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id){
        orcamentoService.deletarOrcamento(id);
    }

    @GetMapping("/status/{statusDesejado}")
    public List<Orcamento> acharPorStatus(@PathVariable("statusDesejado") StatusOrcamento statusOrcamento){
        return orcamentoService.findByStatus(statusOrcamento);
    }
}