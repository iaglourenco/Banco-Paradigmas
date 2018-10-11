/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;


import com.iaglourenco.exceptions.SaldoInsuficienteException;

class ContaPoupanca extends Conta {


    //NAO PODE TER SALDO NEGATIVO, MAS TEM RENDIMENTO MENSAL
    private final double taxaRendimento;

    ContaPoupanca(String nomeCorrentista,String nConta,double taxaRendimento){
        super(nomeCorrentista,nConta);
        this.taxaRendimento=taxaRendimento;
    }



    void render(){
            super.depositar(super.getSaldo()*(taxaRendimento/100));
    }

    @Override
    public boolean sacar(double valor)throws SaldoInsuficienteException {

        if (getSaldo() > 0) {//nao tem como sacar negativo
            super.sacar(valor);
            return true;
        }
        throw new SaldoInsuficienteException("SALDO INSUFICIENTE");
    }

}
