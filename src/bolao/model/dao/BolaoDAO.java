/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.dao;

import bolao.model.bean.Bolao;
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
public class BolaoDAO {

    public void create(Bolao bolao) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO aposta (identificador)VALUES(?)");
            stmt.setString(1, bolao.getIdentificador());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Bolao> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<Bolao> bolaos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM aposta order by data");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Bolao bolao = new Bolao(rs.getString("identificador"), rs.getInt("apostadores"));

                bolaos.add(bolao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApostaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return bolaos;
    }

    public List<Bolao> readForDesc(String filtro, String identificador) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<Bolao> boloes = new ArrayList<>();

        try {
            if (filtro.equals("time")) {
                stmt = con.prepareStatement("SELECT * FROM aposta WHERE identificador LIKE ? order by data");
            }

            stmt.setString(1, "%" + identificador + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Bolao bolao = new Bolao(rs.getString("identificador"), rs.getInt("apostadores"));

                boloes.add(bolao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApostaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return boloes;
    }
}
