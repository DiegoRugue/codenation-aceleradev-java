package br.com.codenation.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador extends BaseModel {
    private Long idTime;
    private String nome;
    private LocalDate dataNascimento;
    private Integer nivelHabilidade;
    private BigDecimal salario;

    public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        super(id);
        this.setIdTime(idTime);
        this.setNome(nome);
        this.setDataNascimento(dataNascimento);
        this.setNivelHabilidade(nivelHabilidade);
        this.setSalario(salario);
    }

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new NullPointerException("nome é obrigatório");
        }
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new NullPointerException("data de nascimento é obrigatório");
        }
        this.dataNascimento = dataNascimento;
    }

    public Integer getNivelHabilidade() {
        return nivelHabilidade;
    }

    public void setNivelHabilidade(Integer nivelHabilidade) {
        if (nivelHabilidade == null) {
            throw new NullPointerException("nivelHabilidade é obrigatório");
        }
        if (nivelHabilidade < 0 || nivelHabilidade > 100) {
            throw new IllegalArgumentException("nivelHabilidade deve estar entre 0 e 100");
        }
        this.nivelHabilidade = nivelHabilidade;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        if (salario == null) {
            throw new NullPointerException("salario é obrigatório");
        }
        if (salario.signum() < 0) {
            throw new IllegalArgumentException("salario deve ser maior que 0");
        }
        this.salario = salario;
    }
}
