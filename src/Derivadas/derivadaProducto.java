/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Derivadas;

import Principal.CDI;
import Principal.ExpresionAlgebraica;
import Principal.SintaxisExpresiones;
import java.util.ArrayList;

/**
 *
 * @author Alexander Batista
 */
public class derivadaProducto extends CDI {

    public static ExpresionAlgebraica[] Derivada_Producto(ArrayList mults) {
        ExpresionAlgebraica[] der1, der2, mul1, mul2, res1, res2, res;
        int pos;
        String cad = "";
        for (int i = 0; i < mults.size(); i++) {
            mults.set(i, mults.get(i).toString().substring(1, mults.get(i).toString().length() - 1));
        }

        for (int i = 0; i < mults.size() - 1; i++) {
            der1 = SintaxisExpresiones.Sintaxis(mults.get(i).toString(), "d");
            mul1 = llenado;
            der2 = SintaxisExpresiones.Sintaxis(mults.get(i + 1).toString(), "d");
            mul2 = llenado;
            cad = "";
            res1 = Operaciones.Producto.ProductoVariables(mul1, der2);
            res2 = Operaciones.Producto.ProductoVariables(mul2, der1);

            res = new ExpresionAlgebraica[res1.length + res2.length];
            for (int j = 0; j < res1.length; j++) {
                res[j] = new ExpresionAlgebraica(res1[j].getSimbolo(), res1[j].getCoeficiente(), res1[j].getVariable(), res1[j].getExponente());
            }
            pos = res1.length;
            for (ExpresionAlgebraica res21 : res2) {
                res[pos] = new ExpresionAlgebraica(res21.getSimbolo(), res21.getCoeficiente(), res21.getVariable(), res21.getExponente());
                pos++;
            }

            for (ExpresionAlgebraica re : res) {
                cad = cad + re.getSimbolo() + re.getCoeficiente() + re.getVariable() + "^" + re.getExponente();
            }
            if (cad.charAt(0) == '+') {
                cad = cad.substring(1, cad.length());
            }
            SintaxisExpresiones.Sintaxis(cad, "d");
            cad = "";
            for (ExpresionAlgebraica llenado1 : llenado) {
                cad = cad + llenado1.getSimbolo() + llenado1.getCoeficiente() + llenado1.getVariable() + "^" + llenado1.getExponente();
            }
            if (cad.charAt(0) == '+') {
                cad = cad.substring(1, cad.length());
            }

            mults.set(i, "");
            mults.set(i + 1, cad);
        }
        SintaxisExpresiones.Sintaxis(cad, "d");

        return llenado;
    }
}