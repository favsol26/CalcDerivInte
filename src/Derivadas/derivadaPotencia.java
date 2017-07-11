/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Derivadas;

import Principal.CDI;
import Principal.ExpresionAlgebraica;

/**
 *
 * @author Alexander Batista
 */
public class derivadaPotencia extends CDI {

    public static ExpresionAlgebraica[] derivada_Potencia(ExpresionAlgebraica[] deriv) {

        String parte;
        resultado = new ExpresionAlgebraica[deriv.length];
        boolean letra = false;

        float coef;
        for (int i = 0; i < deriv.length; i++) {
            resultado[i] = new ExpresionAlgebraica(deriv[i].getSimbolo(), deriv[i].getCoeficiente(), deriv[i].getVariable(), deriv[i].getExponente());
        }

        for (int i = 0; i < resultado.length; i++) {
            coef = Float.valueOf(resultado[i].getSimbolo().concat(String.valueOf(resultado[i].getCoeficiente())));
            for (int j = 0; j < resultado[i].getExponente().length(); j++) {
                parte = String.valueOf(resultado[i].getExponente().charAt(j));
                if ((resultado[i].getExponente().codePointAt(j) >= 97 && resultado[i].getExponente().codePointAt(j) <= 122) || (resultado[i].getExponente().codePointAt(j) >= 65 && resultado[i].getExponente().codePointAt(j) <= 90)) {
                    letra = true;
                }
            }
            if (!letra) {
                float exponente = Float.valueOf(resultado[i].getExponente());
                coef = coef * exponente;
                resultado[i].setExponente(String.valueOf(Float.valueOf(resultado[i].getExponente()) - 1));
                if (coef < 0) {
                    resultado[i].setCoeficiente(coef * -1);
                    resultado[i].setSimbolo("-");
                } else {
                    resultado[i].setCoeficiente(coef);
                    resultado[i].setSimbolo("+");
                }
            } else {
                String exponente;
                mul1 = new ExpresionAlgebraica[1];
                mul1[0] = new ExpresionAlgebraica(resultado[i].getSimbolo(), resultado[i].getCoeficiente(), resultado[i].getVariable(), "0");
                exponente = resultado[i].getExponente();
                ExpresionAlgebraica[] term = derivadaProducto.Derivada_Producto(null);
                resultado[i] = new ExpresionAlgebraica(term[0].getSimbolo(), term[0].getCoeficiente(), term[0].getVariable(), term[0].getExponente());
            }
        }
        return resultado;

    }
}
