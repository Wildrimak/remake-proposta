package br.com.wildrimak.proposta.api.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.wildrimak.proposta.api.requests.PropostaRequest;
import br.com.wildrimak.proposta.api.validators.DocumentoValidator;
import br.com.wildrimak.proposta.domain.models.Proposta;
import br.com.wildrimak.proposta.domain.repositories.PropostaRepository;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private DocumentoValidator documentoValidator;

    @PostMapping
    public ResponseEntity<?> criarProposta(@RequestBody @Valid PropostaRequest propostaRequest,
	    UriComponentsBuilder uriComponentsBuilder) {

	logger.info("PropostaController recebeu no método criarProposta o propostaRequest com esse valor={}",
		propostaRequest);

	Proposta proposta = propostaRequest.toModel();

	Proposta propostaSalva = propostaRepository.save(proposta);

	logger.info(
		"PropostaController no método criarProposta terminou de salvar uma nova proposta com os seguintes valores={}",
		propostaSalva);

	return ResponseEntity
		.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(propostaSalva.getId()).toUri())
		.body(propostaSalva);

    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
	binder.addValidators(documentoValidator);
    }

}
