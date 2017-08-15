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
    public static ArrayList resultados = new ArrayList();
    static boolean trigo2;
    static boolean expon;
    public static Scanner sc = new Scanner(System.in);
    private static boolean asterisco;
    public static ArrayList Descripcion = new ArrayList();

    /**
     * @param cad
     * @param operacion
     * @param Dif
     * @return
     */
    public static ArrayList CDIMaster(String cad, String operacion, String Dif) {
        ExpresionAlgebraica[] trigo = null;

        if (cad.charAt(0) == '+') {
            cad = cad.substring(1, cad.length());
        }
        
        resultado = SintaxisExpresiones.Sintaxis(cad, operacion, true, Dif);

        
        resultados.add("\n");
        if (expz.isEmpty()) {
            if (resultado != null) {
                resultados.add("&&&");
                cad = "\n";
                for (ExpresionAlgebraica finalizado1 : resultado) {
                    if (finalizado1 != null) {
                        if (finalizado1.getSimbolo().equals("&")) {
                            cad = cad + "*";
                        } else if (finalizado1.getSimbolo().equals("/")) {
                            cad = cad + "\n";
                            int len = cad.length();
                            for (int i = 0; i < len; i = i + 2) {
                                cad = cad + "─";
                            }
                            cad = cad + "\n";
                        } else if (finalizado1.getCoeficiente() == 0) {

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
            for (Object expz1 : expz) {
                re = expz1.toString().substring(0, expz1.toString().indexOf(" "));
                if (!"-cos".equals(re) && !"sen".equals(re) && !"-ln".equals(re) && !"ln".equals(re)) {
                    trigo = SintaxisExpresiones.Sintaxis(re, "d", false, Dif);
                }
                if (trigo != null) {
                    for (ExpresionAlgebraica finalizado1 : trigo) {
                        if (finalizado1 != null) {

                            if (finalizado1.getSimbolo().equals("&")) {
                                cad = cad + "*";
                            } else if (finalizado1.getSimbolo().equals("/")) {
                                cad = cad + "\n";
                                for (int i = 0; i < cad.length(); i++) {
                                    cad = cad + "─ +c";
                                }
                                cad = cad + "\n";
                            } else if (finalizado1.getCoeficiente() == 0) {

                            } else if (finalizado1.getExponente().equals("0.0")) {
                                cad = cad + finalizado1.getSimbolo() + finalizado1.getCoeficiente();
                            } else if (finalizado1.getCoeficiente() * 100 == Math.round(finalizado1.getCoeficiente()) * 100) {
                                cad = cad + finalizado1.getSimbolo() + Math.round(finalizado1.getCoeficiente()) + finalizado1.getVariable() + "^" + finalizado1.getExponente();
                            } else {
                                cad = cad + finalizado1.getSimbolo() + finalizado1.getCoeficiente() + finalizado1.getVariable() + "^" + finalizado1.getExponente();
                            }
                        }
                    }
                    cad = cad + expz1.toString().substring(expz1.toString().indexOf(" "), expz1.toString().length());
                } else {
                    cad = cad + expz1.toString();
                }

            }
            resultados.add("&&&");
        }
        cad=cad+"+c";
        if (cad.equals("")) {
            cad = "0";
        }
        if (cad.charAt(0) == '-') {
            resultados.add(cad);
        } else if (cad.charAt(0) == '+') {
            resultados.add(cad.substring(1, cad.length()));
        } else {
            resultados.add(cad);
        }
        resultados.add("");
        for (Object resultado1 : resultados) {
            for (int j = 0; j < resultado1.toString().length(); j++) {
                if (resultado1.toString().charAt(j) == '*') {
                    asterisco = true;
                    break;
                }
            }
        }
        if (asterisco) {
            cad = "";
            for (Object resultado1 : resultados) {
                cad = cad.concat(resultado1.toString());
            }
            cad = cad.substring(0, cad.lastIndexOf("─")+1).concat(" (").concat(cad.substring(cad.indexOf("*") + 1, cad.length())).concat(")\n|").concat(cad.substring(cad.lastIndexOf("─") + 2, cad.indexOf("*"))).concat("|");
            resultados.clear();
            
            resultados.add(cad);
        }

        return resultados;
    }

}
