/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Derivadas;

import Principal.ExpresionAlgebraica;
import Principal.CDI;
import Principal.SintaxisExpresiones;

/**
 *
 * @author Alexander Batista
 */
public class Exponencial extends CDI {

    static ExpresionAlgebraica[] exp;

    public static void main(String[] args) {
        String cad = "e^((x^5+2)(x^2+5))";
//        System.out.print(deriva(exp)[0].getSimbolo()
//                +deriva(exp)[0].getCoeficiente()+deriva(exp)[0].getVariable()
//                +"^"+deriva(exp)[0].getExponente()); 
//        
        System.out.println(exponencial(cad));
    }

    private static String exponencial(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));

        String ExpExter = "e^(".concat(ExpInter) + ")";

        exp = SintaxisExpresiones.Sintaxis(ExpInter, "d");

        cad = "";

        for (ExpresionAlgebraica exp1 : exp) {
            cad = cad.concat(String.format("%s%s%s^%s", exp1.getSimbolo(),
                    exp1.getCoeficiente(), exp1.getVariable(), exp1.getExponente()));
        }

        cad = cad.concat(" ");
        cad = cad.concat(ExpExter);
        //  cad = cad.concat(ExpInter);

        return cad;
    }

    private static ExpresionAlgebraica[] deriva(String exp) {
        String coef = exp.substring(0, exp.indexOf("e"));
        String e = exp.substring(exp.indexOf("e"), exp.length());
        String der = exp.substring(exp.indexOf("^") + 2, exp.length() - 1);
        ExpresionAlgebraica[] exponencial = SintaxisExpresiones.Sintaxis(der, "d");
        resultado = exponencial;
        System.out.println(coef);
        System.out.println(e);
        System.out.println(der);
        return resultado;
    }
}
