package iftm.edu.br.dnolavo.product_api.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import iftm.edu.br.dnolavo.product_api.models.Category;
import iftm.edu.br.dnolavo.product_api.models.Product;

/**
 * Repositório para gerenciar a persistência de produtos.
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByCategoryId(Category idCategoria);

    Product findByProductIdentifier(String identificadorProduto);
}