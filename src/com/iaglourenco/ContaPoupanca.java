/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;


class ContaPoupanca extends Conta {


    //NAO PODE TER SALDO NEGATIVO, MAS TEM RENDIMENTO MENSAL
    private final double taxaRendimento;

    ContaPoupanca(String nomeCorrentista,String nConta,double taxaRendimento){
        super(nomeCorrentista,nConta);
        this.taxaRendimento=taxaRendimento;
    }



    void render(){

            super.depositar(super.getSaldo()*taxaRendimento);
    }

    @Override
    public boolean sacar(double valor){

        if (getSaldo() > 0) {//nao tem como sacar negativo
            super.sacar(valor);
            return true;
        }
        return false;
    }

}
