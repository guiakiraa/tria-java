package tria.repositorios;

import lombok.var;
import tria.entidades.Usuario;
import tria.infraestrutura.DatabaseConfig;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositorio implements _RepositorioCrud<Usuario>{
    @Override
    public void cadastrar(Usuario entidade) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "INSERT INTO T_USUARIO (CPF, NM_USUARIO, SEXO, DT_NASCIMENTO) VALUES (?, ?, ?, ?)";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, entidade.getCpf());
            stmt.setString(2, entidade.getNome());
            stmt.setString(3, entidade.getSexo());
            stmt.setDate(4,  Date.valueOf(entidade.getDataNascimento()));
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Usuario entidade, int id) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "UPDATE T_USUARIO SET CPF = ?, NM_USUARIO = ?, SEXO = ?, DT_NASCIMENTO = ? WHERE ID = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, entidade.getCpf());
            stmt.setString(2, entidade.getNome());
            stmt.setString(3, entidade.getSexo());
            stmt.setDate(4, Date.valueOf(entidade.getDataNascimento()));
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
            var query = "DELETE FROM T_USUARIO WHERE ID_USUARIO = ?";
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
    public Optional<Usuario> buscarPorId(int id) {
        Optional<Usuario> usuario = Optional.empty();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_USUARIO WHERE ID_USUARIO = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);;
            var rs = stmt.executeQuery();
            if (rs.next()) {
                var _id = rs.getInt("ID_USUARIO");
                var cpf = rs.getString("CPF");
                var nome = rs.getString("NM_USUARIO");
                var sexo = rs.getString("SEXO");
                var dtNascimentoSql = rs.getDate("DT_NASCIMENTO");
                var dt_nascimento = dtNascimentoSql.toLocalDate();
                usuario = Optional.of(new Usuario(_id, cpf, nome, sexo, dt_nascimento));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<Usuario> listar() {
        var usuarios = new ArrayList<Usuario>();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_USUARIO";
            var stmt = conn.prepareStatement(query);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var _id = rs.getInt("ID_USUARIO");
                var cpf = rs.getString("CPF");
                var nome = rs.getString("NM_USUARIO");
                var sexo = rs.getString("SEXO");
                var dtNascimentoSql = rs.getDate("DT_NASCIMENTO");
                var dtNascimento = dtNascimentoSql.toLocalDate();
                usuarios.add(new Usuario(_id, cpf, nome, sexo,dtNascimento));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
