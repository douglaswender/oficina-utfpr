/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author dougl
 */
public class Beneficio {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty nome;
    private Boolean marcado;

    public Beneficio() {
    }

    public Beneficio(int id, String nome) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
    }

    public Beneficio(Boolean marcado, int id, String nome){
        this.marcado = marcado;
        this.id      = new SimpleIntegerProperty (id);
        this.nome    = new SimpleStringProperty (nome);
    }

    public Beneficio(String nome) {
        this.nome = new SimpleStringProperty(nome);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome = new SimpleStringProperty(nome);
    }
    
    @Override
    public String toString(){
        return this.getNome();
    }
    
    
    
}
