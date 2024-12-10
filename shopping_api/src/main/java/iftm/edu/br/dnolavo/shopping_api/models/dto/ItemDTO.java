package iftm.edu.br.dnolavo.shopping_api.models.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;


/**
 * DTO para representar um item em uma compra.
 */
@Data
@Document(collection = "item")
public class ItemDTO {
    @NotBlank(message = "O identificador do produto é obrigatório.")
    private String identificadorProduto;
    private String preco;
}