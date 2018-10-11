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
    static final char[] KEY_PADRAO = "0000".toCharArray();



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

        return "Nome do correntista= " + nomeCorrentista+"\n"+
                "Numero da conta= " + nConta+"\n"+
                "Tipo de Conta= " + accType+"\n"+
                "Saldo disponivel= R$ " + Double.toString(saldo)+"\n";
    }


    static int getContasCadastradas() {
        return contasCadastradas;
    }


    static String getStatusOfSystem(){

        int counterPoupanca=0,counterEspecial=0,counterSimples=0,counterDevedores=0;
        double totalMoneyPoupanca=0;
        double totalMoneyEspecial=0;
        double totalMoneySimples=0;
        double totalDividasEspecial=0;

        for(int i=0;i<Conta.getContasCadastradas();i++){
            if(contas[i] instanceof ContaSimples) {
                totalMoneySimples+= contas[i].getSaldo();
                counterSimples++;
            }
            if(contas[i] instanceof ContaPoupanca) {
                totalMoneyPoupanca+=contas[i].getSaldo();
                counterPoupanca++;
            }
            if(contas[i] instanceof ContaEspecial) {
                counterEspecial++;
                if(contas[i].getSaldo()<0){
                    totalDividasEspecial += contas[i].getSaldo();
                    counterDevedores++;
                }else {
                    totalMoneyEspecial += contas[i].getSaldo();
                }
            }

        }

        return
                "TOTAL DE CONTAS CADASTRADAS= "+Integer.toString(Conta.getContasCadastradas())+"\n"+

                        ">>CONTAS SIMPLES CADASTRADAS= " + Integer.toString(counterSimples)+"\n"+
                        "\t|-QUANTIA TOTAL APLICADA NO BANCO= R$ "+Double.toString(totalMoneySimples)+"\n\n"+
                        ">>CONTAS POUPANCA CADASTRADAS = "+Integer.toString(counterPoupanca)+"\n"+
                        "\t|-QUANTIA TOTAL APLICADA NO BANCO= R$ "+Double.toString(totalMoneyPoupanca)+"\n\n"+
                        ">>CONTAS ESPECIAL CADASTRADAS = "+Integer.toString(counterEspecial)+"\n"+
                        "\t|-QUANTIA TOTAL APLICADA NO BANCO= R$ "+Double.toString(totalMoneyEspecial)+"\n\n"+
                        "TOTAL APLICADO NO BANCO= R$ "+Double.toString(totalMoneyEspecial+totalMoneyPoupanca+totalMoneySimples)+"\n\n"+
                        ">>CONTAS ESPECIAIS DEVEDORAS= R$ "+Integer.toString(counterDevedores)+"\n"+
                        "\t|-QUANTIA TOTAL DE DIVIDAS= "+Double.toString(totalDividasEspecial);




    }

}
