/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.connection;

import static bolao.util.GetProperties.PROP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsável pela conexão com o Banco de Dados
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public abstract class ConnectionFactory {

    private static final String DRIVER = PROP.getProperty("DRIVER_DATE");
    private static final String URL = PROP.getProperty("URL_DATE");
    private static final String USER = PROP.getProperty("USER_DATE");
    private static final String PASS = PROP.getProperty("PASSWORD_DATE");

    /**
     * Realiza conexão através das chaves do Properties, definida pelo
     * Administrador do Sistema
     *
     * @return, a conexão criada pelo método
     */
    public static Connection getConnection() {

        try {
            Class.forName(DRIVER);

            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexao", ex);
        }
    }

    /**
     * Método para finalizar uma conexão após toda conexão criada
     *
     * @param con, Conexão aberta
     */
    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para finalizar uma conexão após toda conexão criada
     *
     * @param con, Conexão aberta
     * @param stmt , resultados que o banco retorna
     */
    public static void closeConnection(Connection con, PreparedStatement stmt) {

        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para finalizar uma conexão após toda conexão criada
     *
     * @param con , Conexão aberta
     * @param stmt , resultados que o banco retorna
     * @param rs , resultados que o banco retorna
     */
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

        closeConnection(con, stmt);
        try {
            if (rs != null) {
                rs.close();

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
