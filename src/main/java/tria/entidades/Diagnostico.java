package tria.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diagnostico extends _EntidadeBase{
    private String problema;
    private double orcamento;
    private LocalDate dataEmissao;
    private Carro carro;
}
