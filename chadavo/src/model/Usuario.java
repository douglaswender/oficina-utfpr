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
public class Usuario {
    
    private SimpleIntegerProperty idUsuario;
    private SimpleStringProperty loginUsuario;
    private SimpleStringProperty senhaUsuario;
    private SimpleStringProperty nomeUsuario;

    public Usuario(int idUsuario, String loginUsuario, String senhaUsuario, String nomeUsuario) {
        this.idUsuario = new SimpleIntegerProperty(idUsuario);
        this.loginUsuario = new SimpleStringProperty(loginUsuario);
        this.senhaUsuario = new SimpleStringProperty(senhaUsuario);
        this.nomeUsuario = new SimpleStringProperty(nomeUsuario);
    }

    public Usuario(String loginUsuario, String senhaUsuario, String nomeUsuario) {
        this.loginUsuario = new SimpleStringProperty(loginUsuario);
        this.senhaUsuario = new SimpleStringProperty(senhaUsuario);
        this.nomeUsuario = new SimpleStringProperty(nomeUsuario);
    }

    public Usuario(String loginUsuario, String senhaUsuario) {
        this.loginUsuario = new SimpleStringProperty(loginUsuario);
        this.senhaUsuario = new SimpleStringProperty(senhaUsuario);
    }
    
    

    
    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario.getValue();
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = new SimpleIntegerProperty(idUsuario);
    }

    public String getLoginUsuario() {
        return loginUsuario.get();
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = new SimpleStringProperty(loginUsuario);
    }

    public String getSenhaUsuario() {
        return senhaUsuario.get();
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = new SimpleStringProperty(senhaUsuario);
    }

    public String getNomeUsuario() {
        return nomeUsuario.get();
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = new SimpleStringProperty(nomeUsuario);
    }
    
    
    
    
}
