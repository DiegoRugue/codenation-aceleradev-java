package br.com.codenation;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.model.Time;
import br.com.codenation.repository.TimeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DesafioMeuTimeApplicationTest {

    private final DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

    @AfterEach
    public void clear() {
        desafio.clearAll();
    }

    @Test
    void incluirTimeIdObrigatorio() {
        try {
            desafio.incluirTime(null, null, null, null, null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "idTime é obrigatório");
        }
        try {
            desafio.incluirTime(-1L, null, null, null, null);
        } catch (IllegalArgumentException ex) {
            assertEquals(ex.getMessage(), "idTime deve ser maior que 0");
        }
    }

    @Test
    void incluirTimeNomeObrigatorio() {
        try {
            desafio.incluirTime(1L, null, null, null, null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "nome é obrigatório");
        }
        try {
            desafio.incluirTime(1L, "", null, null, null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "nome é obrigatório");
        }
    }

    @Test
    void incluirTimeDataCriacaoObrigatorio() {
        try {
            desafio.incluirTime(1L, "Teste", null, null, null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "dataCriacao é obrigatório");
        }
    }

    @Test
    void incluirTimeUniformePrincipalObrigatorio() {
        try {
            desafio.incluirTime(1L, "Teste", LocalDate.now(), "  ", null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "corUniformePrincipal é obrigatório");
        }
        try {
            desafio.incluirTime(1L, "Teste", LocalDate.now(), null, null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "corUniformePrincipal é obrigatório");
        }
    }

    @Test
    void incluirTimeUniformeSecundarioObrigatorio() {
        try {
            desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "");
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "corUniformeSecundario é obrigatório");
        }
        try {
            desafio.incluirTime(1L, "Teste", LocalDate.now(), "Amarelo", null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "corUniformeSecundario é obrigatório");
        }
    }

    @Test
    void incluirTimeIdJaCadastrado() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Vermelho");
        try {
            desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Vermelho");
        } catch (IdentificadorUtilizadoException ex) {
            assertEquals(ex.getMessage(), "time já cadastrado");
        }
    }

    @Test
    void incluirTimeUniformeSecundarioNaoPodeSerIgualPrincipal() {
        try {
            desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Azul");
        } catch (IllegalArgumentException ex) {
            assertEquals(ex.getMessage(), "Cor do uniforme principal não pode ser igual ao secundário");
        }
    }

    @Test
    void incluirJogadorIdObrigatorio() {
        try {
            desafio.incluirJogador(null, null, null, null, null, null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "idJogador é obrigatório");
        }
        try {
            desafio.incluirJogador(-1L, null, null, null, null, null);
        } catch (IllegalArgumentException ex) {
            assertEquals(ex.getMessage(), "IdJogador deve ser maior que 0");
        }
    }

    @Test
    void incluirJogadorIdTimeObrigatorio()  {
        try {
            desafio.incluirJogador(1L, null, null, null, null, null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "idTime é obrigatório");
        }
    }

    @Test
    void incluirJogadorNomeObrigatorio() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Vermelho");
        try {
            desafio.incluirJogador(1L, 1L, "", null, null, null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "nome é obrigatório");
        }
        try {
            desafio.incluirJogador(1L, 1L, null, null, null, null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "nome é obrigatório");
        }
    }

    @Test
    void incluirJogadorDataNascimentoObrigatorio() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Vermelho");
        try {
            desafio.incluirJogador(1L, 1L, "Teste", null, null, null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "data de nascimento é obrigatório");
        }
    }

    @Test
    void incluirJogadorNivelHabilidadeObrigatorio() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Vermelho");
        try {
            desafio.incluirJogador(1L, 1L, "Teste", LocalDate.now(), null, null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "nivelHabilidade é obrigatório");
        }
    }

    @Test
    void incluirJogadorSalarioObrigatorio() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Vermelho");
        try {
            desafio.incluirJogador(1L, 1L, "Teste", LocalDate.now(), 5, null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "salario é obrigatório");
        }
    }

    @Test
    void incluirJogadorIdJaCadastrado() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Vermelho");
        desafio.incluirJogador(1L, 1L, "Teste", LocalDate.now(), 5, new BigDecimal(5000));
        try {
            desafio.incluirJogador(1L, 1L, "Teste", LocalDate.now(), 5, new BigDecimal(5000));
        } catch (IdentificadorUtilizadoException ex) {
            assertEquals(ex.getMessage(), "jogador já cadastrado");
        }
    }

    @Test
    void incluirJogadorValidarIntervaloHabilidade() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Vermelho");
        try {
            desafio.incluirJogador(1L, 1L, "Teste", LocalDate.now(), -1, new BigDecimal(5000));
        } catch (IllegalArgumentException ex) {
            assertEquals(ex.getMessage(), "nivelHabilidade deve estar entre 0 e 100");
        }
        try {
            desafio.incluirJogador(1L, 1L, "Teste", LocalDate.now(), 101, new BigDecimal(5000));
        } catch (IllegalArgumentException ex) {
            assertEquals(ex.getMessage(), "nivelHabilidade deve estar entre 0 e 100");
        }
    }

    @Test
    void incluirJogadorSalarioMaiorQueZero() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Vermelho");
        try {
            desafio.incluirJogador(1L, 1L, "Teste", LocalDate.now(), 5, new BigDecimal(-1));
        } catch (IllegalArgumentException ex) {
            assertEquals(ex.getMessage(), "salario deve ser maior que 0");
        }
    }

    @Test
    void incluirJogadorTimeNaoEncontrado() {
        try {
            desafio.incluirJogador(1L, 1L, "Teste", LocalDate.now(), 5, new BigDecimal(5000));
        } catch (TimeNaoEncontradoException ex) {
            assertEquals(ex.getMessage(), "Time não encontrado");
        }
    }

    @Test
    void definirCapitaoTimeBuscaCapitaoTime() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Vermelho");
        desafio.incluirJogador(5L, 1L, "Teste", LocalDate.now(), 5, new BigDecimal(5000));

        desafio.definirCapitao(5L);

        assertEquals(desafio.buscarCapitaoDoTime(1L), 5L);
    }

    @Test
    void definirCapitaoTimeJogadorNaoEncontrado() {
        try {
            desafio.definirCapitao(1L);
        } catch (JogadorNaoEncontradoException ex) {
            assertEquals(ex.getMessage(), "Jogador não encontrado");
        }
    }

    @Test
    void buscaCapitaoTimeNaoEncontrado() {
        try {
            desafio.buscarCapitaoDoTime(1L);
        } catch (TimeNaoEncontradoException ex) {
            assertEquals(ex.getMessage(), "Time não encontrado");
        }
    }

    @Test
    void buscaCapitaoTimeNaoTemCapitao() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Rosa");
        try {
            desafio.buscarCapitaoDoTime(1L);
        } catch (CapitaoNaoInformadoException ex) {
            assertEquals(ex.getMessage(), "Capitão não informado");
        }
    }

    @Test
    void buscaNomeJogador() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Rosa");
        desafio.incluirJogador(5L, 1L, "Teste Jogador", LocalDate.now(), 5, new BigDecimal(5000));
        assertEquals(desafio.buscarNomeJogador(5L), "Teste Jogador");
    }

    @Test
    void buscaNomeJogadorNaoCadastrado() {
        try {
            desafio.buscarNomeJogador(1L);
        } catch (JogadorNaoEncontradoException ex) {
            assertEquals(ex.getMessage(), "Jogador não encontrado");
        }
    }

    @Test
    void buscaNomeTime() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Rosa");
        assertEquals(desafio.buscarNomeTime(1L), "Teste");
    }

    @Test
    void buscaNomeTimeNaoCadastrado() {
        try {
            desafio.buscarNomeTime(1L);
        } catch (TimeNaoEncontradoException ex) {
            assertEquals(ex.getMessage(), "Time não encontrado");
        }
    }

    @Test
    void buscaJogadoresTime() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Rosa");
        desafio.incluirJogador(7L, 1L, "Teste Jogador 1", LocalDate.now(), 5, new BigDecimal(5000));
        desafio.incluirJogador(5L, 1L, "Teste Jogador 2", LocalDate.now(), 5, new BigDecimal(5000));
        desafio.incluirJogador(2L, 1L, "Teste Jogador 3", LocalDate.now(), 5, new BigDecimal(5000));
        desafio.incluirJogador(9L, 1L, "Teste Jogador 4", LocalDate.now(), 5, new BigDecimal(5000));

        List<Long> testList = new ArrayList<>();
        testList.add(2L);
        testList.add(5L);
        testList.add(7L);
        testList.add(9L);

        assertEquals(desafio.buscarJogadoresDoTime(1L), testList);
    }

    @Test
    void buscaJogadoresTimeNaoEncontrado() {
        try {
            desafio.buscarJogadoresDoTime(1L);
        } catch (TimeNaoEncontradoException ex) {
            assertEquals(ex.getMessage(), "Time não encontrado");
        }
    }

    @Test
    void buscaMelhorJogadorTime() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Rosa");
        desafio.incluirJogador(7L, 1L, "Teste Jogador 1", LocalDate.now(), 15, new BigDecimal(5000));
        desafio.incluirJogador(5L, 1L, "Teste Jogador 2", LocalDate.now(), 5, new BigDecimal(5000));
        desafio.incluirJogador(2L, 1L, "Teste Jogador 3", LocalDate.now(), 15, new BigDecimal(5000));
        desafio.incluirJogador(9L, 1L, "Teste Jogador 4", LocalDate.now(), 5, new BigDecimal(5000));


        assertEquals(desafio.buscarMelhorJogadorDoTime(1L), 7L);
    }

    @Test
    void buscaMelhorJogadorTimeNaoEncontrado() {
        try {
            desafio.buscarMelhorJogadorDoTime(1L);
        } catch (TimeNaoEncontradoException ex) {
            assertEquals(ex.getMessage(), "Time não encontrado");
        }
    }

    @Test
    void buscarJogadorMaisVelhoTime() {
        LocalDate date = LocalDate.of(1990, Month.APRIL, 1);

        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Rosa");
        desafio.incluirJogador(7L, 1L, "Teste Jogador 1", date, 15, new BigDecimal(5000));
        desafio.incluirJogador(5L, 1L, "Teste Jogador 2", LocalDate.now(), 5, new BigDecimal(5000));
        desafio.incluirJogador(2L, 1L, "Teste Jogador 3", date, 15, new BigDecimal(5000));
        desafio.incluirJogador(9L, 1L, "Teste Jogador 4", LocalDate.now(), 5, new BigDecimal(5000));


        assertEquals(desafio.buscarJogadorMaisVelho(1L), 2L);
    }

    @Test
    void buscarJogadorMaisVelhoTimeNaoEncontrado() {
        try {
            desafio.buscarJogadorMaisVelho(1L);
        } catch (TimeNaoEncontradoException ex) {
            assertEquals(ex.getMessage(), "Time não encontrado");
        }
    }

    @Test
    void buscarTimes() {
        desafio.incluirTime(5L, "Teste", LocalDate.now(), "Azul", "Rosa");
        desafio.incluirTime(3L, "Teste", LocalDate.now(), "Azul", "Rosa");
        desafio.incluirTime(2L, "Teste", LocalDate.now(), "Azul", "Rosa");
        desafio.incluirTime(9L, "Teste", LocalDate.now(), "Azul", "Rosa");
        desafio.incluirTime(10L, "Teste", LocalDate.now(), "Azul", "Rosa");

        List<Long> testlist = new ArrayList<>();

        testlist.add(2L);
        testlist.add(3L);
        testlist.add(5L);
        testlist.add(9L);
        testlist.add(10L);

        assertEquals(desafio.buscarTimes(), testlist);
    }

    @Test
    void buscarJogadorMaiorSalario() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Rosa");
        desafio.incluirJogador(7L, 1L, "Teste Jogador 1", LocalDate.now(), 15, new BigDecimal(15000));
        desafio.incluirJogador(5L, 1L, "Teste Jogador 2", LocalDate.now(), 5, new BigDecimal(5000));
        desafio.incluirJogador(2L, 1L, "Teste Jogador 3", LocalDate.now(), 15, new BigDecimal(15000));
        desafio.incluirJogador(9L, 1L, "Teste Jogador 4", LocalDate.now(), 5, new BigDecimal(5000));

        assertEquals(desafio.buscarJogadorMaiorSalario(1L), 2L);
    }

    @Test
    void buscarJogadorMaiorSalarioTimeNaoEncontrado() {
        try {
            desafio.buscarJogadorMaiorSalario(1L);
        } catch (TimeNaoEncontradoException ex) {
            assertEquals(ex.getMessage(), "Time não encontrado");
        }
    }

    @Test
    void buscarSalarioJogador() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Rosa");
        desafio.incluirJogador(1L, 1L, "Teste Jogador 1", LocalDate.now(), 15, new BigDecimal(15000));

        assertEquals(desafio.buscarSalarioDoJogador(1L), new BigDecimal(15000));
    }

    @Test
    void buscarSalarioJogadorNaoEncontrado() {
        try {
            desafio.buscarSalarioDoJogador(1L);
        } catch (JogadorNaoEncontradoException ex) {
            assertEquals(ex.getMessage(), "Jogador não encontrado");
        }
    }

    @Test
    void buscarTopJogadores() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Rosa");
        desafio.incluirJogador(7L, 1L, "Teste Jogador 1", LocalDate.now(), 15, new BigDecimal(15000));
        desafio.incluirJogador(5L, 1L, "Teste Jogador 2", LocalDate.now(), 5, new BigDecimal(5000));
        desafio.incluirJogador(2L, 1L, "Teste Jogador 3", LocalDate.now(), 15, new BigDecimal(15000));
        desafio.incluirJogador(9L, 1L, "Teste Jogador 4", LocalDate.now(), 5, new BigDecimal(5000));

        List<Long> testList = new ArrayList<>();
        testList.add(2L);
        testList.add(7L);
        testList.add(5L);

        assertEquals(desafio.buscarTopJogadores(3), testList);
    }

    @Test
    void buscarTopJogadoresNaoExiste() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Rosa");
        List<Long> testList = new ArrayList<>();
        assertEquals(desafio.buscarTopJogadores(3), testList);
    }

    @Test
    void buscarTopJogadoresNulo() {
        try {
            desafio.buscarTopJogadores(null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "top é obrigatório");
        }
    }

    @Test
    void buscarCorCamisaTimeDeFora() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Rosa");
        desafio.incluirTime(2L, "Teste", LocalDate.now(), "Vermelho", "Preto");

        assertEquals(desafio.buscarCorCamisaTimeDeFora(1L, 2L), "Vermelho");
    }

    @Test
    void buscarCorCamisaTimeDeForaCorPrincipalIgual() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Rosa");
        desafio.incluirTime(2L, "Teste", LocalDate.now(), "Azul", "Preto");

        assertEquals(desafio.buscarCorCamisaTimeDeFora(1L, 2L), "Preto");
    }

    @Test
    void buscarCorCamisaTimeDeForaTimeCasaNulo() {
        try {
            desafio.buscarCorCamisaTimeDeFora(null, null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "idTime é obrigatório");
        }
    }

    @Test
    void buscarCorCamisaTimeDeForaTimeForaNulo() {
        desafio.incluirTime(1L, "Teste", LocalDate.now(), "Azul", "Preto");
        try {
            desafio.buscarCorCamisaTimeDeFora(1L, null);
        } catch (NullPointerException ex) {
            assertEquals(ex.getMessage(), "idTime é obrigatório");
        }
    }

    @Test
    void buscarDataCriacao() {
        TimeRepository times = new TimeRepository();
        LocalDate date = LocalDate.now();
        times.incluirTime(new Time(1L, "Teste", date, "Azul", "Preto"));

        assertEquals(times.buscarTimePorId(1L).getDataCriacao(), date);

    }
}
