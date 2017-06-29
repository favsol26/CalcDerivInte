/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import static Principal.CDI.llenado;
import static Principal.CDI.mult1;
import static Principal.CDI.mult2;
import static Principal.CDI.resultado;
import Principal.Enrrutar;
import Principal.ExpresionAlgebraica;
import Principal.SintaxisExpresiones;
import java.util.ArrayList;

/**
 *
 * @author Alexander Batista
 */
public class procesoDerivadaLogaritmo extends Enrrutar {

    String logn="";
    int sig, v=0, f;
    ExpresionAlgebraica[] terminos;

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
            SintaxisExpresiones.Sintaxis(Segmentos.get(0).toString().substring(4, Segmentos.get(0).toString().length() - 1),op);
            mult1 = llenado;
            SintaxisExpresiones.Sintaxis(Segmentos.get(1).toString().substring(1, Segmentos.get(0).toString().length() - 4),op);
            mult2 = llenado;
            SintaxisExpresiones.Sintaxis(logn.substring(3, logn.length() - 1),op);
            if (Signos.isEmpty()) {
                v = (resultado.length + mult1.length + mult2.length + 2);
            } else {
                v = (resultado.length + mult1.length + mult2.length + 1);
            }
        } else {
            resultado=SintaxisExpresiones.Sintaxis(logn.substring(3, logn.length() - 1),op);
            v = resultado.length + llenado.length + 1;
        }
        terminos = new ExpresionAlgebraica[v];
        for (int j = 0; j < resultado.length; j++) {
            terminos[j] = new ExpresionAlgebraica(resultado[j].getSimbolo(), resultado[j].getCoeficiente(), resultado[j].getVariable(), resultado[j].getExponente());
        }
        terminos[resultado.length] = new ExpresionAlgebraica("/", 0, null, null);
        f = resultado.length + 1;
        if (mult1 != null) {
            for (ExpresionAlgebraica termino : mult1) {
                terminos[f] = new ExpresionAlgebraica(termino.getSimbolo(),
                        termino.getCoeficiente(),
                        termino.getVariable(),
                        termino.getExponente());
                f++;
            }
            for (ExpresionAlgebraica termino : mult2) {
                terminos[f] = new ExpresionAlgebraica(termino.getSimbolo(),
                        termino.getCoeficiente(),
                        termino.getVariable(),
                        termino.getExponente());
                f++;
            }
        } else {
            for (ExpresionAlgebraica termino : llenado) {
                terminos[f] = new ExpresionAlgebraica(termino.getSimbolo(),
                        termino.getCoeficiente(),
                        termino.getVariable(),
                        termino.getExponente());
                f++;
            }
        }
        return terminos;
    }
}
