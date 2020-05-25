package com.fabrica.food.domain.service.impl;

import com.fabrica.food.domain.dao.CidadeDao;
import com.fabrica.food.domain.dto.CidadeDto;
import com.fabrica.food.domain.exception.BadValueException;
import com.fabrica.food.domain.exception.NegocioException;
import com.fabrica.food.domain.model.Cidade;
import com.fabrica.food.domain.service.CidadeService;
import com.fabrica.food.domain.service.EstadoService;
import com.fabrica.food.util.Converter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CidadeServiceImpl implements CidadeService {

    @Autowired
    private CidadeDao dao;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    Converter<CidadeDto,Cidade> converter;

    @Override
    public Cidade save(Cidade cidade) {

//        if(Objects.isNull(cidade.getId()))
            existsCidade2(cidade);

        return this.dao.save(cidade);
    }

    private void existsCidade(Cidade cidade) {
        if(this.dao.existsByNome(cidade.getNome().toUpperCase()))
            throw new NegocioException("Cidade " + cidade.getNome() + " estado de "+ cidade.getEstado().getNome() +" já existe na base de dados");
    }

    private void existsCidade2(Cidade cidade) {
        if(this.dao.countByNomeAndEstadoId(cidade.getNome().toUpperCase(),cidade.getEstado().getId()) > 0)
            throw new NegocioException("Cidade " + cidade.getNome() + " estado de "+ cidade.getEstado().getNome() +" já existe na base de dados");
    }

    @Override
    public Cidade update(Long id, Cidade cidadeBodyrequest) {
        Cidade cidade = this.findById(id);

       try{
           BeanUtils.copyProperties(cidadeBodyrequest,cidade,"id");
           cidade = this.save(cidade);
       }catch (DataIntegrityViolationException ex){
           throw new BadValueException("Erro ao tentar atualizar Cidade não existe Estado de Código: " + cidadeBodyrequest.getEstado().getId());
       }

        return cidade;
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
        Cidade cidade = new Cidade();
        return saveAndFlushCustom(cidade,dto);
    }

    @Override
    public CidadeDto updateCustom(Long id, Object bodyRequest) {

        Cidade cidade = this.findById(id);

        String nomeEstadoRequest  = (String) ((LinkedHashMap) bodyRequest).get("nome");

        return saveAndFlushCustom(cidade,bodyRequest);
    }

    private CidadeDto saveAndFlushCustom(Cidade cidade, Object dto){
        CidadeDto cidadeDto = new CidadeDto();
        converter.mapToObject((Map<String, Object>)dto, cidadeDto, CidadeDto.class);

        cidade = preparaCidade(cidadeDto);
        this.save(cidade);

        return getCidadeDtoRetorno(cidade);
    }

    private CidadeDto getCidadeDtoRetorno(Cidade cidade) {
        return new CidadeDto(cidade);
    }

    private Cidade preparaCidade(CidadeDto cidadeDto) {
        Cidade cidade = new Cidade(cidadeDto);
        cidade.setEstado(this.estadoService.findById(cidadeDto.getIdEstado()));
        return cidade;
    }


//    private void convertObjectIn(Map<String, Object> campos, CidadeDto destino) {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        CidadeDto cidadeDtoCampos = objectMapper.convertValue(campos,CidadeDto.class);
//
//        campos.forEach((nomePropriedade, valorPropriedade) -> {
//            Field field = ReflectionUtils.findField(CidadeDto.class, nomePropriedade);
//            field.setAccessible(true);
//
//            Object novoValor = ReflectionUtils.getField(field,cidadeDtoCampos);
//
//            ReflectionUtils.setField(field, destino, novoValor);
//        });
//    }
}
