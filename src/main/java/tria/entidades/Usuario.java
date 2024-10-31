package tria.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends _EntidadeBase{
    private String nome;
    private String cpf;
    private String sexo;
    private LocalDate dataNascimento;
    private Login login;
    private Contato contato;
    private List<Endereco> enderecos = new ArrayList<>();

    public Usuario(int id, String nome, String cpf, String sexo, LocalDate dataNascimento) {
        super(id);
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }
}
