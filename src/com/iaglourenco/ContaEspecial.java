/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;

import com.iaglourenco.exceptions.SaldoInsuficienteException;
import com.iaglourenco.exceptions.ValorInvalidoException;

public class ContaEspecial extends Conta   {

    private double limite;
    private double limitePadrao;
    private String log;




    ContaEspecial(String nomeCorrentista,String nConta,double limite){
        super(nomeCorrentista,nConta);
        this.limite = limitePadrao = limite;
        log =" ";
    }


    @Override
    public String info() {

        return   super.info()+"Limite disponível= R$ "+ Double.toString(limite)+"\n"+
                "Saldo disponivel + Limite= R$ "+Double.toString(super.getSaldo()>=0 ? super.getSaldo()+limite : limite)+"\n";
    }

    @Override
    public boolean sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException {

        if(valor>super.getSaldo()+limite)
            throw new SaldoInsuficienteException("SALDO INSUFICIENTE");

        if(valor<=0) throw new ValorInvalidoException("VALOR INVALIDO");


        if(super.getSaldo()<valor){

            log=log+"SAQUE DE R$ "+Double.toString(valor)+"-> SALDO = R$ "+Double.toString(super.getSaldo()-valor)+"\n\n";
            super.sacar(valor);//saldo tera um valor negativo
            limite-=(Math.abs(super.getSaldo()));//tiro do limite o valor absoluto do saque que esta negativo
            return true;
        }

        log=log+"SAQUE DE R$ "+Double.toString(valor)+"-> SALDO = R$ "+Double.toString(super.getSaldo()-valor)+"\n\n";
        return super.sacar(valor);

    }
    @Override
    String getLog(){
        return this.log;
    }

    @Override
    public boolean depositar(double valor) {

        if(valor<=0) return false;

        if(super.getSaldo()<0){//hora de pagar as dividas

            double limAnt =getLimitePadrao();//acho o limite que o cara tem

            log=log+"DEPÓSITO DE R$ "+Double.toString(valor)+"-> SALDO = R$ "+Double.toString(super.getSaldo()+valor)+"\n\n";
            limite+=valor;//restauro primeiro o limite
            super.depositar(valor);//saldo eh um espelho de limite

            if(super.getSaldo()>0 || limite> limAnt){//restauro o limite ao padrao caso se tenha depositado a mais
                limite = limAnt;
            }

            return true;
        }


        log=log+"DEPÓSITO DE R$ "+Double.toString(valor)+"-> SALDO = R$ "+Double.toString(super.getSaldo()+valor)+"\n\n";

        return super.depositar(valor);
    }

    void juros(double taxaJuros){

        double juros=(super.getSaldo()*(taxaJuros/100));

        super.sacar(Math.abs(juros));

    }

    private double getLimitePadrao() {
        return limitePadrao;
    }
}
