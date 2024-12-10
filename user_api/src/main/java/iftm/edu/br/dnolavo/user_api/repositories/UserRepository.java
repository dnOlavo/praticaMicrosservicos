package iftm.edu.br.dnolavo.user_api.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import iftm.edu.br.dnolavo.user_api.models.User;

/**
 * Interface de repositório para gerenciar as operações de persistência.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByCpf(String cpf);
    List<User> queryByNomeLike(String nome);
}