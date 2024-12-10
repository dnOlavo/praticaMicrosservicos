package iftm.edu.br.dnolavo.user_api.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import iftm.edu.br.dnolavo.user_api.models.dto.UserDTO;

/**
 * Classe que representa o modelo de dados do usuário.
 */
@Document(collection = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {

    @Id
    private String id;

    @Field("nome")
    private String nome;

    @Field("cpf")
    private String cpf;

    @Field("endereco")
    private String endereco;

    @Field("email")
    private String email;

    @Field("telefone")
    private String telefone;

    @Field("dataCadastro")
    private LocalDateTime dataCadastro;

    // Converte um UserDTO para User.
    public static User converter(UserDTO usuarioDTO) {
        User usuario = new User();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEndereco(usuarioDTO.getEndereco());
        usuario.setCpf(usuarioDTO.getCpf());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setDataCadastro(usuarioDTO.getDataCadastro());
        return usuario;
    }
}