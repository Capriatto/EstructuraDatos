/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio.n.pkg3;


public class Pila<E> {

    private Nodo<E> cima;
    private int tamanoPila;

    public void apilar(E dato) {
        Nodo<E> nuevoNodoPila = new Nodo();
        nuevoNodoPila.setDato(dato);
        if (cima == null) {

            cima = nuevoNodoPila;
            tamanoPila++;

        } else {
            Nodo aux = cima;
            cima = nuevoNodoPila;
            cima.siguiente = aux;
            tamanoPila++;

        }
        
        
    }



    public boolean esVacia() {
        if (tamanoPila == 0) {
            return true;
        }
        return false;
    }

    public E cima() {
        E aux = cima.getDato();
        return aux;

    }

    public E desapilar() {
        E aux = cima.getDato();
        cima = cima.siguiente;
        tamanoPila--;

        return aux;

    }
    
    public E concatenarPila(){
        Nodo <E> aux = cima;
        E auxiliar=(E) "";
        while (aux != null) {
            
            auxiliar+=" "+aux.getDato();
            aux = aux.siguiente;

        }
        return auxiliar;
    }

    public Pila invierte(Pila p, Pila n) {

        while (!p.esVacia()) {
            n.apilar(p.desapilar());
        }
        return n;
    }
}
