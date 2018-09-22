/*
 * MIT License
 *
 * Copyright (c) 2018 Iago Lourenço
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
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
