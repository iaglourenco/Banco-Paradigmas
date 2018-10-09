/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;

public class ContaEspecial extends Conta   {

    private double limite;
    private final double limitePadrao=limite;



    ContaEspecial(String nomeCorrentista,String nConta,double limite){
        super(nomeCorrentista,nConta);
        this.limite = limite;
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
            limite=limite+super.getSaldo();//que sera refletido no limite
            return true;
        }

        return super.sacar(valor);//carteira cheia

    }

    @Override
    public boolean depositar(double valor) {

        if(valor<=0) return false;

        if(super.getSaldo()<0){//hora de pagar as dividas

            double limAnt =getLimitePadrao();//acho o limite que o cara tem

            limite+=valor;//restauro primeiro o limite
            super.depositar(valor);//como o saldo ta mais baixo que um anao e ,(-saldo) + valor = sua grana,
                                                                                // no final da tudo certo

            if(limite>limAnt){//pro limite nao ficar nas alturas, restauro ao padrao
                limite = limAnt;
            }

            return true;//retorno a true da true
        }



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
