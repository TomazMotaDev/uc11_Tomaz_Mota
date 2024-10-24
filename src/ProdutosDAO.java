/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    //método para cadastro do produto no banco de dados
    public int cadastrarProduto (ProdutosDTO produto){ 
        conn = new conectaDAO().connectDB();
        //inicializando o status para retorno
        int status = 0;
                
        //caso a conexão NÃO tenha problema
        if (conn != null){
            
            //inserindo o produto no baco de dados
            try{
            prep = conn.prepareStatement("INSERT INTO produtos(nome, valor, status) VALUES (?,?,?);");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());            
            status = prep.executeUpdate();
            
            }catch (SQLException e){
                status = 0;
            }
        }
        return status;
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
        
}

