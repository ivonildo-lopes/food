package com.fabrica.food.domain.service.impl;

import com.fabrica.food.domain.dao.ProdutoDao;
import com.fabrica.food.domain.exception.BadValueException;
import com.fabrica.food.domain.exception.NoContentException;
import com.fabrica.food.domain.model.Produto;
import com.fabrica.food.domain.service.ProdutoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoDao dao;

    @Override
    public Produto save(Produto produto) {
        return this.dao.save(produto);
    }

    @Override
    public Produto update(Long id, Produto produto) {
        Produto cli = this.findById(id);
        BeanUtils.copyProperties(produto,cli,"id");

        return this.save(cli);
    }

    @Override
    public void delete(Long id) {
        Produto produto = this.findById(id);
        this.dao.delete(produto);
    }

    @Override
    public Produto findById(Long id) {

        if(Objects.isNull(id))
            throw new BadValueException("Favor informe o código da produto");

        Produto produto = this.dao.findById(id).orElse(null);

        if(Objects.isNull(produto))
            throw new NoContentException("Essa produto não existe na nossa base de dados");

        return produto;
    }

    @Override
    public List<Produto> findAll() {
        return this.dao.findAll();
    }

    @Override
    public List<Produto> findByName(String nome) {

        if(StringUtils.isEmpty(nome)){
            throw new BadValueException("Favor informe o nome para realizar a consulta");
        }

        return this.dao.findByNomeContains(nome);
    }
}
