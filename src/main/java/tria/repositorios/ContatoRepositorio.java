package tria.repositorios;

import lombok.var;
import tria.entidades.Contato;
import tria.infraestrutura.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContatoRepositorio implements _RepositorioCrud<Contato> {
    @Override
    public void cadastrar(Contato entidade) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "INSERT INTO T_CONTATO (EMAIL, TELEFONE) VALUES (?, ?)";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, entidade.getEmail());
            stmt.setString(2, entidade.getTelefone());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Contato entidade, int id) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "UPDATE T_CONTATO SET EMAIL = ?, TELEFONE = ? WHERE ID_CONTATO = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, entidade.getEmail());
            stmt.setString(2, entidade.getTelefone());
            stmt.setInt(3, id);
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
            var query = "DELETE FROM T_CONTATO WHERE ID_CONTATO = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Contato> buscarPorId(int id) {
        Optional<Contato> contato = Optional.empty();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_CONTATO WHERE ID_CONTATO = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                var _id = rs.getInt("ID_CONTATO");
                var email = rs.getString("EMAIL");
                var telefone = rs.getString("TELEFONE");
                contato = Optional.of(new Contato(_id, email, telefone));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return contato;
    }

    @Override
    public List<Contato> listar() {
        var contatos = new ArrayList<Contato>();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_CONTATO";
            var stmt = conn.prepareStatement(query);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var _id = rs.getInt("ID_CONTATO");
                var email = rs.getString("EMAIL");
                var telefone = rs.getString("TELEFONE");
                contatos.add(new Contato(_id, email, telefone));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return contatos;
    }
}
