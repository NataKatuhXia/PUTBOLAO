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
            stmt = con.prepareStatement("INSERT INTO aposta (identificador,placar,usuario)VALUES(?,?,?)");
            stmt.setString(1, aposta.getIdentificador());
            stmt.setString(2, aposta.getPalpite());
            stmt.setString(3, aposta.getUsuario());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Aposta criada com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Aposta> read(String comando, String identificador, String resultado) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<Aposta> apostas = new ArrayList<>();

        try {
            if (comando.equals("Todos")) {
                stmt = con.prepareStatement("SELECT * FROM aposta WHERE identificador = ? order by identificador");
                stmt.setString(1, identificador);
            } else if (comando.equals("Vencedores")) {
                String array[] = new String[2];
                array = resultado.split("x");
                stmt = con.prepareStatement("SELECT * FROM aposta WHERE identificador = ? and placara = ? and placarb = ? order by identificador");

                stmt.setString(1, identificador);
                stmt.setInt(2, Integer.parseInt(array[0]));
                stmt.setInt(3, Integer.parseInt(array[1]));
            }
            rs = stmt.executeQuery();

            while (rs.next()) {

                Aposta aposta = new Aposta(rs.getString("usuario"), rs.getString("identificador"), rs.getString("placar"), rs.getString("status"));

                apostas.add(aposta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApostaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return apostas;
    }

    public List<Aposta> readForDesc(String identificador, String comando) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<Aposta> apostas = new ArrayList<>();

        try {
            if (comando.equals("Diferente")) {
                stmt = con.prepareStatement("SELECT * FROM aposta WHERE identificador = ? and status != ? order by placar");
            } else {
                stmt = con.prepareStatement("SELECT * FROM aposta WHERE identificador = ? and status = ? order by placar");
            }

            stmt.setString(1, identificador);
            stmt.setString(2, "A definir");

            rs = stmt.executeQuery();

            while (rs.next()) {

                Aposta aposta = new Aposta(rs.getString("usuario"), rs.getString("identificador"), rs.getString("placar"), rs.getString("status"));

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
            stmt = con.prepareStatement("UPDATE aposta SET status = ? WHERE identificador = ?");

            stmt.setString(1, aposta.getStatus());
            stmt.setString(2, aposta.getIdentificador());

            stmt.executeUpdate();

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

    public List<Aposta> readForUser(String usuario) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<Aposta> apostas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM aposta WHERE usuario = ? order by status");
            stmt.setString(1, usuario);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Aposta aposta = new Aposta(rs.getString("usuario"), rs.getString("identificador"), rs.getString("placar"), rs.getString("status"));

                apostas.add(aposta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApostaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return apostas;
    }
}
