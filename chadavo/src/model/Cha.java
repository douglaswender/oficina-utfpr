 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ViniciusBelloli
 */
public class Cha {
    SimpleIntegerProperty id;
    SimpleStringProperty nome;
    SimpleStringProperty descricao_cha;
    SimpleStringProperty modo_preparo;
    ArrayList<Beneficio> beneficios = new ArrayList<>();
    BufferedImage imgcha;

    public Cha(SimpleIntegerProperty id, SimpleStringProperty nome, SimpleStringProperty descricao_cha, SimpleStringProperty modo_preparo, BufferedImage imgcha){
        this.id = id;
        this.nome          = nome;
        this.descricao_cha = descricao_cha;
        this.modo_preparo  = modo_preparo;
        this.imgcha        = imgcha;
    }

    public Cha(String nome, int id){
        this.nome = new SimpleStringProperty(nome);
        this.id   = new SimpleIntegerProperty(id);
    }

    public Cha(int id, String nome, String detalhes) {
        this.id = new SimpleIntegerProperty(id);
        this.descricao_cha = new SimpleStringProperty(detalhes);
        this.nome = new SimpleStringProperty(nome);        
    }

    public ArrayList<Beneficio> getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(ArrayList<Beneficio> beneficios) {
        this.beneficios = beneficios;
    }
    
    public void addBeneficio(Beneficio b){
        beneficios.add(b);
    }
    
    public ArrayList percorreLista(){
        return null;
    }


    public int getId() {
        return id.getValue();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty (id);
    }

    public String getNome() {
        return nome.getValue();

    }

    public void setNome(String nome) {
        this.nome = new SimpleStringProperty (nome);
    }

    public String getDescricao_cha() {
        return descricao_cha.getValue();
    }

    public void setDescricao_cha(String descricao_cha) {
        this.descricao_cha = new SimpleStringProperty (descricao_cha);
    }

    public String getModo_preparo() {

        return modo_preparo.getValue();

    }

    public void setModo_preparo(String modo_preparo) {
        this.modo_preparo = new SimpleStringProperty (modo_preparo);
    }

    public BufferedImage getImgcha() {
        return imgcha;
    }

    public void setImgcha(BufferedImage imgcha) {
        this.imgcha = imgcha;
    }

    public Cha(){
        
    }
    
    @Override
    public String toString(){
        return this.getNome();
    }
    
}
