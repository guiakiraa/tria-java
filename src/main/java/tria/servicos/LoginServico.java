package tria.servicos;


import lombok.var;
import tria.entidades.Login;
import tria.infraestrutura.Log4jLogger;
import tria.repositorios.LoginRepositorio;

import java.util.List;
import java.util.Optional;

public class LoginServico {
    private LoginRepositorio loginRepositorio;
    private Log4jLogger logger;

    public LoginServico() {
        loginRepositorio = new LoginRepositorio();
        logger = new Log4jLogger();
    }

    public void Cadastrar(Login login) {
        var loginValido = validarLogin(login);
        if (loginValido) {
            loginRepositorio.cadastrar(login);
            logger.logLoginCadastrado(login);
        }
    }

    public void Deletar(int id) {
        var login = loginRepositorio.buscarPorId(id);
        if (login.isPresent()) {
            loginRepositorio.remover(id);
            logger.logLoginDeletado(login.get());
        }
    }

    public void Atualizar(Login login, int id) {
        var loginValido = validarLogin(login);
        if (loginValido) {
            loginRepositorio.atualizar(login, id);
            logger.logLoginAtualizado(login);
        }
    }

    public Optional<Login> BuscarPorId(int id) {
        var login = loginRepositorio.buscarPorId(id);
        login.ifPresent(logger::logLoginBuscadoPorId);
        return login;
    }

    public List<Login> Listar() {
        var logins = loginRepositorio.listar();
        logger.logLoginsListados(logins);
        return logins;
    }

    private static boolean validarLogin(Login login) {
        return !login.getSenha().trim().isEmpty() && login.getSenha().trim().length() >= 6;
    }
}
