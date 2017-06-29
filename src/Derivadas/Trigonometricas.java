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
 * @author fav_0
 */
public class Trigonometricas extends CDI {

    static ExpresionAlgebraica[] exp;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        correr(0);
        correr(1);
        correr(2);
        correr(3);
        correr(4);
        correr(5);
    }

    private static void correr(int id) {
        String expr[] = {"sen(x^7)", "cos((2x^9+x)(x^3-x))", "tan(5x^2)", "cot((2x^9+x)(x^3-x))", "sec(8x^3+1)", "csc((2x^9+x)(x^3-x))"};
        String salida = "";

        if (expr[id].toLowerCase().substring(0, expr[id].indexOf("(")).equals("sen")) {
            salida = expr[id];
            id = 0;
        }

        if (expr[id].toLowerCase().substring(0, expr[id].indexOf("(")).equals("cos")) {
            salida = expr[id];
            id = 1;
        }

        if (expr[id].toLowerCase().substring(0, expr[id].indexOf("(")).equals("tan")) {
            salida = expr[id];
            id = 2;
        }

        if (expr[id].toLowerCase().substring(0, expr[id].indexOf("(")).equals("cot")) {
            salida = expr[id];
            id = 3;
        }

        if (expr[id].toLowerCase().substring(0, expr[id].indexOf("(")).equals("sec")) {
            salida = expr[id];
            id = 4;
        }

        if (expr[id].toLowerCase().substring(0, expr[id].indexOf("(")).equals("csc")) {
            id = 5;
            salida = expr[id];
        }

        System.out.println("\n res: " + funcion(salida, id));
    }

    private static String funcion(String cad, int op) {
        String respuesta = "";
        switch (op) {
            case 0: {
                respuesta = sen(cad);
            }
            break;
            case 1: {
                respuesta = cos(cad);
            }
            break;
            case 2: {
                respuesta = tan(cad);
            }
            break;
            case 3: {
                respuesta = cot(cad);
            }
            break;
            case 4: {
                respuesta = sec(cad);
            }
            break;
            case 5: {
                respuesta = csc(cad);
            }
            break;
        }
        return respuesta;
    }

    private static String sen(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));

        String ExpExter = "cos(".concat(ExpInter) + ")";

        exp = SintaxisExpresiones.Sintaxis(ExpInter, "d");

        cad = "";

        for (ExpresionAlgebraica exp1 : exp) {
            cad = cad.concat(String.format("%s%s%s^%s", exp1.getSimbolo(),
                    exp1.getCoeficiente(), exp1.getVariable(), exp1.getExponente()));
        }

        cad = cad + " ";
        cad = cad.concat(ExpExter);
        //  cad = cad.concat(ExpInter);

        return cad;
    }

    private static String cos(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
        exp = SintaxisExpresiones.Sintaxis(ExpInter, "d");

        if (ExpInter.charAt(0) == '-') {
            ExpInter = ExpInter.substring(1, ExpInter.length());
        }
        String ExpExter = "sen(".concat(ExpInter) + ")";

        for (ExpresionAlgebraica exp1 : exp) {
            if ("+".equals(exp1.getSimbolo())) {
                exp1.setSimbolo("-");
            } else {
                exp1.setSimbolo("+");
            }
        }
        cad = "";

        for (ExpresionAlgebraica exp1 : exp) {
            cad = cad.concat(String.format("%s%s%s^%s", exp1.getSimbolo(),
                    exp1.getCoeficiente(), exp1.getVariable(), exp1.getExponente()));
        }

        cad = cad + " ";
        cad = cad.concat(ExpExter);
        //  cad = cad.concat(ExpInter);

        return cad;
    }

    private static String tan(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));

        String ExpExter = "sec^2(".concat(ExpInter) + ")";

        exp = SintaxisExpresiones.Sintaxis(ExpInter, "d");

        cad = "";

        for (ExpresionAlgebraica exp1 : exp) {
            cad = cad.concat(String.format("%s%s%s^%s", exp1.getSimbolo(),
                    exp1.getCoeficiente(), exp1.getVariable(), exp1.getExponente()));
        }

        cad = cad + " ";
        cad = cad.concat(ExpExter);
        //  cad = cad.concat(ExpInter);

        return cad;
    }

    private static String cot(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
        exp = SintaxisExpresiones.Sintaxis(ExpInter, "d");

        if (ExpInter.charAt(0) == '-') {
            ExpInter = ExpInter.substring(1, ExpInter.length());
        }
        String ExpExter = "csc^2(".concat(ExpInter) + ")";

        for (ExpresionAlgebraica exp1 : exp) {
            if ("+".equals(exp1.getSimbolo())) {
                exp1.setSimbolo("-");
            } else {
                exp1.setSimbolo("+");
            }
        }
        cad = "";

        for (ExpresionAlgebraica exp1 : exp) {
            cad = cad.concat(String.format("%s%s%s^%s", exp1.getSimbolo(),
                    exp1.getCoeficiente(), exp1.getVariable(), exp1.getExponente()));
        }

        cad = cad + " ";
        cad = cad.concat(ExpExter);
        //  cad = cad.concat(ExpInter);

        return cad;
    }

    private static String sec(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));

        String ExpExter = "sec(".concat(ExpInter) + ") " + "tan(".concat(ExpInter) + ")";

        exp = SintaxisExpresiones.Sintaxis(ExpInter, "d");

        cad = "";

        for (ExpresionAlgebraica exp1 : exp) {
            cad = cad.concat(String.format("%s%s%s^%s", exp1.getSimbolo(),
                    exp1.getCoeficiente(), exp1.getVariable(), exp1.getExponente()));
        }

        cad = cad + " ";
        cad = cad.concat(ExpExter);
        //  cad = cad.concat(ExpInter);

        return cad;
    }

    private static String csc(String cad) {
        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
        exp = SintaxisExpresiones.Sintaxis(ExpInter, "d");

        if (ExpInter.charAt(0) == '-') {
            ExpInter = ExpInter.substring(1, ExpInter.length());
        }
       String ExpExter = "csc(".concat(ExpInter) + ") " + "cot(".concat(ExpInter) + ")";

        for (ExpresionAlgebraica exp1 : exp) {
            if ("+".equals(exp1.getSimbolo())) {
                exp1.setSimbolo("-");
            } else {
                exp1.setSimbolo("+");
            }
        }
        cad = "";

        for (ExpresionAlgebraica exp1 : exp) {
            cad = cad.concat(String.format("%s%s%s^%s", exp1.getSimbolo(),
                    exp1.getCoeficiente(), exp1.getVariable(), exp1.getExponente()));
        }

        cad = cad + " ";
        cad = cad.concat(ExpExter);
        //  cad = cad.concat(ExpInter);

        return cad;
    }

}
