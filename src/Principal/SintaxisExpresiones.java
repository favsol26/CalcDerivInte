/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.util.ArrayList;

/**
 *
 * @author Alexander Batista
 */
public class SintaxisExpresiones extends CDI {

    public static ExpresionAlgebraica[] Sintaxis(String cad, String op) {
        ArrayList partes = new ArrayList();
        ArrayList signo = new ArrayList();
        String cadena = cad;
        String parte = "";
        boolean parentesis = false;
        for (int i = 0; i < cadena.length(); i++) {
            if ((cadena.charAt(i) == '+' || (cadena.charAt(i) == '-' && !parte.isEmpty())
                    || cadena.charAt(i) == '*' || cadena.charAt(i) == '/'
                    || i == cadena.length() - 1 || (cadena.charAt(i) == ')' && cadena.charAt(i + 1) == '('))) {
                if (cadena.charAt(i) == ')') {
                    parentesis = false;
                    parte = parte.concat(String.valueOf(cadena.charAt(i)));
                } else if (i == cadena.length() - 1) {
                    parte = parte.concat(String.valueOf(cadena.charAt(i)));
                } else if (i > 0 && cadena.charAt(i - 1) == '^') {
                    parte = parte.concat(String.valueOf(cadena.charAt(i)));
                    continue;
                }
                if (!parentesis) {
                    if (i != cadena.length() - 1) {
                        if (cadena.charAt(i) != ')' && cadena.charAt(i) != '(') {
                            signo.add(cadena.charAt(i));
                        }
                    }
                    partes.add(parte);
                    parte = "";
                } else {
                    parte = parte.concat(String.valueOf(cadena.charAt(i)));
                }
            } else {
                if (cadena.charAt(i) == '(') {
                    parentesis = true;
                } else if (cadena.charAt(i) == ')') {
                    parentesis = false;
                }
                parte = parte.concat(String.valueOf(cadena.charAt(i)));
            }
        }
        if (partes.get(0).toString().charAt(0) == '-') {
            partes.set(0, partes.get(0).toString().substring(1, partes.get(0).toString().length()));
            if (!signo.isEmpty()) {
                ArrayList signo2 = signo;
                signo.clear();
                signo.add("-");
                int s = signo2.size();
                for (int i = 0; i < s; i++) {
                    signo.add(signo2.get(i).toString());
                }
            } else {
                signo.add('-');
            }
        }
        return Enrrutar.Enrrutador(partes, signo, op);
    }
}
