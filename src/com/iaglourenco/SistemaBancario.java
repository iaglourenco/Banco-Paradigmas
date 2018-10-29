/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;

import com.iaglourenco.exceptions.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonAreaLayout;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import static com.iaglourenco.Gerente.*;

class SistemaBancario extends JFrame {

    //Instanciação dos listeners
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
    //JButtons
    private final JButton buttonOKCriarConta = new JButton("Criar conta");
    private final JButton buttonBackCriarConta = new JButton("Voltar");
    private final ButtonGroup radioGroup = new ButtonGroup();
    private final JButton buttonOkInfoConta = new JButton("OK");
    private final JButton buttonBackInfoConta = new JButton("Voltar");
    private final JButton buttonBackInfoAllConta = new JButton("Voltar");
    private final JButton buttonOKJuros = new JButton("OK");
    private final JButton buttonBackJuros = new JButton("Voltar");
    private final JButton buttonBackResumo = new JButton("Voltar");
    private final JButton buttonBackGerente = new JButton("Voltar");//Button de uso geral, volta um menu
    private final JLabel labelLogin = new JLabel("Login:");
    private final JButton buttonBackLogin = new JButton("Voltar");//Button de uso geral, volta um menu
    private final JButton buttonOKLogin = new JButton("OK");//Button de uso geral, confirma
    private final JButton buttonOKSacar = new JButton("OK");//Button de uso geral, confirma
    private final JButton buttonBackSacar = new JButton("Voltar");//Button de uso geral, volta um menu
    private final JButton buttonOKDepositar = new JButton("OK");//Button de uso geral, confirma
    private final JButton buttonBackDepositar = new JButton("Voltar");//Button de uso geral, volta um menu
    private final JButton buttonBackMyAcc = new JButton("Voltar");//Button de uso geral, volta um menu
    private final JButton buttonOKAlterSenha = new JButton("OK");//Button de uso geral, confirma
    private final JButton buttonBackAlterSenha = new JButton("Voltar");//Button de uso geral, volta um menu
    //Label usado por todos
    private final JLabel label = new JLabel("Escolha uma opção:");
    private Conta contaAtual;
    //Buttons do primeiro menu, Home
    private JButton buttonGerente;
    private JButton buttonCliente;
    private JButton buttonExit;
    //Components do menuGerente
    private JButton buttonCriarConta;
    private JButton buttonGerarNConta;
    private JRadioButton radioButtonSimpleAcc;
    private JRadioButton radioButtonSpecialAcc;
    private JRadioButton radioButtonPoupancaAcc;
    private int radioAccType = 0;
    private JButton buttonInfoConta;
    private JTextField textFieldInfoConta;
    private JButton buttonInfoAll;
    private JButton buttonRender;
    private JButton buttonJuros;
    private JTextField textFieldJuros;
    private JButton buttonResumo;
    //TextFields Gerente
    private JTextField nameFieldGerente;
    private JTextField contaFieldGerente;
    private JPasswordField passwordFieldGerente;
    private JPasswordField passwordFieldConfirmGerente;
    //Components Login
    private JTextField contaFieldCliente;
    private JPasswordField passwordField;
    //Components do dashboard do Cliente
    private JButton buttonSacar;
    private JTextField valueFieldSacar;
    private JButton buttonDepositar;
    private JTextField valueFieldDepositar;
    private JButton buttonMyAcc;
    private JButton buttonExtrato;
    private JButton buttonAlterSenha;
    private JPasswordField alterPasswordConfirm;
    private JPasswordField alterPasswordField;
    private JButton buttonBackDashboard = new JButton("Sair");
    //Panels de cada menu
    private final JPanel rootPanel;
    private JPanel panelInfoConta;
    private final JPanel panelButtons = new JPanel(new ButtonAreaLayout(true, 1));
    private final CardLayout cardLayout = new CardLayout();
    private final GridBagConstraints constraints = new GridBagConstraints();

    SistemaBancario() {
        super("BANCO PARADIGMAS");

        //cardLayout.maximumLayoutSize(this);
        rootPanel = new JPanel(cardLayout);
        getContentPane().add(rootPanel);
        addListeners();
        constraints.insets=new Insets(1,1,1,1);
        constraints.ipadx=100;
        constraints.ipady=10;
        constraints.gridx=0;

        Gerente.criarConta("Alfredo", "1234", "1234".toCharArray(), Gerente.ACC_SPECIAL, 200);
        Gerente.criarConta("Leopoldo", "4321", "4321".toCharArray(), Gerente.ACC_SIMPLE, 0);
        Gerente.criarConta("Marilza", "1423", "1423".toCharArray(), Gerente.ACC_POUPANCA, 10);

    }

