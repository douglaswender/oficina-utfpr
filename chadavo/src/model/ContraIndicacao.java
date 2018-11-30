/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 *
 * @author dougl
 */
public class ContraIndicacao {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty nome;
    private SimpleStringProperty descricao;
    private CheckBox marcado;

    public ContraIndicacao(int id, String nome, String descricao) {
        this.id        = new SimpleIntegerProperty (id);
        this.nome      = new SimpleStringProperty (nome);
        this.descricao = new SimpleStringProperty (descricao);
    }

    public ContraIndicacao(int id, String nome){
        this.id      = new SimpleIntegerProperty (id);
        this.nome    = new SimpleStringProperty (nome);
        this.marcado = new CheckBox();
    }

    public CheckBox getMarcado() {
        return marcado;
    }

    public void setMarcado(CheckBox marcado) {
        this.marcado = marcado;
    }

    public ContraIndicacao(String nome, String descricao) {
        this.nome      = new SimpleStringProperty (nome);
        this.descricao = new SimpleStringProperty (descricao);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty (id);
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome = new SimpleStringProperty (nome);
    }

    public String getDescricao() {
        return descricao.get();
    }

    public void setDescricao(String descricao) {
        this.descricao = new SimpleStringProperty (descricao);
    }
    
    
    
}
