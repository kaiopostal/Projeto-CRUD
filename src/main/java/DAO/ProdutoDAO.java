/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BD.Conexao;
import Objetos.Produto;
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
public class ProdutoDAO {

    public List<Produto> read() {

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM  tbl_produto");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setQuantidade(rs.getInt("Quantidade"));
                p.setValor(rs.getDouble("valor"));
                produtos.add(p);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao obter dados " + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public void create(Produto p) {

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                    "INSERT INTO tbl_produto (descricao, valor, quantidade) VALUES (?, ?, ?)");
            stmt.setString(1, p.getDescricao());
            stmt.setDouble(2, p.getValor());
            stmt.setInt(3, p.getQuantidade());
            
            
            stmt.execute();
                    JOptionPane.showMessageDialog(null, "Cadastradado com sucesso");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao obter dados " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void update(Produto p){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(
               "UPDATE tbl_produto SET descricao = ? , valor = ?, quantidade = ? WHERE id = ?");
            stmt.setString(1, p.getDescricao());
            stmt.setDouble(2, p.getValor());
            stmt.setInt(3, p.getQuantidade());
            stmt.setInt(4, p.getId());
            
            stmt.execute();
                    JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar" + e);
        }finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void delete(Produto p){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
               "DELETE tbl_produto WHERE id = ?");
            
            stmt.setInt(1, p.getId());
            
            stmt.execute();
                    JOptionPane.showMessageDialog(null, "Deletado com sucesso");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao deletar" + e);
        }finally {
            Conexao.closeConnection(con, stmt);
        }
        
        
    }
    
}
