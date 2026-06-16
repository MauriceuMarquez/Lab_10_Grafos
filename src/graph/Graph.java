package graph; // Define que esta interfaz pertenece al paquete graph.

import java.util.List; // Importa List para devolver listas de vértices adyacentes.

public interface Graph<V, E> { // Define una interfaz genérica para representar el TAD Graph.

    void insertVertex(V vertex); // Declara el método para insertar un vértice en el grafo.

    void insertEdge(V origin, V destination, E edgeData); // Declara el método para insertar una arista entre dos vértices.

    boolean removeVertex(V vertex); // Declara el método para eliminar un vértice del grafo.

    boolean removeEdge(V origin, V destination); // Declara el método para eliminar una arista entre dos vértices.

    boolean searchVertex(V vertex); // Declara el método para buscar si un vértice existe en el grafo.

    boolean searchEdge(V origin, V destination); // Declara el método para buscar si existe una arista entre dos vértices.

    List<V> adjacentVertices(V vertex); // Declara el método para obtener los vértices adyacentes a un vértice.

} // Finaliza la interfaz Graph.