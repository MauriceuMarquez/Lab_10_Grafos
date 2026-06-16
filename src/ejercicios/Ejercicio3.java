package ejercicios; // Define que esta clase pertenece al paquete ejercicios.

import graph.GraphLink; // Importa la implementación GraphLink creada en el paquete graph.
import java.util.List; // Importa List para almacenar vértices adyacentes.

public class Ejercicio3 { // Define la clase principal del Ejercicio 3.

    public static void main(String[] args) { // Inicia la ejecución del programa.

        GraphLink<String, Integer> grafo = new GraphLink<>(); // Crea un grafo no dirigido usando vértices String y pesos Integer.

        grafo.insertVertex("A"); // Inserta el vértice A en el grafo.
        grafo.insertVertex("B"); // Inserta el vértice B en el grafo.
        grafo.insertVertex("C"); // Inserta el vértice C en el grafo.
        grafo.insertVertex("D"); // Inserta el vértice D en el grafo.
        grafo.insertVertex("E"); // Inserta el vértice E en el grafo.

        grafo.insertEdge("A", "B", 4); // Inserta una arista entre A y B con peso 4.
        grafo.insertEdge("A", "C", 2); // Inserta una arista entre A y C con peso 2.
        grafo.insertEdge("B", "D", 5); // Inserta una arista entre B y D con peso 5.
        grafo.insertEdge("C", "D", 1); // Inserta una arista entre C y D con peso 1.
        grafo.insertEdge("D", "E", 3); // Inserta una arista entre D y E con peso 3.

        System.out.println("GRAFO INICIAL:"); // Imprime el título del grafo inicial.
        System.out.println(grafo); // Muestra la lista de adyacencia del grafo.

        System.out.println("BUSCAR VÉRTICE C: " + grafo.searchVertex("C")); // Muestra si el vértice C existe.
        System.out.println("BUSCAR VÉRTICE Z: " + grafo.searchVertex("Z")); // Muestra si el vértice Z existe.

        System.out.println("BUSCAR ARISTA A-B: " + grafo.searchEdge("A", "B")); // Muestra si existe arista entre A y B.
        System.out.println("BUSCAR ARISTA A-E: " + grafo.searchEdge("A", "E")); // Muestra si existe arista entre A y E.

        List<String> adyacentesD = grafo.adjacentVertices("D"); // Obtiene los vértices adyacentes al vértice D.
        System.out.println("ADYACENTES DE D: " + adyacentesD); // Muestra los vértices conectados al vértice D.

        grafo.removeEdge("A", "B"); // Elimina la arista existente entre A y B.
        System.out.println("GRAFO DESPUÉS DE ELIMINAR LA ARISTA A-B:"); // Imprime el título después de eliminar la arista.
        System.out.println(grafo); // Muestra el grafo actualizado después de eliminar la arista.

        grafo.removeVertex("C"); // Elimina el vértice C y sus conexiones.
        System.out.println("GRAFO DESPUÉS DE ELIMINAR EL VÉRTICE C:"); // Imprime el título después de eliminar el vértice.
        System.out.println(grafo); // Muestra el grafo actualizado después de eliminar el vértice.

    } // Finaliza el método main.

} // Finaliza la clase Ejercicio3.