package iftm.edu.br.dnolavo.user_api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import iftm.edu.br.dnolavo.user_api.models.User;

/**
 * Classe DTO para transferência de dados de usuários.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;
    private String endereco;
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;

    // Converte um objeto User para UserDTO.
    public static UserDTO converter(User usuario) {
        UserDTO usuarioDTO = new UserDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEndereco(usuario.getEndereco());
        usuarioDTO.setCpf(usuario.getCpf());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefone(usuario.getTelefone());
        usuarioDTO.setDataCadastro(usuario.getDataCadastro());
        return usuarioDTO;
    }
}