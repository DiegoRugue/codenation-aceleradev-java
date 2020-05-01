package challenge;


import java.util.Objects;

public class Motorista {

    private final String nome;

    private final int idade;

    private final int pontos;

    private final String habilitacao;

    private Motorista(String nome, int idade, int pontos, String habilitacao) {
        this.nome = setNome(nome);
        this.idade = idade;
        this.pontos = pontos;
        this.habilitacao = setHabilitacao(habilitacao);
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getPontos() {
        return pontos;
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    private String setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new NullPointerException("Não permitimos motoristas sem nome");
        }
        return nome;
    }

    private String setHabilitacao(String habilitacao) {
        if (habilitacao == null || habilitacao.trim().isEmpty()) {
            throw new NullPointerException("Não permitimos motoristas sem habilitação");
        }
        return habilitacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Motorista motorista = (Motorista) o;
        return Objects.equals(habilitacao, motorista.habilitacao);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(habilitacao);
    }

    @Override
    public String toString() {
        return "Motorista{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", pontos=" + pontos +
                ", habilitacao='" + habilitacao + '\'' +
                '}';
    }

    public static MotoristaBuilder builder() {
        return new MotoristaBuilder();
    }


    public static class MotoristaBuilder {
        private String nome;

        private int idade;

        private int pontos;

        private String habilitacao;

        private static final int MIN_VALOR = 0;

        private MotoristaBuilder() {
        }

        private int setIdade(int idade) {
            if (idade < MIN_VALOR) {
                throw new IllegalArgumentException("Não é permitido idade negativa");
            }
            return idade;
        }

        private int setPontos(int pontos) {
            if (pontos < MIN_VALOR) {
                throw new IllegalArgumentException("Não é permitido pontos negativos");
            }
            return pontos;
        }

        public MotoristaBuilder withNome(String nome) {
            this.nome = nome;
            return this;
        }

        public MotoristaBuilder withIdade(int idade) {
            this.idade = setIdade(idade);
            return this;
        }

        public MotoristaBuilder withPontos(int pontos) {
            this.pontos = setPontos(pontos);
            return this;
        }

        public MotoristaBuilder withHabilitacao(String habilitacao) {
            this.habilitacao = habilitacao;
            return this;
        }

        public Motorista build() {
            return new Motorista(nome, idade, pontos, habilitacao);
        }
    }
}
