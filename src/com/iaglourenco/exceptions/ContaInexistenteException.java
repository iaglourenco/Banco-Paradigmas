/*
 * Copyright (c) Iago Lourenço, 2018.
 *
 */

package com.iaglourenco.exceptions;

public class ContaInexistenteException extends RuntimeException {

    public ContaInexistenteException(String message){
        super(message);
    }
}
