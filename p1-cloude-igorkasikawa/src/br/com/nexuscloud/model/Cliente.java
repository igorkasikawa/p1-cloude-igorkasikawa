package br.com.nexuscloud.model;

import java.util.Objects;

// Cliente que contrata os servidores - RA: 25165089-2
public class Cliente {
    // Atributos privados por causa do encapsulamento - RA: 25165089-2
    private String cpf;
    private String nome;

    // Construtor que recebe os valores - RA: 25165089-2
    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    // Getters e setters - RA: 25165089-2
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    // Clientes com o mesmo CPF são iguais - RA: 25165089-2
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // é o mesmo objeto
        if (o == null || getClass() != o.getClass()) return false; // nulo ou outra classe
        return Objects.equals(cpf, ((Cliente) o).cpf); // compara o CPF
    }

    // hashCode usa o CPF, igual ao equals - RA: 25165089-2
    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}