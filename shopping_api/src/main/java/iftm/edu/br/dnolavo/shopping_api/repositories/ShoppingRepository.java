package iftm.edu.br.dnolavo.shopping_api.repositories;

import iftm.edu.br.dnolavo.shopping_api.models.Shopping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositório para gerenciar operações de persistência relacionadas às compras.
 * Não é necessário criar um repositório separado para os itens, pois estão embutidos nas compras.
 */
@Repository
public interface ShoppingRepository extends MongoRepository<Shopping, String> {
    
    /**
     * Busca compras pelo identificador do usuário.
     */
    List<Shopping> findByIdentificadorUsuario(String identificadorUsuario);

    /**
     * Busca compras realizadas em uma data específica.
     */
    List<Shopping> findByData(LocalDateTime data);

    /**
     * Busca compras contendo um item com o identificador do produto especificado.
     */
    List<Shopping> findByItensIdentificadorProduto(String identificadorProduto);
    
    /**
     * Busca compras realizadas dentro de um intervalo de datas.
     */
    List<Shopping> findByDataBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    /**
     * Busca compras realizadas dentro de um intervalo de datas e com total acima de um valor especificado.
     */
    List<Shopping> findByDataBetweenAndTotalGreaterThan(LocalDateTime dataInicio, LocalDateTime dataFim, String total);

    /**
     * Busca compras com total maior que um valor especificado.
     */
    List<Shopping> findByTotalGreaterThan(String total);
}