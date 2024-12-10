package iftm.edu.br.dnolavo.user_api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import iftm.edu.br.dnolavo.user_api.models.User;
import iftm.edu.br.dnolavo.user_api.models.dto.UserDTO;
import iftm.edu.br.dnolavo.user_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * Serviço que contém a lógica de negócio dos usuários.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    // Repositório para interagir com o banco de dados.
    private final UserRepository repositorioDeUsuario;

    // Lista todos os usuários e os converte para DTO.
    public List<UserDTO> listarTodos() {
        return repositorioDeUsuario.findAll().stream()
            .map(UserDTO::converter)
            .collect(Collectors.toList());
    }

    // Busca um usuário pelo ID.
    public UserDTO buscarPorId(String idUsuario) {
        return repositorioDeUsuario.findById(idUsuario)
            .map(UserDTO::converter)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Salva um novo usuário no banco de dados.
    public UserDTO salvar(UserDTO usuarioDTO) {
        usuarioDTO.setDataCadastro(LocalDateTime.now());
        User usuario = repositorioDeUsuario.save(User.converter(usuarioDTO));
        return UserDTO.converter(usuario);
    }

    // Exclui um usuário pelo ID.
    public void excluir(String idUsuario) {
        User usuario = repositorioDeUsuario.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        repositorioDeUsuario.delete(usuario);
    }

    // Busca um usuário pelo CPF.
    public UserDTO buscarPorCpf(String cpf) {
        User usuario = repositorioDeUsuario.findByCpf(cpf);
        return usuario != null ? UserDTO.converter(usuario) : null;
    }

    // Pesquisa usuários pelo nome (busca parcial).
    public List<UserDTO> pesquisarPorNome(String nome) {
        return repositorioDeUsuario.queryByNomeLike(nome).stream()
            .map(UserDTO::converter)
            .collect(Collectors.toList());
    }

    // Edita informações de um usuário existente.
    public UserDTO editarUsuario(String idUsuario, UserDTO usuarioDTO) {
        User usuario = repositorioDeUsuario.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Atualiza os campos, caso tenham sido fornecidos no DTO.
        if (usuarioDTO.getEmail() != null) {
            usuario.setEmail(usuarioDTO.getEmail());
        }
        if (usuarioDTO.getTelefone() != null) {
            usuario.setTelefone(usuarioDTO.getTelefone());
        }
        if (usuarioDTO.getEndereco() != null) {
            usuario.setEndereco(usuarioDTO.getEndereco());
        }
        return UserDTO.converter(repositorioDeUsuario.save(usuario));
    }

    // Lista usuários com paginação.
    public Page<UserDTO> listarPaginados(Pageable paginacao) {
        return repositorioDeUsuario.findAll(paginacao).map(UserDTO::converter);
    }
}
