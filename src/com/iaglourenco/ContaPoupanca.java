/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;


import com.iaglourenco.exceptions.SaldoInsuficienteException;

class ContaPoupanca extends Conta {



    //NAO PODE TER SALDO NEGATIVO, MAS TEM RENDIMENTO MENSAL
    private final double taxaRendimento;
    private String log;
    ContaPoupanca(String nomeCorrentista,String nConta,double taxaRendimento){
        super(nomeCorrentista,nConta);
        this.taxaRendimento=taxaRendimento;
        log=" ";
    }

    @Override
    public boolean depositar(double valor) {
        log=log+"DEPÓSITO DE R$ "+Double.toString(valor)+"-> SALDO = R$ "+Double.toString(super.getSaldo()+valor)+"\n\n";
        return super.depositar(valor);
    }

    @Override
    String getLog(){
        return this.log;
    }



    void render(){
            super.depositar(super.getSaldo()*(taxaRendimento/100));
    }

    @Override
    public boolean sacar(double valor)throws SaldoInsuficienteException {

        if (getSaldo() > 0) {//nao tem como sacar negativo
            log=log+"SAQUE DE R$ "+Double.toString(valor)+"-> SALDO = R$ "+Double.toString(super.getSaldo()-valor)+"\n\n";
            super.sacar(valor);
            return true;
        }
        throw new SaldoInsuficienteException("SALDO INSUFICIENTE");
    }

}
