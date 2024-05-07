package DAO;

import DAO.ConexaoDAO;
import DTO.InstrumentoDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class InstrumentoDAO {
    ArrayList<InstrumentoDTO> lista = new ArrayList<InstrumentoDTO>();
    
    public void cadastarInstrumento(InstrumentoDTO i){
        String sql_cad = "INSERT INTO tbl_instrumento(nome, familia, marca, modelo, cor) VALUES (?,?,?,?,?)";
        
        try(Connection c = ConexaoDAO.getConexao(); PreparedStatement p = c.prepareStatement(sql_cad);){
            p.setString(1, i.getNome());
            p.setString(2, i.getFamilia());
            p.setString(3, i.getMarca());
            p.setString(4, i.getModelo());
            p.setString(5, i.getCor());
            p.execute();
        }catch(SQLException er){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: "+er);
        }
    }
    
    public ArrayList<InstrumentoDTO> mostrarInstrumentos(){
        String sql_mostra = "SELECT * FROM tbl_instrumento";
        
        try(Connection c = ConexaoDAO.getConexao(); PreparedStatement p = c.prepareStatement(sql_mostra); ResultSet rs =  p.executeQuery();){
            
            while(rs.next()){
                InstrumentoDTO iDTO = new InstrumentoDTO();
                
                iDTO.setId(rs.getInt("id_instrumento"));
                iDTO.setNome(rs.getString("nome"));
                iDTO.setMarca(rs.getString("marca"));
                iDTO.setModelo(rs.getString("modelo"));
                iDTO.setFamilia(rs.getString("familia"));
                iDTO.setCor(rs.getString("cor"));
                
                lista.add(iDTO);
            }
            
        }catch(SQLException er){
            JOptionPane.showMessageDialog(null, "Erro ao mostrar: "+er);
        }
        return lista;
    }
    
    public void atualizarInstrumento(InstrumentoDTO i){
        String sql_atualiza = "UPDATE tbl_instrumento SET nome = ?, familia = ?, marca = ?, modelo = ?, cor = ? WHERE id_instrumento = ?";
        
        try(Connection c = ConexaoDAO.getConexao(); PreparedStatement p = c.prepareStatement(sql_atualiza);){
            p.setString(1, i.getNome());
            p.setString(2, i.getFamilia());
            p.setString(3, i.getMarca());
            p.setString(4, i.getModelo());
            p.setString(5, i.getCor());
            p.setInt(6, i.getId());
            
            p.execute();
            
        }catch(SQLException er){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+er);
        }
    }
    
    public void deletarInstrumento(int id){
        String sql_deleta = "DELETE FROM tbl_instrumento WHERE id_instrumento = ?";
        
        try(Connection c = ConexaoDAO.getConexao(); PreparedStatement p = c.prepareStatement(sql_deleta);){
            p.setInt(1, id);
            p.execute();
        }catch(SQLException er){
            JOptionPane.showMessageDialog(null, "Erro ao deletar: "+er);
        }
    }
    
}
