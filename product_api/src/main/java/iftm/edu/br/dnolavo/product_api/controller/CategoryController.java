package iftm.edu.br.dnolavo.product_api.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import iftm.edu.br.dnolavo.product_api.models.dto.CategoryDTO;
import iftm.edu.br.dnolavo.product_api.services.CategoryService;

/**
 * Controlador responsável pelas operações relacionadas a categorias.
 */
@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService servicoDeCategoria;

    @GetMapping
    public List<CategoryDTO> listarCategorias() {
        return servicoDeCategoria.obterTodas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO criarCategoria(@RequestBody @Valid CategoryDTO categoriaDTO) {
        return servicoDeCategoria.salvar(categoriaDTO);
    }

    @PutMapping("/{id}")
    public CategoryDTO editarCategoria(@PathVariable String id, @RequestBody CategoryDTO categoriaDTO) {
        return servicoDeCategoria.atualizar(id, categoriaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirCategoria(@PathVariable String id) {
        servicoDeCategoria.excluir(id);
    }

    @GetMapping("/paginadas")
    public Page<CategoryDTO> listarCategoriasPaginadas(Pageable paginacao) {
        return servicoDeCategoria.obterTodasPaginadas(paginacao);
    }
}