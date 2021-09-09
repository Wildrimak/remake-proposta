package br.com.wildrimak.proposta.api.externas.analisefinanceira.endpoints;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.wildrimak.proposta.api.externas.analisefinanceira.requests.SolicitacaoAnaliseRequest;

@FeignClient(name = "${feign.analise_de_credito}", url = "${feign.url_analise_de_credito}")
public interface AnaliseFinanceiraEndpoint {

    @PostMapping("/api/solicitacao")
    public String avalia(SolicitacaoAnaliseRequest solicitacaoAnaliseRequest);

}
