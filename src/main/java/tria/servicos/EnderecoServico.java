package tria.servicos;

import lombok.var;
import tria.entidades.Endereco;
import tria.infraestrutura.Log4jLogger;
import tria.repositorios.EnderecoRepositorio;

import java.util.List;
import java.util.Optional;

public class EnderecoServico {
    private EnderecoRepositorio enderecoRepositorio;
    private Log4jLogger logger;

    private static final String CEP_REGEX = "^\\d{5}-\\d{3}$|^\\d{8}$";

    public EnderecoServico() {
        enderecoRepositorio = new EnderecoRepositorio();
        logger = new Log4jLogger();
    }

    public void Cadastrar(Endereco endereco) {
        var enderecoValido = validarEndereco(endereco);
        if (enderecoValido) {
            enderecoRepositorio.cadastrar(endereco);
            logger.logEnderecoCadastrado(endereco);
        }
    }

    public void Deletar(int id) {
        var endereco = enderecoRepositorio.buscarPorId(id);
        if (endereco.isPresent()) {
            enderecoRepositorio.remover(id);
            logger.logEnderecoDeletado(endereco.get());
        }
    }

    public void Atualizar(Endereco endereco, int id) {
        var enderecoValido = validarEndereco(endereco);
        if (enderecoValido) {
            enderecoRepositorio.atualizar(endereco, id);
            logger.logEnderecoAtualizado(endereco);
        }
    }

    public Optional<Endereco> BuscarPorId(int id) {
        var endereco = enderecoRepositorio.buscarPorId(id);
        endereco.ifPresent(logger::logEnderecoBuscadoPorId);
        return endereco;
    }

    public List<Endereco> Listar() {
        var enderecos = enderecoRepositorio.listar();
        logger.logEnderecosListados(enderecos);
        return enderecos;
    }

    private static boolean validarEndereco(Endereco endereco) {
        return validarCep(endereco.getCep()) && !endereco.getLogradouro().trim().isEmpty()
                && !endereco.getNumero().trim().isEmpty() && !endereco.getBairro().trim().isEmpty()
                && !endereco.getCidade().trim().isEmpty() && !endereco.getEstado().trim().isEmpty();
    }

    private static boolean validarCep(String cep) {
        return cep != null && cep.matches(CEP_REGEX);
    }
}
