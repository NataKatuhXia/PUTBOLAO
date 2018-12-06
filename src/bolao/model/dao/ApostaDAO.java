/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.dao;

import bolao.model.bean.Aposta;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class ApostaDAO {

    public void create(Aposta aposta) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO aposta (identificador,placarA,placarB)VALUES(?,?,?)");
            stmt.setString(1, aposta.getIdentificador());
            stmt.setInt(2, aposta.getPlacarA());
            stmt.setDouble(3, aposta.getPlacarB());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Aposta> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<Aposta> apostas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM aposta order by identificador");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Aposta aposta = new Aposta(rs.getString("identificador"), rs.getInt("placarA"), rs.getInt("placarB"));

                apostas.add(aposta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApostaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return apostas;
    }

    public List<Aposta> readForDesc(String identificador) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<Aposta> apostas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM aposta WHERE identificador LIKE ? order by identificador");
            stmt.setString(1, "%" + identificador + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Aposta aposta = new Aposta(rs.getString("identificador"), rs.getInt("placarA"), rs.getInt("placarB"));

                apostas.add(aposta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApostaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return apostas;
    }

    public void update(Aposta aposta) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE aposta SET identificador = ? ,placarA = ? ,placarB = ? WHERE ID = ?");

            stmt.setString(1, aposta.getIdentificador());
            stmt.setInt(2, aposta.getPlacarA());
            stmt.setDouble(3, aposta.getPlacarB());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void delete(Aposta aposta) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM aposta WHERE identificador = ?");

            stmt.setString(1, aposta.getIdentificador());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
