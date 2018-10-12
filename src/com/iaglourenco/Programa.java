/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco;

import javax.swing.*;

class Programa {



    public static void main(String[] args){

        SistemaBancario sys = new SistemaBancario();
        sys.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        sys.setLocation(200,200);
        sys.menuHome();
        sys.setSize(400,400);
        sys.setVisible(true);
        sys.setResizable(false);




    }

}
