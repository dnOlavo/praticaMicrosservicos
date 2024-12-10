package iftm.edu.br.dnolavo.shopping_api.models;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import iftm.edu.br.dnolavo.shopping_api.models.dto.ShoppingDTO;

/**
 * Classe que representa compras armazenadas no banco de dados.
 */
@Data
@Document(collection = "compra")
public class Shopping {
    @Id
    private String id;
    private String identificadorUsuario;
    private LocalDateTime data;
    private List<Item> itens;
    private String total;

    /**
     * Converte um objeto ShopDTO para Shop.
     */
    public static Shopping converter(ShoppingDTO compraDTO) {
        Shopping compra = new Shopping();
        compra.setIdentificadorUsuario(compraDTO.getIdentificadorUsuario());
        compra.setData(LocalDateTime.now());
        compra.setItens(
            compraDTO.getItens()
                .stream()
                .map(itemDTO -> {
                    Item item = new Item();
                    item.setIdentificadorProduto(itemDTO.getIdentificadorProduto());
                    item.setPreco(itemDTO.getPreco());
                    return item;
                }).collect(Collectors.toList())
        );
        compra.setTotal(compraDTO.getTotal());
        return compra;
    }

    /**
     * Classe interna para representar um item em uma compra.
     */
    @Data
    public static class Item {
        private String identificadorProduto;
        private String preco;
    }
}