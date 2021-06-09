/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BD.Conexao;
import Objetos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author kaiop
 */
public class UsuairioDAO {

    public List<Usuario> read() {

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuario> Usuario = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM  tbl_usuarios");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setNome(rs.getString("nome"));
                Usuario.add(u);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao obter dados " + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return Usuario;

    }

    public void create(Usuario u) {

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                    "INSERT INTO tbl_usuarios(nome, senha, login) VALUES (?,?,?)");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getSenha());
            stmt.setString(3, u.getLogin());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Cadastradado com sucesso");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao obter dados " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }

    }
    
    public void update(Usuario u){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(
              "UPDATE tbl_usuarios SET nome = ?, senha = ?, login = ? WHERE id = ?");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getSenha());
            stmt.setString(3, u.getLogin());
            stmt.setInt(4, u.getId());
            
            stmt.execute();
                    JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar" + e);
        }finally {
            Conexao.closeConnection(con, stmt);
    }
        
        
  }
        public void delete(Usuario u){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
               "DELETE FROM tbl_usuarios WHERE id = ?");
            
            stmt.setInt(1, u.getId());
            
            stmt.execute();
                    JOptionPane.showMessageDialog(null, "Deletado com sucesso");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao deletar" + e);
        }finally {
            Conexao.closeConnection(con, stmt);
        }
        
        
    }



}