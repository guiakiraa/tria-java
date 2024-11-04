package tria.servicos;

import lombok.var;
import tria.entidades.Carro;
import tria.infraestrutura.Log4jLogger;
import tria.repositorios.CarroRepositorio;

import java.util.List;
import java.util.Optional;

public class CarroServico {
    private CarroRepositorio carroRepositorio;
    private Log4jLogger logger;

    private static final String PLACA_REGEX = "^[A-Z]{3}-?\\d{4}$|^[A-Z]{3}\\d[A-Z]\\d{2}$";

    public CarroServico() {
        carroRepositorio = new CarroRepositorio();
        logger = new Log4jLogger();
    }

    public void Cadastrar(Carro carro) {
        var carroValido = validarCarro(carro);
        if (carroValido) {
            carroRepositorio.cadastrar(carro);
            logger.logCarroCadastrado(carro);
        }
    }

    public void Deletar(int id) {
        var carro = carroRepositorio.buscarPorId(id);
        if (carro.isPresent()) {
            carroRepositorio.remover(id);
            logger.logCarroDeletado(carro.get());
        }
    }

    public void Atualizar(Carro carro, int id) {
        var carroValido = validarCarro(carro);
        if (carroValido) {
            carroRepositorio.atualizar(carro, id);
            logger.logCarroAtualizado(carro);
        }
    }

    public Optional<Carro> BuscarPorId(int id) {
        var carro = carroRepositorio.buscarPorId(id);
        carro.ifPresent(logger::logCarroBuscadoPorId);
        return carro;
    }

    public List<Carro> Listar() {
        var carros = carroRepositorio.listar();
        logger.logCarroListados(carros);
        return carros;
    }

    private static boolean validarCarro(Carro carro) {
        return validarPlaca(carro.getPlaca()) && carro.getAno() > 1885 && carro.getQuilometragem() >= 0;
    }

    private static boolean validarPlaca(String placa) {
        return placa != null && placa.toUpperCase().matches(PLACA_REGEX);
    }
}
