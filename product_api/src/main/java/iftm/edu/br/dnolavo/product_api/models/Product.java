package iftm.edu.br.dnolavo.product_api.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import iftm.edu.br.dnolavo.product_api.models.dto.ProductDTO;

/**
 * Entidade que representa um produto no banco de dados.
 */
@Data
@Document(collection = "produto")
public class Product {
    @Id
    private String id;
    @Field(name = "identificadorProduto")
    private String identificadorProduto;
    private String nome;
    private String descricao;
    private String preco;

    @DBRef
    private Category categoria;

    public static Product convert(ProductDTO produtoDTO, Category categoria) {
        Product produto = new Product();
        produto.setIdentificadorProduto(produtoDTO.getIdentificadorProduto());
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setCategoria(categoria);
        return produto;
    }
}