/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;

import javafx.scene.control.PasswordField;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;


class SistemaBancario extends JFrame {


    private final Conta[] contas = new Conta[Gerente.MAX_CONTAS];//array de Objects Conta
    private final Scanner input = new Scanner(System.in);//abertura do scanner

    private String optionString;//String para pegar as opcoes
    private int option;//que sera convertida pra int

    //Buttons do primeiro menu, Home
    private JButton buttonGerente;
    private JButton buttonCliente;
    private JButton buttonExit;

    private JButton buttonBack = new JButton("Voltar");//Button de uso geral, volta um menu
    private JButton buttonOK = new JButton("OK");//Button de uso geral, confirma

    //Buttons do menuGerente
    private JButton buttonCriarConta;
    private JButton buttonInfoConta;
    private JButton buttonInfoAll;
    private JButton buttonRender;
    private JButton buttonJuros;

    //TextFields Gerente
    private JTextField contaFieldGerente;
    private JTextField nameFieldGerente;
    private JTextField valueFieldGerente;

    //TextFields do Login, e dashboard
    private JTextField contaFieldCliente;
    private JPasswordField passwordField,passwordFieldConfirm;
    private JTextField valueFieldCliente;

    //Buttons do dashboard do Cliente
    private JButton buttonSacar;
    private JButton buttonDepositar;
    private JButton buttonMyAcc;
    private JButton buttonAlterSenha;

    private JPanel panel;

    SistemaBancario(){
        super("BANCO PARADIGMAS");

        setLayout(new FlowLayout());
        panel = new JPanel(new GridLayout(10,10));
        setContentPane(panel);
        JLabel label = new JLabel("Escolha uma opçao");
        add(label);

    }


    //Menu Home
    void menuHome(){
        buttonGerente = new JButton("Gerente");
        panel.add(buttonGerente);

        buttonCliente = new JButton("Cliente");
        panel.add(buttonCliente);

        buttonExit = new JButton("Sair");
        panel.add(buttonExit);

        panel.updateUI();
        HomeButtonListener HomeListener = new HomeButtonListener();
        buttonExit.addActionListener(HomeListener);
        buttonGerente.addActionListener(HomeListener);
        buttonCliente.addActionListener(HomeListener);
    }


    //Menu de login
    private void menuCliente() {
        String confKey;
        System.out.print("\n>-Cliente-<\n");
        System.out.print("\nDigite o numero da conta\n>>>");
        String nConta = input.next();
        System.out.print("\nDigite a senha\n>>>");
        String key = input.next();
        int i=Gerente.contaExiste(contas,nConta);
        if (i != -1 && contas[i].verificaSenha(key)) {
            if(contas[i].verificaSenha("0000")){
                do {
                    System.out.print("!!SENHA PADRAO DETECTADA!!\nPOR FAVOR DEFINA UMA SENHA MAIS SEGURA\nDIGITE >>>");
                    key = input.next();
                    System.out.print("REPITA A SENHA\n>>>");
                    confKey = input.next();
                    if(!key.equals(confKey)){
                        System.out.print("!!SENHAS NAO CONFEREM!!");
                    }else{
                        break;
                    }
                }while (true);

                menuDashboard(i);
            }
        } else {
            System.out.print("\n!!CONTA OU SENHA INCORRETA!!\n");
        }

    }

    //Submenu de cliente
    private void menuDashboard(int i){
        while(true) {
            System.out.print("\nOlá, " + contas[i].getNomeCorrentista() + "\n\n");
            System.out.print("1 - Sacar\n");
            System.out.print("2 - Depositar\n");
            System.out.print("3 - Minha conta\n");
            System.out.print("4 - Alterar minha senha\n");
            System.out.print("0 - Sair\n");
            optionString = input.next();
            try {
                option = Integer.parseInt(optionString);
            } catch (Exception e) {
                option = -1;
            }
            switch (option) {
                case 1://Sacar
                    System.out.print("\nDigite valor\n>>>");
                    double valor = input.nextDouble();
                    System.out.print(contas[i].sacar(valor) ? "\n!!!SUCESSO!!!\n" : "\n!!ERRO!!\n");
                    break;
                case 2://Depositar
                    System.out.print("\nDigite valor\n>>>");
                    valor = input.nextDouble();
                    System.out.print(contas[i].depositar(valor) ? "\n!!!SUCESSO!!!\n" : "\n!!ERRO!!\n");
                    break;
                case 3://Minha conta
                    infoConta(contas[i].getnConta());
                    break;
                case 4:
                    System.out.print("\nDigite sua senha atual\n>>>");
                    String key = input.next();
                    System.out.print("\nDigite sua senha nova\n>>>");
                    String key2 = input.next();
                    System.out.print(contas[i].alteraSenha(key, key2) ? "\n!!!SUCESSO!!!\n" : "\n!!ERRO!!\n");
                    break;
                case 0:
                    return;

                default:
                    System.out.print("\n!!OPCAO INVALIDA!!\n");
                    break;
            }
        }
    }
    //Submenus da conta
    private void menuSacar(){
        //TODO fazer UI de saque
    }
    private void menuDepositar(){
        //TODO fazer UI de deposito
    }
    private void menuMinhaConta(){
        //TODO fazer UI de alterar senha
    }
    private void menuAlteraSenha(){
        //TODO fazer UI de Altera senha
    }


