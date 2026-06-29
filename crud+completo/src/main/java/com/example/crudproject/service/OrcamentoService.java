package com.example.crudproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudproject.exception.ValidacaoException;
import com.example.crudproject.model.Cliente;
import com.example.crudproject.model.Orcamento;
import com.example.crudproject.model.Produto;
import com.example.crudproject.model.StatusOrcamento;
import com.example.crudproject.repository.ClienteRepository;
import com.example.crudproject.repository.OrcamentoRepository;
import com.example.crudproject.repository.ProdutoRepository;

@Service
public class OrcamentoService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OrcamentoRepository orcamentoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public Orcamento insertOrcamento(Orcamento orcamento){
        Cliente clienteCompleto = clienteRepository.findById(orcamento.getCliente().getId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + orcamento.getCliente().getId()));
        orcamento.setCliente(clienteCompleto);

        List<Produto> produtosCompletos = new ArrayList<>();

        for (Produto p : orcamento.getProdutos()) {
            Produto produtoDoBanco = produtoRepository.findById(p.getId())
                .orElseThrow(() -> new RuntimeException("Produto com ID " + p.getId() + " não encontrado!"));
            produtosCompletos.add(produtoDoBanco);
        }
        return orcamentoRepository.save(orcamento);
    }

    public List<Orcamento> selectAllOrcamento(){
        return orcamentoRepository.findAll();
    }

    public Orcamento selectOrcamentoById(int id){
        Optional<Orcamento> oc = orcamentoRepository.findById(id);
        if(oc.isPresent()){
            return oc.get();
        }else{
            throw new ValidacaoException("Orçamento não encontrado");
        }
    }

    public Orcamento aprovarOrcamento(int id){
        Orcamento oc = selectOrcamentoById(id);
        oc.aprovar();
        return orcamentoRepository.save(oc);
    }

    public Orcamento rejeitarOrcamento(int id){
        Orcamento oc = selectOrcamentoById(id);
        oc.rejeitar();
        return orcamentoRepository.save(oc);
    }

    public void deletarOrcamento(int id){
        orcamentoRepository.deleteById(id);
    }

    public double valorTotal(int id){
        Orcamento oc = selectOrcamentoById(id);
        return oc.calcularValorTotal();
    }

    public List<Orcamento> findByStatus(StatusOrcamento status){
        return orcamentoRepository.findByStatus(status);
    }
}
