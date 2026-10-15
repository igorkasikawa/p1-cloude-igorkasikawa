package br.com.nexuscloud.model;

import java.util.Objects;

// Aplicação que roda dentro de um servidor - RA: 25165089-2
public class Container {
    // Atributos privados por causa do encapsulamento - RA: 25165089-2
    private String id;
    private double ramNecessaria;

    // Construtor que recebe os valores - RA: 25165089-2
    public Container(String id, double ramNecessaria) {
        this.id = id;
        this.ramNecessaria = ramNecessaria;
    }

    // Getters e setters - RA: 25165089-2
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public double getRamNecessaria() { return ramNecessaria; }
    public void setRamNecessaria(double ramNecessaria) { this.ramNecessaria = ramNecessaria; }

    // Containers com o mesmo ID são iguais - RA: 25165089-2
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // é o mesmo objeto
        if (o == null || getClass() != o.getClass()) return false; // nulo ou outra classe
        return Objects.equals(id, ((Container) o).id); // compara o ID
    }

    // hashCode usa o ID, igual ao equals - RA: 25165089-2
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}