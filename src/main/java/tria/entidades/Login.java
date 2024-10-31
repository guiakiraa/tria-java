package tria.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login extends _EntidadeBase{
    private String senha;

    public Login(int id, String senha) {
        super(id);
        this.senha = senha;
    }
}
