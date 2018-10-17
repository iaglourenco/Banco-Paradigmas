/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;

import com.iaglourenco.exceptions.SaldoInsuficienteException;

class ContaSimples extends Conta {


        //NAO PODE TER SALDO NEGATIVO

    private String log;

        ContaSimples(String nomeCorrentista,String nConta){
            super(nomeCorrentista,nConta);
            log="";
        }

        @Override
        public boolean sacar(double valor) throws SaldoInsuficienteException {

            if (getSaldo() > 0) {//nao tem como sacar negativo
                log=log+"SAQUE DE R$ "+Double.toString(valor)+"-> SALDO = R$ "+Double.toString(super.getSaldo()-valor)+"\n\n";

                super.sacar(valor);
                return true;
            }
            throw new SaldoInsuficienteException("SALDO INSUFICIENTE");
        }

    @Override
    public boolean depositar(double valor) {
        log=log+"DEPÓSITO DE R$ "+Double.toString(valor)+"-> SALDO = R$ "+Double.toString(super.getSaldo()+valor)+"\n\n";
        return super.depositar(valor);
    }

    @Override
    String getLog(){return this.log;}
}
