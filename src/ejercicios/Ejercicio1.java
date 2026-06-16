package ejercicios; // Define que esta clase pertenece al paquete ejercicios.

import graph.GraphLink; // Importa la clase GraphLink desde el paquete graph.
import java.util.ArrayList; // Importa ArrayList para almacenar la ruta más corta.
import java.util.Stack; // Importa Stack para probar el método Dijkstra.

public class Ejercicio1 { // Define la clase principal del Ejercicio 1.

    public static void main(String[] args) { // Inicia la ejecución del programa.

        GraphLink<String, Integer> grafo = new GraphLink<>(); // Crea un grafo no dirigido ponderado.

        grafo.insertEdgeWeight("A", "B", 4); // Inserta una arista ponderada entre A y B.
        grafo.insertEdgeWeight("A", "C", 2); // Inserta una arista ponderada entre A y C.
        grafo.insertEdgeWeight("B", "D", 5); // Inserta una arista ponderada entre B y D.
        grafo.insertEdgeWeight("C", "D", 1); // Inserta una arista ponderada entre C y D.
        grafo.insertEdgeWeight("D", "E", 3); // Inserta una arista ponderada entre D y E.
        grafo.insertEdgeWeight("B", "E", 10); // Inserta una arista ponderada entre B y E.

        System.out.println("GRAFO PONDERADO NO DIRIGIDO:"); // Imprime el título del grafo.

        System.out.println(grafo); // Muestra el grafo con sus vértices, adyacentes y pesos.

        System.out.println("¿EL GRAFO ES CONEXO?: " + grafo.isConexo()); // Muestra si todos los vértices están conectados.

        ArrayList<String> rutaArrayList = grafo.shortPath("A", "E"); // Calcula la ruta más corta usando shortPath.

        System.out.println("RUTA MÁS CORTA CON shortPath(A, E): " + rutaArrayList); // Imprime la ruta devuelta en ArrayList.

        Stack<String> rutaStack = grafo.Dijkstra("A", "E"); // Calcula la ruta más corta usando Dijkstra.

        System.out.println("RUTA MÁS CORTA CON Dijkstra(A, E): " + rutaStack); // Imprime la ruta devuelta en Stack.

        System.out.println("COSTO TOTAL DE LA RUTA: " + grafo.getDistance("A", "E")); // Imprime el costo total del camino más corto.

    } // Finaliza el método main.

} // Finaliza la clase Ejercicio1.