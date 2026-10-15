package br.com.nexuscloud.model;

import br.com.nexuscloud.exception.CapacidadeExcedidaException;
import br.com.nexuscloud.exception.ServidorOfflineException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Servidor que hospeda os containers - RA: 25165089-2
public class Servidor {
    // Atributos privados por causa do encapsulamento - RA: 25165089-2
    private String ip;
    private double ramTotal;
    private double precoPorHora;

    // Nasce desligado, só liga com o método ligar() - RA: 25165089-2
    private boolean statusLigado = false;

    // Já inicializada para não dar NullPointerException - RA: 25165089-2
    private List<Container> containers = new ArrayList<>();

    // Construtor sem status e lista, que já têm valor inicial - RA: 25165089-2
    public Servidor(String ip, double ramTotal, double precoPorHora) {
        this.ip = ip;
        this.ramTotal = ramTotal;
        this.precoPorHora = precoPorHora;
    }

    // Muda o status para ligado - RA: 25165089-2
    public void ligar() {
        this.statusLigado = true;
    }

    // Soma a RAM dos containers com Stream, sem for - RA: 25165089-2
    public double calcularRamEmUso() {
        return containers.stream()
                .mapToDouble(Container::getRamNecessaria) // pega a RAM de cada um
                .sum(); // soma os valores
    }

    // Valida antes de adicionar (Fail-Fast) - RA: 25165089-2
    public void alocarContainer(Container c) throws CapacidadeExcedidaException {
        // Servidor desligado lança a exceção unchecked - RA: 25165089-2
        if (!statusLigado) {
            throw new ServidorOfflineException(
                "Erro: Não é possível alocar containers em um servidor desligado.");
        }
        // RAM em uso + RAM nova acima do total lança a exceção checked - RA: 25165089-2
        if (calcularRamEmUso() + c.getRamNecessaria() > ramTotal) {
            throw new CapacidadeExcedidaException(
                "Falha Crítica: Memória RAM insuficiente no servidor " + ip + ".");
        }
        // Passou nas validações, adiciona na lista - RA: 25165089-2
        containers.add(c);
    }

    // Getters e setters - RA: 25165089-2
    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }
    public double getRamTotal() { return ramTotal; }
    public void setRamTotal(double ramTotal) { this.ramTotal = ramTotal; }
    public double getPrecoPorHora() { return precoPorHora; }
    public void setPrecoPorHora(double precoPorHora) { this.precoPorHora = precoPorHora; }
    public boolean isStatusLigado() { return statusLigado; }
    public List<Container> getContainers() { return containers; }

    // Servidores com o mesmo IP são iguais - RA: 25165089-2
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // é o mesmo objeto
        if (o == null || getClass() != o.getClass()) return false; // nulo ou outra classe
        return Objects.equals(ip, ((Servidor) o).ip); // compara o IP
    }

    // hashCode usa o IP, igual ao equals - RA: 25165089-2
    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}