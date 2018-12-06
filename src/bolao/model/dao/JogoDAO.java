/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.dao;

import bolao.model.bean.Jogo;
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
public class JogoDAO {

    public void create(Jogo jogo) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO aposta (identificador)VALUES(?)");
            stmt.setString(1, jogo.getIdentificador());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Jogo> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<Jogo> jogos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM aposta order by data");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Jogo jogo = new Jogo(rs.getString("identificador"), rs.getInt("apostadores"), rs.getString("resultado"), rs.getString("data"));

                jogos.add(jogo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApostaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return jogos;
    }

    public List<Jogo> readForDesc(String filtro, String identificador) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<Jogo> jogos = new ArrayList<>();

        try {
            if (filtro.equals("time")) {
                stmt = con.prepareStatement("SELECT * FROM aposta WHERE identificador LIKE ? order by data");
            } else if (filtro.equals("data")) {
                stmt = con.prepareStatement("SELECT * FROM aposta WHERE identificador LIKE ? order by data");
            }
            stmt.setString(1, "%" + identificador + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Jogo jogo = new Jogo(rs.getString("identificador"), rs.getInt("apostadores"), rs.getString("resultado"), rs.getString("data"));

                jogos.add(jogo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApostaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return jogos;
    }

    public void Update(Jogo jogo) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Update aposta SET apostadores = ? ,data = ? ,resultado = ? WHERE identificador = ?");

            stmt.setInt(1, jogo.getApostadores());
            stmt.setString(2, (String) jogo.getData());
            stmt.setString(3, jogo.getResultado());
            stmt.setString(4, jogo.getIdentificador());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
