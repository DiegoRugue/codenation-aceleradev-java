package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.concurrent.ThreadLocalRandom.current;

public class Estacionamento {

    private final int MAX_VAGAS_ESTACIONAMENTO = 10;
    private final int MAIOR_IDADE = 18;
    private final int MAX_PONTOS_HABILITACAO = 20;
    private final int IDADE_PREFERENCIAL = 55;
    private List<Carro> carros = new ArrayList<>();

    public void estacionar(Carro carro) {
        verificarMotorista(carro.getMotorista());
        if (carros.size() < MAX_VAGAS_ESTACIONAMENTO) {
            carros.add(carro);
        } else {
            buscarVagaParaEstacionar(carro);
        }
    }

    private void buscarVagaParaEstacionar(Carro carro) {
        Optional<Carro> optionalCarro = carros.stream()
                .filter(carro1 -> carro1.getMotorista()
                        .getIdade() < IDADE_PREFERENCIAL).findFirst();

        if (optionalCarro.isPresent()) {
            carros.remove(optionalCarro.get());
            carros.add(carro);
        } else {
            throw new EstacionamentoException("Não foi possível estacionar, todas as vagas estão ocupadas.");
        }
    }

    public int carrosEstacionados() {
        return carros.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carros.contains(carro);
    }

    private void verificarMotorista(Motorista motorista) {
        if (motorista == null) {
            throw new EstacionamentoException("Não permitimos carros autónomo");
        } else if (motorista.getIdade() < MAIOR_IDADE) {
            throw new EstacionamentoException("Não permitimos motoristas menores de idade");
        } else if (motorista.getPontos() > MAX_PONTOS_HABILITACAO) {
            throw new EstacionamentoException("Não permitimos motoristas com habilitação suspensa");
        }
    }

}
