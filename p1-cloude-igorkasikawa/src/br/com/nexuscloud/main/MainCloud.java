package br.com.nexuscloud.main;

import br.com.nexuscloud.exception.CapacidadeExcedidaException;
import br.com.nexuscloud.model.Cliente;
import br.com.nexuscloud.model.Container;
import br.com.nexuscloud.model.Servidor;
import br.com.nexuscloud.service.CloudManager;

public class MainCloud {
    // throws necessário porque alocarContainer lança exceção checked - RA: 25165089-2
    public static void main(String[] args) throws CapacidadeExcedidaException {
        // 1. Cria o manager - RA: 25165089-2
        CloudManager manager = new CloudManager();

        // 2. Cria o cliente - RA: 25165089-2
        Cliente cliente = new Cliente("111.222.333-44", "Wayne Enterprises");

        // 3. Cria o servidor (começa desligado) - RA: 25165089-2
        Servidor servidor = new Servidor("192.168.0.1", 16.0, 5.50);

        // 4. Registra o cliente e adiciona o servidor a ele - RA: 25165089-2
        manager.registrarCliente(cliente);
        manager.adicionarServidor(cliente, servidor);

        // 5. Cria os dois containers - RA: 25165089-2
        Container c1 = new Container("C-01", 10.0);
        Container c2 = new Container("C-02", 8.0);

        // 6. Teste unchecked: servidor desligado, deve dar erro.
        // Descomentar para testar e comentar de novo depois - RA: 25165089-2
        // servidor.alocarContainer(c1);

        // 7. Liga o servidor - RA: 25165089-2
        servidor.ligar();

        // 8. Aloca o container 1 com sucesso (10.0 de 16.0) - RA: 25165089-2
        servidor.alocarContainer(c1);

        // 9. Teste checked: 10.0 + 8.0 = 18.0, passa de 16.0 e cai no catch - RA: 25165089-2
        try {
            servidor.alocarContainer(c2);
        } catch (CapacidadeExcedidaException e) {
            System.out.println("Erro tratado: " + e.getMessage());
        }

        // 10. Mostra a fatura ativa, esperado 5.5 - RA: 25165089-2
        System.out.println("Fatura ativa: R$ " + manager.calcularFaturaAtiva(cliente));
    }
}