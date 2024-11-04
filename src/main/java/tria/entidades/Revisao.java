package tria.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Revisao extends _EntidadeBase{
    private String problema;
    private Carro carro;

    public Revisao(int id, String problema) {
        super(id);
        this.problema = problema;
    }
}
