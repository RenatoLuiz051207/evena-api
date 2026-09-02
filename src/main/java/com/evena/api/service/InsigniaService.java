package com.evena.api.service;

import com.evena.api.dto.InsigniaRequest;
import com.evena.api.exception.EntidadeNaoEncontradaException;
import com.evena.api.model.*;
import com.evena.api.repository.InsigniaRepository;
import com.evena.api.repository.PerfilInsigniaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InsigniaService {

    private final InsigniaRepository insigniaRepository;
    private final PerfilInsigniaRepository perfilInsigniaRepository;
    private final PerfilService perfilService;

    public InsigniaService(InsigniaRepository insigniaRepository,
                           PerfilInsigniaRepository perfilInsigniaRepository,
                           PerfilService perfilService) {
        this.insigniaRepository = insigniaRepository;
        this.perfilInsigniaRepository = perfilInsigniaRepository;
        this.perfilService = perfilService;
    }

    public List<Insignia> listar() {
        return insigniaRepository.findAll();
    }

    @Transactional
    public Insignia cadastrar(InsigniaRequest request) {
        Insignia insignia = new Insignia();
        insignia.editarDados(request.getNome().trim(), request.getIcone());
        return insigniaRepository.save(insignia);
    }

    @Transactional
    public Insignia editar(Integer id, InsigniaRequest request) {
        Insignia insignia = buscarEntidade(id);
        insignia.editarDados(request.getNome().trim(), request.getIcone());
        return insigniaRepository.save(insignia);
    }

    @Transactional
    public void remover(Integer id) {
        insigniaRepository.delete(buscarEntidade(id));
    }

    @Transactional
    public PerfilInsignia atribuir(Integer perfilId, Integer insigniaId, String missao) {
        Perfil perfil = perfilService.buscarEntidade(perfilId);
        Insignia insignia = buscarEntidade(insigniaId);
        PerfilInsigniaId id = new PerfilInsigniaId(perfilId, insigniaId);

        return perfilInsigniaRepository.findById(id)
                .orElseGet(() -> perfilInsigniaRepository.save(
                        new PerfilInsignia(perfil, insignia, missao)
                ));
    }

    @Transactional
    public PerfilInsignia concluirMissao(Integer perfilId, Integer insigniaId) {
        PerfilInsignia relacao = perfilInsigniaRepository
                .findById(new PerfilInsigniaId(perfilId, insigniaId))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("InsÃ­gnia do perfil nÃ£o encontrada."));

        relacao.concluirMissao();
        return perfilInsigniaRepository.save(relacao);
    }

    public List<PerfilInsignia> listarDoPerfil(Integer perfilId) {
        perfilService.buscarEntidade(perfilId);
        return perfilInsigniaRepository.findByPerfilId(perfilId);
    }

    private Insignia buscarEntidade(Integer id) {
        return insigniaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("InsÃ­gnia nÃ£o encontrada."));
    }
}
