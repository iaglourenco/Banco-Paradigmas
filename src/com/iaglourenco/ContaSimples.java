/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;

import com.iaglourenco.exceptions.SaldoInsuficienteException;

class ContaSimples extends Conta {


        //NAO PODE TER SALDO NEGATIVO


        ContaSimples(String nomeCorrentista,String nConta){
            super(nomeCorrentista,nConta);
        }

        @Override
        public boolean sacar(double valor) throws SaldoInsuficienteException {

            if (getSaldo() > 0) {//nao tem como sacar negativo
                super.sacar(valor);
                return true;
            }
            throw new SaldoInsuficienteException("SALDO INSUFICIENTE");
        }

}
