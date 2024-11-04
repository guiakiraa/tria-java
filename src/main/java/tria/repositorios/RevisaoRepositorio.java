package tria.repositorios;

import lombok.var;
import tria.entidades.Revisao;
import tria.infraestrutura.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RevisaoRepositorio implements _RepositorioCrud<Revisao>{
    @Override
    public void cadastrar(Revisao entidade) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "INSERT INTO T_REVISAO(PB_IDENTIFICADO) VALUES (?)";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, entidade.getProblema());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Revisao entidade, int id) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "UPDATE T_REVISAO SET PB_IDENTIFICADO = ? WHERE ID_REVISAO = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, entidade.getProblema());
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(int id) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "DELETE FROM T_REVISAO WHERE ID_REVISAO = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Revisao> buscarPorId(int id) {
        Optional<Revisao> revisao = Optional.empty();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_REVISAO WHERE ID_REVISAO = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                var _id = rs.getInt("ID_REVISAO");
                var problema = rs.getString("PB_IDENTIFICADO");
                revisao = Optional.of(new Revisao(_id, problema));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return revisao;
    }

    @Override
    public List<Revisao> listar() {
        var revisoes = new ArrayList<Revisao>();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_REVISAO";
            var stmt = conn.prepareStatement(query);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var id = rs.getInt("ID_REVISAO");
                var problema = rs.getString("PB_IDENTIFICADO");
                revisoes.add(new Revisao(id, problema));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return revisoes;
    }
}
