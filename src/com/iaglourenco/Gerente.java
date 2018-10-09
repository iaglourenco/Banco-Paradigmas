/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;

@SuppressWarnings({"WeakerAccess", "SameParameterValue"})
final class Gerente {

    static final int MAX_CONTAS = 1000;//define o maximo de contas
    static final int ACC_SPECIAL = 1;
    static final int ACC_POUPANCA = 2;
    static final int ACC_SIMPLE = 3;


    static String gerarConta(){
        return Integer.toString((int)(1000 + Math.random() * 9999));
    }

    static int contaExiste(Conta[] contas,String numeroConta){
        for(int i=0;i<MAX_CONTAS;i++){
            if(contas[i].getnConta().equals(numeroConta)){
                return i;//retorna a posicao da conta no array
            }
        }
        return -1;//se nao -1
    }


    static boolean criarConta(Conta[] contas,String nameCorr, String nConta,int accType,double juros) {

        if(accType == ACC_POUPANCA)
            contas[Conta.getContasCadastradas()] = new ContaPoupanca(nameCorr, nConta,juros);

        if(accType == ACC_SPECIAL)
            contas[Conta.getContasCadastradas()] = new ContaEspecial(nameCorr, nConta, juros);

        if(accType ==ACC_SIMPLE)
            contas[Conta.getContasCadastradas()] = new ContaSimples(nameCorr, nConta);

        return true;

    }


    static int incrementarJuros(Conta[] contas) {

        int counter=0;
        for(int i=0;i<MAX_CONTAS;i++){
            if(contas[i] instanceof ContaPoupanca){
                ((ContaPoupanca)contas[i]).render();
                counter++;
            }
        }
        return counter;
    }

    static int cobrarJuros(Conta[] contas,double taxaJuros) {

        int counter=0;
        for(int i=0;i<MAX_CONTAS;i++){

            if(contas[i] instanceof ContaEspecial && contas[i].getSaldo()<0){
                ((ContaEspecial)contas[i]).juros(taxaJuros);
                counter++;
            }
        }
        return counter;

    }




}
