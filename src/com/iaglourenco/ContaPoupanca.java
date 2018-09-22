/*
 * Copyright (c) Iago Lourenço. Este código foi feito por mim, a não ser que "mim" tenha deixado.
 * vossa senhoria não tem permissão para usa-lo seja para o que for.
 * salvo se sua vida estiver em risco. Ahoy!
 */

package com.iaglourenco;

class ContaPoupanca extends ContaSimples {


    //NAO PODE TER SALDO NEGATIVO, MAS TEM RENDIMENTO MENSAL

        double taxaRendimento;

    ContaPoupanca(String nomeCorrentista,String nConta){
        super(nomeCorrentista,nConta);

    }



}
