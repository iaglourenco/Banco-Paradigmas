/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;

import com.iaglourenco.exceptions.SenhaIncorretaException;
import com.iaglourenco.exceptions.ValorInvalidoException;

import java.util.Arrays;

abstract class Conta {

    private String nomeCorrentista;
    private String nConta;
    private double saldo;
    private char[] senha;
    private static int contasCadastradas=0;
    static final Conta[] contas = new Conta[Gerente.MAX_CONTAS];//array de Objects Conta
    static final char[] KEY_PADRAO = "0000".toCharArray();


    Conta(String nomeCorrentista,String nConta){

        setnConta(nConta);
        setNomeCorrentista(nomeCorrentista);
        saldo=0.0;
        senha = KEY_PADRAO;
        contasCadastradas++;
    }

    /**
     * Dado senhaAntiga e uma senha nova, altera o campo <tt>senha</tt> se a <tt>senhaAntiga</tt> coincidir com a senha ja cadastrada.
     *
     * @param senhaAntiga a senha antiga
     * @param senhaNova a senha nova
     * @throws SenhaIncorretaException se a senha antiga estiver errada
     */
    void alteraSenha(char[] senhaAntiga, char[] senhaNova) throws SenhaIncorretaException {

        if (!Arrays.equals(senhaAntiga, senha)) {
            throw new SenhaIncorretaException("SENHA INCORRETA");
        } else {
            senha = Arrays.copyOf(senhaNova, senhaNova.length);
            }
    }

    /**
     * Verifica se a senha digitada corresponde a senha cadastrada.
     * @param senha a senha a ser verificada
     * @throws SenhaIncorretaException se a senha estiver errada
     */
    void verificaSenha(char[] senha)throws SenhaIncorretaException {
        if (!Arrays.equals(this.senha, senha)) {
            throw new SenhaIncorretaException("SENHA INCORRETA");
        }

    }


    /**
     * @return o nome do correntista
     */
    String getNomeCorrentista() {
        return nomeCorrentista;
    }

    private void setNomeCorrentista(String nomeCorrentista) {
        this.nomeCorrentista = nomeCorrentista;
    }

    /**
     * @return o numero da conta
     */
    String getnConta() {
        return nConta;
    }

    private void setnConta(String nConta) {
        this.nConta = nConta;
    }

    /**
     * @return o saldo atual
     */
    double getSaldo() {
        return saldo;
    }

    /**
     * Se o valor for acima de 0, decrementa esse valor do <tt>saldo</tt>
     *
     * @param valor o valor a ser sacado
     * @return <tt>true</tt> caso o saque foi efetuado <tt>throw</tt>,caso contrario.
     * @throws ValorInvalidoException caso se tenha digitado um valor abaixo de 0
     */
    public boolean sacar(double valor)throws ValorInvalidoException {

        if (valor>0) {

            this.saldo-=valor;
            return true;
        }
        throw new ValorInvalidoException("VALOR INVALIDO");
    }

    /**
     * Se valor for acima e diferente de 0, incrementa o saldo com esse valor
     *
     * @param valor valor a ser depositado
     * @return  <tt>true</tt> caso o deposito seja efetuado,<tt>false</tt>,caso contrario.
     */
    public boolean depositar(double valor)throws ValorInvalidoException{

        if(valor<=0) { // depositar negativo tem outro nome, e nao vale depositar nada
            throw new ValorInvalidoException("VALOR INVALIDO");
        }

        this.saldo+=valor;
        return true;
    }

    /**
     * Gera uma <tt>String</tt> com as informações da conta
     * @return a String com as informaçoes
     */
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

    abstract String getLog();

    static int getContasCadastradas() {
        return contasCadastradas;
    }

    /**
     * Gera uma <tt>String</tt>, com todas as informaçoes do sistema,
     * como: quantidade de contas cadastradas, total de dinheiro depositado para cada tipo de conta,numero de contas negativadas e o valor.
     *
     * @return a String com as informaçoes
     */
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
                        "   |-QUANTIA TOTAL APLICADA NO BANCO= R$ "+Double.toString(totalMoneySimples)+"\n\n"+
                        ">>CONTAS POUPANCA CADASTRADAS = "+Integer.toString(counterPoupanca)+"\n"+
                        "   |-QUANTIA TOTAL APLICADA NO BANCO= R$ "+Double.toString(totalMoneyPoupanca)+"\n\n"+
                        ">>CONTAS ESPECIAL CADASTRADAS = "+Integer.toString(counterEspecial)+"\n"+
                        "   |-QUANTIA TOTAL APLICADA NO BANCO= R$ "+Double.toString(totalMoneyEspecial)+"\n\n"+
                        "TOTAL APLICADO NO BANCO= R$ "+Double.toString(totalMoneyEspecial+totalMoneyPoupanca+totalMoneySimples)+"\n\n"+
                        ">>CONTAS ESPECIAIS DEVEDORAS= R$ "+Integer.toString(counterDevedores)+"\n"+
                        "   |-QUANTIA TOTAL DE DIVIDAS= "+Double.toString(totalDividasEspecial);




    }

}
