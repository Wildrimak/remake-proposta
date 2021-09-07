package br.com.wildrimak.proposta.api.requests;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wildrimak.proposta.domain.models.Proposta;

public class PropostaRequest {

    private final Logger logger = LoggerFactory.getLogger(PropostaRequest.class);

    @NotEmpty
    private String documento;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String endereco;

    @Positive
    @NotNull
    private BigDecimal salario;

    public PropostaRequest(@NotEmpty String documento, @Email @NotEmpty String email, @NotEmpty String nome,
	    @NotEmpty String endereco, @Positive @NotNull BigDecimal salario) {

	logger.info(
		"PropostaRequest recebeu no construtor os seguintes campos: "
			+ "documento={}, email={}, nome={}, endereco={} e salario={}",
		documento, email, nome, endereco, salario);

	this.documento = documento;
	this.email = email;
	this.nome = nome;
	this.endereco = endereco;
	this.salario = salario;
    }

    public Proposta toModel() {

	logger.info(
		"PropostaRequest no método toModel inicia o processo de criar um modelo de Proposta "
			+ "usando os seguintes valores: documento={}, email={}, nome={}, endereco={} e salario={}",
		documento, email, nome, endereco, salario);

	return new Proposta(documento, email, nome, endereco, salario);

    }

    public String getDocumento() {
	return documento;
    }

    @Override
    public String toString() {
	return "PropostaRequest [documento=" + documento + ", email=" + email + ", nome=" + nome + ", endereco="
		+ endereco + ", salario=" + salario + "]";
    }

}
