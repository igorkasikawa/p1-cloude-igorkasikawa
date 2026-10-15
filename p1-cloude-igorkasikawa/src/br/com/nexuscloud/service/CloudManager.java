package br.com.nexuscloud.service;

import br.com.nexuscloud.model.Cliente;
import br.com.nexuscloud.model.Servidor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Guarda os clientes e os servidores de cada um - RA: 25165089-2
public class CloudManager {
    // HashMap (Hash) com Cliente como chave e Set de Servidores como valor.
    // O HashSet não aceita repetidos e não tem ordem - RA: 25165089-2
    private Map<Cliente, Set<Servidor>> bancoDeDados = new HashMap<>();

    // Cadastra o cliente com conjunto vazio, se ainda não existir - RA: 25165089-2
    public void registrarCliente(Cliente c) {
        bancoDeDados.putIfAbsent(c, new HashSet<>());
    }

    // Adiciona o servidor no conjunto do cliente - RA: 25165089-2
    public void adicionarServidor(Cliente c, Servidor s) {
        bancoDeDados.get(c).add(s);
    }

    // Soma o preço por hora dos servidores ligados, com Stream - RA: 25165089-2
    public double calcularFaturaAtiva(Cliente c) {
        return bancoDeDados.getOrDefault(c, new HashSet<>()) // conjunto vazio se não achar o cliente
                .stream()
                .filter(Servidor::isStatusLigado) // só os ligados
                .mapToDouble(Servidor::getPrecoPorHora) // pega o preço
                .sum(); // soma final
    }

    // Getter do banco de dados - RA: 25165089-2
    public Map<Cliente, Set<Servidor>> getBancoDeDados() {
        return bancoDeDados;
    }
}