package iftm.edu.br.dnolavo.product_api.models.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import iftm.edu.br.dnolavo.product_api.models.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Data Transfer Object (DTO) para produtos, usado para comunicação com o cliente.
 */
@Data
@Document(collection = "produto")
public class ProductDTO {
    @Id
    private String id;
    private String identificadorProduto;
    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;
    private String descricao;
    @NotBlank(message = "O preço do produto é obrigatório")
    private String preco;

    @JsonIgnore
    private String idCategoria;
    private CategoryDTO categoria;

    public static ProductDTO convert(Product produto) {
        ProductDTO produtoDTO = new ProductDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setIdentificadorProduto(produto.getIdentificadorProduto());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setDescricao(produto.getDescricao());
        produtoDTO.setPreco(produto.getPreco());

        produtoDTO.setCategoria(CategoryDTO.convert(produto.getCategoria()));
        return produtoDTO;
    }

    public void setCategoria(CategoryDTO categoria) {
        this.categoria = categoria;
        if (categoria != null) {
            this.idCategoria = categoria.getId();
        }
    }
}