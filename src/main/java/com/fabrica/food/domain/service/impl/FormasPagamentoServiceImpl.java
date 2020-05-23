package com.fabrica.food.domain.service.impl;

import com.fabrica.food.domain.dao.FormaPagamentoDao;
import com.fabrica.food.domain.dto.FormaPagamentoDto;
import com.fabrica.food.domain.model.FormaPagamento;
import com.fabrica.food.domain.service.FormasPagamentoService;
import com.fabrica.food.util.Converter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FormasPagamentoServiceImpl implements FormasPagamentoService {

    @Autowired
    private FormaPagamentoDao dao;

    @Autowired
    Converter<FormaPagamentoDto, FormaPagamento> converter;

    @Override
    public FormaPagamento save(FormaPagamento formaPagamento) {
        return this.dao.save(formaPagamento);
    }

    @Override
    public FormaPagamento update(Long id, FormaPagamento formaPagamento) {
        FormaPagamento cli = this.findById(id);
        BeanUtils.copyProperties(formaPagamento,cli,"id");

        return this.save(cli);
    }

    @Override
    public void delete(Long id) {
        this.dao.deleteById(id);
    }

    @Override
    public FormaPagamento findById(Long id) {
        return this.dao.findById(id).orElse(null);
    }

    @Override
    public List<FormaPagamento> findAll() {
        return this.dao.findAll();
    }

    @Override
    public FormaPagamentoDto saveCustom(Object bodyRequest) {
        FormaPagamento formaPagamento = new FormaPagamento();
        return saveAndFlushCustom(bodyRequest,formaPagamento);
    }

    @Override
    public FormaPagamentoDto updateCustom(Long id, Object bodyRequest) {
        FormaPagamento formaPagamento = this.findById(id);
        return  saveAndFlushCustom(bodyRequest,formaPagamento);
    }

    private FormaPagamentoDto saveAndFlushCustom(Object bodyRequest, FormaPagamento formaPagamento){
        FormaPagamentoDto formaPagamentoDto = new FormaPagamentoDto();

        formaPagamento = converter.getEntity((Map<String, Object>) bodyRequest,formaPagamentoDto,FormaPagamentoDto.class,formaPagamento);
        this.save(formaPagamento);
        formaPagamentoDto = converter.getDto(formaPagamentoDto,formaPagamento);

        return  formaPagamentoDto;
    }

}
