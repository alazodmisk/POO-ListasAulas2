package com.example.crudproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudproject.model.Produto;
import com.example.crudproject.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository productRepository;

    public List<Produto> findAll() {
        return productRepository.findAll();
    }

    public Produto findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Produto save(Produto product) {
        return productRepository.save(product);
    }

    public Produto selectProdutoById(long id){
        Optional<Produto> oc = productRepository.findById(id);
        if(oc.isPresent()){
            return oc.get();
        }else{
            throw new RuntimeException("Orcamento nao encotrado.");
        }
    }

    public Produto mudarPreco(long id, double preco){
        Produto pc = selectProdutoById(id);
        pc.setPreco(preco);
        return productRepository.save(pc);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
