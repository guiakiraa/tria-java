package tria.servicos;

import lombok.var;
import tria.entidades.Revisao;
import tria.infraestrutura.Log4jLogger;
import tria.repositorios.RevisaoRepositorio;

import java.util.List;
import java.util.Optional;

public class RevisaoServico {
    private RevisaoRepositorio revisaoRepositorio;
    private Log4jLogger logger;

    public RevisaoServico() {
        this.revisaoRepositorio = new RevisaoRepositorio();
        this.logger = new Log4jLogger();
    }

    public void Cadastrar(Revisao revisao) {
        var revisaoValida = validarRevisao(revisao);
        if (revisaoValida) {
            this.revisaoRepositorio.cadastrar(revisao);
            logger.logRevisaoCadastrada(revisao);
        }
    }

    public void Atualizar(Revisao revisao, int id) {
        var revisaoValida = validarRevisao(revisao);
        if (revisaoValida) {
            this.revisaoRepositorio.atualizar(revisao, id);
            logger.logRevisaoAtualizada(revisao);
        }
    }

    public void Deletar(int id) {
        var revisao = this.revisaoRepositorio.buscarPorId(id);
        if (revisao.isPresent()) {
            this.revisaoRepositorio.remover(id);
            logger.logRevisaoDeletado(revisao.get());
        }
    }

    public Optional<Revisao> BuscarPorId(int id) {
        var revisao = this.revisaoRepositorio.buscarPorId(id);
        revisao.ifPresent(value -> logger.logRevisaoBuscadaPorId(value));
        return revisao;
    }

    public List<Revisao> Listar() {
        var revisoes = this.revisaoRepositorio.listar();
        logger.logRevisaoListadas(revisoes);
        return revisoes;
    }

    private static boolean validarRevisao(Revisao revisao) {
        return revisao.getProblema() != null && !revisao.getProblema().trim().isEmpty();
    }
}
