/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.sun.javafx.collections.ElementObservableListDecorator;
import controller.Conexao;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.imageio.ImageIO;
import model.Cha;
import model.ChaTable;
import util.ManipularImagem;

/**
 *
 * @author ViniciusBelloli
 */
public class ChaDAO {
    //mudar todos esses dados para passar apenas um chá (objeto)
    public static void Gravar(String nome, String brevedescricao, String detalhes, String especificacao_tecnica, String indicacao, String contra_indicacao, String dicas, String prevencao, BufferedImage imgcha) throws SQLException{
        try {
            Connection con = new Conexao().getConnection();
            PreparedStatement stm = con.prepareStatement("INSERT INTO CHA(NOME, BREVE_DESCRICAO, DETALHES, ESPECIFICACAO_TECNICA, INDICACAO, CONTRA_INDICACAO, DICAS, PREVENCAO, IMGCHA) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setString(1, nome);
            stm.setString(2, brevedescricao);
            stm.setString(3, detalhes);
            stm.setString(4, especificacao_tecnica);
            stm.setString(5, indicacao);
            stm.setString(6, contra_indicacao);
            stm.setString(7, dicas);
            stm.setString(8, prevencao);
            ImagemDAO obj = new ImagemDAO();
            obj.setImagem(ManipularImagem.getImgBytes(imgcha));
            stm.setBytes(9, obj.getImagem());
        
            stm.execute();
            stm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static List Pesquisar(String pesquisa) throws IOException{
        try {            
            Connection con = new Conexao().getConnection();
            PreparedStatement stm = con.prepareStatement("SELECT * FROM cha WHERE nome like%?%");
            stm.setString(1, pesquisa);
            
            ResultSet rs = stm.executeQuery();
            
            List<Cha> lista = new ArrayList<Cha>();

            int nCont = 0;
            while(rs.next()){
                InputStream in = new ByteArrayInputStream(rs.getBytes(9));
                BufferedImage bImageFromConvert = ImageIO.read(in);

                ImageIO.write(bImageFromConvert, "jpg", new File(
                             "c:/foto" + Integer.toString(nCont) + ".jpg"));

                Cha cha = new Cha(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), bImageFromConvert);
                lista.add(cha);
                nCont++;
            }            
            
            rs.close();
            stm.close();
            con.close();
            return lista;
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public List<ChaTable> TodosChas(){
                
        List<ChaTable> chas = new ArrayList<ChaTable>();
        try {
            Connection con = new Conexao().getConnection();
            
            //trocar detalhes por beneficios
            PreparedStatement stm = con.prepareStatement("SELECT nome, detalhes FROM cha");
                        
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                String nome = rs.getString("nome");
                String detalhes = rs.getString("detalhes");
                System.out.println(nome);
                System.out.println(detalhes);
                //Cha c = new ChaTable(nome, detalhes);
                chas.add(new ChaTable(new Cha(nome, detalhes)));
                                
            }
            for (ChaTable cha : chas) {
                System.out.println(cha.getDetalhes());
                System.out.println(cha.getNome());
            }

            return chas;
        } catch (SQLException e) {
        }
        return chas;
        
    }
    
}
