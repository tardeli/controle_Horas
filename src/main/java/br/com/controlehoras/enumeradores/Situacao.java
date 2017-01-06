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
public enum Situacao {
    Ativo("Ativo"),
    Inativo("Inativo");
    
    private final String nome;

    private Situacao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
