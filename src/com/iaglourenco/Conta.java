/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;

abstract class Conta {

    private String nomeCorrentista;
    private String nConta;
    private double saldo;
    private String senha;
    private static int contasCadastradas=0;
    static Conta[] contas = new Conta[Gerente.MAX_CONTAS];//array de Objects Conta



    Conta(String nomeCorrentista,String nConta){

        setnConta(nConta);
        setNomeCorrentista(nomeCorrentista);
        saldo=0.0;
        senha="0000";
        contasCadastradas++;
    }

    boolean alteraSenha(String senhaAntiga, String senhaNova){

        if(senhaAntiga.equals(senha)){
            this.senha = senhaNova;
            return true;
        }
        return false;


    }

    boolean verificaSenha(String senha){ return this.senha.equals(senha);}

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

    public void info(){

        String accType = getClass().getName()
                .replace("com.iaglourenco.","")
                .replace("ContaSimples","Conta Simples")
                .replace("ContaPoupanca","Conta Poupanca")
                .replace("ContaEspecial","Conta Especial");

        System.out.print("\n\nNome do individuo: "+nomeCorrentista+"\n");
        System.out.print("Conta do individuo: "+nConta+"\n");
        System.out.print("Tipo de conta: "+accType+"\n");
        System.out.printf("Saldo disponivel: %.2f\n",saldo);

    }

    public static int getContasCadastradas() {
        return contasCadastradas;
    }
}
