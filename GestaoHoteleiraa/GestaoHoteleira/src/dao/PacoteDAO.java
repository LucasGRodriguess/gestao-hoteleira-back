package dao;
import dao.connection.ConexaoMySQL;
import model.Acomodacao;
import model.Exemplo;
import model.Pacote;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PacoteDAO {

    public Boolean inserir(Pacote pacote) {
        try {
            String sql = "INSERT INTO pacote (nome, id_acomodacao, qtd_diarias, valor_total) VALUES (?, ?, ?, ?)";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, pacote.getNome());
            preparacao.setLong(2, pacote.getAcomodacao().getId());
            preparacao.setInt(3, pacote.getQtdDiarias());
            preparacao.setDouble(4, pacote.getValorTotal());
            int contLinhasAfetadas = preparacao.executeUpdate();
            return contLinhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Pacote> selecionar() {
        try {
            String sql = "SELECT * FROM pacote ORDER BY id";
            Statement stmt = ConexaoMySQL.get().createStatement();
            ResultSet resultado = stmt.executeQuery(sql);

            ArrayList<Pacote> lista = new ArrayList<>();

            while(resultado.next()) {
                Acomodacao acomodacao = selecionarAcomodacaoPorId(resultado.getLong("id_acomodacao"));
                Pacote pacote  = new Pacote(
                        resultado.getLong("id"),
                        resultado.getString("nome"),
                        acomodacao,
                        resultado.getInt("qtd_diarias"),
                        resultado.getDouble("valor_total")
                );
                lista.add(pacote);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean atualizar(Pacote pacote) {
        try {

            String sql = "UPDATE pacote SET nome = ?, id_acomodacao = ?, qtd_diarias = ?, valor_total = ? WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, pacote.getNome());
            preparacao.setLong(2, pacote.getAcomodacao().getId());
            preparacao.setInt(3, pacote.getQtdDiarias());
            preparacao.setDouble(4, pacote.getValorTotal());
            preparacao.setLong(5, pacote.getId());


            int contLinhasAfetadas = preparacao.executeUpdate();
            return contLinhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean deletar(Long id) {
        try {
            String sql = "DELETE FROM pacote WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            int contLinhasAfetadas = preparacao.executeUpdate();
            return contLinhasAfetadas > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Acomodacao selecionarAcomodacaoPorId(Long id) {

        try {
            String sql = "SELECT * FROM acomodacao WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            ResultSet resultado = preparacao.executeQuery();

            if(resultado.next()) {
                Acomodacao acomodacao = new Acomodacao(
                        resultado.getLong("id"),
                        resultado.getString("nome"),
                        resultado.getDouble("valor_diaria"),
                        resultado.getInt("limite_hospedes"),
                        resultado.getString("descricao")
                );
                return acomodacao;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Pacote selecionarPorId(Long id) {

        try {
            String sql = "SELECT * FROM pacote WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            ResultSet resultado = preparacao.executeQuery();

            if(resultado.next()) {
                Acomodacao acomodacao = selecionarAcomodacaoPorId(resultado.getLong("id_acomodacao"));
                Pacote pacote = new Pacote(
                        resultado.getString("nome"),
                        acomodacao,
                        resultado.getInt("qtd_diarias"),
                        resultado.getDouble("valor_total")
                );
                return pacote;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
