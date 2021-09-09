package br.com.wildrimak.proposta.domain.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Proposta {

    @Transient
    private final Logger logger = LoggerFactory.getLogger(Proposta.class);

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotEmpty
    private String documento;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;

    private Proposta() {
	this.statusProposta = null;
    }

    public Proposta(@NotEmpty String documento, @Email @NotEmpty String email, @NotEmpty String nome,
	    @NotEmpty String endereco, @Positive @NotNull BigDecimal salario) {

	this(); // o objetivo é remover warnign e não quebrar encapsulamento da minha classe por
		// causa do framework

	logger.info(
		"Proposta recebeu no construtor os seguintes campos: "
			+ "documento={}, email={}, nome={}, endereco={} e salario={}",
		documento, email, nome, endereco, salario);

	this.documento = documento;
	this.email = email;
	this.nome = nome;
	this.endereco = endereco;
	this.salario = salario;

    }

    public String getId() {
	return id;
    }

    public String getDocumento() {
	return documento;
    }

    public String getNome() {
	return nome;
    }

    public StatusProposta getStatusProposta() {
	return statusProposta;
    }

    public void setStatusProposta(StatusProposta statusProposta) {
	this.statusProposta = statusProposta;
    }

    @Override
    public String toString() {
	return "Proposta [id=" + id + ", documento=" + documento + ", email=" + email + ", nome=" + nome + ", endereco="
		+ endereco + ", salario=" + salario + ", statusProposta=" + statusProposta + "]";
    }

}
