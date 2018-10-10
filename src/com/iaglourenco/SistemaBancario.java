/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import static com.iaglourenco.Conta.contas;


@SuppressWarnings({"ALL", "unused"})
class SistemaBancario extends JFrame {


    private final String caracters ="0987654321";
    private Conta contaAtual;

    private final Scanner input = new Scanner(System.in);//abertura do scanner

    private String optionString;//String para pegar as opcoes
    private int option;//que sera convertida pra int

    //Buttons do primeiro menu, Home
    private JButton buttonGerente;
    private JButton buttonCliente;
    private JButton buttonExit;


    //Instanciaçao dos listeners
    private final HomeButtonListener HomeListener = new HomeButtonListener();
    private final GerenteButtonListener GerenteListener = new GerenteButtonListener();
    private final DashboardButtonListener DashboardListener = new DashboardButtonListener();
    private final LoginButtonListener LoginListener = new LoginButtonListener();
    private final SacarButtonListener SacarListener = new SacarButtonListener();
    private DepositarButtonListener DepositarListener = new DepositarButtonListener();
    private MyAccButtonListener MyAccListener = new MyAccButtonListener();
    private AlterSenhaButtonListener AlterSenhaListener = new AlterSenhaButtonListener();
    private final ValueKeyListener ValueListener = new ValueKeyListener();


    //Buttons do menuGerente
    private JButton buttonCriarConta;
    private JButton buttonInfoConta;
    private JButton buttonInfoAll;
    private JButton buttonRender;
    private JButton buttonJuros;


    private final JButton buttonBackGerente = new JButton("Voltar");//Button de uso geral, volta um menu
    private final JButton buttonOKGerente = new JButton("OK");//Button de uso geral, confirma

    //TextFields Gerente
    private JTextField contaFieldGerente;
    private JTextField nameFieldGerente;
    private JTextField valueFieldGerente;

    //Login
    private JTextField contaFieldCliente;
    private JPasswordField passwordField;
    private JTextField valueFieldCliente;
    private JLabel labelLogin = new JLabel("Login:");
    private final JButton buttonBackLogin = new JButton("Voltar");//Button de uso geral, volta um menu
    private final JButton buttonOKLogin = new JButton("OK");//Button de uso geral, confirma


    //Buttons do dashboard do Cliente
    private JButton buttonSacar;
    private final JButton buttonOKSacar= new JButton("OK");//Button de uso geral, confirma
    private final JButton buttonBackSacar= new JButton("Voltar");//Button de uso geral, volta um menu

    @SuppressWarnings("unused")
    private JButton buttonDepositar;
    private final JButton buttonOKDepositar= new JButton("OK");//Button de uso geral, confirma
    private final JButton buttonBackDepositar= new JButton("Voltar");//Button de uso geral, volta um menu

    private JButton buttonMyAcc;
    private final JButton buttonOKMyAcc= new JButton("OK");//Button de uso geral, confirma
    private final JButton buttonBackMyAcc= new JButton("Voltar");//Button de uso geral, volta um menu

    @SuppressWarnings("unused")
    private JButton buttonAlterSenha;
    private final JButton buttonOKAlterSenha= new JButton("OK");//Button de uso geral, confirma
    private final JButton buttonBackAlterSenha= new JButton("Voltar");//Button de uso geral, volta um menu

    private JPanel panelHome;
    @SuppressWarnings("unused")
    private JPanel panelGerente;
    @SuppressWarnings("unused")
    private JPanel panelLoginCliente;
    @SuppressWarnings("unused")
    private JPanel panelDashboard;
    private JLabel label = new JLabel("Escolha uma opção:");





    SistemaBancario(){
        super("BANCO PARADIGMAS");

        setLayout(new FlowLayout());
        panelHome = new JPanel(new GridLayout(10,10));
        setContentPane(panelHome);
        Gerente.criarConta(Conta.contas,"iago","1234",Gerente.ACC_SIMPLE,0);

    }


