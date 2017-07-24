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

    public ExpresionAlgebraica[] proceso(ArrayList Segmentos, ArrayList Signos, String op, String Dif) {
        String cadena = "";
        if (Segmentos.get(0).toString().substring(0, 3).equals("ln(")) {
            mul1 = SintaxisExpresiones.Sintaxis(Segmentos.get(0).toString().substring(4, Segmentos.get(0).toString().length() - 1), op, false, Dif);
        } else {
            mul1 = SintaxisExpresiones.Sintaxis(Segmentos.get(0).toString().substring(1, Segmentos.get(0).toString().length() - 1), op, false, Dif);
        }
        if (Segmentos.get(0).toString().substring(0, 3).equals("ln(")) {
            mul2 = SintaxisExpresiones.Sintaxis(Segmentos.get(1).toString().substring(1, Segmentos.get(1).toString().length() - 2), op, false, Dif);
        } else {
            mul2 = SintaxisExpresiones.Sintaxis(Segmentos.get(1).toString().substring(1, Segmentos.get(1).toString().length() - 1), op, false, Dif);
        }
        derivadaPotencia.derivada_Potencia(mul1, Dif);
        der1 = new ExpresionAlgebraica[resultado.length];
        for (int j = 0; j < resultado.length; j++) {
            der1[j] = new ExpresionAlgebraica(resultado[j].getSimbolo(), resultado[j].getCoeficiente(), resultado[j].getVariable(), resultado[j].getExponente());
        }
        derivadaPotencia.derivada_Potencia(mul2, Dif);
        der2 = new ExpresionAlgebraica[resultado.length];
        for (int j = 0; j < resultado.length; j++) {
            der2[j] = new ExpresionAlgebraica(resultado[j].getSimbolo(), resultado[j].getCoeficiente(), resultado[j].getVariable(), resultado[j].getExponente());
        }
        resultado = derivadaCosiente.Cosiente(op, Dif);
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
