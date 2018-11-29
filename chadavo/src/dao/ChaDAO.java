/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.Conexao;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import com.jfoenix.controls.JFXCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Cha;

/**
 *
 * @author ViniciusBelloli
 */
public class ChaDAO {

    //mudar todos esses dados para passar apenas um chá (objeto)
    public static void Gravar(String nome, String brevedescricao, String modo_preparo, BufferedImage imgcha, Boolean lAlteracao, Integer id) throws SQLException, IOException {
        try {
            Connection con = new Conexao().getConnection();
            String cSqlExecute;

            if (lAlteracao) {
                cSqlExecute = "UPDATE CHAS SET NOME_CHA = ?, DESCRICAO_CHA = ?, MODO_PREPARO = ?, IMGCHA = ? WHERE COD_CHA = ?";
            }else{
                cSqlExecute = "INSERT INTO CHAS(NOME_CHA, DESCRICAO_CHA, MODO_PREPARO, IMGCHA) VALUES(?, ?, ?, ?)";
            }

            PreparedStatement stm = con.prepareStatement(cSqlExecute);
            stm.setString(1, nome);
            stm.setString(2, brevedescricao);
            stm.setString(3, modo_preparo);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(imgcha, "jpg", bos );
            byte [] data = bos.toByteArray();

            stm.setBytes(4, data);
            
            if (lAlteracao) {
                stm.setInt(5, id);
            }

            stm.execute();
            stm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("Dados Gravados Com Sucesso.");
        alert.show();
    }
    
    public List<Cha> Pesquisar(String pesquisa) throws IOException {
        try {
            Connection con = new Conexao().getConnection();
            PreparedStatement stm = con.prepareStatement("SELECT cod_cha, nome_cha, descricao_cha FROM chas WHERE nome_cha ~* ?");
            stm.setString(1, pesquisa);

            ResultSet rs = stm.executeQuery();

            List<Cha> lista = new ArrayList<>();

            int nCont = 0;
            while (rs.next()) {
                int id = rs.getInt("cod_cha");
                String nome = rs.getString("nome_cha");
                String detalhes = rs.getString("descricao_cha");
                //System.out.println(nome);
                //System.out.println(detalhes);
                //Cha c = new ChaTable(nome, detalhes);
                lista.add(new Cha(id, nome, detalhes));
//                InputStream in = new ByteArrayInputStream(rs.getBytes(9));
//                BufferedImage bImageFromConvert = ImageIO.read(in);
//
//                ImageIO.write(bImageFromConvert, "jpg", new File(
//                             "c:/foto" + Integer.toString(nCont) + ".jpg"));
//
//                ChaTable cha = new Cha(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), bImageFromConvert);
//                lista.add(cha);
//                nCont++;
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

    public List<Cha> TodosChas() {

        List<Cha> chas = new ArrayList<Cha>();
        
        try {
            Connection con = new Conexao().getConnection();

            //trocar detalhes por beneficios
            PreparedStatement stm = con.prepareStatement("SELECT cod_cha, nome_cha, descricao_cha FROM chas");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                int cod = rs.getInt("cod_cha");
                String nome = rs.getString("nome_cha");
                String detalhes = rs.getString("descricao_cha");
                //System.out.println(nome);
                //System.out.println(detalhes);
                //Cha c = new ChaTable(nome, detalhes);
                chas.add(new Cha(cod, nome, detalhes));

            }
//            for (Cha cha : chas) {
//                System.out.println(cha.getDescricao_cha());
//                System.out.println(cha.getNome());
//            }

            return chas;
        } catch (SQLException e) {
        }
        return chas;

    }
    
        public static ObservableList<Cha> pesquisaTodosChas(String pesquisa) throws SQLException {
        
        Connection con = new Conexao().getConnection();
        String cSQL;

        if (pesquisa.matches("[0-9]+")) {
            cSQL = "select * from chas where cod_cha = ?";
        }else{
            cSQL = "select * from chas where nome_cha like ?";
        }
        
        PreparedStatement ps = con.prepareStatement(cSQL);

        if (pesquisa.matches("[0-9]+")) {
            ps.setInt(1, Integer.parseInt(pesquisa));
        }else{
            ps.setString(1, '%' + pesquisa + '%');
        }
        
        ResultSet rs = ps.executeQuery();
        ObservableList<Cha> observableArrayList = null;
        List<Cha> retorno = new ArrayList<>();
        
        try {
            int i = 0;
            
            if (!rs.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informação");
                alert.setHeaderText("Nenhum registro foi encontrados.");
                alert.show();
                return null;
            }
            
            while (rs.next()) {
                Cha c = new Cha(rs.getString("nome_cha"));
                retorno.add(c);
              
                i++;
                //observableArrayList = FXCollections.observableArrayList(new Beneficio(false, rs.getString("nome_beneficio")));
            }
            
            observableArrayList = FXCollections.observableArrayList(retorno);
            
            return observableArrayList;
        } catch (SQLException e) {
            System.out.println("ERRO: #" + e);
            return null;
        } finally {
            ps.close();
        }
        
    }
    
    public static Image capturaImagemCha(Cha c) {
            byte[] imageByte = null;
            Image image = null;

            try {
                    Connection con = new Conexao().getConnection();
                    PreparedStatement stm = con.prepareStatement("SELECT imgcha FROM chas WHERE cod_cha = ?");
                    stm.setInt(1, c.getId());
                    ResultSet rs = stm.executeQuery();

                    rs.next();
                    imageByte = rs.getBytes("imgcha");
                    ImageIcon icon = new ImageIcon(imageByte);
                    BufferedImage img;
                    img = ImageIO.read(new ByteArrayInputStream(imageByte));
                    Image img2 = SwingFXUtils.toFXImage(img, null );
                    return img2;
            } catch (Exception e) {
                    e.printStackTrace();
            }
            return image;
    }

    public static List<JFXCheckBox> TodosBeneficios() throws SQLException{
        List<JFXCheckBox> checkboxes = new ArrayList<>();
        Connection con = new Conexao().getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM beneficios");
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            JFXCheckBox box = new JFXCheckBox(rs.getString(2));
            checkboxes.add(box);
        }

        return null;
    
    }

    public static Cha Pesquisar2(Cha c){
            byte[] imageByte = null;
            Image image = null;

            try {
                    Connection con = new Conexao().getConnection();
                    PreparedStatement stm = con.prepareStatement("SELECT cod_cha, nome_cha, descricao_cha, modo_preparo  FROM chas WHERE cod_cha = ?");
                    stm.setInt(1, c.getId());
                    ResultSet rs = stm.executeQuery();

                    rs.next();
                    Cha cha = new Cha();
                    cha.setId(rs.getInt(1));
                    cha.setNome(rs.getString(2));
                    cha.setDescricao_cha(rs.getString(3));
                    cha.setModo_preparo(rs.getString(4));
                    return cha;
                    
            } catch (Exception e) {
                    e.printStackTrace();
            }

            return null;
    }

}
