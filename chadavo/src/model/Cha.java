/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;

/**
 *
 * @author ViniciusBelloli
 */
public class Cha {
    int id;
    String nome;
    String brevedescricao;
    String detalhes;
    String especificacao_tecnica;
    String indicacao;
    String contra_indicacao;
    String dicas;
    String prevencao;
    BufferedImage imgcha;

    public Cha(int id, String nome, String brevedescricao, String detalhes, String especificacao_tecnica, String indicacao, String contra_indicacao, String dicas, String prevencao, BufferedImage imgcha){
        
        this.id = id;
        this.nome                  = nome;
        this.brevedescricao        = brevedescricao;
        this.detalhes              = detalhes;
        this.especificacao_tecnica = especificacao_tecnica;
        this.indicacao             = indicacao;
        this.contra_indicacao      = contra_indicacao;
        this.dicas                 = dicas;
        this.prevencao             = prevencao;
        this.imgcha                = imgcha;
    }

    public Cha(String nome, String detalhes) {
        this.nome = nome;
        this.detalhes = detalhes;
    }


    
/*
    public class Chas extends RecursiveTreeObject<Chas> {

        StringProperty nome;
        StringProperty brevedescricao;
        StringProperty detalhes;
        StringProperty especificacao_tecnica;
        StringProperty indicacao;
        StringProperty contra_indicacao;
        StringProperty dicas;
        StringProperty prevencao;

        public Chas(String nome, String brevedescricao, String detalhes, String especificacao_tecnica, String indicacao, String contra_indicacao, String dicas, String prevencao) {
            this.nome                  = new SimpleStringProperty(nome);
            this.brevedescricao        = new SimpleStringProperty(brevedescricao);
            this.detalhes              = new SimpleStringProperty(detalhes);
            this.especificacao_tecnica = new SimpleStringProperty(especificacao_tecnica);
            this.indicacao             = new SimpleStringProperty(indicacao);
            this.contra_indicacao      = new SimpleStringProperty(contra_indicacao);
            this.dicas                 = new SimpleStringProperty(dicas);
            this.prevencao             = new SimpleStringProperty(prevencao);                        
        }

    }
*/
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBrevedescricao() {
        return brevedescricao;
    }

    public void setBrevedescricao(String brevedescricao) {
        this.brevedescricao = brevedescricao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getEspecificacao_tecnica() {
        return especificacao_tecnica;
    }

    public void setEspecificacao_tecnica(String especificacao_tecnica) {
        this.especificacao_tecnica = especificacao_tecnica;
    }

    public String getIndicacao() {
        return indicacao;
    }

    public void setIndicacao(String indicacao) {
        this.indicacao = indicacao;
    }

    public String getContra_indicacao() {
        return contra_indicacao;
    }

    public void setContra_indicacao(String contra_indicacao) {
        this.contra_indicacao = contra_indicacao;
    }

    public String getDicas() {
        return dicas;
    }

    public void setDicas(String dicas) {
        this.dicas = dicas;
    }

    public String getPrevencao() {
        return prevencao;
    }

    public void setPrevencao(String prevencao) {
        this.prevencao = prevencao;
    }

    public BufferedImage getImgcha() {
        return imgcha;
    }

    public void setImgcha(BufferedImage imgcha) {
        this.imgcha = imgcha;
    }

    public Cha(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
