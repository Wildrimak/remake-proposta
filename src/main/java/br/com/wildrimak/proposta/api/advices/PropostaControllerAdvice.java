package br.com.wildrimak.proposta.api.advices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.wildrimak.proposta.domain.services.PropostaService;

@RestControllerAdvice
public class PropostaControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(PropostaService.class);
    
    @ExceptionHandler
    public ResponseEntity<?> trataIllegalArgumentException(IllegalArgumentException exception) {

	logger.info("PropostaControllerAdvice recebeu no método trataIllegalArgumentException "
		+ "uma exception cujo mensagem é: {}", exception.getMessage());
	
	String detalhes = exception.getMessage();

	return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(detalhes);

    }

}
