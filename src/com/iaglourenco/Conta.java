/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;

import java.util.Arrays;

abstract class Conta {

    private String nomeCorrentista;
    private String nConta;
    private double saldo;
    private char[] senha;
    private static int contasCadastradas=0;
    static Conta[] contas = new Conta[Gerente.MAX_CONTAS];//array de Objects Conta



    Conta(String nomeCorrentista,String nConta){

        setnConta(nConta);
        setNomeCorrentista(nomeCorrentista);
        saldo=0.0;
        senha = "0000".toCharArray();
        contasCadastradas++;
    }

    boolean alteraSenha(char[] senhaAntiga, char[] senhaNova){

        if(Arrays.equals(senhaAntiga, senha)){
            senha = Arrays.copyOf(senhaNova,senhaNova.length);
            return true;
        }
        return false;
    }

    boolean verificaSenha(char[] senha){ return Arrays.equals(this.senha, senha);}

    String getNomeCorrentista() {
        return nomeCorrentista;
    }

    private void setNomeCorrentista(String nomeCorrentista) {
        this.nomeCorrentista = nomeCorrentista;
    }

    String getnConta() {
        return nConta;
    }

    private void setnConta(String nConta) {
        this.nConta = nConta;
    }

    double getSaldo() {
        return saldo;
    }

    public boolean sacar(double valor){

        if (valor>0) {
            this.saldo-=valor;
            return true;
        }
        return false;
    }

    public boolean depositar(double valor){

        if(valor<=0) // depositar negativo tem outro nome, e nao vale depositar nada
            return false;
        this.saldo+=valor;
        return true;//a true da true novamente
    }

    public String info(){

        String accType = getClass().getName()
                .replace("com.iaglourenco.","")
                .replace("ContaSimples","Conta Simples")
                .replace("ContaPoupanca","Conta Poupanca")
                .replace("ContaEspecial","Conta Especial");

        return "Nome do correntista " + nomeCorrentista+"\n"+
                "Numero da conta " + nConta+"\n"+
                "Tipo de Conta " + accType+"\n"+
                "Saldo disponivel " + Double.toString(saldo)+"\n";
    }


    public static int getContasCadastradas() {
        return contasCadastradas;
    }
}
