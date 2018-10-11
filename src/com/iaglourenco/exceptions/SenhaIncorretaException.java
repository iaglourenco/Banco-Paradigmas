/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco.exceptions;

public class SenhaIncorretaException extends RuntimeException {

    public SenhaIncorretaException(String message){
        super(message);
    }
}
