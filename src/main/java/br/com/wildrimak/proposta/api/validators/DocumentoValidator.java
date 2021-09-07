package br.com.wildrimak.proposta.api.validators;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.wildrimak.proposta.api.requests.PropostaRequest;

@Component
public class DocumentoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
	return PropostaRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

	PropostaRequest propostaRequest = (PropostaRequest) target;

	String documento = propostaRequest.getDocumento();

	if (!isCPFValido(documento)) {

	    if (!isCNPJValido(documento)) {
		errors.rejectValue("documento", "Documento inválido");
	    }

	}

    }

    private boolean isCPFValido(String cpf) {

	CPFValidator cpfValidator = new CPFValidator();
	cpfValidator.initialize(null);

	return cpfValidator.isValid(cpf, null);

    }

    private boolean isCNPJValido(String cnpj) {

	CNPJValidator cnpjValidator = new CNPJValidator();
	cnpjValidator.initialize(null);

	return cnpjValidator.isValid(cnpj, null);

    }

}
