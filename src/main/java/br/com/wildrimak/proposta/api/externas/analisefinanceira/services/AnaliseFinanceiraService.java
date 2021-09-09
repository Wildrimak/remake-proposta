package br.com.wildrimak.proposta.api.externas.analisefinanceira.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wildrimak.proposta.api.externas.analisefinanceira.endpoints.AnaliseFinanceiraEndpoint;
import br.com.wildrimak.proposta.api.externas.analisefinanceira.requests.SolicitacaoAnaliseRequest;
import br.com.wildrimak.proposta.domain.models.Proposta;
import br.com.wildrimak.proposta.domain.models.StatusProposta;
import feign.FeignException.UnprocessableEntity;

@Service
public class AnaliseFinanceiraService {

    private final Logger logger = LoggerFactory.getLogger(AnaliseFinanceiraService.class);

    @Autowired
    private AnaliseFinanceiraEndpoint analiseFinanceiraEndpoint;

    public Proposta consultaElegibilidade(Proposta proposta) {

	logger.info("AnaliseFinanceiraService recebeu no método consultarElegibilidade uma proposta com esse valor={}",
		proposta);

	try {

	    SolicitacaoAnaliseRequest solicitacaoAnaliseRequest = new SolicitacaoAnaliseRequest(proposta);

	    logger.info(
		    "AnaliseFinanceiraService no método consultarElegibilidade irá fazer uma requisicao a api de analiseFinanceira e enviará no corpo da request esse valor={}",
		    solicitacaoAnaliseRequest);

	    // sempre que não for UnprocessableEntity é pq o resultado é Elegivel
	    analiseFinanceiraEndpoint.avalia(solicitacaoAnaliseRequest);

	    proposta.setStatusProposta(StatusProposta.ELEGIVEL);

	} catch (UnprocessableEntity e) {

	    proposta.setStatusProposta(StatusProposta.NAO_ELEGIVEL);
	    logger.warn(
		    "AnaliseFinanceiraService no método consultarElegibilidade teve seu estado classificado como Ineligivel ficando com proposta tendo esse valor={}",
		    proposta);

	}

	return proposta;

    }

}
