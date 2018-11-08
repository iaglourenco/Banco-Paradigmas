/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;

import com.iaglourenco.exceptions.*;

class Gerente {

    static final int MAX_CONTAS = 1000;//define o maximo de contas
    static final int ACC_SPECIAL = 1;
    static final int ACC_POUPANCA = 2;
    static final int ACC_SIMPLE = 3;
    final Conta[] contas = new Conta[Gerente.MAX_CONTAS];//array de Objects Conta
    private int contasCadastradas=0;
    private static Gerente instance;

    
    public static synchronized Gerente getInstance() {
    	if(instance ==null)
    		instance = new Gerente();
    	return instance;
    }

    String getAllInfo() {
    	
    	String allInfo="Quantidade de contas cadastradas= " + Integer.toString(getContasCadastradas())+"\n\n";
    	for (int i = 0; i < Gerente.MAX_CONTAS; i++) {
            if (contas[i] != null)
               allInfo+=contas[i].info() + "-------\n\n";
        }
    	return allInfo;
    }
    
    
    boolean sacar(String nConta,double valor) {
    	
    	Conta acc = contaExiste(nConta);
    	return acc.sacar(valor);    	
    }
   
    boolean depositar(String nConta,double valor) {
    	
    	Conta acc = contaExiste(nConta);
    	return acc.depositar(valor);    	
    }
    
    
    
    /**
     * Gera um numero de conta entre 1000 e 9999.
     * @return Retorna um numero aleatório entre 1000 e 9999
     */
    static String gerarConta(){
        return Integer.toString((int)(1000 + Math.random() * 9999));
    }

    /**
     * Verifica a existencia de uma conta no array
     * @param numeroConta O número da conta a ser procurada.
     * @return O objeto conta
     * @throws ContaInexistenteException Quando a conta requisitada não existe.
     */
    Conta contaExiste(String numeroConta) throws ContaInexistenteException{

        for(int i=0;i<MAX_CONTAS;i++){
            if (contas[i] != null) {
                if (contas[i].getnConta().equals(numeroConta)) {
                    return contas[i];//retorna a conta
                }
            }
        }
        throw new ContaInexistenteException("CONTA INEXISTENTE");
    }


    /**
     * Cria a conta baseado no <tt>accType</tt>
     *
     * @param nameCorr O nome do correntista
     * @param nConta O numero da conta
     * @param password A senha
     * @param accType O tipo de conta, use ACC_SIMPLE=3,ACC_SPECIAL=1 ou ACC_POUPANCA=2.
     * @param valor valor usado por ACC_SPECIAL e ACC_POUPANCA,sera ignorado caso seja ACC_SIMPLE
     * @throws MaxContasException Quando o maximo de conta foi atingido
     * @throws ContaExistenteException Quando a conta ja existe
     * @throws CampoVazioException se algum parametro for null
     */
    void criarConta(String nameCorr, String nConta, char[] password, int accType, double valor)throws MaxContasException,ContaExistenteException,CampoVazioException {

        try {
            if(contaExiste(nConta)!=null){
                throw new ContaExistenteException("CONTA EXISTENTE");
            }
        } catch (ContaInexistenteException e) {

                if (accType == ACC_POUPANCA)
                    contas[contasCadastradas] = new ContaPoupanca(nameCorr, nConta, valor);

                if (accType == ACC_SPECIAL)
                    contas[contasCadastradas] = new ContaEspecial(nameCorr, nConta, valor);

                if (accType == ACC_SIMPLE)
                    contas[contasCadastradas] = new ContaSimples(nameCorr, nConta);

                contaExiste(nConta).alteraSenha("0000".toCharArray(),password);
                contasCadastradas++;
        }

    }


    /**
     * Incrementa o saldo em contas poupança, baseada na taxa de rendimento de cada conta, e adiciona uma linha ao extrato de cada conta.
     * @return a quantidade de contas poupancas que foram efetuados rendimentos
     */
    int incrementarJuros() {

        int counter=0;
        for(int i=0;i<MAX_CONTAS;i++){
            if(contas[i] instanceof ContaPoupanca && contas[i].getSaldo()>0){
                ((ContaPoupanca) contas[i]).render();
                counter++;
            }
        }
        return counter;
    }

    /**
     * Dado a taxa de juros, cobra juros de todas as contas especiais negativadas, e adiciona uma linha ao extrato.
     * @param taxaJuros a taxa que sera usada para cobrança em %
     * @return a quantidade de contas especias em que foram cobrados juros
     */
    int cobrarJuros(double taxaJuros) {

        int counter=0;
        for(int i=0;i<MAX_CONTAS;i++){

            if(contas[i] instanceof ContaEspecial && contas[i].getSaldo()<0){
                ((ContaEspecial) contas[i]).juros(taxaJuros);
                counter++;
            }
        }
        return counter;

    }

    
    int getContasCadastradas() {
    	return contasCadastradas;
    }
    
    /**
     * Gera uma <tt>String</tt>, com todas as informaçoes do sistema,
     * como: quantidade de contas cadastradas, total de dinheiro depositado para cada tipo de conta,numero de contas negativadas e o valor.
     *
     * @return a String com as informaçoes
     */
     String getStatusOfSystem(){

        int counterPoupanca=0,counterEspecial=0,counterSimples=0,counterDevedores=0;
        double totalMoneyPoupanca=0;
        double totalMoneyEspecial=0;
        double totalMoneySimples=0;
        double totalDividasEspecial=0;

        for(int i=0;i<contasCadastradas;i++){
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
                "TOTAL DE CONTAS CADASTRADAS= "+Integer.toString(contasCadastradas)+"\n"+

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
