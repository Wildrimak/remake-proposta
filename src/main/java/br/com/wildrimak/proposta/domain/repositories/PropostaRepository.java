package br.com.wildrimak.proposta.domain.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.wildrimak.proposta.domain.models.Proposta;

public interface PropostaRepository extends CrudRepository<Proposta, String> {

    Optional<Proposta> findByDocumento(String documento);

}
