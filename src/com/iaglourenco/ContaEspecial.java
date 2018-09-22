/*
 * Copyright (c) Iago Lourenço. Este código foi feito por mim, a não ser que "mim" tenha deixado.
 * vossa senhoria não tem permissão para usa-lo seja para o que for.
 * salvo se sua vida estiver em risco. Ahoy!
 */

package com.iaglourenco;

public class ContaEspecial extends Conta   {

    private double limite;



    ContaEspecial(String nomeCorrentista,String nConta,double limite){
        super(nomeCorrentista,nConta);
        this.limite =limite;
    }


    @Override
    public void info() {
        super.info();
        System.out.printf("Limite disponivel: %.2f\n",limite);
        System.out.printf("Saldo disponivel + Limite: %.2f\n",super.getSaldo()>=0 ? super.getSaldo()+limite : limite);
    }

    @Override
    public boolean sacar(double valor) {

        if(valor>super.getSaldo()+limite)
            return false;

        if(valor<=0) return false;


        if(super.getSaldo()<=valor){

            super.sacar(valor);//saldo tera um valor negativo
            limite+=super.getSaldo();
            return true;
        }

        return super.sacar(valor);

    }

    @Override
    public boolean depositar(double valor) {

        if(valor<=0) return false;

        if(super.getSaldo()<0){

            double limAnt = limite-(super.getSaldo());

            limite+=valor;
            super.depositar(valor);

            if(limite>limAnt){
                limite = limAnt;
            }

            return true;
        }



        return super.depositar(valor);
    }
}
