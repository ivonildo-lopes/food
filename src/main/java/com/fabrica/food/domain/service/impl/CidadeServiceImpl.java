package com.fabrica.food.domain.service.impl;

import com.fabrica.food.domain.dao.CidadeDao;
import com.fabrica.food.domain.exception.BadValueException;
import com.fabrica.food.domain.model.Cidade;
import com.fabrica.food.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeServiceImpl implements CidadeService {

    @Autowired
    private CidadeDao dao;

    @Override
    public Cidade save(Cidade cidade) {
        return this.dao.save(cidade);
    }

    @Override
    public Cidade update(Long id, Cidade cidade) {
        Cidade cid = this.findById(id);

       try{
           BeanUtils.copyProperties(cidade,cid,"id");
           cid = this.save(cid);
       }catch (DataIntegrityViolationException ex){
           throw new BadValueException("Erro ao tentar atualizar Cidade não existe Estado de Código: " + cidade.getEstado().getId());
       }

        return cid;
    }

    @Override
    public void delete(Long id) {
        this.dao.deleteById(id);
    }

    @Override
    public Cidade findById(Long id) {
        return this.dao.findById(id).orElse(null);
    }

    @Override
    public List<Cidade> findAll() {
        return this.dao.findAll();
    }
}
