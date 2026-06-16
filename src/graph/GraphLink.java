package graph; // Define que esta clase pertenece al paquete graph.

import java.util.ArrayList; // Importa ArrayList para crear listas dinámicas.
import java.util.LinkedHashMap; // Importa LinkedHashMap para mantener el orden de inserción.
import java.util.List; // Importa List para manejar listas de vértices adyacentes.
import java.util.Map; // Importa Map para representar la lista de adyacencia.

public class GraphLink<V, E> implements Graph<V, E> { // Define una clase que implementa el TAD Graph usando listas de adyacencia.

    private Map<V, Map<V, E>> adjacencyList; // Declara la estructura principal del grafo mediante listas de adyacencia.

    public GraphLink() { // Define el constructor de la clase GraphLink.

        adjacencyList = new LinkedHashMap<>(); // Inicializa el grafo manteniendo el orden en que se insertan los vértices.

    } // Finaliza el constructor.

    @Override // Indica que el método sobrescribe una operación de la interfaz Graph.
    public void insertVertex(V vertex) { // Define el método para insertar un vértice.

        if (!adjacencyList.containsKey(vertex)) { // Verifica que el vértice no exista previamente en el grafo.

            adjacencyList.put(vertex, new LinkedHashMap<>()); // Inserta el vértice con una lista vacía de adyacentes.

        } // Finaliza la verificación de existencia del vértice.

    } // Finaliza el método insertVertex.

    @Override // Indica que el método sobrescribe una operación de la interfaz Graph.
    public void insertEdge(V origin, V destination, E edgeData) { // Define el método para insertar una arista.

        insertVertex(origin); // Inserta el vértice origen si todavía no existe.

        insertVertex(destination); // Inserta el vértice destino si todavía no existe.

        adjacencyList.get(origin).put(destination, edgeData); // Agrega la conexión desde el origen hacia el destino.

        adjacencyList.get(destination).put(origin, edgeData); // Agrega la conexión desde el destino hacia el origen por ser grafo no dirigido.

    } // Finaliza el método insertEdge.

    @Override // Indica que el método sobrescribe una operación de la interfaz Graph.
    public boolean removeVertex(V vertex) { // Define el método para eliminar un vértice.

        if (!adjacencyList.containsKey(vertex)) { // Verifica si el vértice no existe en el grafo.

            return false; // Retorna false porque no se puede eliminar un vértice inexistente.

        } // Finaliza la verificación del vértice.

        adjacencyList.remove(vertex); // Elimina el vértice principal de la lista de adyacencia.

        for (Map<V, E> edges : adjacencyList.values()) { // Recorre todas las listas de adyacencia del grafo.

            edges.remove(vertex); // Elimina cualquier arista que apunte al vértice eliminado.

        } // Finaliza el recorrido de las listas de adyacencia.

        return true; // Retorna true porque el vértice fue eliminado correctamente.

    } // Finaliza el método removeVertex.

    @Override // Indica que el método sobrescribe una operación de la interfaz Graph.
    public boolean removeEdge(V origin, V destination) { // Define el método para eliminar una arista.

        if (!searchEdge(origin, destination)) { // Verifica si la arista no existe en el grafo.

            return false; // Retorna false porque no se puede eliminar una arista inexistente.

        } // Finaliza la verificación de la arista.

        adjacencyList.get(origin).remove(destination); // Elimina la conexión desde el origen hacia el destino.

        adjacencyList.get(destination).remove(origin); // Elimina la conexión desde el destino hacia el origen.

        return true; // Retorna true porque la arista fue eliminada correctamente.

    } // Finaliza el método removeEdge.

    @Override // Indica que el método sobrescribe una operación de la interfaz Graph.
    public boolean searchVertex(V vertex) { // Define el método para buscar un vértice.

        return adjacencyList.containsKey(vertex); // Retorna true si el vértice existe dentro del grafo.

    } // Finaliza el método searchVertex.

    @Override // Indica que el método sobrescribe una operación de la interfaz Graph.
    public boolean searchEdge(V origin, V destination) { // Define el método para buscar una arista.

        return adjacencyList.containsKey(origin) && adjacencyList.get(origin).containsKey(destination); // Retorna true si existe conexión entre origen y destino.

    } // Finaliza el método searchEdge.

    @Override // Indica que el método sobrescribe una operación de la interfaz Graph.
    public List<V> adjacentVertices(V vertex) { // Define el método para obtener los vértices adyacentes.

        if (!adjacencyList.containsKey(vertex)) { // Verifica si el vértice no existe en el grafo.

            return new ArrayList<>(); // Retorna una lista vacía si el vértice no existe.

        } // Finaliza la verificación del vértice.

        return new ArrayList<>(adjacencyList.get(vertex).keySet()); // Retorna la lista de vértices conectados al vértice indicado.

    } // Finaliza el método adjacentVertices.

    public E getEdgeData(V origin, V destination) { // Define un método adicional para obtener el dato o peso de una arista.

        if (!searchEdge(origin, destination)) { // Verifica si la arista no existe.

            return null; // Retorna null si no existe conexión entre los vértices.

        } // Finaliza la verificación de la arista.

        return adjacencyList.get(origin).get(destination); // Retorna el dato asociado a la arista.

    } // Finaliza el método getEdgeData.

    @Override // Indica que el método sobrescribe el método toString.
    public String toString() { // Define el método para mostrar el grafo como texto.

        StringBuilder sb = new StringBuilder(); // Crea un acumulador de texto para formar la salida del grafo.

        for (V vertex : adjacencyList.keySet()) { // Recorre cada vértice almacenado en el grafo.

            sb.append(vertex).append(" -> "); // Agrega el vértice actual a la salida.

            for (V adjacent : adjacencyList.get(vertex).keySet()) { // Recorre los vértices adyacentes del vértice actual.

                sb.append(adjacent).append("(").append(adjacencyList.get(vertex).get(adjacent)).append(") "); // Agrega el adyacente y el dato de la arista.

            } // Finaliza el recorrido de adyacentes.

            sb.append("\n"); // Agrega un salto de línea después de mostrar cada vértice.

        } // Finaliza el recorrido de vértices.

        return sb.toString(); // Retorna la representación textual del grafo.

    } // Finaliza el método toString.

} // Finaliza la clase GraphLink.