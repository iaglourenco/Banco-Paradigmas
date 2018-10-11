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


    static String gerarConta(){
        return Integer.toString((int)(1000 + Math.random() * 9999));
    }

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


    static void criarConta(String nameCorr, String nConta, char[] password, int accType, double valor)throws MaxContasException,ContaExistenteException,CampoVazioException {

        try {
            contaExiste(nConta);
        } catch (ContaExistenteException e){
            throw new ContaExistenteException("CONTA EXISTENTE");
            }


        catch (ContaInexistenteException e) {

                if (accType == ACC_POUPANCA)
                    Conta.contas[Conta.getContasCadastradas()] = new ContaPoupanca(nameCorr, nConta, valor);

                if (accType == ACC_SPECIAL)
                    Conta.contas[Conta.getContasCadastradas()] = new ContaEspecial(nameCorr, nConta, valor);

                if (accType == ACC_SIMPLE)
                    Conta.contas[Conta.getContasCadastradas()] = new ContaSimples(nameCorr, nConta);

                contaExiste(nConta).alteraSenha(Conta.KEY_PADRAO,password);
        }

    }


    static int incrementarJuros() {

        int counter=0;
        for(int i=0;i<MAX_CONTAS;i++){
            if(Conta.contas[i] instanceof ContaPoupanca){
                ((ContaPoupanca) Conta.contas[i]).render();
                counter++;
            }
        }
        return counter;
    }

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
