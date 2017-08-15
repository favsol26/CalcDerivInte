/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integrales;

import Principal.CDI;
import Principal.ExpresionAlgebraica;
import Principal.SintaxisExpresiones;
import java.util.ArrayList;

/**
 *
 * @author Alexander Batista
 */
public class integralCosiente extends CDI {

    static ExpresionAlgebraica[] Expre;
    static ExpresionAlgebraica[] cos1;
    static ExpresionAlgebraica[] cos2;

    public static String cosiente(ArrayList Segmentos, boolean cos, String Dif) {
        String Resultado = "";

        if (cos) {
            Expre = SintaxisExpresiones.Sintaxis(Segmentos.get(1).toString(), "i", false, Dif);

            Resultado = "ln (";
            for (ExpresionAlgebraica finalizado1 : Expre) {
                if (finalizado1 != null) {
                    if (finalizado1.getSimbolo().equals("&")) {
                        Resultado = Resultado + "*";
                    } else if (finalizado1.getCoeficiente() == 0) {

                    } else if (finalizado1.getSimbolo().equals("/")) {
                        Resultado = Resultado + "/";
                    } else if (finalizado1.getExponente().equals("0.0")) {
                        Resultado = Resultado + finalizado1.getSimbolo() + finalizado1.getCoeficiente();
                    } else if (finalizado1.getCoeficiente() * 100 == Math.round(finalizado1.getCoeficiente()) * 100) {
                        Resultado = Resultado + finalizado1.getSimbolo() + Math.round(finalizado1.getCoeficiente()) + finalizado1.getVariable() + "^" + finalizado1.getExponente();
                    } else {
                        Resultado = Resultado + finalizado1.getSimbolo() + finalizado1.getCoeficiente() + finalizado1.getVariable() + "^" + finalizado1.getExponente();
                    }
                }
            }
            Resultado = Resultado + ")";

        }
        
        return Resultado;
  }
}