    //Menu de gerencia
    private void menuGerente() {

        boolean ok=true;

        GerenteButtonListener GerenteListener = new GerenteButtonListener();
        buttonCriarConta.addActionListener(GerenteListener);
        buttonInfoConta.addActionListener(GerenteListener);
        buttonRender.addActionListener(GerenteListener);
        buttonJuros.addActionListener(GerenteListener);
        buttonInfoAll.addActionListener(GerenteListener);
        buttonBack.addActionListener(GerenteListener);

        switch (option) {
            case 1:
                System.out.print("\n+:Criar conta:+\n");
                System.out.print("Digite o nome do futuro correntista\n>>>");
                String nCorr = input.next();
                System.out.print("\nDigite o numero da futura conta, digite \"rand\" para gerar automaticamente\n>>>");
                String nConta = input.next();

                if (nConta.equalsIgnoreCase("rand")) {
                    nConta = Gerente.gerarConta();
                }else if(Gerente.contaExiste(contas,nConta)!=-1){
                    System.out.print("CONTA EXISTENTE DETECTADA, DESEJA CONTINUAR?(S/N)" +
                            "\n*OUTRO NUMERO DE CONTA SERA GERADO*\n");
                    String answer =input.next();
                    if(answer.equalsIgnoreCase("S")) {
                        nConta=Gerente.gerarConta();
                    }else
                        break;
                }
                do {
                    System.out.print("Escolha o tipo de conta\n\n");
                    System.out.print("1 - Conta Simples\n");
                    System.out.print("2 - Conta Poupanca\n");
                    System.out.print("3 - Conta Especial\n");
                    optionString = input.next();
                    try {
                        option = Integer.parseInt(optionString);
                    } catch (Exception e) {
                        System.out.print("\n!!OPCAO INVALIDA!!\n");
                        ok = false;
                    }
                } while(!ok);
                if(Gerente.criarConta(Conta.contas,nCorr,nConta,Gerente.ACC_SIMPLE,0)){
                    System.out.printf("\nCONTA No:%s CRIADA\n",nConta);
                }else{
                    System.out.print("\n!!ERRO - MAXIMO DE CONTAS ATINGIDO!!\n");
                }
                break;

            case 2://info de 1 conta
                System.out.print("\n-:Informaçoes sobre conta:-\n");
                System.out.print("Digite o numero da conta\n>>>");
                nConta = input.next();
                infoConta(nConta);
                break;

            case 3://render poupanca
                System.out.print("\n>-Efetuar rendimentos de poupanca-<\n\nConfirma?(S/N)\n>>>");
                String answer = input.next();

                if(answer.equalsIgnoreCase("S")) {

                    System.out.printf("\nRendimento efetuado em %d contas poupancas\n",Gerente.incrementarJuros(contas));
                }
                break;
            case 4://Cobrança de juros
                System.out.print("\n=+Cobrança de juros cheque especial+=\n");
                System.out.print("\nDigite a taxa de juros a ser cobrada em %\n>>>");
                double taxaJuros = input.nextDouble();
                System.out.print("\nConfirma?(S/N)\n>>>");
                answer =input.next();
                if(answer.equalsIgnoreCase("S")){
                    System.out.printf("Cobranca efetuada em %d contas especiais",Gerente.cobrarJuros(contas,taxaJuros));
                }

                break;

            case 5://imprimir info de todas as contas
                System.out.print("\n-:Informaçoes sobre todas as contas:-\n");
                if(Conta.getContasCadastradas()==0) break;
                for (int i = 0; i < Conta.getContasCadastradas(); i++)
                    if(contas[i]!=null)
                        contas[i].info();
                break;
            case -99:
                statusOfSystem();
                break;
            case 0:
                return;
            default:
                System.out.print("\n!!OPCAO INVALIDA!!\n");
                break;
        }


    }

