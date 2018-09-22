/*
 * Copyright (c) Iago Lourenço. Este código foi feito por mim, a não ser que "mim" tenha deixado.
 * vossa senhoria não tem permissão para usa-lo seja para o que for.
 * salvo se sua vida estiver em risco. Ahoy!
 */

package com.iaglourenco;

class Conta {


    private String nomeCorrentista;
    private String nConta;
    private double saldo;
    private String senha;

    Conta(String nomeCorrentista,String nConta){

        setnConta(nConta);
        setNomeCorrentista(nomeCorrentista);
        saldo=0.0;
        senha="0000";

    }

    boolean alteraSenha(String senhaAntiga, String senhaNova){

        if(senhaAntiga.equals(senha)){
            this.senha = senhaNova;
            return true;
        }
        return false;


    }

    boolean verificaSenha(String senha){return this.senha.equals(senha);}

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

        if (getSaldo() > 0) {
            this.saldo-=valor;
            return true;
        }
        return false;
    }

    public boolean depositar(double valor){

        this.saldo+=valor;
        return true;
    }

    public void info(){

        String accType = getClass().getName()
                .replace("com.iaglourenco.","")
                .replace("ContaSimples","Conta Simples")
                .replace("ContaPOupanca","Conta Poupanca")
                .replace("ContaEspecial","Conta Especial");

        System.out.print("\n\nNome do individuo: "+nomeCorrentista+"\n");
        System.out.print("Conta do individuo: "+nConta+"\n");
        System.out.print("Tipo de conta: "+accType+"\n");
        System.out.printf("Saldo disponivel: %.2f\n",saldo);

    }

}
