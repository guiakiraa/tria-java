package tria.infraestrutura;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tria.entidades.*;

import java.util.List;

public class Log4jLogger {
    private final Logger loginLogger;
    private final Logger contatoLogger;
    private final Logger usuarioLogger;
    private final Logger enderecoLogger;
    private final Logger carroLogger;
    private final Logger diagnosticoLogger;

    public Log4jLogger() {
        this.carroLogger = LogManager.getLogger(Carro.class);
        this.loginLogger = LogManager.getLogger(Login.class);
        this.contatoLogger = LogManager.getLogger(Contato.class);
        this.usuarioLogger = LogManager.getLogger(Usuario.class);
        this.enderecoLogger = LogManager.getLogger(Endereco.class);
        this.diagnosticoLogger = LogManager.getLogger(Diagnostico.class);
    };

    public void logLoginCadastrado(Login login) {
        loginLogger.info("Cadastrado: " + login);
    }
    public void logContatoCadastrado(Contato contato) {
        contatoLogger.info("Cadastrado: " + contato);
    }
    public void logUsuarioCadastrado(Usuario usuario) {
        usuarioLogger.info("Cadastrado: " + usuario);
    }
    public void logEnderecoCadastrado(Endereco endereco) {
        enderecoLogger.info("Cadastrado: " + endereco);
    }
    public void logDiagnosticoCadastrado(Diagnostico diagnostico) {
        diagnosticoLogger.info("Cadastrado: " + diagnostico);
    }
    public void logCarroCadastrado(Carro carro) {
        carroLogger.info("Cadastrado: " + carro);
    }
    public void logLoginDeletado(Login login) {
        loginLogger.info("Deletado: " + login);
    }
    public void logContatoDeletado(Contato contato) {
        contatoLogger.info("Deletado: " + contato);
    }
    public void logUsuarioDeletado(Usuario usuario) {
        usuarioLogger.info("Deletado: " + usuario);
    }
    public void logEnderecoDeletado(Endereco endereco) {
        enderecoLogger.info("Deletado: " + endereco);
    }
    public void logDiagnosticoDeletado(Diagnostico diagnostico) {
        diagnosticoLogger.info("Deletado: " + diagnostico);
    }
    public void logCarroDeletado(Carro carro) {
        carroLogger.info("Deletado: " + carro);
    }
    public void logLoginAtualizado(Login login) {
        loginLogger.info("Atualizado: " + login);
    }
    public void logContatoAtualizado(Contato contato) {
        contatoLogger.info("Atualizado: " + contato);
    }
    public void logUsuarioAtualizado(Usuario usuario) {
        usuarioLogger.info("Atualizado: " + usuario);
    }
    public void logEnderecoAtualizado(Endereco endereco) {
        enderecoLogger.info("Atualizado: " + endereco);
    }
    public void logDiagnosticoAtualizado(Diagnostico diagnostico) {
        diagnosticoLogger.info("Atualizado: " + diagnostico);
    }
    public void logCarroAtualizado(Carro carro) {
        carroLogger.info("Atualizado: " + carro);
    }
    public void logLoginsListados(List<Login> listaLogins) {
        loginLogger.info("Listados: " + listaLogins);
    }
    public void logContatosListados(List<Contato> listaContatos) {
        contatoLogger.info("Listados: " + listaContatos);
    }
    public void logUsuariosListados(List<Usuario> listaUsuarios) {
        usuarioLogger.info("Listados: " + listaUsuarios);
    }
    public void logEnderecosListados(List<Endereco> listaEnderecos) {
        enderecoLogger.info("Listados: " + listaEnderecos);
    }
    public void logDiagnosticoListados(List<Diagnostico> listaDiagnosticos) {
        diagnosticoLogger.info("Listados: " + listaDiagnosticos);
    }
    public void logCarroListados(List<Carro> listaCarros) {
        carroLogger.info("Listados: " + listaCarros);
    }
    public void logLoginBuscadoPorId(Login login) {
        loginLogger.info("Buscado: " + login);
    }
    public void logUsuarioBuscadoPorId(Usuario usuario) {
        usuarioLogger.info("Buscado: " + usuario);
    }
    public void logEnderecoBuscadoPorId(Endereco endereco) {
        enderecoLogger.info("Buscado: " + endereco);
    }
    public void logDiagnosticoBuscadoPorId(Diagnostico diagnostico) {
        diagnosticoLogger.info("Buscado: " + diagnostico);
    }
    public void logCarroBuscadoPorId(Carro carro) {
        carroLogger.info("Buscado: " + carro);
    }
    public void logContatoBuscadoPorId(Contato contato) {
        contatoLogger.info("Buscado: " + contato);
    }
}
