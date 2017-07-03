/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Derivadas.Exponencial;
import Derivadas.Trigonometricas;
import Derivadas.derivadaPotencia;
import Derivadas.derivadaProducto;
import Derivadas.derivadaValorAbsoluto;
import Procesos.procesoDerivadaCociente;
import Procesos.procesoDerivadaLogaritmo;
import java.util.ArrayList;

/**
 *
 * @author Alexander Batista
 */
public class Enrrutar extends CDI {

    static procesoDerivadaCociente PDC = new procesoDerivadaCociente();
    static procesoDerivadaLogaritmo PDL = new procesoDerivadaLogaritmo();

    public static ExpresionAlgebraica[] Enrrutador(ArrayList partes, ArrayList delimitador, String op) {
        ArrayList Segmentos;
        ArrayList Signos;
        boolean parentesis = false;
        Segmentos = partes;
        ExpresionAlgebraica[] terminos;
        Signos = delimitador;
        int v, sig = 0;
        int f;
        String logn = "";
        String cad = "";
        if (!op.toUpperCase().equals("D")) {
            System.out.println("Integrales !!!!");
        } else {

            for (int i = 0; i < Segmentos.size(); i++) {
                for (int l = 2; l < Segmentos.get(i).toString().length(); l++) {
                    if (Segmentos.get(i).toString().charAt(l) == '(') {
                        parentesis = true;
                        System.err.println("Si");
                        break;
                    }
                }
                if (Signos.isEmpty()) {
                    if (Segmentos.get(0).toString().length() > 3 && parentesis) {
                        switch (Segmentos.get(0).toString().toLowerCase().substring(0, Segmentos.get(0).toString().indexOf("("))) {
                            case "e^":
                                resultado=Exponencial.exponencial(Segmentos.get(0).toString());
                                expon = true;
                                break;
                            case "ln":
                                resultado = PDL.proceso(Segmentos, Signos, op);
                                break;
                            case "sen":
                                expz = Trigonometricas.correr(Segmentos);
                                break;
                            case "cos":
                                expz = Trigonometricas.correr(Segmentos);
                                break;
                            case "tan":
                                expz = Trigonometricas.correr(Segmentos);
                                break;
                            case "cot":
                                expz = Trigonometricas.correr(Segmentos);
                                break;
                            case "sec":
                                expz = Trigonometricas.correr(Segmentos);
                                break;
                            case "csc":
                                expz = Trigonometricas.correr(Segmentos);
                                break;
                        }

                    } else if ("|".equals(Segmentos.get(i).toString().substring(0, 1))) {
                        if (Segmentos.size() == 1) {
                            derivadaValorAbsoluto.ValorAbsoluto(Segmentos.get(i).toString().substring(1, Segmentos.get(i).toString().length() - 1), op);
                        } else {
                            cad = "";
                            for (Object Segmento : Segmentos) {
                                cad = cad + (Segmento.toString());
                            }
                            derivadaValorAbsoluto.ValorAbsoluto(cad.substring(1, cad.length() - 1), op);
                        }
                        break;
                    } else if (")".equals(Segmentos.get(0).toString().substring(Segmentos.get(0).toString().length() - 1))
                            && "(".equals(Segmentos.get(1).toString().substring(0, 1)) && Signos.isEmpty()) {
                        resultado = derivadaProducto.Derivada_Producto(Segmentos);
                        break;
                    } else {
                        resultado = derivadaPotencia.derivada_Potencia(ProcesarFunciones.jeraquia(Segmentos, Signos));
                        
                        break;
                    }
                } else if (!Signos.isEmpty()) {
                    if (Signos.get(0).toString().equals("/")) {
                        resultado = PDC.proceso(Segmentos, Signos, op);
                        break;
                    } else if ("|".equals(Segmentos.get(i).toString().substring(0, 1))) {

                        if (Segmentos.size() == Signos.size()) {
                            cad = cad + (Signos.get(0).toString());
                        } else {
                            for (int j = 0; j < Segmentos.size(); j++) {
                                if (j == 0) {
                                    cad = cad + Segmentos.get(j).toString().substring(1, Segmentos.get(j).toString().length());
                                } else if (j == Segmentos.size() - 1) {
                                    cad = cad + Segmentos.get(j).toString().substring(0, Segmentos.get(j).toString().length() - 1);
                                } else {
                                    cad = cad + Segmentos.get(j);
                                }
                                if (j < Signos.size()) {
                                    cad = cad + Signos.get(i);
                                }
                            }
                        }
                        derivadaValorAbsoluto.ValorAbsoluto(cad, op);
                        break;
                    } else {
                        resultado = derivadaPotencia.derivada_Potencia(ProcesarFunciones.jeraquia(Segmentos, Signos));
                        break;
                    }
                }
            }
        }
        return resultado;
    }
}
