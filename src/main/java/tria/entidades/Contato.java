package tria.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contato extends _EntidadeBase{
    private String email;
    private String telefone;

    public Contato(int id, String email, String telefone) {
        super(id);
        this.email = email;
        this.telefone = telefone;
    }
}
