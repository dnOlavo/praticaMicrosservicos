package iftm.edu.br.dnolavo.product_api.services;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import iftm.edu.br.dnolavo.product_api.models.Category;
import iftm.edu.br.dnolavo.product_api.models.Product;
import iftm.edu.br.dnolavo.product_api.models.dto.ProductDTO;
import iftm.edu.br.dnolavo.product_api.repositories.CategoryRepository;
import iftm.edu.br.dnolavo.product_api.repositories.ProductRepository;

/**
 * Serviço que implementa a lógica de negócios para produtos.
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repositorioProduto;
    private final CategoryRepository repositorioCategoria;

    public List<ProductDTO> obterTodos() {
        return repositorioProduto.findAll().stream()
            .map(ProductDTO::convert)
            .collect(Collectors.toList());
    }

    public ProductDTO buscarPorId(String id) {
        Product produto = repositorioProduto.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return ProductDTO.convert(produto);
    }

    public ProductDTO salvar(ProductDTO produtoDTO) {
        Category categoria = repositorioCategoria.findById(produtoDTO.getIdCategoria())
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        Product produto = Product.convert(produtoDTO, categoria);
        produto = repositorioProduto.save(produto);
        return ProductDTO.convert(produto);
    }

    public ProductDTO atualizar(String id, ProductDTO produtoDTO) {
        Product produto = repositorioProduto.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        if (produtoDTO.getNome() != null && !produto.getNome().equals(produtoDTO.getNome())) {
            produto.setNome(produtoDTO.getNome());
        }
        if (produtoDTO.getDescricao() != null && !produto.getDescricao().equals(produtoDTO.getDescricao())) {
            produto.setDescricao(produtoDTO.getDescricao());
        }
        if (produtoDTO.getPreco() != null && !produto.getPreco().equals(produtoDTO.getPreco())) {
            produto.setPreco(produtoDTO.getPreco());
        }
        return ProductDTO.convert(repositorioProduto.save(produto));
    }

    public void excluir(String id) {
        Product produto = repositorioProduto.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        repositorioProduto.delete(produto);
    }

    public Page<ProductDTO> obterTodosPaginados(Pageable paginacao) {
        return repositorioProduto.findAll(paginacao).map(ProductDTO::convert);
    }

    public List<ProductDTO> buscarPorCategoria(Category idCategoria) {
        return repositorioProduto.findByCategoryId(idCategoria).stream()
            .map(ProductDTO::convert)
            .collect(Collectors.toList());
    }

    public ProductDTO buscarPorIdentificador(String identificadorProduto) {
        Product produto = repositorioProduto.findByProductIdentifier(identificadorProduto);
        if (produto != null) {
            return ProductDTO.convert(produto);
        } else {
            throw new RuntimeException("Produto com identificador " + identificadorProduto + " não encontrado");
        }
    }
}