    //Submenus gerencia
    private void menuCriarConta(){
        //TODO fazer UI de criação de contas

    }
    private void menuInfoConta(){
        //TODO fazer UI de Info de contas


    }
    private void menuJuros(){
        //TODO fazer UI de cobranca de juros
    }
    private void menuRender(){
        //TODO fazer UI de rendimento de poupanca
    }
    private void menuInfoAll(){
        //TODO fazer UI de info de todas as contas
    }

//Listeners
    private class HomeButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            panel.remove(buttonGerente);
            panel.remove(buttonCliente);
            panel.remove(buttonExit);

            if(e.getSource() == buttonExit){
                System.exit(0);
            }else if(e.getSource() == buttonCliente){
                menuCliente();
            }else if (e.getSource() == buttonGerente){

                buttonCriarConta = new JButton("Criar conta");
                panel.add(buttonCriarConta);

                buttonInfoConta = new JButton("Informações de conta");
                panel.add(buttonInfoConta);

                buttonRender = new JButton("Efetuar rendimentos");
                panel.add(buttonRender);

                buttonJuros = new JButton("Cobrar juros");
                panel.add(buttonJuros);

                buttonInfoAll = new JButton("Informações de todas as contas");
                panel.add(buttonInfoAll);

                panel.add(buttonBack);

                panel.updateUI();
                menuGerente();
            }

        }
    }

    private class DashboardButtonListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            panel.remove(buttonSacar);
            panel.remove(buttonDepositar);
            panel.remove(buttonAlterSenha);
            panel.remove(buttonMyAcc);
            panel.remove(buttonBack);
            if (e.getSource() == buttonSacar){
                menuSacar();
            }else if(e.getSource() == buttonDepositar){
                menuDepositar();
            }else if(e.getSource() == buttonAlterSenha){
                menuAlteraSenha();
            }else if(e.getSource() == buttonMyAcc){
                menuMinhaConta();
            }else if (e.getSource() == buttonBack){
                menuHome();
            }
        }
    }

    private class GerenteButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            panel.remove(buttonBack);
            panel.remove(buttonInfoAll);
            panel.remove(buttonJuros);
            panel.remove(buttonRender);
            panel.remove(buttonCriarConta);
            panel.remove(buttonInfoConta);

            if(e.getSource() == buttonCriarConta){
                menuCriarConta();
            }else if(e.getSource() ==buttonInfoConta){
                menuInfoConta();
            }else if(e.getSource() == buttonRender){
                menuRender();
            }else if(e.getSource() == buttonJuros){
                menuJuros();
            }else if(e.getSource() == buttonInfoAll){
                menuInfoAll();
            }else if (e.getSource() == buttonBack){
                menuHome();
            }
        }
    }






    private void infoConta(String conta) {

        if(Conta.getContasCadastradas()==0) return;
        for (int i = 0; i < Conta.getContasCadastradas(); i++) {
            if (contas[i].getnConta().equals(conta)) {
                contas[i].info();
            }
        }
    }

    private void statusOfSystem(){

        int counterPoupanca=0,counterEspecial=0,counterSimples=0,counterDevedores=0;
        double totalMoneyPoupanca=0;
        double totalMoneyEspecial=0;
        double totalMoneySimples=0;
        double totalDividasEspecial=0;

        for(int i=0;i<Conta.getContasCadastradas();i++){
            if(contas[i] instanceof ContaSimples) {
                totalMoneySimples+=contas[i].getSaldo();
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

        System.out.print("\nSTATUS DO SISTEMA\n");

        System.out.printf("\nTOTAL DE CONTAS CADASTRADAS\t= %d\n",Conta.getContasCadastradas());

        System.out.printf("\nCONTAS SIMPLES CADASTRADAS = %d\n",counterSimples);
        System.out.printf("\t|-QUANTIA TOTAL APLICADA NO BANCO\t=\t%.2f\n",totalMoneySimples);

        System.out.printf("\nCONTAS POUPANCA CADASTRADAS = %d\n",counterPoupanca);
        System.out.printf("\t|-QUANTIA TOTAL APLICADA NO BANCO\t=\t%.2f\n",totalMoneyPoupanca);

        System.out.printf("\nCONTAS ESPECIAL CADASTRADAS = %d\n",counterEspecial);
        System.out.printf("\t|-QUANTIA TOTAL APLICADA NO BANCO\t=\t%.2f\n",totalMoneyEspecial);

        System.out.print("\t                                   \t--------\n");
        System.out.printf("\t          TOTAL APLICADO NO BANCO\t=\t%.2f\n",totalMoneyEspecial+totalMoneyPoupanca+totalMoneySimples);


        System.out.printf("\nCONTAS ESPECIAIS DEVEDORAS = %d\n",counterDevedores);
        System.out.printf("\t|-QUANTIA TOTAL DE DIVIDAS COM O BANCO\t=\t%.2f\n",totalDividasEspecial);




    }


}