    //Menu Home
    void menuHome(){

        add(label);

        buttonGerente = new JButton("Gerente");
        panelHome.add(buttonGerente);

        buttonCliente = new JButton("Cliente");
        panelHome.add(buttonCliente);

        buttonExit = new JButton("Sair");
        panelHome.add(buttonExit);

        panelHome.updateUI();
        buttonExit.addActionListener(HomeListener);
        buttonGerente.addActionListener(HomeListener);
        buttonCliente.addActionListener(HomeListener);
    }


    //Menu de login
    private void menuLoginCliente() {

        add(labelLogin);
        JLabel labelContaFieldCliente = new JLabel("Digite a conta");
        contaFieldCliente = new JTextField();
        contaFieldCliente.setToolTipText("Digite aqui a sua conta");
        JLabel labelPasswordField = new JLabel("Digite a senha");
        passwordField = new JPasswordField();
        passwordField.setToolTipText("Digite aqui a sua senha");

        panelLoginCliente.add(labelContaFieldCliente);
        panelLoginCliente.add(contaFieldCliente);
        panelLoginCliente.add(labelPasswordField);
        panelLoginCliente.add(passwordField);
        panelLoginCliente.add(buttonOKLogin);
        panelLoginCliente.add(buttonBackLogin);

        contaFieldCliente.addKeyListener(ValueListener);
        buttonOKLogin.addActionListener(LoginListener);
        buttonBackLogin.addActionListener(LoginListener);
        panelLoginCliente.updateUI();

    }

