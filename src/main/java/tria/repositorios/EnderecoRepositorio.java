package tria.repositorios;

import lombok.var;
import tria.entidades.Endereco;
import tria.infraestrutura.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnderecoRepositorio implements _RepositorioCrud<Endereco>{
    @Override
    public void cadastrar(Endereco entidade) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "INSERT INTO T_ENDERECO (CEP, COMPLEMENTO, LOGRADOURO, NUM_LOGRADOURO, ESTADO, CIDADE, BAIRRO) VALUES (?, ?, ?, ?, ?, ?, ?)";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, entidade.getCep());
            stmt.setString(2, entidade.getComplemento());
            stmt.setString(3, entidade.getLogradouro());
            stmt.setString(4, entidade.getNumero());
            stmt.setString(5, entidade.getEstado());
            stmt.setString(6, entidade.getCidade());
            stmt.setString(7, entidade.getBairro());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Endereco entidade, int id) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "UPDATE T_ENDERECO SET CEP = ?, COMPLEMENTO = ?, LOGRADOURO = ?, NUM_LOGRADOURO = ?, ESTADO = ?, CIDADE = ?, BAIRRO = ? WHERE ID_ENDERECO = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, entidade.getCep());
            stmt.setString(2, entidade.getComplemento());
            stmt.setString(3, entidade.getLogradouro());
            stmt.setString(4, entidade.getNumero());
            stmt.setString(5, entidade.getEstado());
            stmt.setString(6, entidade.getCidade());
            stmt.setString(7, entidade.getBairro());
            stmt.setInt(8, id);
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
            var query = "DELETE FROM T_ENDERECO WHERE ID_ENDERECO = ?";
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
    public Optional<Endereco> buscarPorId(int id) {
        Optional<Endereco> endereco = Optional.empty();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_ENDERECO WHERE ID_ENDERECO = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                var _id = rs.getInt("ID_ENDERECO");
                var cep = rs.getString("CEP");
                var complemento = rs.getString("COMPLEMENTO");
                var logradouro = rs.getString("LOGRADOURO");
                var numero = rs.getString("NUM_LOGRADOURO");
                var estado = rs.getString("ESTADO");
                var cidade = rs.getString("CIDADE");
                var bairro = rs.getString("BAIRRO");
                endereco = Optional.of(new Endereco(_id, cep, complemento, logradouro, numero, estado, cidade, bairro));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return endereco;
    }

    @Override
    public List<Endereco> listar() {
        var enderecos = new ArrayList<Endereco>();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_ENDERECO";
            var stmt = conn.prepareStatement(query);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var _id = rs.getInt("ID_ENDERECO");
                var cep = rs.getString("CEP");
                var complemento = rs.getString("COMPLEMENTO");
                var logradouro = rs.getString("LOGRADOURO");
                var numero = rs.getString("NUM_LOGRADOURO");
                var estado = rs.getString("ESTADO");
                var cidade = rs.getString("CIDADE");
                var bairro = rs.getString("BAIRRO");
                enderecos.add(new Endereco(_id, cep, complemento, logradouro, numero, estado, cidade, bairro));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecos;
    }
}
