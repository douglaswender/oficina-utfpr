/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author dougl
 */
public class ChaTable {
    
    private SimpleStringProperty nome;
    private SimpleStringProperty detalhes;

    public ChaTable(Cha c) {
        this.nome = new SimpleStringProperty(c.getNome());
        this.detalhes = new SimpleStringProperty(c.getDetalhes());
    }

    public String getNome() {
        return nome.getValue();
    }

    public String getDetalhes() {
        return detalhes.getValue() ;
    }
    
    
    
    
    
}
