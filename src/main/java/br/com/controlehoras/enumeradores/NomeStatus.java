/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlehoras.enumeradores;

/**
 *
 * @author Tardeli
 */
public enum NomeStatus {
    Ferias("Ferias"),
    Atestado("Atestado"),
    Licenca("Licenca"),
    Compensacao("Compensacao");
    
    private final String nome;

    private NomeStatus(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
