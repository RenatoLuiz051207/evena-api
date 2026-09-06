package com.evena.api.service;

import com.evena.api.dto.LocalizacaoRequest;
import com.evena.api.exception.EntidadeNaoEncontradaException;
import com.evena.api.model.Evento;
import com.evena.api.model.Localizacao;
import com.evena.api.repository.LocalizacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository localizacaoRepository;
    private final EventoService eventoService;

    public LocalizacaoService(LocalizacaoRepository localizacaoRepository, EventoService eventoService) {
        this.localizacaoRepository = localizacaoRepository;
        this.eventoService = eventoService;
    }

    public List<Localizacao> listar() {
        return localizacaoRepository.findAll();
    }

    public List<Localizacao> buscarLocalizacao(String cidade) {
        if (cidade == null || cidade.isBlank()) {
            return listar();
        }
        return localizacaoRepository.findByCidadeContainingIgnoreCase(cidade.trim());
    }

    @Transactional
    public Localizacao cadastrar(LocalizacaoRequest request) {
        Evento evento = eventoService.buscarEntidade(request.getEventoId());

        Localizacao localizacao = new Localizacao();
        localizacao.setEvento(evento);
        aplicarDados(localizacao, request);

        return localizacaoRepository.save(localizacao);
    }

    @Transactional
    public Localizacao editar(Integer id, LocalizacaoRequest request) {
        Localizacao localizacao = buscarEntidade(id);
        aplicarDados(localizacao, request);
        return localizacaoRepository.save(localizacao);
    }

    @Transactional
    public void remover(Integer id) {
        localizacaoRepository.delete(buscarEntidade(id));
    }

    private void aplicarDados(Localizacao localizacao, LocalizacaoRequest request) {
        localizacao.atualizarDados(
                request.getLatitude(),
                request.getLongitude(),
                request.getEndereco(),
                request.getUf(),
                request.getCep(),
                request.getCidade(),
                request.getNomeEstabelecimento()
        );
    }

    private Localizacao buscarEntidade(Integer id) {
        return localizacaoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Localização não encontrada."));
    }
}
