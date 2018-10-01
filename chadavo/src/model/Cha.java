/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;
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
    SimpleStringProperty beneficios;
    SimpleStringProperty ingredientes;
    SimpleStringProperty contra_indicacao;
    SimpleStringProperty modo_preparo;
    BufferedImage imgcha;

    public Cha(SimpleIntegerProperty id, SimpleStringProperty nome, SimpleStringProperty descricao_cha, SimpleStringProperty beneficios, SimpleStringProperty ingredientes, SimpleStringProperty contra_indicacao, SimpleStringProperty modo_preparo, BufferedImage imgcha){
        this.id = id;
        this.nome          = nome;
        this.descricao_cha = descricao_cha;
        this.imgcha        = imgcha;
    }

    public Cha(SimpleIntegerProperty id, SimpleStringProperty nome, SimpleStringProperty detalhes) {
        this.id = id; 
        this.nome = nome;
        this.descricao_cha = descricao_cha;
    }

    public Cha(int id, String nome, String detalhes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Cha(Cha cha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id.getValue();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty (id);
    }

    public SimpleStringProperty getNome() {
        return nome;
    }

    public void setNome(SimpleStringProperty nome) {
        this.nome = nome;
    }

    public SimpleStringProperty getDescricao_cha() {
        return descricao_cha;
    }

    public void setDescricao_cha(SimpleStringProperty descricao_cha) {
        this.descricao_cha = descricao_cha;
    }

    public SimpleStringProperty getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(SimpleStringProperty beneficios) {
        this.beneficios = beneficios;
    }

    public SimpleStringProperty getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(SimpleStringProperty ingredientes) {
        this.ingredientes = ingredientes;
    }

    public SimpleStringProperty getContra_indicacao() {
        return contra_indicacao;
    }

    public void setContra_indicacao(SimpleStringProperty contra_indicacao) {
        this.contra_indicacao = contra_indicacao;
    }

    public SimpleStringProperty getModo_preparo() {
        return modo_preparo;
    }

    public void setModo_preparo(SimpleStringProperty modo_preparo) {
        this.modo_preparo = modo_preparo;
    }

    public BufferedImage getImgcha() {
        return imgcha;
    }

    public void setImgcha(BufferedImage imgcha) {
        this.imgcha = imgcha;
    }

    public Cha(){
        
    }
    
}
