package br.com.wildrimak.proposta.api.externas.analisefinanceira.requests;

import javax.validation.constraints.NotNull;

import br.com.wildrimak.proposta.domain.models.Proposta;

public class SolicitacaoAnaliseRequest {

    private String documento;
    private String nome;
    private String idProposta;

    public SolicitacaoAnaliseRequest(@NotNull Proposta proposta) {

	this.documento = proposta.getDocumento();
	this.nome = proposta.getNome();
	this.idProposta = proposta.getId();

    }

    public String getDocumento() {
	return documento;
    }

    public String getNome() {
	return nome;
    }

    public String getIdProposta() {
	return idProposta;
    }

    @Override
    public String toString() {
	return "SolicitacaoAnaliseRequest [documento=" + documento + ", nome=" + nome + ", idProposta=" + idProposta
		+ "]";
    }

}
