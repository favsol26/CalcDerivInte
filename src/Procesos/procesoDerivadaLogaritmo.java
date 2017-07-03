/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import static Principal.CDI.llenado;
import static Principal.CDI.mul1;
import static Principal.CDI.mul2;
import Principal.Enrrutar;
import Principal.ExpresionAlgebraica;
import Principal.ProcesarFunciones;
import Principal.Revisar;
import Principal.SintaxisExpresiones;
import java.util.ArrayList;

/**
 *
 * @author Alexander Batista
 */
public class procesoDerivadaLogaritmo extends Enrrutar {

    String logn = "";
    int sig, v = 0, f;
    ExpresionAlgebraica[] terminos;
    ExpresionAlgebraica[] resultados;

    public ExpresionAlgebraica[] proceso(ArrayList Segmentos, ArrayList Signos, String op) {
        if (Segmentos.size() != 1) {
            Segmentos.stream().map((Segmento) -> {
                logn = logn.concat(Segmento.toString());
                return Segmento;
            }).filter((_item) -> (!Signos.isEmpty() && sig < Signos.size())).map((_item) -> {
                logn = logn.concat(Signos.get(sig).toString());
                return _item;
            }).forEach((_item) -> {
                sig++;
            });
        } else {
            logn = Segmentos.get(0).toString();
        }
        if (Segmentos.get(0).toString().charAt(3) == '(') {
            Segmentos.set(0, Segmentos.get(0).toString().substring(3, Segmentos.get(0).toString().length()));
            Segmentos.set(Segmentos.size() - 1, Segmentos.get(Segmentos.size() - 1).toString().substring(0, Segmentos.get(Segmentos.size() - 1).toString().length() - 1));
            String cad = "";
            for (int i = 0; i < Segmentos.size(); i++) {
                cad = cad + Segmentos.get(i).toString();
                if (!Signos.isEmpty() && i < Signos.size()) {
                    cad = cad + Signos.get(i);
                }
            }

            resultados = Revisar.revisarFuncion(cad, op);

            v = resultados.length + 1;
        } else {
            resultados = Revisar.revisarFuncion(logn.substring(3, logn.length() - 1), op);
            v = resultados.length + llenado.length + 1;
        }
        terminos = new ExpresionAlgebraica[v];
        for (int j = 0; j < resultados.length; j++) {
            terminos[j] = new ExpresionAlgebraica(resultados[j].getSimbolo(), resultados[j].getCoeficiente(), resultados[j].getVariable(), resultados[j].getExponente());
        }
        terminos[resultados.length] = new ExpresionAlgebraica("/", 0, null, null);
        f = resultados.length + 1;

        if (mul1!=null) {
            for (ExpresionAlgebraica termino : terminos) {
                expz.add(termino.getSimbolo() + termino.getCoeficiente() + termino.getVariable() + "^" + termino.getExponente());
            }
            for (int i = 0; i < Segmentos.size(); i++) {
                expz.add(Segmentos.get(i));
            }
        }else{
            for (ExpresionAlgebraica llenado1 : llenado) {
                terminos[f] = new ExpresionAlgebraica(llenado1.getSimbolo(), llenado1.getCoeficiente(), llenado1.getVariable(), llenado1.getExponente());
                f++;
            }  
        }
        for (int i = 0; i < expz.size(); i++) {
            if (expz.get(i).toString().charAt(0)!='/') {
                System.out.print(expz.get(i));
            } else {
                System.out.print("/");

            }
        }
        System.out.println("");
        return terminos;
    }
}
