package com.fabrica.food.domain.service.impl;

import com.fabrica.food.domain.dao.CidadeDao;
import com.fabrica.food.domain.dto.CidadeDto;
import com.fabrica.food.domain.exception.BadValueException;
import com.fabrica.food.domain.exception.NegocioException;
import com.fabrica.food.domain.exception.NoContentException;
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

        if(Objects.isNull(cidade)) throw new BadValueException("Cidade não pode ser nula!");
        if(Objects.isNull(cidade.getEstado())
                || Objects.isNull(cidade.getEstado().getId())) throw new NegocioException("Favor infome o estado!");

        existsCidade(cidade);

        return this.dao.save(cidade);
    }

    private void existsCidade(Cidade cidade) {
        if(Objects.isNull(cidade.getNome())) throw new BadValueException("Favor Informe o nome da cidade!");

        if(this.dao.countByNomeAndEstadoId(cidade.getNome().toUpperCase(),cidade.getEstado().getId()) > 0)
            throw new NegocioException("Cidade " + cidade.getNome() + " no Estado de: "+ cidade.getEstado().getNome() +" já existe na base de dados");
    }

    @Override
    public Cidade update(Long id, Cidade cidadeBodyrequest) {
        if(Objects.isNull(cidadeBodyrequest)) throw new BadValueException("Cidade não pode ser nula!");
        if(Objects.isNull(cidadeBodyrequest.getNome())
                || cidadeBodyrequest.getNome().isEmpty()) throw new BadValueException("Favor Informe o nome da cidade!");

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
        Cidade cidade = this.findById(id);
        this.dao.delete(cidade);
    }

    @Override
    public Cidade findById(Long id) {
        if(Objects.isNull(id))
            throw new BadValueException("Informe o código da cidade ");

        Cidade cidade = this.dao.findById(id).orElse(null);

        if(Objects.isNull(cidade)){
            throw new NoContentException("Essa cidade não existe na nossa base");
        }

        return cidade;
    }

    @Override
    public List<Cidade> findAll() {
        return this.dao.findAll();
    }

    @Override
    public CidadeDto saveCustom(Object bodyRequest) {
        if(Objects.isNull(bodyRequest)) throw new BadValueException("Cidade não pode ser nula!");

        String nomeCidadeRequest =  (String) ((Map) bodyRequest).get("nome");

        if(Objects.isNull(nomeCidadeRequest)
                || nomeCidadeRequest.isEmpty()) throw new BadValueException("Favor Informe o nome da cidade!");

        Cidade cidade = new Cidade();
        return saveAndFlushCustom(cidade,bodyRequest);
    }

    @Override
    public CidadeDto updateCustom(Long id, Object bodyRequest) {
        if(Objects.isNull(bodyRequest)) throw new BadValueException("Cidade não pode ser nula!");

        String nomeCidadeRequest =  (String) ((Map) bodyRequest).get("nome");

        if(Objects.isNull(nomeCidadeRequest)
                || nomeCidadeRequest.isEmpty()) throw new BadValueException("Favor Informe o nome da cidade!");

        Cidade cidade = this.findById(id);
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
