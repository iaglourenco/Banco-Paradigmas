/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;


import com.iaglourenco.exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import static com.iaglourenco.Gerente.*;


class SistemaBancario extends JFrame {

    private Conta contaAtual;

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
    private final DepositarButtonListener DepositarListener = new DepositarButtonListener();
    private final MyAccButtonListener MyAccListener = new MyAccButtonListener();
    private final AlterSenhaButtonListener AlterSenhaListener = new AlterSenhaButtonListener();
    private final CriarContaButtonListener CriarContaListener = new CriarContaButtonListener();
    private final TipoContaRadioListener TipoContaListener = new TipoContaRadioListener();
    private final InfoAllContaButtonListener InfoAllContaListener = new InfoAllContaButtonListener();
    private final InfoContaButtonListener InfoContaListener = new InfoContaButtonListener();
    private final JurosButtonListener JurosListener = new JurosButtonListener();
    private final NumberKeyListener NumberListener = new NumberKeyListener();


    //Components do menuGerente
    private JButton buttonCriarConta;
    private JButton buttonGerarNConta;
    private final JButton buttonOKCriarConta = new JButton("Criar conta");
    private final JButton buttonBackCriarConta = new JButton("Voltar");
    private JRadioButton radioButtonSimpleAcc;
    private JRadioButton radioButtonSpecialAcc;
    private JRadioButton radioButtonPoupancaAcc;
    private int radioAccType=0;
    private final ButtonGroup radioGroup = new ButtonGroup();

    private JButton buttonInfoConta;
    private JTextField textFieldInfoConta;
    private final JButton buttonOkInfoConta = new JButton("OK");
    private final JButton buttonBackInfoConta = new JButton("Voltar");

    private JButton buttonInfoAll;
    private final JButton buttonBackInfoAllConta = new JButton("Voltar");

    private JButton buttonRender;

    private JButton buttonJuros;
    private JTextField textFieldJuros;
    private final JButton buttonOKJuros = new JButton("OK");
    private final JButton buttonBackJuros= new JButton("Voltar");

    private final JButton buttonBackResumo = new JButton("Voltar");
    private JButton buttonResumo;

    private final JButton buttonBackGerente = new JButton("Voltar");//Button de uso geral, volta um menu

    //TextFields Gerente
    private JTextField nameFieldGerente;
    private JTextField contaFieldGerente;
    private JPasswordField passwordFieldGerente;
    private JPasswordField passwordFieldConfirmGerente;

    //Components Login
    private JTextField contaFieldCliente;
    private JPasswordField passwordField;
    private final JLabel labelLogin = new JLabel("Login:");
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

    private JButton buttonAlterSenha;
    private final JButton buttonOKAlterSenha= new JButton("OK");//Button de uso geral, confirma
    private final JButton buttonBackAlterSenha= new JButton("Voltar");//Button de uso geral, volta um menu
    private JPasswordField alterPasswordConfirm;
    private JPasswordField alterPasswordField;
    private JButton buttonBackDashboard = new JButton("Sair");


    //Panels de cada menu
    private JPanel panelHome;
    private JPanel panelGerente;
    private JPanel panelCriarConta;
    private JPanel panelInfoConta;
    private JPanel panelInfoAllConta;
    private JPanel panelJuros;
    private JPanel panelResumo;
    private JPanel panelLoginCliente;
    private JPanel panelDashboard;
    private JPanel panelSacar;
    private JPanel panelDepositar;
    private JPanel panelMyAcc;
    private JPanel panelAlterSenha;

    //Label usado por todos
    private final JLabel label = new JLabel("Escolha uma opção:");


    SistemaBancario(){
        super("BANCO PARADIGMAS");

        panelHome = new JPanel();
        panelHome.setLayout(new GridLayout(10,10));
        setContentPane(panelHome);

        Gerente.criarConta("iago","1234","1234".toCharArray(),Gerente.ACC_SPECIAL,200);
        Gerente.criarConta("iago","4321","4321".toCharArray(),Gerente.ACC_SIMPLE,2200);
        Gerente.criarConta("iago","1423","1423".toCharArray(),Gerente.ACC_POUPANCA,21100);

    }


