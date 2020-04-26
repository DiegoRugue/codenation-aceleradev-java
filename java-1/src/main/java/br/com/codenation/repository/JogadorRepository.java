package br.com.codenation.repository;

import br.com.codenation.model.Jogador;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JogadorRepository {
    private List<Jogador> jogadores = new ArrayList<>();

    private Jogador buscarJogadorPorId(Long id) {
        return jogadores.stream().filter(x -> x.getId().equals(id)).findFirst().get();
    }

    public List<Long> buscarTopJogadores(int quantidadeJogadores) {
        return jogadores.stream()
                .sorted(Comparator.comparing(Jogador::getSalario)
                        .reversed()
                        .thenComparing(Jogador::getId))
                .mapToLong(Jogador::getId)
                .limit(quantidadeJogadores)
                .boxed()
                .collect(Collectors.toList());
    }

    public Boolean verificarExisteIdJogador(Long id) {
        return jogadores.stream().anyMatch(jogador -> jogador.getId().equals(id));
    }

    public void incluirJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public Long buscarTimeJogador(Long id) {
        return buscarJogadorPorId(id).getIdTime();
    }

    public String buscarNomeJogador(Long id) {
        return buscarJogadorPorId(id).getNome();
    }

    public List<Long> buscarJogadoresTime(Long idTime) {
        return jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime))
                .mapToLong(Jogador::getId)
                .boxed()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    public Long buscarMelhorJogadorTime(Long idTime) {
        return jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime))
                .max(Comparator.comparing(Jogador::getNivelHabilidade))
                .get()
                .getId();
    }

    public Long buscarJogadorMaisVelhoTime(Long idTime) {
        return jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime))
                .sorted(Comparator.comparing(Jogador::getId))
                .min(Comparator.comparing(Jogador::getDataNascimento))
                .get()
                .getId();
    }

    public Long buscarJogadorMaiorSalarioTime(Long idTime) {
        return jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime))
                .sorted(Comparator.comparing(Jogador::getId))
                .max(Comparator.comparing(Jogador::getSalario))
                .get()
                .getId();
    }

    public BigDecimal buscarSalarioJogador(Long id) {
        return buscarJogadorPorId(id).getSalario();
    }

    public void clear() {
        jogadores.clear();
    }
}
