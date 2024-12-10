package iftm.edu.br.dnolavo.product_api.controller;

import java.util.List;
import java.util.UUID;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import iftm.edu.br.dnolavo.product_api.models.Category;
import iftm.edu.br.dnolavo.product_api.models.dto.ProductDTO;
import iftm.edu.br.dnolavo.product_api.services.ProductService;

/**
 * Controlador responsável pelas operações relacionadas a produtos.
 */
@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService servicoDeProduto;

    @GetMapping
    public List<ProductDTO> listarProdutos() {
        return servicoDeProduto.obterTodos();
    }

    @GetMapping("/{id}")
    public ProductDTO buscarProdutoPorId(@PathVariable String id) {
        return servicoDeProduto.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO criarProduto(@RequestBody @Valid ProductDTO produtoDTO) {
        produtoDTO.setIdentificadorProduto(UUID.randomUUID().toString());
        if (produtoDTO.getCategoria() != null) {
            produtoDTO.setIdCategoria(produtoDTO.getCategoria().getId());
        }
        return servicoDeProduto.salvar(produtoDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO editarProduto(@PathVariable String id, @RequestBody ProductDTO produtoDTO) {
        return servicoDeProduto.atualizar(id, produtoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirProduto(@PathVariable String id) {
        servicoDeProduto.excluir(id);
    }

    @GetMapping("/paginados")
    public Page<ProductDTO> listarProdutosPaginados(Pageable paginacao) {
        return servicoDeProduto.obterTodosPaginados(paginacao);
    }

    @GetMapping("/categoria/{idCategoria}")
    public List<ProductDTO> listarProdutosPorCategoria(@PathVariable Category idCategoria) {
        return servicoDeProduto.buscarPorCategoria(idCategoria);
    }

    @GetMapping("/identificador/{identificadorProduto}")
    public ProductDTO buscarProdutoPorIdentificador(@PathVariable String identificadorProduto) {
        return servicoDeProduto.buscarPorIdentificador(identificadorProduto);
    }
}