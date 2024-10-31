package tria.entidades;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carro extends _EntidadeBase{
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private double quilometragem;
    private Usuario usuario;
}
