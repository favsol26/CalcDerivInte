///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Operaciones;
//
//import Principal.CDI;
//import static Principal.CDI.llenado;
//import static Principal.CDI.mult1;
//import static Principal.CDI.mult2;
//import static Principal.CDI.resultado;
//import Principal.ExpresionAlgebraica;
//import Principal.SintaxisExpresiones;
//import java.util.ArrayList;
//import java.util.Iterator;
//
///**
// *
// * @author Alexander Batista
// */
//public class Rutas extends CDI {
//
//    public static ExpresionAlgebraica[] logaritmo(ArrayList Segmentos, ArrayList Signos) {
//        
//        ExpresionAlgebraica[] terminos;
//        int v, sig = 0;
//        int f;
//        String logn = "";
//
//        if (Segmentos.size() != 1) {
//
//            for (Iterator it = Segmentos.iterator(); it.hasNext();) {
//                Object Segmento = it.next();
//                logn = logn.concat(Segmento.toString());
//                if (!Signos.isEmpty() && sig < Signos.size()) {
//                    logn = logn.concat(Signos.get(sig).toString());
//                    sig++;
//                }
//            }
//        } else {
//            logn = Segmentos.get(0).toString();
//        }
//
//        if (Segmentos.get(0).toString().charAt(3) == '(') {
//
//            SintaxisExpresiones.Sintaxis(Segmentos.get(0).toString().substring(4,
//                    Segmentos.get(0).toString().length() - 1));
//            mult1 = llenado;
//
//            SintaxisExpresiones.Sintaxis(Segmentos.get(1).toString().substring(1,
//                    Segmentos.get(0).toString().length() - 4));
//            mult2 = llenado;
//
//            //    System.out.println(logn.substring(3, logn.length() - 1));
//            SintaxisExpresiones.Sintaxis(logn.substring(3, logn.length() - 1));
//
//            if (Signos.isEmpty()) {
//                v = (resultado.length + mult1.length + mult2.length + 1);
//            } else {
//                v = (resultado.length + mult1.length + mult2.length);
//            }
//
//        } else {
//            SintaxisExpresiones.Sintaxis(logn.substring(3, logn.length() - 1));
//            v = resultado.length + llenado.length + 1;
//        }
//
//        terminos = new ExpresionAlgebraica[v];
//        for (int j = 0; j < resultado.length; j++) {
//            terminos[j] = new ExpresionAlgebraica(resultado[j].getSimbolo(),
//                    resultado[j].getCoeficiente(),
//                    resultado[j].getVariable(),
//                    resultado[j].getExponente());
//        }
//
//        terminos[resultado.length] = new ExpresionAlgebraica("/", 0, null, null);
//        f = resultado.length + 1;
//
//        if (mult1 != null) {
//            for (ExpresionAlgebraica termino : mult1) {
//                terminos[f] = new ExpresionAlgebraica(termino.getSimbolo(),
//                        termino.getCoeficiente(),
//                        termino.getVariable(),
//                        termino.getExponente());
//                f++;
//            }
//            for (ExpresionAlgebraica termino : mult2) {
//                terminos[f] = new ExpresionAlgebraica(termino.getSimbolo(),
//                        termino.getCoeficiente(),
//                        termino.getVariable(),
//                        termino.getExponente());
//                f++;
//            }
//            
//        } else {
//            for (ExpresionAlgebraica termino : llenado) {
//                terminos[f] = new ExpresionAlgebraica(termino.getSimbolo(),
//                        termino.getCoeficiente(),
//                        termino.getVariable(),
//                        termino.getExponente());
//                f++;
//            }
//        }
//        return terminos;
//    }
//}