    //Menu Home
    void menuHome(){
        setTitle("Banco Paradigmas");
        panelHome.add(label);

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
        setTitle("Login");
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
        setTitle("Minha Conta: "+contaAtual.getnConta());
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
        setTitle("SAQUE");
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
        setTitle("DEPóSITO");
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
        setTitle("MINHA CONTA");
        JLabel labelMyAcc = new JLabel("Informaçoes da sua conta");
        JTextArea myAccTextArea = new JTextArea();
        myAccTextArea.append(contaAtual.info());
        myAccTextArea.setEditable(false);
        JScrollPane myAccScrollPane = new JScrollPane(myAccTextArea);
        panelMyAcc.add(labelMyAcc,BorderLayout.PAGE_START);
        panelMyAcc.add(myAccScrollPane);
        panelMyAcc.add(buttonBackMyAcc,BorderLayout.PAGE_END);
        panelMyAcc.updateUI();
        buttonBackMyAcc.addActionListener(MyAccListener);
    }

    private void menuAlteraSenha(){
        setTitle("ALTERAR SENHA");
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
        setTitle("GERENTE");
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
        setTitle("CRIAR CONTA");
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
        panelCriarConta.add(radioButtonPoupancaAcc);
        panelCriarConta.add(radioButtonSimpleAcc);
        panelCriarConta.add(radioButtonSpecialAcc);
        panelCriarConta.add(buttonOKCriarConta);
        panelCriarConta.add(buttonBackCriarConta);



        radioButtonSimpleAcc.addItemListener(TipoContaListener);
        radioButtonSpecialAcc.addItemListener(TipoContaListener);
        radioButtonPoupancaAcc.addItemListener(TipoContaListener);
        contaFieldGerente.addKeyListener(NumberListener);
        buttonOKCriarConta.addActionListener(CriarContaListener);
        buttonBackCriarConta.addActionListener(CriarContaListener);

        panelCriarConta.updateUI();
    }

    private void menuInfoConta(){
        setTitle("INFO DE CONTA");
        JLabel labelInfoConta = new JLabel("Digite o número da conta");
        textFieldInfoConta = new JTextField();
        textFieldInfoConta.addKeyListener(NumberListener);

        panelInfoConta.add(labelInfoConta);
        panelInfoConta.add(textFieldInfoConta);
        panelInfoConta.add(buttonOkInfoConta);
        panelInfoConta.add(buttonBackInfoConta);

        buttonBackInfoConta.addActionListener(InfoContaListener);
        buttonOkInfoConta.addActionListener(InfoContaListener);
        panelInfoConta.updateUI();
    }

    private void menuJuros(){
        setTitle("COBRAR JUROS");
        JLabel labelJuros = new JLabel("Digite a taxa");
        textFieldJuros = new JTextField();
        textFieldJuros.addKeyListener(NumberListener);

        panelJuros.add(labelJuros);
        panelJuros.add(textFieldJuros);
        panelJuros.add(buttonOKJuros);
        panelJuros.add(buttonBackJuros);

        buttonOKJuros.addActionListener(JurosListener);
        buttonBackJuros.addActionListener(JurosListener);

        panelJuros.updateUI();
    }

    private void menuInfoAll(){
        setTitle("INFO DE TODAS AS CONTAS");
        JLabel labelInfoAll = new JLabel("Informações de todas as contas");
        JTextArea infoAllTextArea = new JTextArea();

        infoAllTextArea.setText("Quantidade de contas cadastradas= "+Integer.toString(Conta.getContasCadastradas())+"\n\n");
        for(int i=0;i<Gerente.MAX_CONTAS;i++){
            if(Conta.contas[i]!=null)
                infoAllTextArea.append(Conta.contas[i].info()+"-------\n\n");
        }


        infoAllTextArea.setEditable(false);
        JScrollPane myAccScrollPane = new JScrollPane(infoAllTextArea);
        panelInfoAllConta.add(labelInfoAll,BorderLayout.PAGE_START);
        panelInfoAllConta.add(myAccScrollPane);
        panelInfoAllConta.add(buttonBackInfoAllConta,BorderLayout.PAGE_END);
        panelInfoAllConta.updateUI();
        buttonBackInfoAllConta.addActionListener(InfoAllContaListener);
    }

    private void menuResumo(){
        setTitle("STATUS DO SISTEMA");
        JLabel labelResumo = new JLabel("Status do banco");
        JTextArea resumoTextArea = new JTextArea();
        resumoTextArea.append(Conta.getStatusOfSystem());
        resumoTextArea.setEditable(false);
        JScrollPane resumoScrollPane = new JScrollPane(resumoTextArea);
        panelResumo.add(labelResumo,BorderLayout.PAGE_START);
        panelResumo.add(resumoScrollPane);
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
    private class HomeButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonExit) {

