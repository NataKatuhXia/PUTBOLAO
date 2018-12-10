/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.dao;

import static bolao.controler.GetProperties.PROP;
import bolao.model.bean.Administrador;
import bolao.model.bean.Apostador;
import bolao.model.bean.Pessoa;
import bolao.util.Observer;
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
public class PessoaDAO implements Observer {

    Pessoa pessoa;

    public static boolean validationLogin(String usuario) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        boolean check = true;
        try {
            stmt = con.prepareStatement("SELECT * FROM pessoa WHERE usuario = ?");
            stmt.setString(1, usuario);

            rs = stmt.executeQuery();

            if (rs.next()) {

                /* Usuario nao liberado por isso false */
                check = false;

            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return check;
    }

    public Pessoa checkLogin(String comando, String usuario, String senha) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("SELECT * FROM pessoa WHERE usuario = ? and senha = ?");
            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            if (rs.next()) {

                check = true;

            }
            if (check) {
                if (rs.getBoolean("adm")) {
                    pessoa = new Administrador().createAccount(comando, rs.getString("nome"), rs.getString("usuario"), rs.getString("senha"), rs.getString("pontos"));
                } else {
                    pessoa = new Apostador().createAccount(comando, rs.getString("nome"), rs.getString("usuario"), rs.getString("senha"), rs.getString("pontos"));
                }
            } else {
                pessoa = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return pessoa;
    }

    public void create(Pessoa pessoa) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO pessoa (usuario,senha,nome,adm)VALUES(?,?,?,?)");
            stmt.setString(1, pessoa.getUsuario());
            stmt.setString(2, pessoa.getSenha());
            stmt.setString(3, pessoa.getNome());
            stmt.setBoolean(4, pessoa.isContaADM());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void delete(String usuario) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM pessoa WHERE usuario = ?");

            stmt.setString(1, usuario);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir " + ex);
        } catch (NullPointerException ax) {
            JOptionPane.showMessageDialog(null, "Usuario nao encontrado");
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    @Override
    public void updateAposta(String comando, String usuario) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            if (comando.equals("vencendor")) {
                stmt = con.prepareStatement("UPDATE pessoa SET pontos = pontos + ? WHERE usuario = ?");
                stmt.setInt(1, Integer.parseInt(PROP.getProperty("PONTOS_VITORIA")));
                stmt.setString(2, usuario);
            } else if (comando.equals("Realizar aposta")) {
                stmt = con.prepareStatement("UPDATE pessoa SET pontos = pontos - ? WHERE usuario = ?");
                stmt.setInt(1, Integer.parseInt(PROP.getProperty("PONTOS_APOSTA")));
                stmt.setString(2, usuario);
            }

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void updateAccount(String nome, String senha, String usuario) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("UPDATE pessoa SET nome = ?, senha = ?, usuario = ? WHERE usuario = ?");
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            stmt.setString(3, usuario);
            stmt.setString(4, usuario);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Pessoa> ranking() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Pessoa> pessoas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM pessoa WHERE adm = false order by pontos desc");

            rs = stmt.executeQuery();

            while (rs.next()) {

                Pessoa apostador = new Apostador().createAccount("Consulta", rs.getString("nome"), rs.getString("usuario"), rs.getString("senha"), rs.getString("pontos"));

                pessoas.add(apostador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApostaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return pessoas;
    }

    public List<Pessoa> search(String nome, String usuario) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Pessoa> pessoas = new ArrayList<>();

        try {
            if (!nome.equals("") && !usuario.equals("")) {
                stmt = con.prepareStatement("SELECT * FROM pessoa WHERE adm = false and usuario LIKE ? and nome LIKE ? order by pontos desc");
                stmt.setString(1, usuario);
                stmt.setString(2, "%" + nome + "%");
            } else if (nome.equals("") && !usuario.equals("")) {
                stmt = con.prepareStatement("SELECT * FROM pessoa WHERE adm = false and usuario LIKE ? order by pontos desc");
                stmt.setString(1, usuario);
            } else if (usuario.equals("") && !nome.equals("")) {
                stmt = con.prepareStatement("SELECT * FROM pessoa WHERE adm = false and nome LIKE ? order by pontos desc");
                stmt.setString(1, "%" + nome + "%");
            } else {
                stmt = con.prepareStatement("SELECT * FROM pessoa WHERE adm = false order by pontos desc");
            }

            rs = stmt.executeQuery();

            while (rs.next()) {

                Pessoa apostador = new Apostador().createAccount("Consulta", rs.getString("nome"), rs.getString("usuario"), rs.getString("senha"), rs.getString("pontos"));

                pessoas.add(apostador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApostaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return pessoas;

    }
}
