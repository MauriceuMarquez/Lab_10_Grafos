package ejercicios; // Define que esta clase pertenece al paquete ejercicios.

import graph.GraphListEdge; // Importa la clase GraphListEdge desde el paquete graph.

public class Ejercicio4 { // Define la clase principal del Ejercicio 4.

    public static void main(String[] args) { // Inicia la ejecución del programa.

        GraphListEdge<String> grafo1 = new GraphListEdge<>(); // Crea el primer grafo dirigido.

        grafo1.insertEdge("A", "B"); // Inserta una arista dirigida desde A hacia B.
        grafo1.insertEdge("B", "C"); // Inserta una arista dirigida desde B hacia C.
        grafo1.insertEdge("C", "D"); // Inserta una arista dirigida desde C hacia D.
        grafo1.insertEdge("D", "A"); // Inserta una arista dirigida desde D hacia A.
        grafo1.insertEdge("A", "C"); // Inserta una arista dirigida desde A hacia C.

        GraphListEdge<String> grafo2 = new GraphListEdge<>(); // Crea el segundo grafo dirigido para comparar isomorfismo.

        grafo2.insertEdge("W", "X"); // Inserta una arista dirigida desde W hacia X.
        grafo2.insertEdge("X", "Y"); // Inserta una arista dirigida desde X hacia Y.
        grafo2.insertEdge("Y", "Z"); // Inserta una arista dirigida desde Y hacia Z.
        grafo2.insertEdge("Z", "W"); // Inserta una arista dirigida desde Z hacia W.
        grafo2.insertEdge("W", "Y"); // Inserta una arista dirigida desde W hacia Y.

        System.out.println("GRAFO 1:"); // Imprime el título del primer grafo.
        System.out.println(grafo1); // Muestra la lista de adyacencia del primer grafo.

        System.out.println("GRAFO 2:"); // Imprime el título del segundo grafo.
        System.out.println(grafo2); // Muestra la lista de adyacencia del segundo grafo.

        System.out.println("¿El grafo 1 es conexo?: " + grafo1.isConexo()); // Muestra si el primer grafo es conexo.

        System.out.println("¿El grafo 1 es plano?: " + grafo1.isPlano()); // Muestra si el primer grafo cumple la condición básica de planaridad.

        System.out.println("¿El grafo 1 es isomorfo con el grafo 2?: " + grafo1.isIsomorfo(grafo2)); // Muestra si ambos grafos tienen la misma forma básica.

        System.out.println("¿El grafo 1 es autocomplementario?: " + grafo1.isAutoComplementario()); // Muestra si el grafo es isomorfo con su complemento.

    } // Finaliza el método main.

} // Finaliza la clase Ejercicio4.