    private void addListeners() {

        buttonBackGerente.addActionListener(GerenteListener);

        buttonOKCriarConta.addActionListener(CriarContaListener);
        buttonBackCriarConta.addActionListener(CriarContaListener);

        buttonOkInfoConta.addActionListener(InfoContaListener);
        buttonBackInfoConta.addActionListener(InfoContaListener);

        buttonOKJuros.addActionListener(JurosListener);
        buttonBackJuros.addActionListener(JurosListener);

        buttonBackInfoAllConta.addActionListener(InfoAllContaListener);

        buttonBackLogin.addActionListener(LoginListener);
        buttonOKLogin.addActionListener(LoginListener);

        buttonOKSacar.addActionListener(SacarListener);
        buttonBackSacar.addActionListener(SacarListener);

        buttonOKDepositar.addActionListener(DepositarListener);
        buttonBackDepositar.addActionListener(DepositarListener);

        buttonBackMyAcc.addActionListener(MyAccListener);

        buttonOKAlterSenha.addActionListener(AlterSenhaListener);
        buttonBackAlterSenha.addActionListener(AlterSenhaListener);

    }

    //Menu Home
    void menuHome() {
        setTitle("Banco Paradigmas");
        JPanel panelHome = new JPanel();
        panelHome.setLayout(new GridBagLayout());
        rootPanel.add("Home", panelHome);
        constraints.fill = GridBagConstraints.BOTH;


        panelHome.add(label,constraints);

        buttonGerente = new JButton("Gerente");
        panelHome.add(buttonGerente,constraints);

        buttonCliente = new JButton("Cliente");

        panelHome.add(buttonCliente,constraints);
        buttonExit = new JButton("Sair");

        panelHome.add(buttonExit,constraints);

        cardLayout.show(rootPanel,"Home");
        buttonExit.addActionListener(HomeListener);
        buttonGerente.addActionListener(HomeListener);
        buttonCliente.addActionListener(HomeListener);

    }

    //Menu de login
    private void menuLoginCliente() {
        setTitle("Login");
        JPanel panelLoginCliente = new JPanel();
        panelLoginCliente.setLayout(new GridBagLayout());
        rootPanel.add("Login", panelLoginCliente);
        constraints.fill = GridBagConstraints.HORIZONTAL;


        panelLoginCliente.add(labelLogin,constraints);
        JLabel labelContaFieldCliente = new JLabel("Digite a conta");
        contaFieldCliente = new JTextField();
        contaFieldCliente.setToolTipText("Digite aqui a sua conta");
        JLabel labelPasswordField = new JLabel("Digite a senha");
        passwordField = new JPasswordField();
        passwordField.setToolTipText("Digite aqui a sua senha");


        panelLoginCliente.add(labelContaFieldCliente,constraints);

        panelLoginCliente.add(contaFieldCliente,constraints);

        panelLoginCliente.add(labelPasswordField,constraints);

        panelLoginCliente.add(passwordField,constraints);

        panelLoginCliente.add(buttonOKLogin,constraints);

        panelLoginCliente.add(buttonBackLogin,constraints);

        contaFieldCliente.addKeyListener(NumberListener);


        cardLayout.show(rootPanel, "Login");

    }

    //Submenu de cliente
    private void menuDashboard() {
        setTitle("Minha Conta: " + contaAtual.getnConta());
        JPanel panelDashboard = new JPanel();
        panelDashboard.setLayout(new GridBagLayout());
        rootPanel.add("Dashboard", panelDashboard);
        constraints.fill = GridBagConstraints.BOTH;


        JLabel welcomeLabel = new JLabel("Olá, " + contaAtual.getNomeCorrentista().toUpperCase());
        JLabel welcomeLabel1 = new JLabel("O que deseja fazer?");
        buttonSacar = new JButton("Sacar");
        buttonDepositar = new JButton("Depositar");
        buttonAlterSenha = new JButton("Alterar senha");
        buttonMyAcc = new JButton("Minha Conta");
        buttonBackDashboard = new JButton("Sair");

        panelDashboard.add(welcomeLabel,constraints);
        panelDashboard.add(welcomeLabel1,constraints);
        panelDashboard.add(buttonSacar,constraints);
        panelDashboard.add(buttonDepositar,constraints);
        panelDashboard.add(buttonAlterSenha,constraints);
        panelDashboard.add(buttonMyAcc,constraints);
        panelDashboard.add(buttonBackDashboard,constraints);

        cardLayout.show(rootPanel,"Dashboard");

        buttonSacar.addActionListener(DashboardListener);
        buttonDepositar.addActionListener(DashboardListener);
        buttonMyAcc.addActionListener(DashboardListener);
        buttonAlterSenha.addActionListener(DashboardListener);
        buttonBackDashboard.addActionListener(DashboardListener);

    }

