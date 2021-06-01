/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Objetos.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author kaiop
 */
public class ProdutoTableModel extends AbstractTableModel{
    private List<Produto> dados = new ArrayList<>();
    private String[] colunas = {"Descriçao", "Quantidade", "Valor"};
    
    @Override
    public String getColumnName(int Column){
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
        switch(coluna){
            case 0:
                return dados.get(linha).getDescricao();
            case 1:
                return dados.get(linha).getQuantidade();
            case 2:
                return dados.get(linha).getValor();    
        }
        return null;
    }   
    
    @Override
    public void setValueAt(Object valor,int linha, int coluna){
        switch(coluna){
            case 0:
                dados.get(linha).setDescricao((String) valor);
            case 1:
                dados.get(linha).setQuantidade(Integer.parseInt((String) valor));
            case 2:
                dados.get(linha).setValor(Double.parseDouble((String) valor));
        }
        this.fireTableRowsUpdated(linha, linha);
    }
    
    
}
