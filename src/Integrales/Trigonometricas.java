/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integrales;

//import Principal.CDI;
import Principal.ExpresionAlgebraica;
import Principal.SintaxisExpresiones;
import java.util.ArrayList;

/**
 *
 * @author fav_0
 */
public class Trigonometricas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> esp;
        esp = new ArrayList<>();
        esp.add("sen(x)");
        esp.add("cos(x)");
        esp.add("tan(x)");
        esp.add("cot(x)");
        esp.add("sec(x)");
        esp.add("csc(x)");

        System.out.println(correr(esp));
        // TODO code application logic here
    }

    static ExpresionAlgebraica[] exp;

    public static ArrayList correr(ArrayList expre) {
        ArrayList expr = expre;
        String salida;
        int id;
        for (int i = 0; i < expr.size(); i++) {
            switch (expr.get(i).toString().toLowerCase().substring(0, expr.get(i).toString().indexOf("("))) {
                case "sen":
                    salida = expr.get(i).toString();
                    id = 0;
                    expr.set(i, funcion(salida, id));
                    break;
                case "cos":
                    salida = expr.get(i).toString();
                    id = 1;
                    expr.set(i, funcion(salida, id));
                    break;
                case "tan":
                    salida = expr.get(i).toString();
                    id = 2;
                    expr.set(i, funcion(salida, id));
                    break;
                case "cot":
                    salida = expr.get(i).toString();
                    id = 3;
                    expr.set(i, funcion(salida, id));
                    break;
                case "sec":
                    salida = expr.get(i).toString();
                    id = 4;
                    expr.set(i, funcion(salida, id));
                    break;
                case "csc":
                    id = 5;
                    salida = expr.get(i).toString();
                    expr.set(i, funcion(salida, id));
                    break;
            }

        }
        expr.stream().forEach((expr1) -> {
            System.out.println(expr1);
        });
        return expr;
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

        String ExpExter = "-cos (".concat(ExpInter) + ") + C";

        cad = "";
        cad = cad.concat(ExpExter);
        return cad;
    }

    private static String cos(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
        String ExpExter = "sen (".concat(ExpInter) + ") + C";

        cad = "";

        cad = cad.concat(ExpExter);

        return cad;
    }

    private static String tan(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));

        String ExpExter = "-ln |cos(".concat(ExpInter) + ")| + C";

        cad = "";

        cad = cad.concat(ExpExter);

        return cad;
    }

    private static String cot(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
        String ExpExter = "ln |sen(".concat(ExpInter) + ")| + C";

        cad = "";

        cad = cad.concat(ExpExter);

        return cad;
    }

    private static String sec(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));

        String ExpExter = "ln |sec(".concat(ExpInter) + ") " + "tan(".concat(ExpInter) + ")|";

        cad = "";

        cad = cad + " ";
        cad = cad.concat(ExpExter);

        return cad;
    }

    private static String csc(String cad) {
        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
        String ExpExter = "-ln |csc(".concat(ExpInter) + ") " + "cot(".concat(ExpInter) + ")| + C";

        cad = "";

        cad = cad.concat(ExpExter);

        return cad;
    }

}
