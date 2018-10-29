/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Programa {



    public static void main(String[] args){

        SistemaBancario sys = new SistemaBancario();
        sys.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        sys.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int res = JOptionPane.showConfirmDialog(null,"Você tem certeza que quer sair?","SAIR",JOptionPane.YES_NO_OPTION);
                if(res == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        sys.setSize(400,400);
        sys.setLocationRelativeTo(null);
        sys.menuHome();
        sys.setVisible(true);
        sys.setResizable(false);


    }

}
