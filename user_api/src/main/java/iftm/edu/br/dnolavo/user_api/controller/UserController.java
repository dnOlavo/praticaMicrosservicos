package iftm.edu.br.dnolavo.user_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import iftm.edu.br.dnolavo.user_api.models.dto.UserDTO;
import iftm.edu.br.dnolavo.user_api.services.UserService;
import java.util.List;
import jakarta.validation.Valid;

/**
 * Controlador responsável por gerenciar as operações da API de usuários.
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService servicoDeUsuario;

    // Recupera a lista de todos os usuários.
    @GetMapping
    public List<UserDTO> obterUsuarios() {
        return servicoDeUsuario.listarTodos();
    }

    // Recupera um usuário específico pelo ID.
    @GetMapping("/{id}")
    public UserDTO buscarPorId(@PathVariable String id) {
        return servicoDeUsuario.buscarPorId(id);
    }

    // Cria um novo usuário.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO criarNovoUsuario(@RequestBody @Valid UserDTO usuarioDTO) {
        return servicoDeUsuario.salvar(usuarioDTO);
    }

    // Recupera um usuário pelo CPF.
    @GetMapping("/{cpf}/cpf")
    public UserDTO buscarPorCpf(@PathVariable String cpf) {
        return servicoDeUsuario.buscarPorCpf(cpf);
    }

    // Exclui um usuário pelo ID.
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirUsuario(@PathVariable String id) {
        servicoDeUsuario.excluir(id);
    }

    // Pesquisa usuários pelo nome (busca parcial).
    @GetMapping("/search")
    public List<UserDTO> pesquisarPorNome(@RequestParam(name = "nome", required = true) String nome) {
        return servicoDeUsuario.pesquisarPorNome(nome);
    }

    // Edita informações parciais de um usuário com base no ID.
    @PatchMapping("/{id}")
    public UserDTO editarUsuario(@PathVariable String id, @RequestBody UserDTO usuarioDTO) {
        return servicoDeUsuario.editarUsuario(id, usuarioDTO);
    }

    // Recupera uma lista paginada de usuários.
    @GetMapping("/pageable")
    public Page<UserDTO> obterUsuariosPaginados(Pageable paginacao) {
        return servicoDeUsuario.listarPaginados(paginacao);
    }
}