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
            limite+=super.getSaldo();//que sera refletido no limite
            return true;
        }

        return super.sacar(valor);//carteira cheia

    }

    @Override
    public boolean depositar(double valor) {

        if(valor<=0) return false;

        if(super.getSaldo()<0){//hora de pagar as dividas

            double limAnt = limite-(super.getSaldo());//acho o limite que o cara tem

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
}
