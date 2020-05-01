package br.com.codenation.model;

import java.time.LocalDate;

public class Time extends BaseModel {
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;
    private Long idCapitao;

    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        super(id);
        this.setNome(nome);
        this.setDataCriacao(dataCriacao);
        this.setCorUniformePrincipal(corUniformePrincipal);
        this.setCorUniformeSecundario(corUniformeSecundario);
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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        if (dataCriacao == null) {
            throw new NullPointerException("dataCriacao é obrigatório");
        }
        this.dataCriacao = dataCriacao;
    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    public void setCorUniformePrincipal(String corUniformePrincipal) {
        if (corUniformePrincipal == null || corUniformePrincipal.trim().isEmpty()) {
            throw new NullPointerException("corUniformePrincipal é obrigatório");
        }
        this.corUniformePrincipal = corUniformePrincipal;
    }

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    public void setCorUniformeSecundario(String corUniformeSecundario) {
        if (corUniformeSecundario == null || corUniformeSecundario.trim().isEmpty()) {
            throw new NullPointerException("corUniformeSecundario é obrigatório");
        }
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public Long getIdCapitao() {
        return idCapitao;
    }

    public void setIdCapitao(Long idCapitao) {
        if (idCapitao == null) {
            throw new NullPointerException("id capitão não pode ser nulo");
        }
        if (idCapitao < 0) {
            throw new IllegalArgumentException("Id capitão não pode ser negativo");
        }
        this.idCapitao = idCapitao;
    }
}
