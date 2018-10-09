/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;

    class ContaSimples extends Conta {


        //NAO PODE TER SALDO NEGATIVO


        ContaSimples(String nomeCorrentista,String nConta){
            super(nomeCorrentista,nConta);
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
