/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Derivadas.derivadaCosiente;
import Derivadas.derivadaPotencia;
import Principal.Enrrutar;
import Principal.ExpresionAlgebraica;
import Principal.ProcesarFunciones;
import Principal.SintaxisExpresiones;
import java.util.ArrayList;

/**
 *
 * @author Alexander Batista
 */
public class procesoDerivadaCociente extends Enrrutar {

    public ExpresionAlgebraica[] proceso(ArrayList Segmentos, ArrayList Signos, String op) {
        String cadena = "";
        if (Segmentos.get(0).toString().substring(0, 3).equals("ln(")) {
            SintaxisExpresiones.Sintaxis(Segmentos.get(0).toString().substring(4, Segmentos.get(0).toString().length() - 1),op);
        } else {
            SintaxisExpresiones.Sintaxis(Segmentos.get(0).toString().substring(1, Segmentos.get(0).toString().length() - 1),op);
        }

        mul1 = new ExpresionAlgebraica[llenado.length];

        for (int j = 0; j < llenado.length; j++) {
            mul1[j] = new ExpresionAlgebraica(llenado[j].getSimbolo(), llenado[j].getCoeficiente(), llenado[j].getVariable(), llenado[j].getExponente());
        }
        if (Segmentos.get(0).toString().substring(0, 3).equals("ln(")) {
            SintaxisExpresiones.Sintaxis(Segmentos.get(1).toString().substring(1, Segmentos.get(1).toString().length() - 2),op);
        } else {
            SintaxisExpresiones.Sintaxis(Segmentos.get(1).toString().substring(1, Segmentos.get(1).toString().length() - 1),op);
        }

        mul2 = new ExpresionAlgebraica[llenado.length];
        for (int j = 0; j < llenado.length; j++) {
            mul2[j] = new ExpresionAlgebraica(llenado[j].getSimbolo(), llenado[j].getCoeficiente(), llenado[j].getVariable(), llenado[j].getExponente());
        }

        derivadaPotencia.derivada_Potencia(mul1);
        der1 = new ExpresionAlgebraica[resultado.length];
        for (int j = 0; j < resultado.length; j++) {
            der1[j] = new ExpresionAlgebraica(resultado[j].getSimbolo(), resultado[j].getCoeficiente(), resultado[j].getVariable(), resultado[j].getExponente());
        }

        derivadaPotencia.derivada_Potencia(mul2);
        der2 = new ExpresionAlgebraica[resultado.length];
        for (int j = 0; j < resultado.length; j++) {
            der2[j] = new ExpresionAlgebraica(resultado[j].getSimbolo(), resultado[j].getCoeficiente(), resultado[j].getVariable(), resultado[j].getExponente());
        }

        resultado = derivadaCosiente.Cosiente(op);
        Segmentos.clear();
        Signos.clear();
        for (int j = 0; j < resultado.length; j++) {
            Segmentos.add(resultado[j].getCoeficiente() + resultado[j].getVariable() + "^" + resultado[j].getExponente());
            if (j != 0) {
                Signos.add(resultado[j].getSimbolo());
            }
        }
        ProcesarFunciones.jeraquia(Segmentos, Signos);
        return llenado;
    }
}
