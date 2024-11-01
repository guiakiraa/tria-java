package tria.servicos;

import lombok.var;
import tria.entidades.Usuario;
import tria.infraestrutura.Log4jLogger;
import tria.repositorios.UsuarioRepositorio;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

public class UsuarioServico {
    private UsuarioRepositorio usuarioRepositorio;
    private Log4jLogger logger;

    private static final String CPF_REGEX = "^\\d{11}$";

    private static final int IDADE_MINIMA = 18;

    public UsuarioServico() {
        usuarioRepositorio = new UsuarioRepositorio();
        logger = new Log4jLogger();
    }

    public void Cadastrar(Usuario usuario) {
        var usuarioValido = validarUsuario(usuario);
        if (usuarioValido) {
            usuarioRepositorio.cadastrar(usuario);
            logger.logUsuarioCadastrado(usuario);
        }
    }

    public void Deletar(int id) {
        var usuario = usuarioRepositorio.buscarPorId(id);
        if (usuario.isPresent()) {
            usuarioRepositorio.remover(id);
            logger.logUsuarioDeletado(usuario.get());
        }
    }

    public void Atualizar(Usuario usuario, int id) {
        var usuarioValido = validarUsuario(usuario);
        if (usuarioValido) {
            usuarioRepositorio.atualizar(usuario, id);
            logger.logUsuarioAtualizado(usuario);
        }
    }

    public Optional<Usuario> BuscarPorId(int id) {
        var usuario = usuarioRepositorio.buscarPorId(id);
        usuario.ifPresent(logger::logUsuarioBuscadoPorId);
        return usuario;
    }

    public List<Usuario> Listar() {
        var usuarios = usuarioRepositorio.listar();
        logger.logUsuariosListados(usuarios);
        return usuarios;
    }

    private static boolean validarUsuario(Usuario usuario) {
        return validarCpf(usuario.getCpf()) && validarSexo(usuario.getSexo())
                && validarDataNascimento(usuario.getDataNascimento());
    }

    private static boolean validarCpf(String cpf) {
        return cpf != null && cpf.matches(CPF_REGEX);
    }

    private static boolean validarSexo(String sexo) {
        return sexo != null && (sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F"));
    }

    private static boolean validarDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
            return false;
        }
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        return idade >= IDADE_MINIMA;
    }
}
