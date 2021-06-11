/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.UsuairioDAO;
import Objetos.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author kaiop
 */
public class UsuarioTableModel extends AbstractTableModel {

    private List<Usuario> dados = new ArrayList<>();
    private String[] colunas = {"Nome", "Senha", "Login", "Tipo"};

    @Override
    public String getColumnName(int Column) {
        return colunas[Column];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;

    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getNome();
            case 1:
                return dados.get(linha).getSenha();
            case 2:
                return dados.get(linha).getLogin();
            case 3:
                return dados.get(linha).getTipo();

        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setNome((String) valor);
                break;
            case 1:
                dados.get(linha).setSenha((String) valor);
                break;
            case 2:
                dados.get(linha).setLogin((String) valor);
                break;
            case 3:
                dados.get(linha).setTipo((String) valor);
                break;
        }
        this.fireTableRowsUpdated(linha, linha);

    }
    
    public void addLinha(Usuario u){
        this.dados.add(u);
        this.fireTableDataChanged();
    }
    
    public void removeLinha(int linha){
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
   public Usuario pegaDadosLinha (int linha){
       return dados.get(linha);
   }
    private void lerDados(){
       UsuairioDAO udao = new UsuairioDAO();
       
       for(Usuario u : udao.read()){
           
           this.addLinha(u);
       }
       this.fireTableDataChanged();
   }
   public void recarregaTabela(){
       this.dados.clear();
       lerDados();
       this.fireTableDataChanged();
   }

}
