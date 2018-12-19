/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.dao;

import bolao.model.bean.Jogo;
import bolao.connection.ConnectionFactory;
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
            stmt = con.prepareStatement("INSERT INTO jogo (identificador,date) VALUES(?,?)");
            stmt.setString(1, jogo.getIdentificador());
            stmt.setString(2, jogo.getData());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public Jogo searchJogo(String comando, String identificador) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        Jogo jogo = null;

        try {
            if (comando.equals("Gerar Resultado")) {
                stmt = con.prepareStatement("SELECT * FROM jogo WHERE identificador = ? and resultado is null");
            } else {
                stmt = con.prepareStatement("SELECT * FROM jogo WHERE identificador = ?");
            }
            stmt.setString(1, identificador);
            rs = stmt.executeQuery();

            while (rs.next()) {

                jogo = new Jogo(rs.getString("identificador"), rs.getInt("apostadores"), rs.getString("resultado"), rs.getString("date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return jogo;
    }

    public List<Jogo> searchAll(String comando, String day) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<Jogo> jogos = new ArrayList<>();

        try {
            if (null != day && comando.equals("Todos")) {
                stmt = con.prepareStatement("SELECT * FROM jogo WHERE resultado is null and date = ? order by apostadores desc");
                stmt.setString(1, day);
            } else if (comando.equals("Todos")) {
                stmt = con.prepareStatement("SELECT * FROM jogo");
            } else if (comando.equals("Abertos")) {
                stmt = con.prepareStatement("SELECT * FROM jogo WHERE resultado is null order by date, apostadores desc");
            } else if (comando.equals("Fechado")) {
                stmt = con.prepareStatement("SELECT * FROM jogo WHERE resultado is not null and date = ? order by apostadores desc");
                stmt.setString(1, day);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {

                Jogo jogo = new Jogo(rs.getString("identificador"), rs.getInt("apostadores"), rs.getString("resultado"), rs.getString("date"));

                jogos.add(jogo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                stmt = con.prepareStatement("SELECT * FROM jogo WHERE identificador LIKE ? order by data");
            } else if (filtro.equals("data")) {
                stmt = con.prepareStatement("SELECT * FROM jogo WHERE identificador LIKE ? order by data");
            }
            stmt.setString(1, "%" + identificador + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Jogo jogo = new Jogo(rs.getString("identificador"), rs.getInt("apostadores"), rs.getString("resultado"), rs.getString("data"));

                jogos.add(jogo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return jogos;
    }

    public void update(Jogo jogo) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE jogo SET apostadores = ? ,date = ? ,resultado = ? WHERE identificador = ?");

            stmt.setInt(1, jogo.getApostadores());
            stmt.setString(2, (String) jogo.getData());
            stmt.setString(3, jogo.getResultado());
            stmt.setString(4, jogo.getIdentificador());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void update(String comando, String identificador) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            if (comando.equals("Realizar aposta")) {
                stmt = con.prepareStatement("UPDATE jogo SET apostadores = apostadores + 1 WHERE identificador = ?");
            } else if (comando.equals("Excluir aposta")) {
                stmt = con.prepareStatement("UPDATE jogo SET apostadores = apostadores - 1 WHERE identificador = ?");
            }
            stmt.setString(1, identificador);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
