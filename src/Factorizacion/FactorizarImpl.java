/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factorizacion;

import Principal.ExpresionAlgebraica;

public class FactorizarImpl extends Factorizar {

    public int limite;
    public ExpresionAlgebraica[] todo;

    public void llamar(String op) {
        piramide(limite);
        BinomioNewton(limite, todo,op);
    }
}
