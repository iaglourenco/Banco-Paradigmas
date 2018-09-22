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

import java.util.Scanner;

class Programa {

    private static final int MAX_CONTAS = 1000 ;

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        String key,key2,optionString,nConta,nCorr;
        int option,contasCadastradas=0;
        double valor,limite;
        boolean bool=false;
        Conta[] contas = new Conta[MAX_CONTAS];

        do{
            System.out.print("\n.:BANCO:.\n\n");
            System.out.print("1 - Gerente\n");
            System.out.print("2 - Cliente\n");
            System.out.print("0 - Sair\n>>>");
            optionString = input.next();
            try{
                option = Integer.parseInt(optionString);
            }catch(Exception e) {
                option = -1;
            }
            switch (option){
                case 1://Gerente
                    do{
                        System.out.printf("\n<:Gerente:>\n\t\t\tContas Cadastradas = %d/%d\n",contasCadastradas,MAX_CONTAS);
                        System.out.print("\n1 - Criar conta\n");
                        System.out.print("2 - Info de contas\n");
                        System.out.print("0 - Voltar\n>>>");
                        optionString=input.next();
                        try{
                            option = Integer.parseInt(optionString);
                        }catch(Exception e) {
                            option = -1;
                        }
                        switch (option){
                            case 1:
                                if(contasCadastradas>=MAX_CONTAS){
                                    System.out.print("!!ERRO - MAXIMO DE CONTAS ATINGIDO!!!");
                                    break;
                                }
                                System.out.print("\n+:Criar conta:+\n");
                                System.out.print("Digite o nome do futuro correntista\n>>>");
                                nCorr=input.next();
                                System.out.print("\nDigite o numero da futura conta\n>>>");
                                nConta=input.next();
                                do {
                                    System.out.print("Escolha o tipo de conta\n\n");
                                    System.out.print("1 - Conta Simples\n");
                                    System.out.print("2 - Conta Poupanca\n");
                                    System.out.print("3 - Conta Especial\n");
                                    optionString = input.next();
                                    try{
                                        option = Integer.parseInt(optionString);
                                    }catch(Exception e) {
                                        option = -1;
                                    }
                                    switch (option) {

                                        case 1:
                                            contas[contasCadastradas++] = new ContaSimples(nCorr, nConta);
                                            bool=true;
                                            break;
                                        case 2:
                                            contas[contasCadastradas++] = new ContaPoupanca(nCorr, nConta);
                                            bool=true;
                                            break;
                                        case 3:
                                            System.out.print("\nDigite o limite\n>>>");
                                            limite = input.nextDouble();
                                            contas[contasCadastradas++] = new ContaEspecial(nCorr, nConta, limite);
                                            bool=true;
                                            break;

                                        default:
                                            System.out.print("\n!!OPCAO INVALIDA!!\n");
                                            break;

                                    }
                                } while (!bool);
                                break;

                            case 2:
                                System.out.print("\n-:Informaçoes sobre conta:-\n");
                                System.out.print("Digite o numero da conta\n>>>");
                                nConta = input.next();
                                for(int i=0;i<MAX_CONTAS;i++){
                                    if(contas[i].getnConta().equals(nConta)){
                                        contas[i].info();
                                        break;
                                    }
                                }
                                break;
                            case 0:
                                break;
                            default:
                                System.out.print("\n!!OPCAO INVALIDA!!\n");
                                break;
                        }

                    }while(option!=0);
                    break;
                case 2://Cliente
                    System.out.print("\n>-Cliente-<\n");
                    System.out.print("\nDigite o numero da conta\n>>>");
                    nConta=input.next();
                    System.out.print("\nDigite a senha\n>>>");
                    key=input.next();
                    for(int i=0;i<contasCadastradas;i++){
                        if(contas[i].getnConta().equals(nConta) && contas[i].verificaSenha(key)){
                            do {
                                System.out.print("\nOlá, " + contas[i].getNomeCorrentista() + "\n\n");
                                System.out.print("1 - Sacar\n");
                                System.out.print("2 - Depositar\n");
                                System.out.print("3 - Minha conta\n");
                                System.out.print("4 - Alterar minha senha\n");
                                System.out.print("0 - Sair\n");
                                optionString = input.next();
                                try{
                                    option = Integer.parseInt(optionString);
                                }catch(Exception e) {
                                    option = -1;
                                }
                                switch (option) {
                                    case 1://Sacar
                                        System.out.print("\nDigite valor\n>>>");
                                        valor = input.nextDouble();
                                        System.out.print(contas[i].sacar(valor) ? "\n!!!SUCESSO!!!\n" : "\n!!ERRO!!\n");
                                        break;
                                    case 2://Depositar
                                        System.out.print("\nDigite valor\n>>>");
                                        valor = input.nextDouble();
                                        System.out.print(contas[i].depositar(valor) ? "\n!!!SUCESSO!!!\n" : "\n!!ERRO!!\n");
                                        break;
                                    case 3://Minha conta
                                        contas[i].info();
                                        break;
                                    case 4:
                                        System.out.print("\nDigite sua senha atual\n>>>");
                                        key = input.next();
                                        System.out.print("\nDigite sua senha nova\n>>>");
                                        key2=input.next();
                                        System.out.print(contas[i].alteraSenha(key, key2) ? "\n!!!SUCESSO!!!\n" : "\n!!ERRO!!\n");
                                        break;
                                    case 0:
                                        option=0;
                                        break;


                                    default:
                                        System.out.print("\n!!OPCAO INVALIDA!!\n");
                                        break;
                                }
                            }while (option!=0);

                        }

                    }
                    break;
                case 0://Sair
                    System.exit(0);
                    break;
                default:
                    System.out.print("\n!!OPCAO INVALIDA!!\n");
                    break;


            }



        }while(true);












    }

}
