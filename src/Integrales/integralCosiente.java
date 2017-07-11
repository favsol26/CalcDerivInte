/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integrales;

import Principal.CDI;
import Principal.ExpresionAlgebraica;
import Principal.ProcesarFunciones;
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

    public static ExpresionAlgebraica[] cosiente(ArrayList Segmentos, boolean cos) {
        String cad = "";

        if (cos) {
            Expre = SintaxisExpresiones.Sintaxis(Segmentos.get(1).toString().substring(Segmentos.get(1).toString().indexOf("("), Segmentos.get(1).toString().lastIndexOf(")")), "i", false);

            cad = "ln (";
            for (ExpresionAlgebraica finalizado1 : Expre) {
                if (finalizado1 != null) {
                    if (finalizado1.getSimbolo().equals("&")) {
                        cad = cad + "*";
                    } else if (finalizado1.getCoeficiente() == 0) {

                    } else if (finalizado1.getSimbolo().equals("/")) {
                        cad = cad + "/";
                    } else if (finalizado1.getExponente().equals("0.0")) {
                        cad = cad + finalizado1.getSimbolo() + finalizado1.getCoeficiente();
                    } else if (finalizado1.getCoeficiente() * 100 == Math.round(finalizado1.getCoeficiente()) * 100) {
                        cad = cad + finalizado1.getSimbolo() + Math.round(finalizado1.getCoeficiente()) + finalizado1.getVariable() + "^" + finalizado1.getExponente();
                    } else {
                        cad = cad + finalizado1.getSimbolo() + finalizado1.getCoeficiente() + finalizado1.getVariable() + "^" + finalizado1.getExponente();
                    }
                }
            }
            cad = cad + ")";
            System.out.println(cad);
        } else {
            for (int i = 0; i < Segmentos.size(); i++) {
                Segmentos.set(i, Segmentos.get(i).toString().substring(1, Segmentos.get(i).toString().length() - 1));
            }
            cos1 = SintaxisExpresiones.Sintaxis(Segmentos.get(0).toString(), "i", false);
            cos2 = SintaxisExpresiones.Sintaxis(Segmentos.get(1).toString(), "i", false);
            Expre = Operaciones.Cociente.CocienteVariables(cos1, cos2);
            for (ExpresionAlgebraica Expre1 : Expre) {
                cad = cad + Expre1.getSimbolo() + Expre1.getCoeficiente() + Expre1.getVariable() + "^" + Expre1.getExponente();
            }
            if (cad.charAt(0) == '+') {
                cad = cad.substring(1, cad.length() - 1);
            }
            
        }
        return SintaxisExpresiones.Sintaxis(cad, "i", true);
    }
}
