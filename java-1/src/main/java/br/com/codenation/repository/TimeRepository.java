package br.com.codenation.repository;

import br.com.codenation.model.Time;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TimeRepository {
    private List<Time> times = new ArrayList<>();

    public Time buscarTimePorId(Long id) {
        return times.stream().filter(x -> x.getId().equals(id)).findFirst().get();
    }

    public List<Long> listarTimes() {
        return times.stream()
                .mapToLong(Time::getId)
                .boxed()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    public Boolean verificarExisteIdTime(Long id) {
        return times.stream().anyMatch(time -> time.getId().equals(id));
    }

    public void incluirTime(Time time) {
        times.add(time);
    }

    public void definirCapitao(Long id, Long idJogador) {
        buscarTimePorId(id).setIdCapitao(idJogador);
    }

    public Boolean verificarExisteCapitaoTime(Long id) {
        return !(buscarTimePorId(id).getIdCapitao() == null);
    }

    public Long buscarCapitaoTime(Long id) {
        return buscarTimePorId(id).getIdCapitao();
    }

    public String buscarNomeTime(Long id) {
        return buscarTimePorId(id).getNome();
    }

    public void clear() {
        times.clear();
    }
}
