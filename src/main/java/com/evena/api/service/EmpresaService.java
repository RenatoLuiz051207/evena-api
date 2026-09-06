package com.evena.api.service;

import com.evena.api.dto.EmpresaRequest;
import com.evena.api.dto.EventoRequest;
import com.evena.api.dto.EventoResponse;
import com.evena.api.exception.EntidadeNaoEncontradaException;
import com.evena.api.model.Empresa;
import com.evena.api.model.Perfil;
import com.evena.api.repository.EmpresaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final PerfilService perfilService;
    private final EventoService eventoService;

    public EmpresaService(EmpresaRepository empresaRepository,
                          PerfilService perfilService,
                          EventoService eventoService) {
        this.empresaRepository = empresaRepository;
        this.perfilService = perfilService;
        this.eventoService = eventoService;
    }

    public List<Empresa> listar() {
        return empresaRepository.findAll();
    }

    @Transactional
    public Empresa cadastrar(EmpresaRequest request) {
        Perfil perfil = perfilService.buscarEntidade(request.getPerfilId());
        Empresa empresa = new Empresa();
        empresa.setPerfil(perfil);
        empresa.editarDados(request.getCnpj(), request.getNome().trim(),
                request.getEndereco(), request.getSetor());
        return empresaRepository.save(empresa);
    }

    @Transactional
    public Empresa editar(Integer id, EmpresaRequest request) {
        Empresa empresa = buscarEntidade(id);
        empresa.editarDados(request.getCnpj(), request.getNome().trim(),
                request.getEndereco(), request.getSetor());
        return empresaRepository.save(empresa);
    }

    @Transactional
    public void remover(Integer id) {
        empresaRepository.delete(buscarEntidade(id));
    }

    public EventoResponse cadastrarEvento(Integer empresaId, EventoRequest request) {
        buscarEntidade(empresaId);
        return eventoService.cadastrar(empresaId, request);
    }

    public EventoResponse editarEvento(Integer eventoId, EventoRequest request) {
        return eventoService.editar(eventoId, request);
    }

    public void removerEvento(Integer eventoId) {
        eventoService.remover(eventoId);
    }

    private Empresa buscarEntidade(Integer id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Empresa não encontrada."));
    }
}