    //Submenus da conta
    private void menuSacar() {
        setTitle("SAQUE");
        JPanel panelSacar = new JPanel();
        panelSacar.setLayout(new GridBagLayout());
        rootPanel.add("Sacar", panelSacar);
        constraints.fill = GridBagConstraints.BOTH;

        JLabel labelSacar = new JLabel("Digite o valor do saque");
        valueFieldSacar = new JTextField();
        valueFieldSacar.addKeyListener(NumberListener);
        panelSacar.add(labelSacar,constraints);
        panelSacar.add(valueFieldSacar,constraints);
        panelSacar.add(buttonOKSacar,constraints);
        panelSacar.add(buttonBackSacar,constraints);

        cardLayout.show(rootPanel,"Sacar");



    }

    private void menuDepositar() {
        setTitle("DEPÓSITO");
        JPanel panelDepositar = new JPanel();
        panelDepositar.setLayout(new GridBagLayout());
        rootPanel.add("Depositar", panelDepositar);
        constraints.fill = GridBagConstraints.BOTH;

        JLabel labelDepositar = new JLabel("Digite o valor do depósito");
        valueFieldDepositar = new JTextField();
        valueFieldDepositar.addKeyListener(NumberListener);
        panelDepositar.add(labelDepositar);
        panelDepositar.add(valueFieldDepositar,constraints);
        panelDepositar.add(buttonOKDepositar,constraints);
        panelDepositar.add(buttonBackDepositar,constraints);

        cardLayout.show(rootPanel,"Depositar");

    }

    private void menuMinhaConta() {
        setTitle("MINHA CONTA");
        JPanel panelMyAcc = new JPanel();
        panelMyAcc.setLayout(new BorderLayout());
        rootPanel.add("MyAcc", panelMyAcc);
        constraints.fill = GridBagConstraints.BOTH;

        JLabel labelMyAcc = new JLabel("Informaçoes da sua conta");
        JTextArea myAccTextArea = new JTextArea();
        myAccTextArea.append(contaAtual.info());
        myAccTextArea.setEditable(false);
        JScrollPane myAccScrollPane = new JScrollPane(myAccTextArea);
        panelMyAcc.add(labelMyAcc,BorderLayout.PAGE_START);
        panelMyAcc.add(myAccScrollPane);

        buttonExtrato = new JButton("Gerar extrato na tela");
        panelButtons.add(buttonExtrato);
        panelButtons.add(buttonBackMyAcc);
        panelMyAcc.add(panelButtons,BorderLayout.PAGE_END);

        cardLayout.show(rootPanel,"MyAcc");
        buttonExtrato.addActionListener(MyAccListener);

    }

    private void menuAlteraSenha() {
        setTitle("ALTERAR SENHA");
        JPanel panelAlterSenha = new JPanel();
        panelAlterSenha.setLayout(new GridBagLayout());
        rootPanel.add("AlterSenha", panelAlterSenha);
        constraints.fill = GridBagConstraints.BOTH;

        JLabel labelAlterSenha1 = new JLabel("Digite a senha atual");
        alterPasswordField = new JPasswordField();
        JLabel labelAlterSenha2 = new JLabel("Digite a senha nova");
        alterPasswordConfirm = new JPasswordField();

        panelAlterSenha.add(labelAlterSenha1,constraints);
        panelAlterSenha.add(alterPasswordField,constraints);
        panelAlterSenha.add(labelAlterSenha2,constraints);
        panelAlterSenha.add(alterPasswordConfirm,constraints);
        panelAlterSenha.add(buttonOKAlterSenha,constraints);
        panelAlterSenha.add(buttonBackAlterSenha,constraints);

        cardLayout.show(rootPanel,"AlterSenha");




    }


