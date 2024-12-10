package iftm.edu.br.dnolavo.shopping_api.services;

import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import iftm.edu.br.dnolavo.shopping_api.models.dto.ShoppingDTO;
import iftm.edu.br.dnolavo.shopping_api.models.Shopping;
import iftm.edu.br.dnolavo.shopping_api.repositories.ShoppingRepository;
import org.springframework.stereotype.Service;

/**
 * Serviço para realizar a lógica de negócios das operações de compras.
 */
@Service
@RequiredArgsConstructor
public class ShoppingService {

    private final ShoppingRepository shoppingRepository;

    /**
     * Retorna todas as compras cadastradas no sistema.
     */
    public List<ShoppingDTO> obterTodas() {
        List<Shopping> compras = shoppingRepository.findAll();
        return compras.stream()
            .map(ShoppingDTO::converter)
            .collect(Collectors.toList());
    }

    /**
     * Busca uma compra pelo ID.
     */
    public ShoppingDTO buscarPorId(String id) {
        Shopping compra = shoppingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Compra não encontrada."));
        return ShoppingDTO.converter(compra);
    }

    /**
     * Salva uma nova compra no banco de dados.
     */
    public ShoppingDTO salvar(ShoppingDTO compraDTO) {
        Shopping compra = shoppingRepository.save(Shopping.converter(compraDTO));
        return ShoppingDTO.converter(compra);
    }

    /**
     * Busca compras pelo identificador do usuário.
     */
    public List<ShoppingDTO> buscarPorUsuario(String identificadorUsuario) {
        List<Shopping> compras = shoppingRepository.findByIdentificadorUsuario(identificadorUsuario);
        return compras.stream()
            .map(ShoppingDTO::converter)
            .collect(Collectors.toList());
    }

    /**
     * Busca compras realizadas em uma data específica.
     */
    public List<ShoppingDTO> buscarPorData(LocalDateTime data) {
        List<Shopping> compras = shoppingRepository.findByData(data);
        if (compras.isEmpty()) {
            throw new RuntimeException("Nenhuma compra encontrada para esta data.");
        }
        return compras.stream()
                      .map(ShoppingDTO::converter)
                      .collect(Collectors.toList());
    }

    /**
     * Busca compras que contêm um produto específico pelo identificador do produto.
     */
    public List<ShoppingDTO> buscarPorProduto(String identificadorProduto) {
        List<Shopping> compras = shoppingRepository.findByItensIdentificadorProduto(identificadorProduto);
        if (compras.isEmpty()) {
            throw new RuntimeException("Nenhuma compra encontrada para este produto.");
        }
        return compras.stream()
                .map(ShoppingDTO::converter)
                .collect(Collectors.toList());
    }

    /**
     * Realiza uma busca por filtros de data e valor mínimo total.
     */
    public List<ShoppingDTO> buscarPorFiltros(LocalDateTime dataInicio, LocalDateTime dataFim, String valorMinimo) {
        List<Shopping> compras;

        if (dataInicio != null && dataFim != null && valorMinimo != null) {
            compras = shoppingRepository.findByDataBetweenAndTotalGreaterThan(dataInicio, dataFim, valorMinimo);
        } else if (dataInicio != null && dataFim != null) {
            compras = shoppingRepository.findByDataBetween(dataInicio, dataFim);
        } else if (valorMinimo != null) {
            compras = shoppingRepository.findByTotalGreaterThan(valorMinimo);
        } else {
            throw new IllegalArgumentException("Pelo menos um filtro deve ser fornecido.");
        }
        return compras.stream()
                .map(ShoppingDTO::converter)
                .collect(Collectors.toList());
    }

    /**
     * Gera um relatório de compras realizadas entre um intervalo de datas.
     */
    public List<ShoppingDTO> gerarRelatorioPorData(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Shopping> compras = shoppingRepository.findByDataBetween(dataInicio, dataFim);
        if (compras.isEmpty()) {
            throw new RuntimeException("Nenhuma compra encontrada para este intervalo de datas.");
        }
        return compras.stream()
            .map(ShoppingDTO::converter)
            .collect(Collectors.toList());
    }
}