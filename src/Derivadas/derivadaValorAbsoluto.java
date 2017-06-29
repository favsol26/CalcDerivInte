/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Derivadas;

import Principal.CDI;
import Principal.ExpresionAlgebraica;
import Principal.SintaxisExpresiones;

/**
 *
 * @author Alexander Batista
 */
public class derivadaValorAbsoluto extends CDI {

    public static ExpresionAlgebraica[] ValorAbsoluto(String Segmentos, String op) {
        String cad = Segmentos;
        int z = 0, h, s = 0;
        ExpresionAlgebraica[] valor = SintaxisExpresiones.Sintaxis(cad, op);

        resultado = new ExpresionAlgebraica[valor.length + (llenado.length * 2) + 2];
        for (int i = 0; i < llenado.length; i++) {
            resultado[i] = new ExpresionAlgebraica(llenado[i].getSimbolo(), llenado[i].getCoeficiente(), llenado[i].getVariable(), llenado[i].getExponente());
            z++;
        }
        resultado[z] = new ExpresionAlgebraica("/", 0, null, null);
        z++;
        for (ExpresionAlgebraica llenado1 : llenado) {
            resultado[z] = new ExpresionAlgebraica("+", llenado1.getCoeficiente(), llenado1.getVariable(), llenado1.getExponente());
            z++;
            s++;
        }
        resultado[z] = new ExpresionAlgebraica("&", 0, null, null);
        System.out.println(z);
        z++;
        h = z;
        s = 0;
        for (int i = z; i < valor.length + h; i++) {
            resultado[i] = new ExpresionAlgebraica(valor[s].getSimbolo(), valor[s].getCoeficiente(), valor[s].getVariable(), valor[s].getExponente());
            s++;
            z++;
        }

        return resultado;
    }

}
