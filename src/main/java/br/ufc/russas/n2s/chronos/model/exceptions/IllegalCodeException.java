/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.chronos.model.exceptions;

import java.io.Serializable;

/**
 *
 * @author N2S
 */
public class IllegalCodeException extends IllegalArgumentException implements Serializable{

    public IllegalCodeException() {

    }

    public IllegalCodeException(String message) {
        super(message);
    }
}
