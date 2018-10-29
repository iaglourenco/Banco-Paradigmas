/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;


import com.iaglourenco.exceptions.CampoVazioException;
import com.iaglourenco.exceptions.ContaExistenteException;
import com.iaglourenco.exceptions.ContaInexistenteException;
import com.iaglourenco.exceptions.MaxContasException;


class Gerente {

    static final int MAX_CONTAS = 1000;//define o maximo de contas
    static final int ACC_SPECIAL = 1;
    static final int ACC_POUPANCA = 2;
    static final int ACC_SIMPLE = 3;


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
    static Conta contaExiste(String numeroConta) throws ContaInexistenteException{

        for(int i=0;i<MAX_CONTAS;i++){
            if (Conta.contas[i] != null) {
                if (Conta.contas[i].getnConta().equals(numeroConta)) {
                    return Conta.contas[i];//retorna a conta
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
    static void criarConta(String nameCorr, String nConta, char[] password, int accType, double valor)throws MaxContasException,ContaExistenteException,CampoVazioException {

        try {
            if(contaExiste(nConta)!=null){
                throw new ContaExistenteException("CONTA EXISTENTE");
            }
        } catch (ContaInexistenteException e) {

                if (accType == ACC_POUPANCA)
                    Conta.contas[Conta.getContasCadastradas()] = new ContaPoupanca(nameCorr, nConta, valor);

                if (accType == ACC_SPECIAL)
                    Conta.contas[Conta.getContasCadastradas()] = new ContaEspecial(nameCorr, nConta, valor);

                if (accType == ACC_SIMPLE)
                    Conta.contas[Conta.getContasCadastradas()] = new ContaSimples(nameCorr, nConta);

                contaExiste(nConta).alteraSenha(Conta.KEY_PADRAO,password);
        }

    }


    /**
     * Incrementa o saldo em contas poupança, baseada na taxa de rendimento de cada conta, e adiciona uma linha ao extrato de cada conta.
     * @return a quantidade de contas poupancas que foram efetuados rendimentos
     */
    static int incrementarJuros() {

        int counter=0;
        for(int i=0;i<MAX_CONTAS;i++){
            if(Conta.contas[i] instanceof ContaPoupanca && Conta.contas[i].getSaldo()>0){
                ((ContaPoupanca) Conta.contas[i]).render();
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
    static int cobrarJuros(double taxaJuros) {

        int counter=0;
        for(int i=0;i<MAX_CONTAS;i++){

            if(Conta.contas[i] instanceof ContaEspecial && Conta.contas[i].getSaldo()<0){
                ((ContaEspecial) Conta.contas[i]).juros(taxaJuros);
                counter++;
            }
        }
        return counter;

    }




}
