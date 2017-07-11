/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alexander Batista
 */
public class CDI {

    public static ExpresionAlgebraica[] resultado;

    //float x = 1;
    public static ExpresionAlgebraica[] Exp;
    public static ExpresionAlgebraica[] llenado;
    public static ExpresionAlgebraica[] mul1;
    public static ExpresionAlgebraica[] mul2;
    public static ExpresionAlgebraica[] der1;
    public static ExpresionAlgebraica[] der2;
    public static ExpresionAlgebraica[] mult2C;
    public static ExpresionAlgebraica[] producto;
    public static DecimalFormat df = new DecimalFormat("###.##");
    public static String result = "";
    public static ArrayList expz = new ArrayList();
    static boolean trigo2;
    static boolean expon;
    public static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String cad, operacion;
        ExpresionAlgebraica[] trigo;
        System.out.println("intruduzca una función: ");
        cad = sc.nextLine();
        System.out.println("intruduzca una operación: ");
        operacion = sc.nextLine();

        if (cad.charAt(0) == '+') {
            cad = cad.substring(1, cad.length());
        }

        resultado = Revisar.revisarFuncion(cad, operacion);

        if (operacion.toLowerCase().equals("d")) {
            operacion = "derivar";
        } else {
            operacion = "integrar";
        }
        System.out.println("\n");
        if (expz.isEmpty()) {
            if (resultado != null) {
                System.out.print("Al " + operacion + " se obtiene: ");
                cad = "";
                for (ExpresionAlgebraica finalizado1 : resultado) {
                    if (finalizado1 != null) {
                        if (finalizado1.getSimbolo().equals("&")) {
                            cad = cad + "*";
                        } else if (finalizado1.getCoeficiente() == 0) {

                        } else if (finalizado1.getSimbolo().equals("/")) {
                            cad = cad + "/";
                        } else if (finalizado1.getExponente().equals("0.0")) {
                            cad = cad + finalizado1.getSimbolo() + finalizado1.getCoeficiente();
                        } else if (finalizado1.getCoeficiente() * 100 == Math.round(finalizado1.getCoeficiente()) * 100) {
                            cad = cad + finalizado1.getSimbolo() + Math.round(finalizado1.getCoeficiente()) + finalizado1.getVariable() + "^" + finalizado1.getExponente();
                        } else {
                            cad = cad + finalizado1.getSimbolo() + finalizado1.getCoeficiente() + finalizado1.getVariable() + "^" + finalizado1.getExponente();
                        }
                    }
                }
            }
        } else {
            cad = "";
            String re;
            for (int i = 0; i < expz.size(); i++) {
                re = expz.get(i).toString().substring(0, expz.get(i).toString().indexOf(" "));
                Revisar.revisarFuncion(re, "d");
                trigo = llenado;

                for (ExpresionAlgebraica finalizado1 : trigo) {
                    if (finalizado1 != null) {
                        if (finalizado1.getSimbolo().equals("&")) {
                            cad = cad + "*";
                        } else if (finalizado1.getCoeficiente() == 0) {

                        } else if (finalizado1.getSimbolo().equals("/")) {
                            cad = cad + "/";
                        } else if (finalizado1.getExponente().equals("0.0")) {
                            cad = cad + finalizado1.getSimbolo() + finalizado1.getCoeficiente();
                        } else if (finalizado1.getCoeficiente() * 100 == Math.round(finalizado1.getCoeficiente()) * 100) {
                            cad = cad + finalizado1.getSimbolo() + Math.round(finalizado1.getCoeficiente()) + finalizado1.getVariable() + "^" + finalizado1.getExponente();
                        } else {
                            cad = cad + finalizado1.getSimbolo() + finalizado1.getCoeficiente() + finalizado1.getVariable() + "^" + finalizado1.getExponente();
                        }
                    }
                }
                cad = cad + expz.get(i).toString().substring(expz.get(i).toString().indexOf(" "), expz.get(i).toString().length());
            }
            System.out.print("Al " + operacion + " se obtiene: ");
        }
        if (cad.equals("")) {
            cad = "0";
        }
        if (cad.charAt(0) == '-') {
            System.out.println(cad);
        } else {
            System.out.println(cad.substring(1, cad.length()));
        }
        System.out.println("");
    }

}
