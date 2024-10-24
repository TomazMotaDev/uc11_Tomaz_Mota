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
        conn = new conectaDAO().connectDB();
        String sql = "SELECT id, nome, valor, status FROM produtos";
        
        if(conn != null){
            try{
                /*Statement para que a busca seja feito no banco de dados*/
                prep = this.conn.prepareStatement(sql);
                ResultSet rs = prep.executeQuery();
                
                /*Criando um objeto para cada produto encontrado na consulta ao banco
                e adicionando a listagem de produtos*/
                while(rs.next()){
                    ProdutosDTO produto = new ProdutosDTO();
                    produto.setId(rs.getInt("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setValor(rs.getInt("valor"));
                    produto.setStatus(rs.getString("status"));

                    listagem.add(produto);                
                }
            }catch (SQLException ex){
                listagem = null;
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao buscar a lista de filmes no banco de dados.");
            }
        }
        
        return listagem;
    }
        
}

