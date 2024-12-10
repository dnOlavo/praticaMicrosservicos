package iftm.edu.br.dnolavo.shopping_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import iftm.edu.br.dnolavo.shopping_api.models.dto.ShoppingDTO;
import iftm.edu.br.dnolavo.shopping_api.services.ShoppingService;

/**
 * Controlador para gerenciar endpoints de compras (shopping).
 */
@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class ShoppingController {
    
    private final ShoppingService shoppingService;

    /**
     * Retorna todas as compras cadastradas.
     */
    @GetMapping
    public List<ShoppingDTO> obterTodasAsCompras() {
        return shoppingService.obterTodas();
    }

    /**
     * Retorna uma compra específica pelo seu ID.
     */
    @GetMapping("/{id}")
    public ShoppingDTO obterCompraPorId(@PathVariable String id) {
        return shoppingService.buscarPorId(id);
    }

    /**
     * Cadastra uma nova compra.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingDTO criarCompra(@RequestBody @Valid ShoppingDTO compraDTO) {
        return shoppingService.salvar(compraDTO);
    }

    /**
     * Retorna compras associadas a um usuário específico pelo identificador do usuário.
     */
    @GetMapping("/comprasPorUsuario")
    public List<ShoppingDTO> buscarComprasPorUsuario(@RequestParam String identificadorUsuario) {
        return shoppingService.buscarPorUsuario(identificadorUsuario);
    }

    /**
     * Retorna compras realizadas em uma data específica.
     * O formato da data deve ser "yyyy-MM-ddTHH:mm:ss".
     */
    @GetMapping("/comprasPorData")
    public List<ShoppingDTO> buscarComprasPorData(@RequestParam String data) {
        LocalDateTime dataHora = LocalDateTime.parse(data, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return shoppingService.buscarPorData(dataHora);
    }

    /**
     * Retorna compras contendo um produto específico pelo identificador do produto.
     */
    @GetMapping("/produto/{identificadorProduto}")
    public List<ShoppingDTO> buscarComprasPorProduto(@PathVariable String identificadorProduto) {
        return shoppingService.buscarPorProduto(identificadorProduto);
    }

    /**
     * Realiza uma busca filtrada por intervalos de data e valor mínimo total.
     */
    @GetMapping("/filtro")
    public List<ShoppingDTO> buscarPorFiltros(
        @RequestParam(required = false) LocalDateTime dataInicio,
        @RequestParam(required = false) LocalDateTime dataFim,
        @RequestParam(required = false) String valorMinimo) {
            return shoppingService.buscarPorFiltros(dataInicio, dataFim, valorMinimo);
        }

    /**
     * Gera um relatório de compras entre um intervalo de datas.
     */
    @GetMapping("/relatorio")
    public List<ShoppingDTO> gerarRelatorioPorData(
        @RequestParam LocalDateTime dataInicio,
        @RequestParam LocalDateTime dataFim) {
            return shoppingService.gerarRelatorioPorData(dataInicio, dataFim);
        }
}