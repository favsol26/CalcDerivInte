/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factorizacion;

import Principal.CDI;
import Principal.ExpresionAlgebraica;
import Principal.SintaxisExpresiones;
import java.util.ArrayList;

/**
 *
 * @author Alexander Batista
 */
public class Factorizar extends CDI {

    static int[][] anterior;
    static ExpresionAlgebraica[] resuelto;

    public static void piramide(int limite) {
        anterior = new int[limite + 1][limite + 1];
        anterior[0][0] = 1;
        anterior[1][0] = 1;
        anterior[1][1] = 1;
        for (int i = 0; i < limite + 1; i++) {
            for (int j = 0; j < limite + 1; j++) {
                if (i != 0) {
                    anterior[i][j] = anterior[i - 1][j];
                }
                for (int k = 0; k < i; k++) {
                    if (k == 0) {
                        anterior[i][k] = 1;
                    } else {
                        anterior[i][k] = anterior[i - 1][k - 1] + anterior[i - 1][k];
                    }
                }
            }
            anterior[i][i] = 1;
        }
    }

    public static ExpresionAlgebraica[] BinomioNewton(int pos, ExpresionAlgebraica[] parte, String op) {
        resuelto = new ExpresionAlgebraica[pos + 1];
        int c = pos, t = 0, z = 0, signo, signo2;
        float coef;
        ArrayList exponente = new ArrayList();
        String sig, cadena = "";
        for (int i = 0; i < pos + 1; i++) {
            if (parte[0].getSimbolo().equals("-")) {
                signo = -1;
            } else {
                signo = 1;
            }
            if (parte[1].getSimbolo().equals("-")) {
                signo2 = -1;
            } else {
                signo2 = 1;
            }

            coef = anterior[pos][i] * ((int) (Math.pow((double) (parte[0].getCoeficiente() * signo), c)) * (int) (Math.pow((double) (parte[1].getCoeficiente() * signo2), t)));
            if (coef < 0) {
                sig = "-";
                coef = coef * -1;
            } else {
                sig = "+";
            }
            resuelto[i] = new ExpresionAlgebraica(sig,
                    coef, parte[0].getVariable().concat(parte[1].getVariable()),
                    String.valueOf((Integer.parseInt(parte[0].getExponente()) * c)) + "&" + String.valueOf((Integer.parseInt(parte[1].getExponente()) * t)));
            c--;
            t++;
        }

        for (ExpresionAlgebraica resuelto1 : resuelto) {
            for (int k = 0; k < resuelto1.getExponente().length(); k++) {
                if ('&' == resuelto1.getExponente().charAt(k)) {
                    exponente.add(resuelto1.getExponente().substring(0, k));
                    exponente.add(resuelto1.getExponente().substring(k + 1, resuelto1.getExponente().length()));
                }
            }
        }
        for (ExpresionAlgebraica resuelto1 : resuelto) {
            cadena = cadena + resuelto1.getSimbolo() + resuelto1.getCoeficiente();
            if (exponente.get(z).equals("0")) {
                cadena = cadena + resuelto1.getVariable().substring(1, resuelto1.getVariable().length()) + "^" + exponente.get(z + 1);
            } else if (exponente.get(z + 1).equals("0")) {
                cadena = cadena + resuelto1.getVariable().substring(0, 1) + "^" + exponente.get(z);
            } else {
                if (!resuelto1.getVariable().substring(0, 1).equals(resuelto1.getVariable().substring(1, resuelto1.getVariable().length()))) {
                    cadena = cadena + resuelto1.getVariable().substring(0, 1) + "^" + exponente.get(z) + resuelto1.getVariable().substring(1, resuelto1.getVariable().length()) + "^" + exponente.get(z + 1);
                } else {
                    cadena = cadena + resuelto1.getVariable().substring(0, 1) + "^" + String.valueOf(Integer.valueOf(exponente.get(z).toString()) + Integer.valueOf(exponente.get(z + 1).toString()));
                }
            }
            z = z + 2;
        }

        if (cadena.charAt(0) == '+') {
            return SintaxisExpresiones.Sintaxis(cadena.substring(1, cadena.length()), op, false);
        } else {
            return SintaxisExpresiones.Sintaxis(cadena, op, false);
        }
    }
}
