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

    public static ExpresionAlgebraica[] Cosiente(String op, String dif) {
        ExpresionAlgebraica[] Division;
        int h = 0, k;
        String cadena = "";
        k = (mul1.length + mul2.length) * 2;

        resultado = new ExpresionAlgebraica[k];
        String signo;

        for (int i = 0; i < mul2.length; i++) {
            for (ExpresionAlgebraica der11 : der1) {
                if (mul2[i].getSimbolo().equals(der1[i].getSimbolo())) {
                    signo = "+";
                } else {
                    signo = "-";
                }
                resultado[h] = new ExpresionAlgebraica(signo, mul2[i].getCoeficiente() * der11.getCoeficiente(), mul1[i].getVariable(), String.valueOf(Float.valueOf(mul2[i].getExponente()) + Float.valueOf(der11.getExponente())));
                h++;
            }
        }
        for (int i = 0; i < mul1.length; i++) {
            for (ExpresionAlgebraica der21 : der2) {
                if (mul1[i].getSimbolo().equals(der2[i].getSimbolo()) && mul1[i].getSimbolo().equals("-")) {
                    signo = "+";
                } else {
                    signo = "-";
                }
                resultado[h] = new ExpresionAlgebraica(signo, mul1[i].getCoeficiente() * der21.getCoeficiente(), mul1[i].getVariable(), String.valueOf(Float.valueOf(mul1[i].getExponente()) + Float.valueOf(der21.getExponente())));
                h++;
            }
        }

        FactorizarImpl fac = new FactorizarImpl();
        if (mul2.length > 1) {
            fac.limite = 2;
            fac.todo = mul2;
            fac.llamar(op, dif);
        } else {
            float num;
            num = Float.valueOf(mul2[0].getSimbolo().concat(String.valueOf(mul2[0].getCoeficiente())));
            num = Float.valueOf(String.valueOf(Math.pow((double) num, 2)));
            if (num < 0) {
                mul2[0].setSimbolo("-");
                mul2[0].setCoeficiente(num * -1);
            } else {
                mul2[0].setSimbolo("+");
                mul2[0].setCoeficiente(num);
            }
            mul2[0].setExponente(String.valueOf(Integer.valueOf(mul2[0].getExponente()) * 2));
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
            resultado = SintaxisExpresiones.Sintaxis(cadena.substring(1, cadena.length()), op, false, dif);
        } else {
            resultado = SintaxisExpresiones.Sintaxis(cadena, op, false, dif);
        }
        for (int i = 0; i < llenado.length; i++) {
            resultado[i] = new ExpresionAlgebraica(llenado[i].getSimbolo(), llenado[i].getCoeficiente(), llenado[i].getVariable(), llenado[i].getExponente());
        }

        int t = 0;
        Division = new ExpresionAlgebraica[(resultado.length + mult2C.length) + 1];
        for (ExpresionAlgebraica resultado1 : resultado) {
            Division[t] = new ExpresionAlgebraica(resultado1.getSimbolo(), resultado1.getCoeficiente(), resultado1.getVariable(), resultado1.getExponente());
            t++;
        }
        Division[t]= new ExpresionAlgebraica("/", 0, null, null);
        t++;
        for (ExpresionAlgebraica mul2C1 : mult2C) {
            Division[t] = new ExpresionAlgebraica(mul2C1.getSimbolo(), mul2C1.getCoeficiente(), mul2C1.getVariable(), mul2C1.getExponente());
            t++;
        }
        return Division;
    }
}
