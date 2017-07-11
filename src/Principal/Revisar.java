/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.util.ArrayList;

/**
 *
 * @author Faustino Ayala
 */
public class Revisar {

    static ArrayList pre = new ArrayList();

    public static ExpresionAlgebraica[] revisarFuncion(String cad, String op) {
        String letra = "";
        String compara;
        String enunciado = "Valido";
        boolean parentecis = false;
        for (int i = 0; i < cad.length(); i++) {
            parentecis = false;
            letra = String.valueOf(cad.charAt(i));
            if ("(".equals(letra)) {
                parentecis = true;
                break;
            }
        }

        if (!parentecis) {
            for (int i = 0; i < cad.length(); i++) {
                letra = String.valueOf(cad.charAt(i));
                if ((letra.hashCode() >= 97 && letra.hashCode() <= 122) || (letra.hashCode() >= 65 && letra.hashCode() <= 90)) {
                    break;
                }
            }
            compara = letra;
            for (int i = 0; i < cad.length(); i++) {
                letra = String.valueOf(cad.charAt(i));
                if ((letra.hashCode() >= 97 && letra.hashCode() <= 122) || (letra.hashCode() >= 65 && letra.hashCode() <= 90)) {
                    if (!letra.equals(compara)) {
                        enunciado = "Expresión no valida...";
                        break;
                    } else {
                        enunciado = "Expresión valida...";
                    }
                }
            }
        }

        System.out.println(enunciado);
        cad = Estructurar(cad);
        if (!"Expresión no valida...".equals(enunciado)) {
            return SintaxisExpresiones.Sintaxis(cad, op,true);
        } else {
            return null;
        }

    }

    public static String Estructurar(String cad) {
        String res = cad;
        int h, u = 0;

        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) == ')') {
                h = i;
                pre.add(res.substring(u, h + 1));
                u = i + 1;
            }
        }

        return res;
    }
}
