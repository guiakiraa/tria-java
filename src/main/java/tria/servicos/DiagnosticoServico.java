package tria.servicos;

import lombok.var;
import tria.entidades.Diagnostico;
import tria.infraestrutura.Log4jLogger;
import tria.repositorios.DiagnosticoRepositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DiagnosticoServico {
    private DiagnosticoRepositorio diagnosticoRepositorio;
    private Log4jLogger logger;

    public DiagnosticoServico() {
        diagnosticoRepositorio = new DiagnosticoRepositorio();
        logger = new Log4jLogger();
    }

    public void Cadastrar(Diagnostico diagnostico) {
        var diagnosticoValido = validarDiagnostico(diagnostico);
        if (diagnosticoValido) {
            diagnosticoRepositorio.cadastrar(diagnostico);
            logger.logDiagnosticoCadastrado(diagnostico);
        }
    }

    public void Deletar(int id) {
        var diagnostico = diagnosticoRepositorio.buscarPorId(id);
        if (diagnostico.isPresent()) {
            diagnosticoRepositorio.remover(id);
            logger.logDiagnosticoDeletado(diagnostico.get());
        }
    }

    public void Atualizar(Diagnostico diagnostico, int id) {
        var diagnosticoValido = validarDiagnostico(diagnostico);
        if (diagnosticoValido) {
            diagnosticoRepositorio.atualizar(diagnostico, id);
            logger.logDiagnosticoAtualizado(diagnostico);
        }
    }

    public Optional<Diagnostico> BuscarPorId(int id) {
        var diagnostico = diagnosticoRepositorio.buscarPorId(id);
        diagnostico.ifPresent(logger::logDiagnosticoBuscadoPorId);
        return diagnostico;
    }

    public List<Diagnostico> Listar() {
        var diagnosticos = diagnosticoRepositorio.listar();
        logger.logDiagnosticoListados(diagnosticos);
        return diagnosticos;
    }

    private static boolean validarDiagnostico(Diagnostico diagnostico) {
        return validarOrcamento(diagnostico.getOrcamento()) && validarDataEmissao(diagnostico.getDataEmissao());
    }

    private static boolean validarOrcamento(double orcamento) {
        return orcamento >= 0;
    }

    private static boolean validarDataEmissao(LocalDate dataEmissao) {
        return dataEmissao != null && !dataEmissao.isAfter(LocalDate.now());
    }
}
