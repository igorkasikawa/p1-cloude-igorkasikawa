package br.com.nexuscloud.exception;

// Exceção unchecked (RuntimeException), não exige try-catch - RA: 25165089-2
public class ServidorOfflineException extends RuntimeException {

    // Passa a mensagem para a classe pai - RA: 25165089-2
    public ServidorOfflineException(String mensagem) {
        super(mensagem);
    }
}