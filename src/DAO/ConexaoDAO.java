package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ConexaoDAO {    
    public static Connection getConexao(){
        String conecta = "jdbc:postgresql://localhost:5432/meuBD";
        String usuario = "postgres";
        String senha = "postgre";
        
        try{
            Connection con = (Connection) DriverManager.getConnection(conecta,usuario,senha);
            return con;
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro: "+erro);
            return null;
        }
    }
}
