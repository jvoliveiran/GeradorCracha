/**
 * 
 */
package com.jvoliveira.geradorcracha.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jvoliveira.geradorcracha.domain.Pessoa;

/**
 * @author João Victor
 *
 */
@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{

}
