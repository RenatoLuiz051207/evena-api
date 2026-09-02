package com.evena.api.config;

import com.evena.api.model.*;
import com.evena.api.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class CargaInicial {

    @Bean
    CommandLineRunner carregarDados(PerfilRepository perfilRepository,
                                    EmpresaRepository empresaRepository,
                                    EventoRepository eventoRepository,
                                    DataEventoRepository dataEventoRepository,
                                    ArtistaRepository artistaRepository,
                                    CategoriaRepository categoriaRepository,
                                    LocalizacaoRepository localizacaoRepository,
                                    EventoArtistaRepository eventoArtistaRepository,
                                    InsigniaRepository insigniaRepository,
                                    PasswordEncoder passwordEncoder) {
        return args -> {
            if (eventoRepository.count() > 0) {
                return;
            }

            Perfil perfil = new Perfil(
                    "Equipe Evena",
                    "contato@evena.com",
                    passwordEncoder.encode("Evena123")
            );
            perfil = perfilRepository.save(perfil);

            Empresa empresa = new Empresa();
            empresa.setPerfil(perfil);
            empresa.editarDados(
                    "00.000.000/0001-00",
                    "Evena Eventos",
                    "SÃ£o Paulo - SP",
                    "Eventos"
            );
            empresa = empresaRepository.save(empresa);

            Artista artista = new Artista();
            artista.editarDados("Equipe Evena", "Eventos e experiÃªncias", null);
            artista = artistaRepository.save(artista);

            List<DadoEventoInicial> dados = List.of(
                    new DadoEventoInicial(
                            "Marketing Digital Experience 2026",
                            "assets/images/evento1.jpg",
                            LocalDateTime.of(2026, 9, 12, 19, 0),
                            "Centro de ConvenÃ§Ãµes Paulista",
                            "Av. Paulista, 1578 - Bela Vista, SÃ£o Paulo - SP",
                            "Uma noite de conteÃºdo prÃ¡tico sobre marketing digital, conteÃºdo, trÃ¡fego pago e construÃ§Ã£o de marca com profissionais do mercado.",
                            "Networking"
                    ),
                    new DadoEventoInicial(
                            "Festival Aurora",
                            "assets/images/evento2.jpeg",
                            LocalDateTime.of(2026, 9, 20, 16, 0),
                            "Parque Villa-Lobos",
                            "Av. Prof. Fonseca Rodrigues, 2001 - Alto de Pinheiros, SÃ£o Paulo - SP",
                            "Festival ao ar livre com mÃºsica, arte, gastronomia e experiÃªncias para curtir com os amigos.",
                            "MÃºsica"
                    ),
                    new DadoEventoInicial(
                            "Future Tech Summit",
                            "assets/images/evento3.webp",
                            LocalDateTime.of(2026, 10, 3, 9, 0),
                            "Expo Center Norte",
                            "Rua JosÃ© Bernardo Pinto, 333 - Vila Guilherme, SÃ£o Paulo - SP",
                            "Tecnologia, inteligÃªncia artificial, desenvolvimento e produtos digitais em um dia inteiro de palestras e networking.",
                            "Tecnologia"
                    ),
                    new DadoEventoInicial(
                            "Game Dev Night",
                            "assets/images/evento4.webp",
                            LocalDateTime.of(2026, 10, 10, 18, 30),
                            "Arena Hub",
                            "Al. Rio Claro, 241 - Bela Vista, SÃ£o Paulo - SP",
                            "Encontro para quem gosta de games, programaÃ§Ã£o e criaÃ§Ã£o de experiÃªncias interativas.",
                            "Workshop"
                    ),
                    new DadoEventoInicial(
                            "Sabores de SÃ£o Paulo",
                            "assets/images/evento1.jpg",
                            LocalDateTime.of(2026, 10, 18, 12, 0),
                            "Mercado Municipal",
                            "Rua da Cantareira, 306 - Centro HistÃ³rico, SÃ£o Paulo - SP",
                            "ExperiÃªncia gastronÃ´mica com chefs convidados, degustaÃ§Ãµes e oficinas rÃ¡pidas.",
                            "Gastronomia"
                    ),
                    new DadoEventoInicial(
                            "ConexÃ£o Empreendedora",
                            "assets/images/evento2.jpeg",
                            LocalDateTime.of(2026, 10, 24, 14, 0),
                            "Cubo ItaÃº",
                            "Alameda Vicente Pinzon, 54 - Vila OlÃ­mpia, SÃ£o Paulo - SP",
                            "Palestras, cases e rodas de conversa para quem quer tirar projetos do papel e conhecer novas pessoas.",
                            "Networking"
                    ),
                    new DadoEventoInicial(
                            "Teatro: Depois da Meia-Noite",
                            "assets/images/evento3.webp",
                            LocalDateTime.of(2026, 11, 7, 20, 0),
                            "Teatro Gazeta",
                            "Av. Paulista, 900 - Bela Vista, SÃ£o Paulo - SP",
                            "Uma peÃ§a contemporÃ¢nea sobre escolhas, encontros e tudo aquilo que muda quando a cidade desacelera.",
                            "Teatro"
                    ),
                    new DadoEventoInicial(
                            "Arena Esports Weekend",
                            "assets/images/evento4.webp",
                            LocalDateTime.of(2026, 11, 15, 10, 0),
                            "Shopping Center Norte",
                            "Travessa Casalbuono, 120 - Vila Guilherme, SÃ£o Paulo - SP",
                            "Campeonatos, desafios, comunidade gamer e espaÃ§os para testar jogos e conhecer criadores.",
                            "Workshop"
                    )
            );

            for (DadoEventoInicial dado : dados) {
                Evento evento = new Evento();
                evento.setEmpresa(empresa);
                evento.setTitulo(dado.titulo());
                evento.setStatus(true);
                evento.setClassificacao("Livre");
                evento.setBanner(dado.imagem());
                evento.setCapa(dado.imagem());
                evento.setDescricao(dado.descricao());
                evento.setPreco(BigDecimal.ZERO);
                evento = eventoRepository.save(evento);

                dataEventoRepository.save(new DataEvento(evento, dado.dataHora()));

                Localizacao localizacao = new Localizacao();
                localizacao.setEvento(evento);
                localizacao.setEndereco(dado.endereco());
                localizacao.setUf("SP");
                localizacao.setCidade("SÃ£o Paulo");
                localizacao.setNomeEstabelecimento(dado.local());
                localizacaoRepository.save(localizacao);

                Categoria categoria = new Categoria();
                categoria.setEvento(evento);
                categoria.setArtista(artista);
                categoria.setTipo(dado.categoria());
                categoria.setEstilo(dado.categoria());
                categoriaRepository.save(categoria);

                eventoArtistaRepository.save(new EventoArtista(evento, artista));
            }

            Insignia primeira = new Insignia();
            primeira.editarDados("Primeiro evento", null);
            insigniaRepository.save(primeira);

            Insignia explorador = new Insignia();
            explorador.editarDados("Explorador", null);
            insigniaRepository.save(explorador);
        };
    }

    private record DadoEventoInicial(String titulo,
                                     String imagem,
                                     LocalDateTime dataHora,
                                     String local,
                                     String endereco,
                                     String descricao,
                                     String categoria) {
    }
}