    //Submenu de cliente
    @SuppressWarnings("unused")
    private void menuDashboard(){
        while(true) {
            System.out.print("\nOlá, " + contaAtual.getNomeCorrentista() + "\n\n");
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
                    System.out.print(contaAtual.sacar(valor) ? "\n!!!SUCESSO!!!\n" : "\n!!ERRO!!\n");
                    break;
                case 2://Depositar
                    System.out.print("\nDigite valor\n>>>");
                    valor = input.nextDouble();
                    System.out.print(contaAtual.depositar(valor) ? "\n!!!SUCESSO!!!\n" : "\n!!ERRO!!\n");
                    break;
                case 3://Minha conta
                    removeAllListenersAndComponentsLogin();
                    panelHome = new JPanel(new GridLayout(10,10));
                    setContentPane(panelHome);
                    menuMinhaConta();

                    break;
                case 4:
                    System.out.print("\nDigite sua senha atual\n>>>");
                    String key = input.next();
                    System.out.print("\nDigite sua senha nova\n>>>");
                    String key2 = input.next();
                    //System.out.print(contas[i].alteraSenha(key, key2) ? "\n!!!SUCESSO!!!\n" : "\n!!ERRO!!\n");
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

        JLabel labelSacar = new JLabel("Digite o valor do saque");
        valueFieldCliente = new JTextField("Digite o valor");
        valueFieldCliente.addKeyListener(ValueListener);
        panelHome.add(labelSacar);
        panelHome.add(valueFieldCliente);
        panelHome.add(buttonOKSacar);
        panelHome.add(buttonBackSacar);
        panelHome.updateUI();

    }

    private void menuDepositar(){
        JLabel labelDepositar = new JLabel("Digite o valor do deposito");
        valueFieldCliente = new JTextField("Digite o valor");
        valueFieldCliente.addKeyListener(ValueListener);
        panelHome.add(labelDepositar);
        panelHome.add(valueFieldCliente);
        panelHome.add(buttonOKDepositar);
        panelHome.add(buttonBackDepositar);
        panelHome.updateUI();
    }

    private void menuMinhaConta(){
        JLabel labelMyAcc = new JLabel("Informaçoes da sua conta");
        JTextArea myAccTextArea = new JTextArea(contaAtual.info());
        myAccTextArea.setEditable(false);
        myAccTextArea.setSize(panelHome.getSize());
        panelHome.add(labelMyAcc);
        panelHome.add(myAccTextArea);
        panelHome.updateUI();

    }

    private void menuAlteraSenha(){
        JLabel labelAlterSenha1 = new JLabel("Digite a senha atual");
        JPasswordField alterPasswordField = new JPasswordField();
        JLabel labelAlterSenha2 = new JLabel("Digite a senha nova");
        JPasswordField alterPasswordConfirm = new JPasswordField();
        panelHome.add(labelAlterSenha1);
        panelHome.add(alterPasswordField);
        panelHome.add(labelAlterSenha2);
        panelHome.add(alterPasswordConfirm);
        panelHome.add(buttonOKAlterSenha);
        panelHome.add(buttonBackAlterSenha);
        panelHome.updateUI();

    }


    //Menu de gerencia
    private void menuGerente() {

        boolean ok=true;

        buttonCriarConta.addActionListener(GerenteListener);
        buttonInfoConta.addActionListener(GerenteListener);
        buttonRender.addActionListener(GerenteListener);
        buttonJuros.addActionListener(GerenteListener);
        buttonInfoAll.addActionListener(GerenteListener);
        buttonBackGerente.addActionListener(GerenteListener);

        option = 1;
        switch (option) {
            case 1:
                System.out.print("\n+:Criar conta:+\n");
                System.out.print("Digite o nome do futuro correntista\n>>>");
                String nCorr = input.next();
                System.out.print("\nDigite o numero da futura conta, digite \"rand\" para gerar automaticamente\n>>>");
                String nConta = input.next();

                if (nConta.equalsIgnoreCase("rand")) {
                    nConta = Gerente.gerarConta();
                }else if(Gerente.contaExiste(contas,nConta)!= null){
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
                if(Gerente.criarConta(contas,nCorr,nConta,Gerente.ACC_SIMPLE,0)){
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

            removeAllListenersAndComponentsHome();

            if(e.getSource() == buttonExit){

                int res = JOptionPane.showConfirmDialog(null,"Tem certeza?","SAIR",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(res == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
                panelHome = new JPanel(new GridLayout(10,10));
                setContentPane(panelHome);
                menuHome();
            }else if(e.getSource() == buttonCliente){
                panelLoginCliente = new JPanel(new GridLayout(10,10));
                setContentPane(panelLoginCliente);
                menuLoginCliente();
            }else if (e.getSource() == buttonGerente){
                panelGerente = new JPanel(new GridLayout(10,10));
                setContentPane(panelGerente);

                add(label);
                buttonCriarConta = new JButton("Criar conta");
                panelGerente.add(buttonCriarConta);

                buttonInfoConta = new JButton("Informações de conta");
                panelGerente.add(buttonInfoConta);

                buttonRender = new JButton("Efetuar rendimentos");
                panelGerente.add(buttonRender);

                buttonJuros = new JButton("Cobrar juros");
                panelGerente.add(buttonJuros);

                buttonInfoAll = new JButton("Informações de todas as contas");
                panelGerente.add(buttonInfoAll);

                panelGerente.add(buttonBackGerente);

                panelGerente.updateUI();

                menuGerente();
            }

        }
    }

    private class DashboardButtonListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            removeAllListenersAndComponentsDashboard();
            if (e.getSource() == buttonSacar){
                menuSacar();
            }else if(e.getSource() == buttonDepositar){
                menuDepositar();
            }else if(e.getSource() == buttonAlterSenha){
                menuAlteraSenha();
            }else if(e.getSource() == buttonMyAcc){

                menuMinhaConta();
            }else if (e.getSource() == buttonBackSacar){
                menuHome();
            }
        }
    }

    private class GerenteButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            removeAllListenersAndComponentsGerente();

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
            }else if (e.getSource() == buttonBackGerente){
                panelHome = new JPanel(new GridLayout(10,10));
                setContentPane(panelHome);
                menuHome();
            }
        }
    }

