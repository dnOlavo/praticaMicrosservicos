package iftm.edu.br.dnolavo.shopping_api.models.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import iftm.edu.br.dnolavo.shopping_api.models.Shopping;

/**
 * DTO para representar compras realizadas.
 */
@Data
@Document(collection = "compra")
public class ShoppingDTO {
    @Id
    private String id;
    @NotBlank(message = "O identificador do usuário é obrigatório.")
    private String identificadorUsuario;
    private LocalDateTime data;
    private List<ItemDTO> itens;
    private String total;

    /**
     * Converte um objeto Shop para ShopDTO.
     */
    public static ShoppingDTO converter(Shopping compra) {
        ShoppingDTO compraDTO = new ShoppingDTO();
        compraDTO.setId(compra.getId());
        compraDTO.setIdentificadorUsuario(compra.getIdentificadorUsuario());
        compraDTO.setData(compra.getData());
        compraDTO.setItens(
            compra.getItens().stream()
                .map(item -> {
                    ItemDTO itemDTO = new ItemDTO();
                    itemDTO.setIdentificadorProduto(item.getIdentificadorProduto());
                    itemDTO.setPreco(item.getPreco());
                    return itemDTO;
                }).collect(Collectors.toList())
        );
        compraDTO.setTotal(compra.getTotal());
        return compraDTO;
    }
}