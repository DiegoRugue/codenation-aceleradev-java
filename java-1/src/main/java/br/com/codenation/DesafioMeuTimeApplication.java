package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.model.Jogador;
import br.com.codenation.model.Time;
import br.com.codenation.repository.JogadorRepository;
import br.com.codenation.repository.TimeRepository;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

    private final TimeRepository timeRepository = new TimeRepository();
    private final JogadorRepository jogadorRepository = new JogadorRepository();

	private void verificarExisteTime(Long idTime) {
		verificarIdTimeNull(idTime);
		if (!timeRepository.verificarExisteIdTime(idTime)) {
			throw new TimeNaoEncontradoException("Time não encontrado");
		}
	}

	private void verificarExisteJogador(Long idJogador) {
		verificarIdJogadorNull(idJogador);
		if (!jogadorRepository.verificarExisteIdJogador(idJogador)) {
			throw new JogadorNaoEncontradoException("Jogador não encontrado");
		}
	}

	private void verificarIdJogadorNull(Long id) {
		if (id == null) {
			throw new NullPointerException("idJogador é obrigatório");
		}
		if (id < 0) {
			throw new IllegalArgumentException("IdJogador deve ser maior que 0");
		}
	}

	private void verificarIdTimeNull(Long id) {
		if (id == null) {
			throw new NullPointerException("idTime é obrigatório");
		}
		if (id < 0) {
			throw new IllegalArgumentException("idTime deve ser maior que 0");
		}
	}

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		verificarIdTimeNull(id);
		if (timeRepository.verificarExisteIdTime(id)) {
			throw new IdentificadorUtilizadoException("time já cadastrado");
		}
		timeRepository.incluirTime(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		verificarIdJogadorNull(id);
		if (jogadorRepository.verificarExisteIdJogador(id)) {
			throw new IdentificadorUtilizadoException("jogador já cadastrado");
		}
		verificarExisteTime(idTime);
		jogadorRepository.incluirJogador(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		verificarExisteJogador(idJogador);
		Long idTime = jogadorRepository.buscarTimeJogador(idJogador);
		timeRepository.definirCapitao(idTime, idJogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		verificarExisteTime(idTime);
		if (!timeRepository.verificarExisteCapitaoTime(idTime)) {
			throw new CapitaoNaoInformadoException("Capitão não informado");
		}
		return timeRepository.buscarCapitaoTime(idTime);
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		verificarExisteJogador(idJogador);
		return jogadorRepository.buscarNomeJogador(idJogador);
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		verificarExisteTime(idTime);
		return timeRepository.buscarNomeTime(idTime);
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		verificarExisteTime(idTime);
		return jogadorRepository.buscarJogadoresTime(idTime);
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		verificarExisteTime(idTime);
		return jogadorRepository.buscarMelhorJogadorTime(idTime);
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		verificarExisteTime(idTime);
		return jogadorRepository.buscarJogadorMaisVelhoTime(idTime);
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return timeRepository.listarTimes();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		verificarExisteTime(idTime);
    	return jogadorRepository.buscarJogadorMaiorSalarioTime(idTime);
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		verificarExisteJogador(idJogador);
		return jogadorRepository.buscarSalarioJogador(idJogador);
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		if (top == null) {
			throw new NullPointerException("top é obrigatório");
		}
		if (top < 0) {
			throw new IllegalArgumentException("top não pode ser negativo");
		}
    	return jogadorRepository.buscarTopJogadores(top);
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		verificarExisteTime(timeDaCasa);
		verificarExisteTime(timeDeFora);

		Time timeCasa = timeRepository.buscarTimePorId(timeDaCasa);
		Time timeFora = timeRepository.buscarTimePorId(timeDeFora);

		boolean uniformesIguais = timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal());

		if (uniformesIguais) {
			return timeFora.getCorUniformeSecundario();
		}
		return timeFora.getCorUniformePrincipal();
	}
}