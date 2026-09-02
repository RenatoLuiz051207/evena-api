package com.evena.api.service;

import com.evena.api.dto.*;
import com.evena.api.exception.EntidadeNaoEncontradaException;
import com.evena.api.exception.RegraNegocioException;
import com.evena.api.model.*;
import com.evena.api.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final EventoRepository eventoRepository;
    private final EventoPerfilRepository eventoPerfilRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventoService eventoService;

    public PerfilService(PerfilRepository perfilRepository,
                         EventoRepository eventoRepository,
                         EventoPerfilRepository eventoPerfilRepository,
                         PasswordEncoder passwordEncoder,
                         EventoService eventoService) {
        this.perfilRepository = perfilRepository;
        this.eventoRepository = eventoRepository;
        this.eventoPerfilRepository = eventoPerfilRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventoService = eventoService;
    }

    @Transactional
    public PerfilResponse cadastrar(PerfilCadastroRequest request) {
        String email = request.getEmail().trim().toLowerCase();

        if (perfilRepository.existsByEmailIgnoreCase(email)) {
            throw new RegraNegocioException("JÃ¡ existe uma conta com esse e-mail.");
        }

        Perfil perfil = new Perfil(
                request.getNome().trim(),
                email,
                passwordEncoder.encode(request.getSenha())
        );

        return new PerfilResponse(perfilRepository.save(perfil));
    }

    public PerfilResponse autenticar(LoginRequest request) {
        Perfil perfil = perfilRepository.findByEmailIgnoreCase(request.getEmail().trim())
                .orElseThrow(() -> new RegraNegocioException("E-mail ou senha invÃ¡lidos."));

        if (!passwordEncoder.matches(request.getSenha(), perfil.getSenha())) {
            throw new RegraNegocioException("E-mail ou senha invÃ¡lidos.");
        }

        return new PerfilResponse(perfil);
    }

    public PerfilResponse buscar(Integer id) {
        return new PerfilResponse(buscarEntidade(id));
    }

    @Transactional
    public PerfilResponse editarPerfil(Integer id, PerfilAtualizacaoRequest request) {
        Perfil perfil = buscarEntidade(id);

        perfil.editarPerfil(
                request.getNome().trim(),
                request.getTelefone(),
                request.getFoto(),
                request.getBanner(),
                request.getDescricao()
        );

        return new PerfilResponse(perfilRepository.save(perfil));
    }

    @Transactional
    public void recuperarSenha(RecuperarSenhaRequest request) {
        Perfil perfil = perfilRepository.findByEmailIgnoreCase(request.getEmail().trim())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("E-mail nÃ£o cadastrado."));

        perfil.alterarSenha(passwordEncoder.encode(request.getNovaSenha()));
        perfilRepository.save(perfil);
    }

    @Transactional
    public void adicionarFavorito(Integer perfilId, Integer eventoId) {
        Perfil perfil = buscarEntidade(perfilId);
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Evento nÃ£o encontrado."));

        EventoPerfilId id = new EventoPerfilId(eventoId, perfilId);

        if (!eventoPerfilRepository.existsById(id)) {
            eventoPerfilRepository.save(new EventoPerfil(evento, perfil));
        }
    }

    @Transactional
    public void removerFavorito(Integer perfilId, Integer eventoId) {
        eventoPerfilRepository.deleteById(new EventoPerfilId(eventoId, perfilId));
    }

    public List<EventoResponse> listarFavoritos(Integer perfilId) {
        buscarEntidade(perfilId);

        return eventoPerfilRepository.findByPerfilId(perfilId)
                .stream()
                .map(relacao -> eventoService.buscar(relacao.getEvento().getId()))
                .toList();
    }

    public Perfil buscarEntidade(Integer id) {
        return perfilRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Perfil nÃ£o encontrado."));
    }
}
