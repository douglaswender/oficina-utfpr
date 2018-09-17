/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.Conexao;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.ManipularImagem;

/**
 *
 * @author ViniciusBelloli
 */
public class ChaDAO {

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
}