            int res = JOptionPane.showConfirmDialog(null, "Tem certeza?", "SAIR", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
            panelHome = new JPanel(new GridLayout(10, 10));
            setContentPane(panelHome);
            menuHome();
        } else if (e.getSource() == buttonCliente) {
            panelLoginCliente = new JPanel(new GridLayout(10, 10));
            setContentPane(panelLoginCliente);
            menuLoginCliente();
        } else if (e.getSource() == buttonGerente) {
            panelGerente = new JPanel(new GridLayout(10, 10));
            setContentPane(panelGerente);
            menuGerente();
        }



    }
}

    //===================================================================

    private class GerenteButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {



            if(e.getSource() == buttonCriarConta){
                panelCriarConta = new JPanel();
                panelCriarConta.setLayout(new BoxLayout(panelCriarConta,BoxLayout.Y_AXIS));
                setContentPane(panelCriarConta);
                menuCriarConta();
            }else if(e.getSource() ==buttonInfoConta){
                panelInfoConta = new JPanel(new GridLayout(10,10));
                setContentPane(panelInfoConta);
                menuInfoConta();
            }else if(e.getSource() == buttonRender){

                int resp = JOptionPane.showOptionDialog(null,"DESEJA EFETUAR OS RENDIMENTOS NAS CONTAS POUPANÇA?","EFETUAR RENDIMENTOS",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                if(resp == JOptionPane.YES_OPTION){

                    int accRendidas = Gerente.incrementarJuros();

                    JOptionPane.showMessageDialog(null,"RENDIMENTOS EFETUADOS EM "+ Integer.toString(accRendidas)+" CONTAS POUPANÇA","SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                    panelGerente = new JPanel(new GridLayout(10,10));
                    setContentPane(panelGerente);
                    menuGerente();
                }else if (resp == JOptionPane.NO_OPTION){
                    panelGerente = new JPanel(new GridLayout(10,10));
                    setContentPane(panelGerente);
                    menuGerente();
                }

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

            if(e.getSource() == buttonOKCriarConta) {


                try {
                    if (Conta.getContasCadastradas() >= Gerente.MAX_CONTAS) throw new MaxContasException("MAXIMO DE CONTAS ATINGIDO");

                    if(contaFieldGerente.getText().isEmpty())throw new CampoVazioException("CAMPO(S) VAZIO(S)");
                    if(nameFieldGerente.getText().isEmpty())throw new CampoVazioException("CAMPO(S) VAZIO(S)");

                    if (!Arrays.equals(passwordFieldGerente.getPassword(), passwordFieldConfirmGerente.getPassword()))
                        throw new SenhasDiferemException("SENHAS DIFEREM");


                    if(radioAccType == ACC_SIMPLE) {
                        Gerente.criarConta(nameFieldGerente.getText(),contaFieldGerente.getText(),passwordFieldGerente.getPassword(), ACC_SIMPLE, 0);
                        JOptionPane.showMessageDialog(null,"CONTA CRIADA","SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                        panelGerente = new JPanel(new GridLayout(10,10));
                        setContentPane(panelGerente);
                        menuGerente();
                    }
                    else if(radioAccType == ACC_POUPANCA) {

                        try{
                            double rend = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o rendimento", "RENDIMENTO", JOptionPane.QUESTION_MESSAGE));
                            if(rend<0){
                                throw new ValorInvalidoException("VALOR INVALIDO");
                            }
                            Gerente.criarConta(nameFieldGerente.getText(),contaFieldGerente.getText(),passwordFieldGerente.getPassword(),ACC_POUPANCA,rend);
                            JOptionPane.showMessageDialog(null,"CONTA CRIADA","SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                            panelGerente = new JPanel(new GridLayout(10,10));
                            setContentPane(panelGerente);
                            menuGerente();
                        }catch (NumberFormatException num){
                            throw new ValorInvalidoException("VALOR INVALIDO DIGITADO");
                        }
                    }else if(radioAccType == ACC_SPECIAL ) {

                        try {
                            double lim = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o limite", "LIMITE", JOptionPane.QUESTION_MESSAGE));
                            if(lim<0){
                                throw new ValorInvalidoException("VALOR INVALIDO");
                            }
                            Gerente.criarConta(nameFieldGerente.getText(),contaFieldGerente.getText(),passwordFieldGerente.getPassword(), ACC_SPECIAL, lim);
                            JOptionPane.showMessageDialog(null, "CONTA CRIADA", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                            panelGerente = new JPanel(new GridLayout(10, 10));
                            setContentPane(panelGerente);
                            menuGerente();
                        }catch (NumberFormatException num){
                            throw new ValorInvalidoException("VALOR INVALIDO DIGITADO");
                        }
                    }else{
                        throw new CampoVazioException("CAMPO(S) VAZIO(S)");
                    }

                } catch( SenhasDiferemException key) {
                    JOptionPane.showMessageDialog(null, "SENHAS NAO CONFEREM", "ERRO", JOptionPane.ERROR_MESSAGE);

                }catch ( ContaExistenteException contaExiste) {
                    JOptionPane.showMessageDialog(null, "NUMERO DE CONTA EXISTENTE", "ERRO", JOptionPane.ERROR_MESSAGE);

                }catch ( CampoVazioException campoVazio) {
                    JOptionPane.showMessageDialog(null, "PREENCHA TODAS AS INFORMAÇÕES", "ERRO", JOptionPane.ERROR_MESSAGE);

                }catch (ValorInvalidoException valorInvalido){
                    JOptionPane.showMessageDialog(null, "VALOR INVÁLIDO DIGITADO", "ERRO", JOptionPane.ERROR_MESSAGE);

                } catch ( Exception exc){
                    JOptionPane.showMessageDialog(null, exc.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                }

            }else if(e.getSource() == buttonBackCriarConta){
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
                radioAccType = ACC_SIMPLE;
            }else if(e.getSource() == radioButtonPoupancaAcc){
                radioAccType = ACC_POUPANCA;
            }else if(e.getSource() == radioButtonSpecialAcc) {
                radioAccType = ACC_SPECIAL;
            }

        }
    }

    private class InfoContaButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == buttonOkInfoConta){

                try {
                    Conta acc = Gerente.contaExiste(textFieldInfoConta.getText());

                    remove(panelInfoConta);
                    panelInfoConta = new JPanel(new BorderLayout());
                    setContentPane(panelInfoConta);

                    JTextArea infoContaTextArea = new JTextArea();
                    infoContaTextArea.append(acc.info());
                    panelInfoConta.add(infoContaTextArea, BorderLayout.PAGE_START);
                    panelInfoConta.add(buttonBackInfoConta, BorderLayout.PAGE_END);
                    buttonOkInfoConta.removeActionListener(InfoContaListener);
                    panelInfoConta.updateUI();
                }catch (ContaInexistenteException contaNExiste) {
                    JOptionPane.showMessageDialog(null, "CONTA NAO ENCONTRADA", "ERRO", JOptionPane.ERROR_MESSAGE);
                    remove(panelInfoConta);
                    panelInfoConta = new JPanel(new GridLayout(10,10));
                    setContentPane(panelInfoConta);
                    buttonOkInfoConta.removeActionListener(InfoContaListener);
                    buttonBackInfoConta.removeActionListener(InfoContaListener);
                    menuInfoConta();
                }
            }else if(e.getSource() == buttonBackInfoConta){
                panelGerente = new JPanel(new GridLayout(10,10));
                setContentPane(panelGerente);
                menuGerente();
            }

        }
    }

    private class InfoAllContaButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == buttonBackInfoAllConta){
                panelGerente = new JPanel(new GridLayout(10,10));
                setContentPane(panelGerente);
                menuGerente();
            }
        }
    }

    private class JurosButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {



            if(e.getSource() == buttonOKJuros){

                int accJuros = Gerente.cobrarJuros(Double.parseDouble(textFieldJuros.getText()));

                JOptionPane.showMessageDialog(null,"JUROS COBRADOS DE "+Integer.toString(accJuros)+" CONTAS ESPECIAIS","SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                panelGerente = new JPanel(new GridLayout(10,10));
                setContentPane(panelGerente);
                menuGerente();

            }else if (e.getSource() == buttonBackJuros){
                panelGerente = new JPanel(new GridLayout(10,10));
                setContentPane(panelGerente);
                menuGerente();
            }



        }
    }

    //===================================================================

    private class LoginButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == buttonOKLogin) {
                try{
                contaAtual = Gerente.contaExiste(contaFieldCliente.getText());
                contaAtual.verificaSenha(passwordField.getPassword()) ;
                panelDashboard = new JPanel(new GridLayout(10, 10));
                setContentPane(panelDashboard);
                menuDashboard();

            }catch(SenhaIncorretaException senhaErrada){
                    JOptionPane.showMessageDialog(null,"Número de conta e/ou senha incorreto(s)!","ERRO",JOptionPane.ERROR_MESSAGE);
                    remove(panelLoginCliente);
                    buttonOKLogin.removeActionListener(LoginListener);
                    buttonBackLogin.removeActionListener(LoginListener);
                }catch (ContaInexistenteException contaNExiste){
                    JOptionPane.showMessageDialog(null,"Número de conta e/ou senha incorreto(s)!","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(e.getSource() == buttonBackLogin) {
                panelHome = new JPanel(new GridLayout(10,10));
                setContentPane(panelHome);
                menuHome();
            }
        }
    }

    private class DashboardButtonListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

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
                try {

                if(valueFieldSacar.getText().isEmpty()){
                    throw new ValorInvalidoException("VALOR INVALIDO");
                }

                contaAtual.sacar(Double.parseDouble(valueFieldSacar.getText()));
                JOptionPane.showMessageDialog(null,"SAQUE EFETUADO","INFO",JOptionPane.INFORMATION_MESSAGE);
                remove(panelSacar);
                buttonOKSacar.removeActionListener(SacarListener);
                buttonBackSacar.removeActionListener(SacarListener);
                panelDashboard = new JPanel(new GridLayout(10,10));
                setContentPane(panelDashboard);
                menuDashboard();

                }catch (SaldoInsuficienteException semGrana){
                        JOptionPane.showMessageDialog(null,"SAQUE NAO EFETUADO","ERRO",JOptionPane.ERROR_MESSAGE);
                }catch (ValorInvalidoException invalido){
                    JOptionPane.showMessageDialog(null,"Digite um valor!","ERRO",JOptionPane.ERROR_MESSAGE);
                }


            }else if(e.getSource() == buttonBackSacar){
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
                try {

                    if(valueFieldSacar.getText().isEmpty()){
                        throw new ValorInvalidoException("VALOR INVALIDO");
                    }

                    contaAtual.depositar(Double.parseDouble(valueFieldDepositar.getText()));
                    JOptionPane.showMessageDialog(null,"DEPÓSITO EFETUADO","INFO",JOptionPane.INFORMATION_MESSAGE);
                    remove(panelDepositar);
                    buttonBackDepositar.removeActionListener(DepositarListener);
                    buttonOKDepositar.removeActionListener(DepositarListener);
                    panelDashboard = new JPanel(new GridLayout(10,10));
                    setContentPane(panelDashboard);
                    menuDashboard();

                }catch (ValorInvalidoException invalido){
                    JOptionPane.showMessageDialog(null,"DIGITE UM VALOR VÁLIDO!","ERRO",JOptionPane.ERROR_MESSAGE);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"DEPÓSITO NÃO EFETUADO "+ex.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }else if(e.getSource() == buttonBackDepositar){
                panelDashboard = new JPanel(new GridLayout(10,10));
                setContentPane(panelDashboard);
                menuDashboard();

            }
        }
    }

    private class AlterSenhaButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == buttonOKAlterSenha) {

                try{
                contaAtual.alteraSenha(alterPasswordField.getPassword(), alterPasswordConfirm.getPassword());
                JOptionPane.showMessageDialog(null, "SENHA ALTERADA COM SUCESSO!", "INFO", JOptionPane.INFORMATION_MESSAGE);
                panelDashboard = new JPanel(new GridLayout(10, 10));
                setContentPane(panelDashboard);
                menuDashboard();
            }catch (SenhaIncorretaException senhaAntErrada){
                    JOptionPane.showMessageDialog(null,"SENHA ANTIGA INCORRETA!","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }else if(e.getSource() == buttonBackAlterSenha){
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
                panelDashboard = new JPanel(new GridLayout(10,10));
                setContentPane(panelDashboard);
                menuDashboard();
            }
        }
    }

    private class NumberKeyListener implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {
            String caracters = "0987654321";
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





}