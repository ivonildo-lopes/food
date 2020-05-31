package com.fabrica.food.domain.service.impl;

import com.fabrica.food.domain.dao.FormaPagamentoDao;
import com.fabrica.food.domain.dto.FormaPagamentoDto;
import com.fabrica.food.domain.exception.BadValueException;
import com.fabrica.food.domain.exception.NegocioException;
import com.fabrica.food.domain.exception.NoContentException;
import com.fabrica.food.domain.model.FormaPagamento;
import com.fabrica.food.domain.service.FormasPagamentoService;
import com.fabrica.food.util.Converter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class FormasPagamentoServiceImpl implements FormasPagamentoService {

    @Autowired
    private FormaPagamentoDao dao;

    @Autowired
    Converter<FormaPagamentoDto, FormaPagamento> converter;

    @Override
    @Transactional
    public FormaPagamento save(FormaPagamento formaPagamento) {

       validaFormaPagamento(formaPagamento);

        if (Objects.isNull(formaPagamento.getId()))existsFormaPagamento(formaPagamento);

        return this.dao.save(formaPagamento);
    }

    private void existsFormaPagamento(FormaPagamento formaPagamento) {
        if(this.dao.existsByDescricao(formaPagamento.getDescricao().toUpperCase()))
            throw new NegocioException("Forma de pagamento:  " + formaPagamento.getDescricao() + "  já existe na base de dados");
    }

    private void validaFormaPagamento(FormaPagamento formaPagamento){
        if(Objects.isNull(formaPagamento)) throw new BadValueException("A forma de pagamento não pode ser nula");

        if(Objects.isNull(formaPagamento.getDescricao())
                 || formaPagamento.getDescricao().isEmpty())
            throw new BadValueException("Favor informe a forma de pagamento");
    }

    private void checkAmbiguos(String descricaoBodyRequest, FormaPagamento formaPagamentoDataBase) {
        if(!descricaoBodyRequest.toUpperCase().equals(formaPagamentoDataBase.getDescricao().toUpperCase()) &&
                this.dao.countByDescricao(descricaoBodyRequest.toUpperCase()) > 0){
            throw new NegocioException("Não foi possivel atualizar o Estado " + descricaoBodyRequest.toUpperCase() +  " já existe");
        }
    }

    @Override
    @Transactional
    public FormaPagamento update(Long id, FormaPagamento formaPagamentoBodyRequest) {

        if(Objects.isNull(id)) throw new BadValueException("Informe o código da Forma de pagamento");

        validaFormaPagamento(formaPagamentoBodyRequest);

        FormaPagamento formaPagamento = this.findById(id);
        checkAmbiguos(formaPagamentoBodyRequest.getDescricao(), formaPagamento);

        BeanUtils.copyProperties(formaPagamentoBodyRequest,formaPagamento,"id");

        return this.save(formaPagamento);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        FormaPagamento formaPagamento = this.findById(id);
        this.dao.delete(formaPagamento);
    }

    @Override
    public FormaPagamento findById(Long id) {

        if(Objects.isNull(id)) throw new BadValueException("Favor informe o codigo");

        FormaPagamento formaPagamento = this.dao.findById(id).orElse(null);

        if(Objects.isNull(formaPagamento)) throw new NoContentException("Esse código não existe na nossa base de dados");

        return formaPagamento;
    }

    @Override
    public List<FormaPagamento> findAll() {
        return this.dao.findAll();
    }

    @Override
    @Transactional
    public FormaPagamentoDto saveCustom(Object bodyRequest) {
        validaFormaPagamentoDto(bodyRequest);
        FormaPagamento formaPagamento = new FormaPagamento();
        return saveAndFlushCustom(bodyRequest,formaPagamento);
    }

    @Override
    @Transactional
    public FormaPagamentoDto updateCustom(Long id, Object bodyRequest) {
        validaFormaPagamentoDto(bodyRequest);
        FormaPagamento formaPagamento = this.findById(id);
        return  saveAndFlushCustom(bodyRequest,formaPagamento);
    }

    private void validaFormaPagamentoDto(Object bodyRequest){
        if(Objects.isNull(bodyRequest)) throw new BadValueException("A forma de pagamento não pode ser nula");

        String descricao = (String) ((Map) bodyRequest).get("descricao");

        if(Objects.isNull(descricao)
                 || descricao.isEmpty())
            throw new BadValueException("Favor informe a forma de pagamento");
    }

    private FormaPagamentoDto saveAndFlushCustom(Object bodyRequest, FormaPagamento formaPagamento){
        FormaPagamentoDto formaPagamentoDto = new FormaPagamentoDto();

        formaPagamento = converter.getEntity((Map<String, Object>) bodyRequest,formaPagamentoDto,FormaPagamentoDto.class,formaPagamento);
        this.save(formaPagamento);
        formaPagamentoDto = converter.getDto(formaPagamentoDto,formaPagamento);

        return  formaPagamentoDto;
    }

}
