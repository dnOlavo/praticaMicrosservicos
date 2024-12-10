package iftm.edu.br.dnolavo.product_api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import iftm.edu.br.dnolavo.product_api.models.Category;

/**
 * Repositório para gerenciar a persistência de categorias.
 */
public interface CategoryRepository extends MongoRepository<Category, String> {
}