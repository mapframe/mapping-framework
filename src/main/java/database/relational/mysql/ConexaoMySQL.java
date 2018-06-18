/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.relational.mysql;

import database.relational.mysql.BDMySQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class ConexaoMySQL {
        
    public static Connection open() {
        Connection c = null;
        try {
            Class.forName(BDMySQL.JDBC_DRIVER);
            c = DriverManager.getConnection(BDMySQL.URL_CONEXAO, BDMySQL.USUARIO, BDMySQL.SENHA);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoMySQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Driver " + BDMySQL.JDBC_DRIVER + " não foi encontrado na memória.");
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
     
}


