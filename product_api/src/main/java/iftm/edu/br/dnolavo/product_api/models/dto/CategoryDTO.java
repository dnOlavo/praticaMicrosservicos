package iftm.edu.br.dnolavo.product_api.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import iftm.edu.br.dnolavo.product_api.models.Category;

/**
 * Data Transfer Object (DTO) para categorias, usado para comunicação com o cliente.
 */
@Data
@Document(collection = "categoria")
public class CategoryDTO {
    @Id
    private String id;
    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;

    public static CategoryDTO convert(Category categoria) {
        CategoryDTO categoriaDTO = new CategoryDTO();
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNome(categoria.getNome());
        return categoriaDTO;
    }
}