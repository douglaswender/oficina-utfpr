/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author dougl
 */
public class Usuario {
    
    private int idUsuario;
    private String loginUsuario;
    private String senhaUsuario;
    private String nomeUsuario;

    public Usuario(int idUsuario, String loginUsuario, String senhaUsuario, String nomeUsuario) {
        this.idUsuario = idUsuario;
        this.loginUsuario = loginUsuario;
        this.senhaUsuario = senhaUsuario;
        this.nomeUsuario = nomeUsuario;
    }

    public Usuario(String loginUsuario, String senhaUsuario, String nomeUsuario) {
        this.loginUsuario = loginUsuario;
        this.senhaUsuario = senhaUsuario;
        this.nomeUsuario = nomeUsuario;
    }

    
    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    
    
    
}
