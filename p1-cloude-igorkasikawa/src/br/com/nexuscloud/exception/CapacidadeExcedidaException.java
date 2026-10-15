package br.com.nexuscloud.exception;

// Exceção checked (Exception), exige try-catch ou throws - RA: 25165089-2
public class CapacidadeExcedidaException extends Exception {

    // Passa a mensagem para a classe pai - RA: 25165089-2
    public CapacidadeExcedidaException(String mensagem) {
        super(mensagem);
    }
}