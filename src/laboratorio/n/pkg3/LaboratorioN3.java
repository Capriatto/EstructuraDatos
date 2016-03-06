/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio.n.pkg3;

import java.util.Scanner;

public class LaboratorioN3 {
    public static void main(String[] args) {

        System.out.println("*Escribe una expresión infija: ");
        Scanner expresion = new Scanner(System.in);

        String expr = comprobarExpresion(expresion.nextLine());
        String[] expresionInfija = expr.split(" ");

        Pila pilaExpresionInfija = new Pila();
        Pila pilaAuxiliar = new Pila();
        Pila pilaExpresionPostfija = new Pila();
        Pila pilaInvertirDatos = new Pila();

        for (int i = expresionInfija.length - 1; i >= 0; i--) {
            pilaExpresionInfija.apilar(expresionInfija[i]);
        }

        try {
            while (!pilaExpresionInfija.esVacia()) {
                switch (jerarquiaOps((String) pilaExpresionInfija.cima())) {
                    case 1:
                        pilaAuxiliar.apilar(pilaExpresionInfija.desapilar());
                        break;
                    case 3:
                    case 4:
                        while (jerarquiaOps((String) pilaAuxiliar.cima()) >= jerarquiaOps((String) pilaExpresionInfija.cima())) {
                            pilaExpresionPostfija.apilar(pilaAuxiliar.desapilar());
                        }
                        pilaAuxiliar.apilar(pilaExpresionInfija.desapilar());
                        break;
                    case 2:
                        while (!pilaAuxiliar.cima().equals("(")) {
                            pilaExpresionPostfija.apilar(pilaAuxiliar.desapilar());

                        }
                        pilaAuxiliar.desapilar();
                        pilaExpresionInfija.desapilar();
                        break;
                    default:
                        pilaExpresionPostfija.apilar(pilaExpresionInfija.desapilar());
                }
            }
            String infix = expr.replace(" ", "");
            pilaInvertirDatos.invierte(pilaExpresionPostfija, pilaInvertirDatos);
            String postfix = pilaInvertirDatos.concatenarPila().toString().replaceAll("[\\]\\[,]", "");
            System.out.println("------------------------------------\n");
            System.out.println("Expresion Infija ---> " + infix);
            System.out.println("Expresion Postfija  ---> " + postfix);

        } catch (Exception ex) {
            System.out.println("Error en la expresión algebraica");
        }
    }

    private static String comprobarExpresion(String s) {
        s = s.replaceAll("\\s+", "");
        s = "(" + s + ")";
        String simbols = "+-*/()";
        String str = "";

        for (int i = 0; i < s.length(); i++) {
            if (simbols.contains("" + s.charAt(i))) {
                str += " " + s.charAt(i) + " ";
            } else {
                str += s.charAt(i);
            }
        }
        return str.replaceAll("\\s+", " ").trim();
    }

    private static int jerarquiaOps(String operacion) {
        int prioridad = Integer.MAX_VALUE;
        if (operacion.equals("(")) {
            prioridad = 1;
        }
        if (operacion.equals(")")) {
            prioridad = 2;
        }
        if (operacion.equals("+") || operacion.equals("-")) {
            prioridad = 3;
        }
        if (operacion.equals("*") || operacion.equals("/")) {
            prioridad = 4;
        }
        if (operacion.equals("^")) {
            prioridad = 5;
        }
        return prioridad;
    }

}
