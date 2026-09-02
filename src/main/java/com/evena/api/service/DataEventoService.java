package com.evena.api.service;

import com.evena.api.dto.DataEventoRequest;
import com.evena.api.exception.EntidadeNaoEncontradaException;
import com.evena.api.model.DataEvento;
import com.evena.api.model.Evento;
import com.evena.api.repository.DataEventoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DataEventoService {

    private final DataEventoRepository dataEventoRepository;
    private final EventoService eventoService;

    public DataEventoService(DataEventoRepository dataEventoRepository, EventoService eventoService) {
        this.dataEventoRepository = dataEventoRepository;
        this.eventoService = eventoService;
    }

    @Transactional
    public DataEvento adicionarData(Integer eventoId, DataEventoRequest request) {
        Evento evento = eventoService.buscarEntidade(eventoId);
        return dataEventoRepository.save(new DataEvento(evento, request.getDataHora()));
    }

    @Transactional
    public DataEvento alterarDataHora(Integer id, DataEventoRequest request) {
        DataEvento dataEvento = buscarEntidade(id);
        dataEvento.alterarDataHora(request.getDataHora());
        return dataEventoRepository.save(dataEvento);
    }

    @Transactional
    public void removerData(Integer id) {
        dataEventoRepository.delete(buscarEntidade(id));
    }

    public List<DataEvento> listarDatas(Integer eventoId) {
        eventoService.buscarEntidade(eventoId);
        return dataEventoRepository.findByEventoIdOrderByDataHoraAsc(eventoId);
    }

    private DataEvento buscarEntidade(Integer id) {
        return dataEventoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Data do evento nÃ£o encontrada."));
    }
}
