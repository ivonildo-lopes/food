package com.fabrica.food.domain.service.impl;

import com.fabrica.food.domain.dao.CidadeDao;
import com.fabrica.food.domain.dto.CidadeDto;
import com.fabrica.food.domain.dto.EstadoDto;
import com.fabrica.food.domain.exception.BadValueException;
import com.fabrica.food.domain.model.Cidade;
import com.fabrica.food.domain.model.Estado;
import com.fabrica.food.domain.model.Restaurante;
import com.fabrica.food.domain.service.CidadeService;
import com.fabrica.food.domain.service.EstadoService;
import com.fabrica.food.util.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CidadeServiceImpl implements CidadeService {

    @Autowired
    private CidadeDao dao;

    @Autowired
    private EstadoService estadoService;

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

    @Override
    public CidadeDto saveCustom(Object dto) {

        CidadeDto cidadeDto = new CidadeDto();
        updateOnlyField((Map<String, Object>) dto, cidadeDto);

        Cidade cidade = preparaCidade(cidadeDto);
        this.save(cidade);

        return getCidadeDtoRetorno(cidadeDto, cidade);
    }

    private CidadeDto getCidadeDtoRetorno(CidadeDto cidadeDto, Cidade cidade) {
        BeanUtils.copyProperties(cidade,cidadeDto,"");
        EstadoDto estadoDto = new EstadoDto();
        BeanUtils.copyProperties(cidade.getEstado(),estadoDto);
        cidadeDto.setEstado(estadoDto);
        return cidadeDto;
    }

    private Cidade preparaCidade(CidadeDto cidadeDto) {
        Cidade cidade = new Cidade(cidadeDto);

        cidade.setEstado(this.estadoService.findById(cidadeDto.getIdEstado()));
        return cidade;
    }

    private void updateOnlyField(Map<String, Object> campos, CidadeDto destino) {

        ObjectMapper objectMapper = new ObjectMapper();
        CidadeDto cidadeDtoCampos = objectMapper.convertValue(campos,CidadeDto.class);


        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(CidadeDto.class, nomePropriedade);
            field.setAccessible(true);


            Object novoValor = ReflectionUtils.getField(field,cidadeDtoCampos);

            ReflectionUtils.setField(field, destino, novoValor);
        });
    }
}
