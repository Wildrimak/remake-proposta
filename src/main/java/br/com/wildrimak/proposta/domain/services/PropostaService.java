package br.com.wildrimak.proposta.domain.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wildrimak.proposta.api.externas.analisefinanceira.services.AnaliseFinanceiraService;
import br.com.wildrimak.proposta.domain.models.Proposta;
import br.com.wildrimak.proposta.domain.repositories.PropostaRepository;

@Service
public class PropostaService {

    private final Logger logger = LoggerFactory.getLogger(PropostaService.class);

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private AnaliseFinanceiraService analiseFinanceiraService;

    public Proposta criarProposta(Proposta proposta) {

	logger.info("PropostaService recebeu no método criarProposta o modelo proposta com esse valor={}", proposta);

	Optional<Proposta> encontrada = propostaRepository.findByDocumento(proposta.getDocumento());

	// uma melhoria para esse codigo é salvar os dados de documento sem pontos e
	// traços para evitar duplo cadastro
	if (encontrada.isPresent()) {

	    logger.warn("PropostaService recebeu no método criarProposta uma proposta cujo documento já se "
		    + "encontra salvo no banco de dados, com esses dados={}", encontrada.get());

	    throw new IllegalArgumentException("Esse documento já foi cadastrado");

	}

	Proposta salva = propostaRepository.save(proposta);

	logger.info("PropostaService no método criarProposta terminou de salvar a proposta e ficou com esse valor={}",
		salva);

	Proposta avaliada = analiseFinanceiraService.consultaElegibilidade(salva);

	logger.info(
		"PropostaService no método criarProposta terminou de avaliar a elegibilidade da proposta e ficou com esse valor={}",
		avaliada);

	return avaliada;

    }

}