    //Menu de gerencia
    private void menuGerente() {
        setTitle("GERENTE");
        JPanel panelGerente = new JPanel();
        panelGerente.setLayout(new GridBagLayout());
        rootPanel.add("Gerente", panelGerente);
        constraints.fill = GridBagConstraints.BOTH;

        panelGerente.add(label,constraints);
        buttonCriarConta = new JButton("Criar conta");
        panelGerente.add(buttonCriarConta,constraints);

        buttonInfoConta = new JButton("Informações de conta");
        panelGerente.add(buttonInfoConta,constraints);

        buttonRender = new JButton("Efetuar rendimentos");
        panelGerente.add(buttonRender,constraints);

        buttonJuros = new JButton("Cobrar juros");
        panelGerente.add(buttonJuros,constraints);

        buttonInfoAll = new JButton("Informações de todas as contas");
        panelGerente.add(buttonInfoAll,constraints);

        buttonResumo = new JButton("Status do sitema");
        panelGerente.add(buttonResumo,constraints);

        panelGerente.add(buttonBackGerente,constraints);

        buttonCriarConta.addActionListener(GerenteListener);
        buttonResumo.addActionListener(GerenteListener);
        buttonRender.addActionListener(GerenteListener);
        buttonJuros.addActionListener(GerenteListener);
        buttonInfoConta.addActionListener(GerenteListener);
        buttonInfoAll.addActionListener(GerenteListener);


        cardLayout.show(rootPanel,"Gerente");

    }

    //Submenus gerencia
    private void menuCriarConta() {
        JPanel panelCriarConta = new JPanel();
        panelCriarConta.setLayout(new BoxLayout(panelCriarConta, BoxLayout.Y_AXIS));
        rootPanel.add("Criar Conta", panelCriarConta);
        JLabel labelCreateAcc = new JLabel("Criar conta:");
        JLabel labelName = new JLabel("Nome do correntista");
        nameFieldGerente = new JTextField();
        JLabel labelConta = new JLabel("Numero de conta");
        contaFieldGerente = new JTextField();
        buttonGerarNConta = new JButton("Gerar número de conta");
        buttonGerarNConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttonGerarNConta) {
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


        cardLayout.show(rootPanel,"Criar Conta");
    }

    private void menuInfoConta() {
        setTitle("INFO DE CONTA");
        panelInfoConta = new JPanel();
        panelInfoConta.setLayout(new GridBagLayout());
        rootPanel.add("InfoConta",panelInfoConta);
        constraints.fill = GridBagConstraints.BOTH;

        JLabel labelInfoConta = new JLabel("Digite o número da conta");
        textFieldInfoConta = new JTextField();
        textFieldInfoConta.addKeyListener(NumberListener);

        panelInfoConta.add(labelInfoConta,constraints);
        panelInfoConta.add(textFieldInfoConta,constraints);
        panelInfoConta.add(buttonOkInfoConta,constraints);
        panelInfoConta.add(buttonBackInfoConta,constraints);

        cardLayout.show(rootPanel,"InfoConta");


    }

    private void menuJuros() {
        setTitle("COBRAR JUROS");
        JPanel panelJuros = new JPanel();
        panelJuros.setLayout(new GridBagLayout());
        rootPanel.add("Juros", panelJuros);
        constraints.fill = GridBagConstraints.BOTH;

        JLabel labelJuros = new JLabel("Digite a taxa");
        textFieldJuros = new JTextField();
        textFieldJuros.addKeyListener(NumberListener);

        panelJuros.add(labelJuros,constraints);
        panelJuros.add(textFieldJuros,constraints);
        panelJuros.add(buttonOKJuros,constraints);
        panelJuros.add(buttonBackJuros,constraints);


        cardLayout.show(rootPanel,"Juros");
    }

