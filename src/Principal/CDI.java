/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author Alexander Batista
 */
public class CDI {

    public static ExpresionAlgebraica[] resultado = null;

    //float x = 1;
    public static ExpresionAlgebraica[] Exp;
    public static ExpresionAlgebraica[] llenado;
    public static ExpresionAlgebraica[] mult1;
    public static ExpresionAlgebraica[] mult1d;
    public static ExpresionAlgebraica[] mult2;
    public static ExpresionAlgebraica[] mult2d;
    public static ExpresionAlgebraica[] mult2C;
    public static ExpresionAlgebraica[] producto;
    public static DecimalFormat df = new DecimalFormat("###.##");
    public static String result = "";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cad, op;
        System.out.println("intruduzca una función: ");
        cad = sc.nextLine();
        System.out.println("intruduzca una operación: ");
        op = sc.nextLine();
        
        if (cad.charAt(0) == '+') {
            cad = cad.substring(1, cad.length());
        }
        resultado = Revisar.revisarFuncion(cad,op);
        System.out.println("\n");
        if (resultado != null) {
            System.out.print("Al derivar se optiene: ");
            cad = "";
            for (ExpresionAlgebraica finalizado1 : resultado) {
                if (finalizado1 != null) {
                    if (finalizado1.getSimbolo().equals("&")) {
                        cad = cad + "*";
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
        } else {
            System.out.println("No se procesó");
            cad="";
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
