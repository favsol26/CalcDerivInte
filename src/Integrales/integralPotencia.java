/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integrales;

import Principal.ExpresionAlgebraica;

/**
 *
 * @author Alexander Batista
 */
public class integralPotencia {

    static ExpresionAlgebraica[] resu;

    public static ExpresionAlgebraica[] integral_Potencia(ExpresionAlgebraica[] integrar, String Dif) {
        String signo;
        resu = integrar;
        float exp, coef, res;
        for (int i = 0; i < resu.length; i++) {
            if (!"0".equals(resu[i].getExponente())) {
                if (!"-1".equals(resu[i].getExponente())) {
                    exp = Float.valueOf(resu[i].getExponente());
                    coef = Float.valueOf(resu[i].getSimbolo() + "" + resu[i].getCoeficiente());
                    exp++;
                    res = coef / exp;
                    if (res < 0) {
                        signo = "-";
                        res = (coef / exp) * -1;
                    } else {
                        signo = "+";
                    }
                    if (resu[i].getVariable().equals(Dif)) {
                        resu[i] = new ExpresionAlgebraica(signo, res, resu[i].getVariable(), String.valueOf(exp));
                    } else {
                        resu[i] = new ExpresionAlgebraica(signo, res, resu[i].getVariable().concat(Dif), String.valueOf(exp));

                    }
                } else {
                    System.out.println("Imposible hacer esta Operation");
                }
            } else {
                resu[i] = new ExpresionAlgebraica(resu[i].getSimbolo(), resu[i].getCoeficiente(), resu[i].getVariable(), "1");
            }
        }
        return resu;
    }
}