    private void menuInfoAll() {
        setTitle("INFO DE TODAS AS CONTAS");
        JPanel panelInfoAllConta = new JPanel();
        panelInfoAllConta.setLayout(new BorderLayout());
        rootPanel.add("InfoAllConta", panelInfoAllConta);
        constraints.fill = GridBagConstraints.BOTH;

        JLabel labelInfoAll = new JLabel("Informações de todas as contas");
        JTextArea infoAllTextArea = new JTextArea();

        infoAllTextArea.setText("Quantidade de contas cadastradas= " + Integer.toString(Conta.getContasCadastradas()) + "\n\n");
        for (int i = 0; i < Gerente.MAX_CONTAS; i++) {
            if (Conta.contas[i] != null)
                infoAllTextArea.append(Conta.contas[i].info() + "-------\n\n");
        }
        infoAllTextArea.setEditable(false);
        JScrollPane myAccScrollPane = new JScrollPane(infoAllTextArea);

        panelInfoAllConta.add(labelInfoAll,BorderLayout.PAGE_START);
        panelInfoAllConta.add(myAccScrollPane);
        panelInfoAllConta.add(buttonBackInfoAllConta,BorderLayout.PAGE_END);

        cardLayout.show(rootPanel,"InfoAllConta");
    }

    private void menuResumo() {
        setTitle("STATUS DO SISTEMA");
        JPanel panelResumo = new JPanel();
        panelResumo.setLayout(new GridBagLayout());
        rootPanel.add("Resumo", panelResumo);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.ipadx=0;

        JLabel labelResumo = new JLabel("Status do banco");
        JTextArea resumoTextArea = new JTextArea();
        resumoTextArea.append(Conta.getStatusOfSystem());
        resumoTextArea.setEditable(false);
        JScrollPane resumoScrollPane = new JScrollPane(resumoTextArea);

        panelResumo.add(labelResumo,constraints);
        panelResumo.add(resumoScrollPane,constraints);
        panelResumo.add(buttonBackResumo, constraints);

        cardLayout.show(rootPanel,"Resumo");

        buttonBackResumo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuGerente();
            }
        });
        constraints.ipadx=100;
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
                menuHome();
            } else if (e.getSource() == buttonCliente) {
                menuLoginCliente();
            } else if (e.getSource() == buttonGerente) {
                menuGerente();
            }


        }
    }

    //===================================================================

    private class GerenteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == buttonCriarConta) {

                menuCriarConta();
            } else if (e.getSource() == buttonInfoConta) {
                menuInfoConta();
            } else if (e.getSource() == buttonRender) {

                int resp = JOptionPane.showOptionDialog(null, "DESEJA EFETUAR OS RENDIMENTOS NAS CONTAS POUPANÇA?", "EFETUAR RENDIMENTOS", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (resp == JOptionPane.YES_OPTION) {
                    int accRendidas = Gerente.incrementarJuros();

                    JOptionPane.showMessageDialog(null, "RENDIMENTOS EFETUADOS EM " + Integer.toString(accRendidas) + " CONTAS POUPANÇA", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (e.getSource() == buttonJuros) {
                menuJuros();
            } else if (e.getSource() == buttonInfoAll) {
                menuInfoAll();
            } else if (e.getSource() == buttonBackGerente) {
                menuHome();
            } else if (e.getSource() == buttonResumo) {
                menuResumo();
            }
        }
    }

    private class CriarContaButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == buttonOKCriarConta) {


                try {
                    if (Conta.getContasCadastradas() >= Gerente.MAX_CONTAS)
                        throw new MaxContasException("MAXIMO DE CONTAS ATINGIDO");

                    if (contaFieldGerente.getText().isEmpty()) throw new CampoVazioException("CAMPO(S) VAZIO(S)");
                    if (nameFieldGerente.getText().isEmpty()) throw new CampoVazioException("CAMPO(S) VAZIO(S)");

                    if (!Arrays.equals(passwordFieldGerente.getPassword(), passwordFieldConfirmGerente.getPassword()))
                        throw new SenhasDiferemException("SENHAS DIFEREM");


                    if (radioAccType == ACC_SIMPLE) {
                        Gerente.criarConta(nameFieldGerente.getText(), contaFieldGerente.getText(), passwordFieldGerente.getPassword(), ACC_SIMPLE, 0);
                        JOptionPane.showMessageDialog(null, "CONTA CRIADA", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                        menuGerente();
                    } else if (radioAccType == ACC_POUPANCA) {

                        try {
                            double rend = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o rendimento", "RENDIMENTO", JOptionPane.QUESTION_MESSAGE));
                            if (rend < 0) {
                                throw new ValorInvalidoException("VALOR INVALIDO");
                            }
                            Gerente.criarConta(nameFieldGerente.getText(), contaFieldGerente.getText(), passwordFieldGerente.getPassword(), ACC_POUPANCA, rend);
                            JOptionPane.showMessageDialog(null, "CONTA CRIADA", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                            menuGerente();
                        } catch (NumberFormatException num) {
                            throw new ValorInvalidoException("VALOR INVALIDO DIGITADO");
                        }
                    } else if (radioAccType == ACC_SPECIAL) {

                        try {
                            double lim = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o limite", "LIMITE", JOptionPane.QUESTION_MESSAGE));
                            if (lim < 0) {
                                throw new ValorInvalidoException("VALOR INVALIDO");
                            }
                            Gerente.criarConta(nameFieldGerente.getText(), contaFieldGerente.getText(), passwordFieldGerente.getPassword(), ACC_SPECIAL, lim);
                            JOptionPane.showMessageDialog(null, "CONTA CRIADA", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                            menuGerente();
                        } catch (NumberFormatException num) {
                            throw new ValorInvalidoException("VALOR INVALIDO DIGITADO");
                        }
                    } else {
                        throw new CampoVazioException("CAMPO(S) VAZIO(S)");
                    }

                } catch (SenhasDiferemException key) {
                    JOptionPane.showMessageDialog(null, "SENHAS NAO CONFEREM", "ERRO", JOptionPane.ERROR_MESSAGE);

                } catch (ContaExistenteException contaExiste) {
                    JOptionPane.showMessageDialog(null, "NUMERO DE CONTA EXISTENTE", "ERRO", JOptionPane.ERROR_MESSAGE);

                } catch (CampoVazioException campoVazio) {
                    JOptionPane.showMessageDialog(null, "PREENCHA TODAS AS INFORMAÇÕES", "ERRO", JOptionPane.ERROR_MESSAGE);

                } catch (ValorInvalidoException valorInvalido) {
                    JOptionPane.showMessageDialog(null, "VALOR INVÁLIDO DIGITADO", "ERRO", JOptionPane.ERROR_MESSAGE);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, exc.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                }

            } else if (e.getSource() == buttonBackCriarConta) {
                menuGerente();
            }

        }
    }

    private class TipoContaRadioListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getSource() == radioButtonSimpleAcc) {
                radioAccType = ACC_SIMPLE;
            } else if (e.getSource() == radioButtonPoupancaAcc) {
                radioAccType = ACC_POUPANCA;
            } else if (e.getSource() == radioButtonSpecialAcc) {
                radioAccType = ACC_SPECIAL;
            }

        }
    }

    private class InfoContaButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == buttonOkInfoConta) {

                try {
                    Conta acc = Gerente.contaExiste(textFieldInfoConta.getText());

                    panelInfoConta = new JPanel(new GridBagLayout());
                    rootPanel.add("InfoConta2",panelInfoConta);
                    constraints.fill = GridBagConstraints.BOTH;
                    JTextArea infoContaTextArea = new JTextArea();
                    infoContaTextArea.append(acc.info());
                    panelInfoConta.add(infoContaTextArea,constraints);
                    panelInfoConta.add(buttonBackInfoConta,constraints);
                    cardLayout.show(rootPanel,"InfoConta2");
                    buttonOkInfoConta.removeActionListener(InfoContaListener);

                } catch (ContaInexistenteException contaNExiste) {
                    JOptionPane.showMessageDialog(null, "CONTA NAO ENCONTRADA", "ERRO", JOptionPane.ERROR_MESSAGE);
                    menuInfoConta();
                }
            } else if (e.getSource() == buttonBackInfoConta) {
                menuGerente();
            }

        }
    }

    private class InfoAllContaButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == buttonBackInfoAllConta) {
                menuGerente();
            }
        }
    }

    private class JurosButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


            if (e.getSource() == buttonOKJuros) {

                int accJuros = Gerente.cobrarJuros(Double.parseDouble(textFieldJuros.getText()));

                JOptionPane.showMessageDialog(null, "JUROS COBRADOS DE " + Integer.toString(accJuros) + " CONTAS ESPECIAIS", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                menuGerente();

            } else if (e.getSource() == buttonBackJuros) {
                menuGerente();
            }


        }
    }

    //===================================================================

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


            if (e.getSource() == buttonOKLogin) {
                try {
                    contaAtual = Gerente.contaExiste(contaFieldCliente.getText());
                    contaAtual.verificaSenha(passwordField.getPassword());
                    menuDashboard();

                } catch (SenhaIncorretaException senhaErrada) {
                    JOptionPane.showMessageDialog(null, "Número de conta e/ou senha incorreto(s)!", "ERRO", JOptionPane.ERROR_MESSAGE);

                } catch (ContaInexistenteException contaNExiste) {
                    JOptionPane.showMessageDialog(null, "Número de conta e/ou senha incorreto(s)!", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == buttonBackLogin) {
                menuHome();
            }
        }
    }

    private class DashboardButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == buttonSacar) {
                menuSacar();
            } else if (e.getSource() == buttonDepositar) {
                menuDepositar();
            } else if (e.getSource() == buttonAlterSenha) {

                menuAlteraSenha();
            } else if (e.getSource() == buttonMyAcc) {
                menuMinhaConta();
            } else if (e.getSource() == buttonBackDashboard) {
                int res = JOptionPane.showConfirmDialog(null, "Tem certeza?", "SAIR", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (res == JOptionPane.YES_OPTION) {
                    menuHome();
                }
            }
        }
    }

    private class SacarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == buttonOKSacar) {
                try {

                    if (valueFieldSacar.getText().isEmpty()) {
                        throw new ValorInvalidoException("VALOR INVALIDO");
                    }

                    contaAtual.sacar(Double.parseDouble(valueFieldSacar.getText()));
                    JOptionPane.showMessageDialog(null, "SAQUE EFETUADO\nSALDO ATUAL = R$ " + Double.toString(contaAtual.getSaldo()), "INFO", JOptionPane.INFORMATION_MESSAGE);
                    menuDashboard();
                } catch (SaldoInsuficienteException semGrana) {
                    JOptionPane.showMessageDialog(null, "SAQUE NãO EFETUADO\n" + semGrana.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                } catch (ValorInvalidoException invalido) {
                    JOptionPane.showMessageDialog(null, "DIGITE UM VALOR VÁLIDO!", "ERRO", JOptionPane.ERROR_MESSAGE);
                }

            } else if (e.getSource() == buttonBackSacar) {
                menuDashboard();
            }

        }
    }

    private class DepositarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


            if (e.getSource() == buttonOKDepositar) {
                try {

                    if (valueFieldDepositar.getText().isEmpty()) {
                        throw new ValorInvalidoException("VALOR INVALIDO");
                    }

                    contaAtual.depositar(Double.parseDouble(valueFieldDepositar.getText()));
                    JOptionPane.showMessageDialog(null, "DEPÓSITO EFETUADO\nSALDO ATUAL = R$ " + Double.toString(contaAtual.getSaldo()), "INFO", JOptionPane.INFORMATION_MESSAGE);
                    menuDashboard();

                } catch (ValorInvalidoException invalido) {
                    JOptionPane.showMessageDialog(null, "DIGITE UM VALOR VÁLIDO!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    menuDepositar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "DEPÓSITO NÃO EFETUADO " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == buttonBackDepositar) {
                menuDashboard();

            }
        }
    }

    private class AlterSenhaButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == buttonOKAlterSenha) {

                try {
                    contaAtual.alteraSenha(alterPasswordField.getPassword(), alterPasswordConfirm.getPassword());
                    JOptionPane.showMessageDialog(null, "SENHA ALTERADA COM SUCESSO!", "INFO", JOptionPane.INFORMATION_MESSAGE);
                    menuDashboard();
                } catch (SenhaIncorretaException senhaAntErrada) {
                    JOptionPane.showMessageDialog(null, "SENHA ANTIGA INCORRETA!", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == buttonBackAlterSenha) {
                menuDashboard();
            }
        }
    }

    private class MyAccButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == buttonBackMyAcc) {
                panelButtons.remove(buttonExtrato);
                panelButtons.updateUI();
                menuDashboard();
            } else if (e.getSource() == buttonExtrato) {

                JTextArea extratoArea = new JTextArea();
                extratoArea.append(contaAtual.getLog());
                extratoArea.setEditable(false);
                JScrollPane paneScrollExtrato = new JScrollPane(extratoArea);
                JOptionPane.showMessageDialog(null, paneScrollExtrato, "EXTRATO", JOptionPane.PLAIN_MESSAGE);

            }
        }
    }

    private class NumberKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            String caracters = "0987654321";
            if (!caracters.contains(e.getKeyChar() + "")) {
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