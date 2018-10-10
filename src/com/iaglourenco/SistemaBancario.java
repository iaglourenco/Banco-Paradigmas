/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import static com.iaglourenco.Conta.contas;



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
    private CriarContaButtonListener CriarContaListener = new CriarContaButtonListener();
    private TipoContaRadioListener TipoContaListener = new TipoContaRadioListener();
    private InfoAllContaButtonListener InfoAllContaListener = new InfoAllContaButtonListener();
    private InfoContaButtonListener InfoContaListener = new InfoContaButtonListener();
    private JurosButtonListener JurosListener = new JurosButtonListener();
    private RenderButtonListener RenderListener = new RenderButtonListener();
    private final NumberKeyListener NumberListener = new NumberKeyListener();
    private Dimension buttonsDimensions = new Dimension(100,100);


    //Buttons do menuGerente
    private JButton buttonCriarConta;
    private JButton buttonGerarNConta;
    private JButton buttonOKCriarConta = new JButton("Criar conta");
    private JButton buttonBackCriarConta = new JButton("Voltar");
    private JRadioButton radioButtonSimpleAcc;
    private JRadioButton radioButtonSpecialAcc;
    private JRadioButton radioButtonPoupancaAcc;
    private ButtonGroup radioGroup = new ButtonGroup();

    private JButton buttonInfoConta;
    private JButton buttonBackInfoConta = new JButton("Voltar");

    private JButton buttonInfoAll;
    private JTextArea infoAllTextArea;
    private JButton buttonBackInfoAllConta = new JButton("Voltar");

    private JButton buttonRender;
    private JButton buttonOKRender = new JButton("OK");
    private JButton buttonBackRender = new JButton("Voltar");

    private JButton buttonJuros;
    private JButton buttonOKJuros = new JButton("OK");
    private JButton buttonBackJuros= new JButton("Voltar");


    private final JButton buttonBackGerente = new JButton("Voltar");//Button de uso geral, volta um menu
    private final JButton buttonOKGerente = new JButton("OK");//Button de uso geral, confirma

    //TextFields Gerente
    private JTextField nameFieldGerente;
    private JTextField contaFieldGerente;
    private JPasswordField passwordFieldGerente;
    private JPasswordField passwordFieldConfirmGerente;
    private JTextField valueFieldGerente;

    //Login
    private JTextField contaFieldCliente;
    private JPasswordField passwordField;
    private JLabel labelLogin = new JLabel("Login:");
    private final JButton buttonBackLogin = new JButton("Voltar");//Button de uso geral, volta um menu
    private final JButton buttonOKLogin = new JButton("OK");//Button de uso geral, confirma


    //Components do dashboard do Cliente
    private JButton buttonSacar;
    private final JButton buttonOKSacar= new JButton("OK");//Button de uso geral, confirma
    private final JButton buttonBackSacar= new JButton("Voltar");//Button de uso geral, volta um menu
    private JTextField valueFieldSacar;

    private JButton buttonDepositar;
    private final JButton buttonOKDepositar= new JButton("OK");//Button de uso geral, confirma
    private final JButton buttonBackDepositar= new JButton("Voltar");//Button de uso geral, volta um menu
    private JTextField valueFieldDepositar;

    private JButton buttonMyAcc;
    private final JButton buttonBackMyAcc= new JButton("Voltar");//Button de uso geral, volta um menu
    private JTextArea myAccTextArea;

    private JButton buttonAlterSenha;
    private JButton buttonOKAlterSenha= new JButton("OK");//Button de uso geral, confirma
    private JButton buttonBackAlterSenha= new JButton("Voltar");//Button de uso geral, volta um menu
    private JPasswordField alterPasswordConfirm;
    private JPasswordField alterPasswordField;
    private JButton buttonBackDashboard = new JButton("Sair");

    
    private JPanel panelHome;
    private JPanel panelGerente;
    private JPanel panelCriarConta,panelInfoConta,panelInfoAllConta,panelRender,panelJuros,panelResumo;

    private JTextArea resumoTextArea;
    private JButton buttonBackResumo = new JButton("Voltar");
    private JButton buttonResumo;

    private JPanel panelLoginCliente;

    private JPanel panelDashboard;
    private JPanel panelSacar;
    private JPanel panelDepositar;
    private JPanel panelMyAcc;
    private JPanel panelAlterSenha;


    private JLabel label = new JLabel("Escolha uma opção:");


    SistemaBancario(){
        super("BANCO PARADIGMAS");

        panelHome = new JPanel();
        panelHome.setLayout(new GridLayout(10,10));
        setContentPane(panelHome);
        Gerente.criarConta(Conta.contas,"iago","1234",Gerente.ACC_SPECIAL,200);
        Gerente.criarConta(Conta.contas,"iago","4232",Gerente.ACC_SIMPLE,2200);
        Gerente.criarConta(Conta.contas,"iago","1424",Gerente.ACC_POUPANCA,21100);
        Gerente.criarConta(Conta.contas,"iago","4123",Gerente.ACC_SPECIAL,20320);


    }


    //Menu Home
    void menuHome(){

        panelHome.add(label);

        buttonGerente = new JButton("Gerente");
        panelHome.add(buttonGerente);

        buttonCliente = new JButton("Cliente");
        panelHome.add(buttonCliente);

        buttonExit = new JButton("Sair");
        buttonExit.setSize(buttonsDimensions);
        panelHome.add(buttonExit);

        panelHome.updateUI();
        buttonExit.addActionListener(HomeListener);
        buttonGerente.addActionListener(HomeListener);
        buttonCliente.addActionListener(HomeListener);
    }


    //Menu de login
    private void menuLoginCliente() {

        panelLoginCliente.add(labelLogin);
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

        contaFieldCliente.addKeyListener(NumberListener);
        buttonOKLogin.addActionListener(LoginListener);
        buttonBackLogin.addActionListener(LoginListener);
        panelLoginCliente.updateUI();

    }

    //Submenu de cliente
    private void menuDashboard(){

        JLabel welcomeLabel = new JLabel("Olá, "+contaAtual.getNomeCorrentista().toUpperCase());
        JLabel welcomeLabel1 = new JLabel("O que deseja fazer?");
        buttonSacar = new JButton("Sacar");
        buttonDepositar = new JButton("Depositar");
        buttonAlterSenha = new JButton("Alterar senha");
        buttonMyAcc = new JButton("Minha Conta");
        buttonBackDashboard = new JButton("Sair");

        panelDashboard.add(welcomeLabel);
        panelDashboard.add(welcomeLabel1);
        panelDashboard.add(buttonSacar);
        panelDashboard.add(buttonDepositar);
        panelDashboard.add(buttonAlterSenha);
        panelDashboard.add(buttonMyAcc);
        panelDashboard.add(buttonBackDashboard);
        panelDashboard.updateUI();

        buttonSacar.addActionListener(DashboardListener);
        buttonDepositar.addActionListener(DashboardListener);
        buttonMyAcc.addActionListener(DashboardListener);
        buttonAlterSenha.addActionListener(DashboardListener);
        buttonBackDashboard.addActionListener(DashboardListener);

    }
    //Submenus da conta
    private void menuSacar(){

        JLabel labelSacar = new JLabel("Digite o valor do saque");
        valueFieldSacar = new JTextField();
        valueFieldSacar.addKeyListener(NumberListener);
        panelSacar.add(labelSacar);
        panelSacar.add(valueFieldSacar);
        panelSacar.add(buttonOKSacar);
        panelSacar.add(buttonBackSacar);
        panelSacar.updateUI();

        buttonOKSacar.addActionListener(SacarListener);
        buttonBackSacar.addActionListener(SacarListener);

    }

    private void menuDepositar(){
        JLabel labelDepositar = new JLabel("Digite o valor do deposito");
        valueFieldDepositar = new JTextField();
        valueFieldDepositar.addKeyListener(NumberListener);
        panelDepositar.add(labelDepositar);
        panelDepositar.add(valueFieldDepositar);
        panelDepositar.add(buttonOKDepositar);
        panelDepositar.add(buttonBackDepositar);
        panelDepositar.updateUI();

        buttonOKDepositar.addActionListener(DepositarListener);
        buttonBackDepositar.addActionListener(DepositarListener);
    }

    private void menuMinhaConta(){
        JLabel labelMyAcc = new JLabel("Informaçoes da sua conta");
        myAccTextArea = new JTextArea();
        myAccTextArea.append(contaAtual.info());
        myAccTextArea.setEditable(false);
        JScrollPane myAccScrollPane = new JScrollPane(myAccTextArea);
        panelMyAcc.add(labelMyAcc,BorderLayout.PAGE_START);
        panelMyAcc.add(myAccScrollPane);
        buttonBackMyAcc.setPreferredSize(buttonsDimensions);
        panelMyAcc.add(buttonBackMyAcc,BorderLayout.PAGE_END);
        panelMyAcc.updateUI();
        buttonBackMyAcc.addActionListener(MyAccListener);
    }

    private void menuAlteraSenha(){
        JLabel labelAlterSenha1 = new JLabel("Digite a senha atual");
        alterPasswordField = new JPasswordField();
        JLabel labelAlterSenha2 = new JLabel("Digite a senha nova");
        alterPasswordConfirm = new JPasswordField();
        panelAlterSenha.add(labelAlterSenha1);
        panelAlterSenha.add(alterPasswordField);
        panelAlterSenha.add(labelAlterSenha2);
        panelAlterSenha.add(alterPasswordConfirm);
        panelAlterSenha.add(buttonOKAlterSenha);
        panelAlterSenha.add(buttonBackAlterSenha);
        panelAlterSenha.updateUI();

        buttonOKAlterSenha.addActionListener(AlterSenhaListener);
        buttonBackAlterSenha.addActionListener(AlterSenhaListener);


    }


    //Menu de gerencia
    private void menuGerente() {

        panelGerente.add(label);
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

        buttonResumo = new JButton("Status do sitema");
        panelGerente.add(buttonResumo);

        panelGerente.add(buttonBackGerente);

        panelGerente.updateUI();
        buttonCriarConta.addActionListener(GerenteListener);
        buttonInfoConta.addActionListener(GerenteListener);
        buttonRender.addActionListener(GerenteListener);
        buttonJuros.addActionListener(GerenteListener);
        buttonInfoAll.addActionListener(GerenteListener);
        buttonResumo.addActionListener(GerenteListener);
        buttonBackGerente.addActionListener(GerenteListener);


    }

    //Submenus gerencia
    private void menuCriarConta(){

        JLabel labelCreateAcc = new JLabel("Criar conta:");
        JLabel labelName = new JLabel("Nome do correntista");
        nameFieldGerente = new JTextField();
        JLabel labelConta = new JLabel("Numero de conta");
        contaFieldGerente = new JTextField();
        buttonGerarNConta = new JButton("Gerar número de conta");
        buttonGerarNConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == buttonGerarNConta){
                    contaFieldGerente.setText(Gerente.gerarConta());
                }
            }
        });
        JLabel labelPass1 = new JLabel("Senha");
        passwordFieldGerente = new JPasswordField();
        JLabel labelPass2 = new JLabel("Confirme Senha");
        passwordFieldConfirmGerente = new JPasswordField();
        JLabel labelTConta = new JLabel("Tipo de conta");
        radioButtonSimpleAcc = new JRadioButton("Conta Simples");
        radioButtonSpecialAcc = new JRadioButton("Conta Especial");
        radioButtonPoupancaAcc = new JRadioButton("Conta Poupança");

        radioGroup.add(radioButtonSimpleAcc);
        radioGroup.add(radioButtonSpecialAcc);
        radioGroup.add(radioButtonPoupancaAcc);


        panelCriarConta.add(labelCreateAcc);
        panelCriarConta.add(labelName);
        panelCriarConta.add(nameFieldGerente);
        panelCriarConta.add(labelConta);
        panelCriarConta.add(contaFieldGerente);
        panelCriarConta.add(buttonGerarNConta);
        panelCriarConta.add(labelPass1);
        panelCriarConta.add(passwordFieldGerente);
        panelCriarConta.add(labelPass2);
        panelCriarConta.add(passwordFieldConfirmGerente);
        panelCriarConta.add(labelTConta);
        radioButtonSimpleAcc.addItemListener(TipoContaListener);
        radioButtonSpecialAcc.addItemListener(TipoContaListener);
        radioButtonPoupancaAcc.addItemListener(TipoContaListener);
        panelCriarConta.add(buttonOKCriarConta);
        panelCriarConta.add(buttonBackCriarConta);
        buttonOKCriarConta.addActionListener(CriarContaListener);
        buttonBackCriarConta.addActionListener(CriarContaListener);

        panelCriarConta.updateUI();
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
        JLabel labelInfoAll = new JLabel("Informações de todas as contas");
        infoAllTextArea = new JTextArea();

        infoAllTextArea.setText("Quantidade de contas cadastradas= "+Integer.toString(Conta.getContasCadastradas())+"\n\n");
        for(int i=0;i<Gerente.MAX_CONTAS;i++){
            if(Conta.contas[i]!=null)
                infoAllTextArea.append(Conta.contas[i].info()+"-------\n\n");
        }


        infoAllTextArea.setEditable(false);
        JScrollPane myAccScrollPane = new JScrollPane(infoAllTextArea);
        panelInfoAllConta.add(labelInfoAll,BorderLayout.PAGE_START);
        panelInfoAllConta.add(myAccScrollPane);
        buttonBackInfoAllConta.setPreferredSize(buttonsDimensions);
        panelInfoAllConta.add(buttonBackInfoAllConta,BorderLayout.PAGE_END);
        panelInfoAllConta.updateUI();
        buttonBackInfoAllConta.addActionListener(InfoAllContaListener);
    }

    private void menuResumo(){
        JLabel labelResumo = new JLabel("Status do banco");
        resumoTextArea = new JTextArea();
        resumoTextArea.append(statusOfSystem());
        resumoTextArea.setEditable(false);
        JScrollPane resumoScrollPane = new JScrollPane(resumoTextArea);
        panelResumo.add(labelResumo,BorderLayout.PAGE_START);
        panelResumo.add(resumoScrollPane);
        buttonBackResumo.setPreferredSize(buttonsDimensions);
        panelResumo.add(buttonBackResumo,BorderLayout.PAGE_END);
        panelResumo.updateUI();
        buttonBackResumo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panelResumo);
                buttonBackResumo.removeActionListener(this);
                panelGerente = new JPanel(new GridLayout(10,10));
                setContentPane(panelGerente);
                menuGerente();
            }
        });
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
                menuGerente();
            }

        }
    }

    //===================================================================

    private class GerenteButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            removeAllListenersAndComponentsGerente();

            if(e.getSource() == buttonCriarConta){
                panelCriarConta = new JPanel();
                panelCriarConta.setLayout(new BoxLayout(panelCriarConta,BoxLayout.Y_AXIS));
                setContentPane(panelCriarConta);
                menuCriarConta();
            }else if(e.getSource() ==buttonInfoConta){
                panelInfoConta = new JPanel();
                panelInfoConta.setLayout(new BoxLayout(panelInfoConta,BoxLayout.Y_AXIS));
                setContentPane(panelInfoConta);
                menuInfoConta();
            }else if(e.getSource() == buttonRender){
                panelRender = new JPanel(new GridLayout(10,10));
                setContentPane(panelRender);
                menuRender();
            }else if(e.getSource() == buttonJuros){
                panelJuros = new JPanel(new GridLayout(10,10));
                setContentPane(panelJuros);
                menuJuros();
            }else if(e.getSource() == buttonInfoAll){
                panelInfoAllConta = new JPanel(new BorderLayout(5,5));
                setContentPane(panelInfoAllConta);
                menuInfoAll();
            }else if (e.getSource() == buttonBackGerente){
                panelHome = new JPanel(new GridLayout(10,10));
                setContentPane(panelHome);
                menuHome();
            }else if(e.getSource() == buttonResumo){
                panelResumo = new JPanel(new BorderLayout(5,5));
                setContentPane(panelResumo);
                menuResumo();
            }
        }
    }

    private class CriarContaButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == buttonOKCriarConta){

                //if(Gerente.criarConta(Conta.contas,nameFieldGerente.getText().toString(),contaFieldGerente.getText().toString()))


                menuGerente();
            }else if(e.getSource() == buttonBackCriarConta){
                removeAllListenersAndComponentsCriarConta();
                panelGerente = new JPanel(new GridLayout(10,10));
                setContentPane(panelGerente);
                menuGerente();
            }

        }
    }

    private class TipoContaRadioListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {

            if(e.getSource() == radioButtonSimpleAcc){


            }
        }
    }

    private class InfoContaButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class InfoAllContaButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            removeAllListenersAndComponentsInfoAllConta();
            if(e.getSource() == buttonBackInfoAllConta){
                panelGerente = new JPanel(new GridLayout(10,10));
                setContentPane(panelGerente);
                menuGerente();
            }
        }
    }

    private class RenderButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class JurosButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    //===================================================================

    private class LoginButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == buttonOKLogin){
                contaAtual = Gerente.contaExiste(contas,contaFieldCliente.getText());
                if(contaAtual != null && contaAtual.verificaSenha(passwordField.getPassword())){
                    removeAllListenersAndComponentsLogin();
                    panelDashboard = new JPanel(new GridLayout(10,10));
                    setContentPane(panelDashboard);
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

    private class DashboardButtonListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            removeAllListenersAndComponentsDashboard();
            if (e.getSource() == buttonSacar){
                panelSacar = new JPanel(new GridLayout(10,10));
                setContentPane(panelSacar);
                menuSacar();
            }else if(e.getSource() == buttonDepositar){
                panelDepositar = new JPanel(new GridLayout(10,10));
                setContentPane(panelDepositar);
                menuDepositar();
            }else if(e.getSource() == buttonAlterSenha){
                panelAlterSenha = new JPanel(new GridLayout(10,10));
                setContentPane(panelAlterSenha);
                menuAlteraSenha();
            }else if(e.getSource() == buttonMyAcc){
                panelMyAcc = new JPanel(new BorderLayout());
                setContentPane(panelMyAcc);
                menuMinhaConta();
            }else if (e.getSource() == buttonBackDashboard){
                int res = JOptionPane.showConfirmDialog(null,"Tem certeza?","SAIR",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(res == JOptionPane.YES_OPTION){
                    panelHome = new JPanel(new GridLayout(10,10));
                    setContentPane(panelHome);
                    menuHome();
                }
            }
        }
    }

    private class SacarButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == buttonOKSacar){
                if(valueFieldSacar.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Digite um valor!","ERRO",JOptionPane.ERROR_MESSAGE);
                }
                if(contaAtual.sacar(Double.parseDouble(valueFieldSacar.getText()))){
                    JOptionPane.showMessageDialog(null,"SAQUE EFETUADO","INFO",JOptionPane.INFORMATION_MESSAGE);
                    removeAllListenersAndComponentsSacar();
                    panelDashboard = new JPanel(new GridLayout(10,10));
                    setContentPane(panelDashboard);
                    menuDashboard();
                }else{
                    JOptionPane.showMessageDialog(null,"SAQUE NAO EFETUADO","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }else if(e.getSource() == buttonBackSacar){
                removeAllListenersAndComponentsSacar();
                panelDashboard = new JPanel(new GridLayout(10,10));
                setContentPane(panelDashboard);
                menuDashboard();
            }

        }
    }

    private class DepositarButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {


            if(e.getSource() == buttonOKDepositar){
                if(valueFieldDepositar.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Digite um valor!","ERRO",JOptionPane.ERROR_MESSAGE);
                }
                if(contaAtual.depositar(Double.parseDouble(valueFieldDepositar.getText()))){
                    JOptionPane.showMessageDialog(null,"DEPÒSITO EFETUADO","INFO",JOptionPane.INFORMATION_MESSAGE);
                    removeAllListenersAndComponentsDepositar();
                    panelDashboard = new JPanel(new GridLayout(10,10));
                    setContentPane(panelDashboard);
                    menuDashboard();

                }else{
                    JOptionPane.showMessageDialog(null,"DEPOSITO NAO EFETUADO","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }else if(e.getSource() == buttonBackDepositar){
                removeAllListenersAndComponentsDepositar();
                panelDashboard = new JPanel(new GridLayout(10,10));
                setContentPane(panelDashboard);
                menuDashboard();

            }
        }
    }

    private class AlterSenhaButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == buttonOKAlterSenha){
                if(contaAtual.alteraSenha(alterPasswordField.getPassword(),alterPasswordConfirm.getPassword())){
                    JOptionPane.showMessageDialog(null,"SENHA ALTERADA!","INFO",JOptionPane.INFORMATION_MESSAGE);
                    removeAllListenersAndComponentsAlterSenha();
                    panelDashboard = new JPanel(new GridLayout(10,10));
                    setContentPane(panelDashboard);
                    menuDashboard();
                }else{
                    JOptionPane.showMessageDialog(null,"SENHA INCORRETA!","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }else if(e.getSource() == buttonBackAlterSenha){
                removeAllListenersAndComponentsAlterSenha();
                panelDashboard = new JPanel(new GridLayout(10,10));
                setContentPane(panelDashboard);
                menuDashboard();
            }
        }
    }

    private class MyAccButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == buttonBackMyAcc){
                removeAllListenersAndComponentsMyAcc();
                panelDashboard = new JPanel(new GridLayout(10,10));
                setContentPane(panelDashboard);
                menuDashboard();
            }
        }
    }

    private class NumberKeyListener implements KeyListener{
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

    //===================================================================


    //Remove todos os components e listeners do layout
    //===================================================================
    private void removeAllListenersAndComponentsHome(){

        remove(panelHome);


        buttonCliente.removeActionListener(HomeListener);
        buttonGerente.removeActionListener(HomeListener);
        buttonExit.removeActionListener(HomeListener);
    }

    //===================================================================
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

    private void removeAllListenersAndComponentsCriarConta(){

        remove(panelCriarConta);


        buttonGerarNConta.removeActionListener(CriarContaListener);
        buttonOKCriarConta.removeActionListener(CriarContaListener);
        buttonBackCriarConta.removeActionListener(CriarContaListener);

    }

    private void removeAllListenersAndComponentsJuros(){

    }

    private void removeAllListenersAndComponentsRender(){

    }

    private void removeAllListenersAndComponentsInfoConta(){

    }

    private void removeAllListenersAndComponentsInfoAllConta(){

        remove(buttonBackInfoAllConta);
        buttonBackInfoAllConta.removeActionListener(InfoAllContaListener);
    }

    //===================================================================

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

        remove(panelSacar);
        buttonOKSacar.removeActionListener(SacarListener);
        buttonBackSacar.removeActionListener(SacarListener);
    }

    private void removeAllListenersAndComponentsDepositar(){
        remove(panelDepositar);
        buttonBackDepositar.removeActionListener(DepositarListener);
        buttonOKDepositar.removeActionListener(DepositarListener);
    }

    private void removeAllListenersAndComponentsMyAcc(){
        remove(panelMyAcc);
        buttonBackMyAcc.removeActionListener(MyAccListener);
        }

    private void removeAllListenersAndComponentsAlterSenha(){
        remove(panelAlterSenha);
        buttonBackAlterSenha.removeActionListener(AlterSenhaListener);
        buttonOKAlterSenha.removeActionListener(AlterSenhaListener);
    }


    private String statusOfSystem(){

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

        return
                "TOTAL DE CONTAS CADASTRADAS= "+Integer.toString(Conta.getContasCadastradas())+"\n"+

                        ">>CONTAS SIMPLES CADASTRADAS= " + Integer.toString(counterSimples)+"\n"+
                        "\t|-QUANTIA TOTAL APLICADA NO BANCO= R$ "+Double.toString(totalMoneySimples)+"\n\n"+
                        ">>CONTAS POUPANCA CADASTRADAS = "+Integer.toString(counterPoupanca)+"\n"+
                        "\t|-QUANTIA TOTAL APLICADA NO BANCO= R$ "+Double.toString(totalMoneyPoupanca)+"\n\n"+
                        ">>CONTAS ESPECIAL CADASTRADAS = "+Integer.toString(counterEspecial)+"\n"+
                        "\t|-QUANTIA TOTAL APLICADA NO BANCO= R$ "+Double.toString(totalMoneyEspecial)+"\n\n"+
                        "TOTAL APLICADO NO BANCO= R$ "+Double.toString(totalMoneyEspecial+totalMoneyPoupanca+totalMoneySimples)+"\n\n"+
                        ">>CONTAS ESPECIAIS DEVEDORAS= R$ "+Integer.toString(counterDevedores)+"\n"+
                        "\t|-QUANTIA TOTAL DE DIVIDAS= "+Double.toString(totalDividasEspecial);




    }


}