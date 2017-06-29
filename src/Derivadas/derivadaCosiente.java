/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Derivadas;

import Factorizacion.FactorizarImpl;
import Principal.CDI;
import Principal.ExpresionAlgebraica;
import Principal.SintaxisExpresiones;

/**
 *
 * @author Alexander Batista
 */
public class derivadaCosiente extends CDI {

    public static ExpresionAlgebraica[] Cosiente(String op) {
        ExpresionAlgebraica[] Division;
        int h = 0, k;
        String cadena = "";
        k = (mult1.length + mult2.length) * 2;

        resultado = new ExpresionAlgebraica[k];
        String signo;

        for (int i = 0; i < mult2.length; i++) {
            for (ExpresionAlgebraica mult1d1 : mult1d) {
                if (mult2[i].getSimbolo().equals(mult1d[i].getSimbolo())) {
                    signo = "+";
                } else {
                    signo = "-";
                }
                resultado[h] = new ExpresionAlgebraica(signo, mult2[i].getCoeficiente() * mult1d1.getCoeficiente(), mult1[i].getVariable(), String.valueOf(Float.valueOf(mult2[i].getExponente()) + Float.valueOf(mult1d1.getExponente())));
                h++;
            }
        }
        for (int i = 0; i < mult1.length; i++) {
            for (ExpresionAlgebraica mult2d1 : mult2d) {
                if (mult1[i].getSimbolo().equals(mult2d[i].getSimbolo()) && mult1[i].getSimbolo().equals("-")) {
                    signo = "+";
                } else {
                    signo = "-";
                }
                resultado[h] = new ExpresionAlgebraica(signo, mult1[i].getCoeficiente() * mult2d1.getCoeficiente(), mult1[i].getVariable(), String.valueOf(Float.valueOf(mult1[i].getExponente()) + Float.valueOf(mult2d1.getExponente())));
                h++;
            }
        }

        FactorizarImpl fac = new FactorizarImpl();
        if (mult2.length > 1) {
            fac.limite = 2;
            fac.todo = mult2;
            fac.llamar(op);
        } else {
            float num;
            num = Float.valueOf(mult2[0].getSimbolo().concat(String.valueOf(mult2[0].getCoeficiente())));
            num = Float.valueOf(String.valueOf(Math.pow((double) num, 2)));
            if (num < 0) {
                mult2[0].setSimbolo("-");
                mult2[0].setCoeficiente(num * -1);
            } else {
                mult2[0].setSimbolo("+");
                mult2[0].setCoeficiente(num);
            }
            mult2[0].setExponente(String.valueOf(Integer.valueOf(mult2[0].getExponente()) * 2));
        }
        mult2C = new ExpresionAlgebraica[llenado.length];
        for (int i = 0; i < llenado.length; i++) {
            mult2C[i] = new ExpresionAlgebraica(llenado[i].getSimbolo(), llenado[i].getCoeficiente(), llenado[i].getVariable(), llenado[i].getExponente());
        }

        for (ExpresionAlgebraica resultado1 : resultado) {
            if (resultado1 != null) {
                cadena = cadena + resultado1.getSimbolo() + resultado1.getCoeficiente() + resultado1.getVariable() + "^" + resultado1.getExponente();
            }
        }
        if (cadena.charAt(0) == '+') {
            SintaxisExpresiones.Sintaxis(cadena.substring(1, cadena.length()), op);
        } else {
            SintaxisExpresiones.Sintaxis(cadena, op);
        }
        resultado = new ExpresionAlgebraica[llenado.length];
        for (int i = 0; i < llenado.length; i++) {
            resultado[i] = new ExpresionAlgebraica(llenado[i].getSimbolo(), llenado[i].getCoeficiente(), llenado[i].getVariable(), llenado[i].getExponente());
        }

        int t = 0;
        Division = new ExpresionAlgebraica[(resultado.length * mult2C.length)];
        for (ExpresionAlgebraica resultado1 : resultado) {
            for (ExpresionAlgebraica mult2C1 : mult2C) {
                if (resultado1.getSimbolo().equals(mult2C1.getSimbolo())) {
                    signo = "+";
                } else {
                    signo = "-";
                }
                Division[t] = new ExpresionAlgebraica(signo, resultado1.getCoeficiente() / mult2C1.getCoeficiente(), resultado1.getVariable(), String.valueOf(Float.valueOf(resultado1.getExponente()) - Float.valueOf(mult2C1.getExponente())));
                t++;
            }
        }
        return Division;
    }
}
