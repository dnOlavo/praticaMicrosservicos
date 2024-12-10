package iftm.edu.br.dnolavo.product_api.services;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import iftm.edu.br.dnolavo.product_api.models.Category;
import iftm.edu.br.dnolavo.product_api.models.dto.CategoryDTO;
import iftm.edu.br.dnolavo.product_api.repositories.CategoryRepository;

/**
 * Serviço que implementa a lógica de negócios para categorias.
 */
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repositorioCategoria;

    public List<CategoryDTO> obterTodas() {
        return repositorioCategoria.findAll().stream()
            .map(CategoryDTO::convert)
            .collect(Collectors.toList());
    }

    public CategoryDTO salvar(CategoryDTO categoriaDTO) {
        Category categoria = repositorioCategoria.save(Category.convert(categoriaDTO));
        return CategoryDTO.convert(categoria);
    }

    public CategoryDTO atualizar(String id, CategoryDTO categoriaDTO) {
        Category categoria = repositorioCategoria.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        if (categoriaDTO.getNome() != null && !categoria.getNome().equals(categoriaDTO.getNome())) {
            categoria.setNome(categoriaDTO.getNome());
        }
        return CategoryDTO.convert(repositorioCategoria.save(categoria));
    }

    public void excluir(String id) {
        Category categoria = repositorioCategoria.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        repositorioCategoria.delete(categoria);
    }

    public Page<CategoryDTO> obterTodasPaginadas(Pageable paginacao) {
        return repositorioCategoria.findAll(paginacao).map(CategoryDTO::convert);
    }
}