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
public class integralProducto extends CDI {

    static ExpresionAlgebraica[] mult1;
    static ExpresionAlgebraica[] mult2;
    static ExpresionAlgebraica[] res;

    public static ExpresionAlgebraica[] integral_producto(ArrayList Segmentos, String Dif) {
        String cad = "";
        for (int i = 0; i < Segmentos.size() - 1; i++) {
            mult1 = SintaxisExpresiones.Sintaxis(Segmentos.get(i).toString().substring(1, Segmentos.get(i).toString().length() - 1), "d", false, Dif);

            mult2 = SintaxisExpresiones.Sintaxis(Segmentos.get(i + 1).toString().substring(1, Segmentos.get(i + 1).toString().length() - 1), "d", false, Dif);

            res = Operaciones.Producto.ProductoVariables(mult1, mult2);
            cad = "(";
            for (ExpresionAlgebraica re : res) {
                cad = cad + re.getSimbolo() + re.getCoeficiente() + re.getVariable() + "^" + re.getExponente();
            }
            if (cad.charAt(1) == '+') {
                cad = "(" + cad.substring(2, cad.length());
            }
            cad = cad + ")";
            Segmentos.set(i, "");
            Segmentos.set(i + 1, cad);
        }
        cad = cad.substring(1, cad.length() - 1);
        System.out.println("***" + cad + "***");

        res = integralPotencia.integral_Potencia(SintaxisExpresiones.Sintaxis(cad, "d", false, Dif), Dif);
        return res;
    }

}
