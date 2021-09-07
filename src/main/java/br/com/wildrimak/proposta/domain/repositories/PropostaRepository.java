package br.com.wildrimak.proposta.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.wildrimak.proposta.domain.models.Proposta;

public interface PropostaRepository extends CrudRepository<Proposta, String> {

}