    private class LoginButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == buttonOKLogin){
                contaAtual = Gerente.contaExiste(contas,contaFieldCliente.getText());
                if(contaAtual != null && contaAtual.verificaSenha(passwordField.getPassword())){
                    removeAllListenersAndComponentsLogin();
                    menuDashboard();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Número de conta ou senha incorreto(s)!","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(e.getSource() == buttonBackLogin) {
                removeAllListenersAndComponentsLogin();
                panelHome = new JPanel(new GridLayout(10,10));
                setContentPane(panelHome);
                menuHome();
            }
        }
    }

    private class SacarButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == buttonOKSacar){
                if(valueFieldCliente.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Digite um valor!","ERRO",JOptionPane.ERROR_MESSAGE);
                }
                if(contaAtual.sacar(Double.parseDouble(valueFieldCliente.getText()))){
                    JOptionPane.showMessageDialog(null,"SAQUE EFETUADO","INFO",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"SAQUE NAO EFETUADO","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }else if(e.getSource() == buttonBackSacar){
                removeAllListenersAndComponentsSacar();
                menuDashboard();
            }

        }
    }

    private class DepositarButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {


            if(e.getSource() == buttonOKDepositar){
                if(valueFieldCliente.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Digite um valor!","ERRO",JOptionPane.ERROR_MESSAGE);
                }
                if(contaAtual.depositar(Double.parseDouble(valueFieldCliente.getText()))){
                    JOptionPane.showMessageDialog(null,"DEPÒSITO EFETUADO","INFO",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"DEPOSITO NAO EFETUADO","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }else if(e.getSource() == buttonBackDepositar){
                removeAllListenersAndComponentsDepositar();
                menuDashboard();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private class AlterSenhaButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == buttonOKAlterSenha){

            }else if(e.getSource() == buttonBackAlterSenha){
                removeAllListenersAndComponentsAlterSenha();
                menuDashboard();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private class MyAccButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == buttonOKMyAcc){

            }else if(e.getSource() == buttonBackMyAcc){
                removeAllListenersAndComponentsMyAcc();
                menuDashboard();
            }
        }
    }

    private class ValueKeyListener implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {
            if(!caracters.contains(e.getKeyChar()+"")){
                e.consume();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }



    private void removeAllListenersAndComponentsHome(){

        remove(panelHome);


        buttonCliente.removeActionListener(HomeListener);
        buttonGerente.removeActionListener(HomeListener);
        buttonExit.removeActionListener(HomeListener);
    }
    private void removeAllListenersAndComponentsGerente(){

        remove(panelGerente);

        buttonCriarConta.removeActionListener(GerenteListener);
        buttonInfoConta.removeActionListener(GerenteListener);
        buttonInfoAll.removeActionListener(GerenteListener);
        buttonRender.removeActionListener(GerenteListener);
        buttonJuros.removeActionListener(GerenteListener);
        buttonBackGerente.removeActionListener(GerenteListener);
        buttonOKGerente.removeActionListener(GerenteListener);
    }
    private void removeAllListenersAndComponentsLogin(){
        remove(panelLoginCliente);
        buttonBackLogin.removeActionListener(LoginListener);
        buttonOKLogin.removeActionListener(LoginListener);
    }
    private void removeAllListenersAndComponentsDashboard(){

        remove(panelDashboard);
        buttonSacar.removeActionListener(DashboardListener);

        buttonDepositar.removeActionListener(DashboardListener);

        buttonMyAcc.removeActionListener(DashboardListener);

        buttonAlterSenha.removeActionListener(DashboardListener);


    }
    private void removeAllListenersAndComponentsSacar(){

        buttonOKSacar.removeActionListener(SacarListener);
        buttonBackSacar.removeActionListener(SacarListener);
    }
    private void removeAllListenersAndComponentsDepositar(){
        buttonBackDepositar.removeActionListener(SacarListener);
        buttonOKDepositar.removeActionListener(SacarListener);
    }
    private void removeAllListenersAndComponentsMyAcc(){
        buttonBackMyAcc.removeActionListener(SacarListener);
        buttonOKMyAcc.removeActionListener(SacarListener);
    }
    private void removeAllListenersAndComponentsAlterSenha(){
        buttonBackAlterSenha.removeActionListener(SacarListener);
        buttonOKAlterSenha.removeActionListener(SacarListener);
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