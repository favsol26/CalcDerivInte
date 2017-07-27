/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import static Principal.CDI.llenado;
import static Principal.CDI.mul1;
import Principal.Enrrutar;
import Principal.ExpresionAlgebraica;
import Principal.SintaxisExpresiones;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Alexander Batista
 */
public class procesoDerivadaLogaritmo extends Enrrutar {

    String logn = "";
    int sig, v = 0, f;
    ExpresionAlgebraica[] terminos;
    ExpresionAlgebraica[] resultados2;

    public ExpresionAlgebraica[] proceso(ArrayList Segmentos, ArrayList Signos, String op, String dif) {
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
            resultados2 = SintaxisExpresiones.Sintaxis(cad, op, true, dif);
            v = resultados2.length + 1;
        } else {
            resultados2 = SintaxisExpresiones.Sintaxis(logn.substring(3, logn.length() - 1), op,true, dif);
            v = resultados2.length + llenado.length + 1;
        }
        terminos = new ExpresionAlgebraica[v];
        for (int j = 0; j < resultados2.length; j++) {
            terminos[j] = new ExpresionAlgebraica(resultados2[j].getSimbolo(), resultados2[j].getCoeficiente(), resultados2[j].getVariable(), resultados2[j].getExponente());
        }
        terminos[resultados2.length] = new ExpresionAlgebraica("/", 0, null, null);
        f = resultados2.length + 1;
        if (mul1 != null) {
            for (ExpresionAlgebraica termino : terminos) {
                expz.add(termino.getSimbolo() + termino.getCoeficiente() + termino.getVariable() + "^" + termino.getExponente());
            }
            Segmentos.stream().forEach((Segmento) -> {
                expz.add(Segmento);
            });
        } else {
            for (ExpresionAlgebraica llenado1 : llenado) {
                terminos[f] = new ExpresionAlgebraica(llenado1.getSimbolo(), llenado1.getCoeficiente(), llenado1.getVariable(), llenado1.getExponente());
                f++;
            }
        }
        for (Iterator it = expz.iterator(); it.hasNext();) {
            Object expz1 = it.next();
            if (expz1.toString().charAt(0) != '/') {
                System.out.print(expz1);
            } else {
                System.out.print("/");
            }
        }
        System.out.println("");
        
        return terminos;
    }
}
