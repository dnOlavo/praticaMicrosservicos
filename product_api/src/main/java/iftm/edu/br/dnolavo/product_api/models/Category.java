package iftm.edu.br.dnolavo.product_api.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import iftm.edu.br.dnolavo.product_api.models.dto.CategoryDTO;
import jakarta.validation.constraints.NotBlank;

/**
 * Entidade que representa uma categoria no banco de dados.
 */
@Data
@Document(collection = "categoria")
public class Category {
    @Id
    private String id;
    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;

    public static Category convert(CategoryDTO categoriaDTO) {
        Category categoria = new Category();
        categoria.setNome(categoriaDTO.getNome());
        return categoria;
    }
}