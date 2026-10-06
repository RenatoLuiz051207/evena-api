package com.evena.api.service;

import com.evena.api.dto.EventoRequest;
import com.evena.api.dto.EventoResponse;
import com.evena.api.exception.EntidadeNaoEncontradaException;
import com.evena.api.mapper.EventoMapper;
import com.evena.api.model.Categoria;
import com.evena.api.model.DataEvento;
import com.evena.api.model.Empresa;
import com.evena.api.model.Evento;
import com.evena.api.model.Localizacao;
import com.evena.api.repository.CategoriaRepository;
import com.evena.api.repository.DataEventoRepository;
import com.evena.api.repository.EmpresaRepository;
import com.evena.api.repository.EventoArtistaRepository;
import com.evena.api.repository.EventoPerfilRepository;
import com.evena.api.repository.EventoRepository;
import com.evena.api.repository.LocalizacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final EmpresaRepository empresaRepository;
    private final DataEventoRepository dataEventoRepository;
    private final CategoriaRepository categoriaRepository;
    private final LocalizacaoRepository localizacaoRepository;
    private final EventoArtistaRepository eventoArtistaRepository;
    private final EventoPerfilRepository eventoPerfilRepository;
    private final EventoMapper eventoMapper;

    public EventoService(EventoRepository eventoRepository,
                         EmpresaRepository empresaRepository,
                         DataEventoRepository dataEventoRepository,
                         CategoriaRepository categoriaRepository,
                         LocalizacaoRepository localizacaoRepository,
                         EventoArtistaRepository eventoArtistaRepository,
                         EventoPerfilRepository eventoPerfilRepository,
                         EventoMapper eventoMapper) {
        this.eventoRepository = eventoRepository;
        this.empresaRepository = empresaRepository;
        this.dataEventoRepository = dataEventoRepository;
        this.categoriaRepository = categoriaRepository;
        this.localizacaoRepository = localizacaoRepository;
        this.eventoArtistaRepository = eventoArtistaRepository;
        this.eventoPerfilRepository = eventoPerfilRepository;
        this.eventoMapper = eventoMapper;
    }

    public List<EventoResponse> listar() {
        return eventoRepository.findAll()
                .stream()
                .map(this::montarResponse)
                .sorted(Comparator.comparing(
                        this::primeiraData,
                        Comparator.nullsLast(Comparator.naturalOrder())
                ))
                .toList();
    }

    public List<EventoResponse> listarAtivos() {
        return eventoRepository.findByStatusTrueOrderByIdDesc()
                .stream()
                .map(this::montarResponse)
                .sorted(Comparator.comparing(
                        this::primeiraData,
                        Comparator.nullsLast(Comparator.naturalOrder())
                ))
                .toList();
    }

    public EventoResponse buscar(Integer id) {
        return montarResponse(buscarEntidade(id));
    }

    public List<EventoResponse> pesquisar(String termo) {
        String valor = termo == null ? "" : termo.trim().toLowerCase(Locale.ROOT);

        if (valor.isEmpty()) {
            return listarAtivos();
        }

        return listarAtivos()
                .stream()
                .filter(evento -> contem(evento.getTitulo(), valor)
                        || contem(evento.getDescricao(), valor)
                        || contem(evento.getCategoria(), valor)
                        || contem(evento.getLocal(), valor)
                        || contem(evento.getEndereco(), valor)
                        || contem(evento.getCidade(), valor))
                .toList();
    }

    @Transactional
    public EventoResponse cadastrar(Integer empresaId, EventoRequest request) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Empresa não encontrada."));

        Evento evento = eventoMapper.toEntity(request);
        evento.setEmpresa(empresa);
        evento.setStatus(request.getStatus() == null || request.getStatus());
        return montarResponse(eventoRepository.save(evento));
    }

    @Transactional
    public EventoResponse editar(Integer id, EventoRequest request) {
        Evento evento = buscarEntidade(id);

        evento.editarDados(
                request.getTitulo().trim(),
                request.getClassificacao(),
                request.getBanner(),
                request.getCapa(),
                request.getDescricao().trim(),
                request.getPreco(),
                request.getLink()
        );

        if (request.getStatus() != null) {
            evento.setStatus(request.getStatus());
        }

        return montarResponse(eventoRepository.save(evento));
    }

    @Transactional
    public EventoResponse ativar(Integer id) {
        Evento evento = buscarEntidade(id);
        evento.ativar();
        return montarResponse(eventoRepository.save(evento));
    }

    @Transactional
    public EventoResponse desativar(Integer id) {
        Evento evento = buscarEntidade(id);
        evento.desativar();
        return montarResponse(eventoRepository.save(evento));
    }

    @Transactional
    public void remover(Integer id) {
        Evento evento = buscarEntidade(id);
        eventoPerfilRepository.deleteByEventoId(id);
        eventoArtistaRepository.deleteByEventoId(id);
        categoriaRepository.deleteByEventoId(id);
        localizacaoRepository.deleteByEventoId(id);
        dataEventoRepository.deleteByEventoId(id);
        eventoRepository.delete(evento);
    }

    public Evento buscarEntidade(Integer id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Evento não encontrado."));
    }

    private LocalDateTime primeiraData(EventoResponse evento) {
        return evento.getDatas().isEmpty() ? null : evento.getDatas().get(0);
    }

    private boolean contem(String texto, String termo) {
        return texto != null && texto.toLowerCase(Locale.ROOT).contains(termo);
    }

    private EventoResponse montarResponse(Evento evento) {
        List<LocalDateTime> datas = dataEventoRepository.findByEventoIdOrderByDataHoraAsc(evento.getId())
                .stream()
                .map(DataEvento::getDataHora)
                .toList();

        Categoria categoria = categoriaRepository.findByEventoId(evento.getId())
                .stream()
                .findFirst()
                .orElse(null);

        Localizacao localizacao = localizacaoRepository.findByEventoId(evento.getId())
                .stream()
                .findFirst()
                .orElse(null);

        List<String> artistas = eventoArtistaRepository.findByEventoId(evento.getId())
                .stream()
                .map(relacao -> relacao.getArtista().getNome())
                .toList();

        return eventoMapper.toResponse(evento, datas, categoria, localizacao, artistas);
    }
}
