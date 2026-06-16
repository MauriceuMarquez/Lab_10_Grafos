package ejercicios; // Define que la clase pertenece al paquete ejercicios.

import org.jgrapht.Graph; // Importa la interfaz Graph para manejar grafos.
import org.jgrapht.GraphPath; // Importa la clase GraphPath para almacenar el camino más corto.
import org.jgrapht.alg.shortestpath.DijkstraShortestPath; // Importa el algoritmo de Dijkstra.
import org.jgrapht.graph.DefaultWeightedEdge; // Importa las aristas ponderadas por defecto.
import org.jgrapht.graph.SimpleWeightedGraph; // Importa el grafo ponderado no dirigido.

public class Ejercicio2 { // Define la clase principal del ejercicio.

    public static void main(String[] args) { // Inicia la ejecución del programa.

        Graph<String, DefaultWeightedEdge> grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class); // Crea un grafo ponderado no dirigido.

        agregarCiudad(grafo, "Arequipa"); // Agrega la ciudad Arequipa al grafo.
        agregarCiudad(grafo, "Cusco"); // Agrega la ciudad Cusco al grafo.
        agregarCiudad(grafo, "Puno"); // Agrega la ciudad Puno al grafo.
        agregarCiudad(grafo, "Tacna"); // Agrega la ciudad Tacna al grafo.
        agregarCiudad(grafo, "Moquegua"); // Agrega la ciudad Moquegua al grafo.

        agregarCarretera(grafo, "Arequipa", "Cusco", 510); // Registra la carretera entre Arequipa y Cusco.
        agregarCarretera(grafo, "Arequipa", "Moquegua", 230); // Registra la carretera entre Arequipa y Moquegua.
        agregarCarretera(grafo, "Moquegua", "Tacna", 160); // Registra la carretera entre Moquegua y Tacna.
        agregarCarretera(grafo, "Cusco", "Puno", 390); // Registra la carretera entre Cusco y Puno.
        agregarCarretera(grafo, "Puno", "Tacna", 420); // Registra la carretera entre Puno y Tacna.

        mostrarCiudades(grafo); // Muestra todas las ciudades registradas.
        mostrarCarreteras(grafo); // Muestra todas las carreteras registradas.
        calcularCaminoMasCorto(grafo, "Arequipa", "Tacna"); // Calcula la ruta más corta entre Arequipa y Tacna.

    } // Finaliza el método main.

    public static void agregarCiudad(Graph<String, DefaultWeightedEdge> grafo, String ciudad) { // Define el método para agregar una ciudad.

        grafo.addVertex(ciudad); // Inserta la ciudad como vértice del grafo.

    } // Finaliza el método agregarCiudad.

    public static void agregarCarretera(Graph<String, DefaultWeightedEdge> grafo, String origen, String destino, double distancia) { // Define el método para agregar una carretera.

        DefaultWeightedEdge carretera = grafo.addEdge(origen, destino); // Crea una arista entre dos ciudades.

        grafo.setEdgeWeight(carretera, distancia); // Asigna la distancia como peso de la arista.

    } // Finaliza el método agregarCarretera.

    public static void mostrarCiudades(Graph<String, DefaultWeightedEdge> grafo) { // Define el método para mostrar las ciudades.

        System.out.println("LISTA DE CIUDADES:"); // Imprime el encabezado de ciudades.

        for (String ciudad : grafo.vertexSet()) { // Recorre todas las ciudades registradas.

            System.out.println("- " + ciudad); // Imprime el nombre de cada ciudad.

        } // Finaliza el recorrido de ciudades.

        System.out.println(); // Imprime una línea en blanco.

    } // Finaliza el método mostrarCiudades.

    public static void mostrarCarreteras(Graph<String, DefaultWeightedEdge> grafo) { // Define el método para mostrar las carreteras.

        System.out.println("CARRETERAS REGISTRADAS:"); // Imprime el encabezado de carreteras.

        for (DefaultWeightedEdge carretera : grafo.edgeSet()) { // Recorre todas las carreteras registradas.

            String origen = grafo.getEdgeSource(carretera); // Obtiene la ciudad origen de la carretera.

            String destino = grafo.getEdgeTarget(carretera); // Obtiene la ciudad destino de la carretera.

            double distancia = grafo.getEdgeWeight(carretera); // Obtiene la distancia de la carretera.

            System.out.println(origen + " <-> " + destino + " = " + distancia + " km"); // Imprime la carretera con su distancia.

        } // Finaliza el recorrido de carreteras.

        System.out.println(); // Imprime una línea en blanco.

    } // Finaliza el método mostrarCarreteras.

    public static void calcularCaminoMasCorto(Graph<String, DefaultWeightedEdge> grafo, String origen, String destino) { // Define el método para calcular el camino más corto.

        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(grafo); // Crea el algoritmo de Dijkstra sobre el grafo.

        GraphPath<String, DefaultWeightedEdge> camino = dijkstra.getPath(origen, destino); // Obtiene el camino más corto entre origen y destino.

        System.out.println("CAMINO MÁS CORTO DE " + origen + " A " + destino + ":"); // Imprime el encabezado del resultado.

        if (camino != null) { // Verifica si existe una ruta entre las ciudades.

            System.out.println("Ruta: " + camino.getVertexList()); // Imprime la lista de ciudades de la ruta más corta.

            System.out.println("Costo total: " + camino.getWeight() + " km"); // Imprime el costo total de la ruta.

        } else { // Ejecuta este bloque si no existe ruta.

            System.out.println("No existe una ruta disponible entre las ciudades indicadas."); // Informa que no existe camino.

        } // Finaliza la condición.

    } // Finaliza el método calcularCaminoMasCorto.

} // Finaliza la clase Ejercicio2.