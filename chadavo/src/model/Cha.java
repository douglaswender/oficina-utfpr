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
        this.beneficios    = beneficios;
        this.ingredientes  = ingredientes;
        this.contra_indicacao = contra_indicacao;
        this.modo_preparo  = modo_preparo;
    }


    public Cha(int id, String nome, String detalhes) {
        this.id = new SimpleIntegerProperty(id);
        this.descricao_cha = new SimpleStringProperty(detalhes);
        this.nome = new SimpleStringProperty(nome);        
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

    public String getBeneficios() {
        return beneficios.get();

    }

    public void setBeneficios(String beneficios) {
        this.beneficios = new SimpleStringProperty (beneficios);
    }

    public String getIngredientes() {

        return ingredientes.getValue();

    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = new SimpleStringProperty (ingredientes);
    }

    public String getContra_indicacao() {

        return contra_indicacao.getValue();

    }

    public void setContra_indicacao(String contra_indicacao) {
        this.contra_indicacao = new SimpleStringProperty (contra_indicacao);
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
