package tria.servicos;

import lombok.var;
import tria.entidades.Contato;
import tria.infraestrutura.Log4jLogger;
import tria.repositorios.ContatoRepositorio;

import java.util.List;
import java.util.Optional;

public class ContatoServico {
    private ContatoRepositorio contatoRepositorio;
    private Log4jLogger logger;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String TELEFONE_REGEX = "^\\(?\\d{2}\\)?[\\s-]?\\d{4,5}-?\\d{4}$|^\\d{10,11}$";

    public ContatoServico() {
        contatoRepositorio = new ContatoRepositorio();
        logger = new Log4jLogger();
    }

    public void Cadastrar(Contato contato) {
        var contatoValido = validarContato(contato);
        if (contatoValido) {
            contatoRepositorio.cadastrar(contato);
            logger.logContatoCadastrado(contato);
        }
    }

    public void Deletar(int id) {
        var contato = contatoRepositorio.buscarPorId(id);
        if (contato.isPresent()) {
            contatoRepositorio.remover(id);
            logger.logContatoDeletado(contato.get());
        }
    }

    public void Atualizar(Contato contato, int id) {
        var contatoValido = validarContato(contato);
        if (contatoValido) {
            contatoRepositorio.atualizar(contato, id);
            logger.logContatoAtualizado(contato);
        }
    }

    public Optional<Contato> BuscarPorId(int id) {
        var contato = contatoRepositorio.buscarPorId(id);
        contato.ifPresent(logger::logContatoBuscadoPorId);
        return contato;
    }

    public List<Contato> Listar() {
        var contatos = contatoRepositorio.listar();
        logger.logContatosListados(contatos);
        return contatos;
    }

    private static boolean validarContato(Contato contato) {
        return validarEmail(contato.getEmail()) && validarTelefone(contato.getTelefone());
    }

    private static boolean validarEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    private static boolean validarTelefone(String telefone) {
        return telefone != null && telefone.matches(TELEFONE_REGEX);
    }
}
