package graph; // Define que esta clase pertenece al paquete graph.

import java.util.ArrayList; // Importa ArrayList para manejar listas dinámicas.
import java.util.HashMap; // Importa HashMap para guardar distancias y predecesores.
import java.util.HashSet; // Importa HashSet para controlar vértices visitados.
import java.util.LinkedHashMap; // Importa LinkedHashMap para conservar el orden de inserción.
import java.util.List; // Importa List para trabajar con listas.
import java.util.Map; // Importa Map para manejar la lista de adyacencia.
import java.util.PriorityQueue; // Importa PriorityQueue para aplicar Dijkstra.
import java.util.Set; // Importa Set para almacenar vértices sin repetir.
import java.util.Stack; // Importa Stack para devolver la ruta más corta.

public class GraphLink<V, E> implements Graph<V, E> { // Define una implementación genérica del TAD Graph.

    private Map<V, Map<V, Integer>> adjacencyList; // Guarda cada vértice con sus adyacentes y pesos.

    public GraphLink() { // Define el constructor de la clase.

        adjacencyList = new LinkedHashMap<>(); // Inicializa la lista de adyacencia del grafo.

    } // Finaliza el constructor.

    @Override // Indica que el método pertenece a la interfaz Graph.
    public void insertVertex(V vertex) { // Inserta un vértice en el grafo.

        adjacencyList.putIfAbsent(vertex, new LinkedHashMap<>()); // Agrega el vértice solo si todavía no existe.

    } // Finaliza el método insertVertex.

    @Override // Indica que el método pertenece a la interfaz Graph.
    public void insertEdge(V origin, V destination, E edgeData) { // Inserta una arista genérica entre dos vértices.

        insertEdgeWeight(origin, destination, Integer.parseInt(edgeData.toString())); // Inserta la arista usando el dato recibido como peso.

    } // Finaliza el método insertEdge.

    public void insertEdgeWeight(V origin, V destination, int weight) { // Inserta una arista ponderada entre dos vértices.

        insertVertex(origin); // Asegura que el vértice origen exista.

        insertVertex(destination); // Asegura que el vértice destino exista.

        adjacencyList.get(origin).put(destination, weight); // Agrega la conexión desde origen hacia destino con su peso.

        adjacencyList.get(destination).put(origin, weight); // Agrega la conexión desde destino hacia origen por ser grafo no dirigido.

    } // Finaliza el método insertEdgeWeight.

    @Override // Indica que el método pertenece a la interfaz Graph.
    public boolean removeVertex(V vertex) { // Elimina un vértice del grafo.

        if (!adjacencyList.containsKey(vertex)) { // Verifica si el vértice no existe.

            return false; // Retorna false porque no se puede eliminar un vértice inexistente.

        } // Finaliza la validación del vértice.

        adjacencyList.remove(vertex); // Elimina el vértice principal del grafo.

        for (Map<V, Integer> edges : adjacencyList.values()) { // Recorre todas las listas de adyacencia.

            edges.remove(vertex); // Elimina cualquier conexión hacia el vértice eliminado.

        } // Finaliza el recorrido de adyacencias.

        return true; // Retorna true porque el vértice fue eliminado.

    } // Finaliza el método removeVertex.

    @Override // Indica que el método pertenece a la interfaz Graph.
    public boolean removeEdge(V origin, V destination) { // Elimina una arista entre dos vértices.

        if (!searchEdge(origin, destination)) { // Verifica si la arista no existe.

            return false; // Retorna false porque no hay arista que eliminar.

        } // Finaliza la validación de la arista.

        adjacencyList.get(origin).remove(destination); // Elimina la conexión desde origen hacia destino.

        adjacencyList.get(destination).remove(origin); // Elimina la conexión desde destino hacia origen.

        return true; // Retorna true porque la arista fue eliminada.

    } // Finaliza el método removeEdge.

    @Override // Indica que el método pertenece a la interfaz Graph.
    public boolean searchVertex(V vertex) { // Busca un vértice dentro del grafo.

        return adjacencyList.containsKey(vertex); // Retorna true si el vértice existe.

    } // Finaliza el método searchVertex.

    @Override // Indica que el método pertenece a la interfaz Graph.
    public boolean searchEdge(V origin, V destination) { // Busca una arista entre dos vértices.

        return adjacencyList.containsKey(origin) && adjacencyList.get(origin).containsKey(destination); // Retorna true si existe conexión entre ambos vértices.

    } // Finaliza el método searchEdge.

    @Override // Indica que el método pertenece a la interfaz Graph.
    public List<V> adjacentVertices(V vertex) { // Obtiene los vértices adyacentes de un vértice.

        if (!adjacencyList.containsKey(vertex)) { // Verifica si el vértice no existe.

            return new ArrayList<>(); // Retorna una lista vacía si no existe el vértice.

        } // Finaliza la validación del vértice.

        return new ArrayList<>(adjacencyList.get(vertex).keySet()); // Retorna los vértices conectados al vértice indicado.

    } // Finaliza el método adjacentVertices.

    public ArrayList<V> shortPath(V origin, V destination) { // Calcula la ruta más corta y la retorna como ArrayList.

        Stack<V> stack = Dijkstra(origin, destination); // Obtiene la ruta más corta usando Dijkstra.

        return new ArrayList<>(stack); // Convierte el Stack obtenido en un ArrayList.

    } // Finaliza el método shortPath.

    public boolean isConexo() { // Verifica si el grafo es conexo.

        if (adjacencyList.isEmpty()) { // Verifica si el grafo está vacío.

            return true; // Retorna true para evitar errores en un grafo sin vértices.

        } // Finaliza la validación de grafo vacío.

        Set<V> visited = new HashSet<>(); // Crea un conjunto para guardar los vértices visitados.

        V start = adjacencyList.keySet().iterator().next(); // Obtiene el primer vértice del grafo.

        dfs(start, visited); // Realiza un recorrido en profundidad desde el primer vértice.

        return visited.size() == adjacencyList.size(); // Retorna true si todos los vértices fueron visitados.

    } // Finaliza el método isConexo.

    private void dfs(V vertex, Set<V> visited) { // Ejecuta DFS para recorrer el grafo.

        visited.add(vertex); // Marca el vértice actual como visitado.

        for (V neighbor : adjacencyList.get(vertex).keySet()) { // Recorre los vecinos del vértice actual.

            if (!visited.contains(neighbor)) { // Verifica si el vecino todavía no fue visitado.

                dfs(neighbor, visited); // Continúa el recorrido desde el vecino.

            } // Finaliza la condición del vecino.

        } // Finaliza el recorrido de vecinos.

    } // Finaliza el método dfs.

    public Stack<V> Dijkstra(V origin, V destination) { // Calcula la ruta más corta entre dos vértices usando Dijkstra.

        Map<V, Integer> distances = new HashMap<>(); // Guarda la distancia mínima desde el origen hacia cada vértice.

        Map<V, V> previous = new HashMap<>(); // Guarda el vértice anterior dentro de la ruta más corta.

        PriorityQueue<V> queue = new PriorityQueue<>((a, b) -> distances.get(a) - distances.get(b)); // Ordena los vértices según su menor distancia.

        Stack<V> path = new Stack<>(); // Crea una pila para almacenar la ruta final.

        for (V vertex : adjacencyList.keySet()) { // Recorre todos los vértices del grafo.

            distances.put(vertex, Integer.MAX_VALUE); // Inicializa cada distancia como infinita.

        } // Finaliza la inicialización de distancias.

        if (!adjacencyList.containsKey(origin) || !adjacencyList.containsKey(destination)) { // Verifica que origen y destino existan.

            return path; // Retorna una ruta vacía si algún vértice no existe.

        } // Finaliza la validación de existencia.

        distances.put(origin, 0); // Asigna distancia cero al vértice origen.

        queue.add(origin); // Agrega el vértice origen a la cola de prioridad.

        while (!queue.isEmpty()) { // Repite el proceso mientras existan vértices por evaluar.

            V current = queue.poll(); // Obtiene el vértice con menor distancia acumulada.

            if (current.equals(destination)) { // Verifica si ya se llegó al destino.

                break; // Detiene el algoritmo porque ya se encontró la ruta más corta.

            } // Finaliza la verificación del destino.

            for (V neighbor : adjacencyList.get(current).keySet()) { // Recorre los vecinos del vértice actual.

                int weight = adjacencyList.get(current).get(neighbor); // Obtiene el peso de la arista hacia el vecino.

                int newDistance = distances.get(current) + weight; // Calcula la nueva distancia acumulada.

                if (newDistance < distances.get(neighbor)) { // Verifica si la nueva distancia es mejor.

                    distances.put(neighbor, newDistance); // Actualiza la distancia mínima del vecino.

                    previous.put(neighbor, current); // Guarda el vértice actual como predecesor del vecino.

                    queue.add(neighbor); // Agrega el vecino a la cola para seguir evaluándolo.

                } // Finaliza la comparación de distancias.

            } // Finaliza el recorrido de vecinos.

        } // Finaliza el ciclo principal de Dijkstra.

        if (!origin.equals(destination) && !previous.containsKey(destination)) { // Verifica si no existe ruta hacia el destino.

            return path; // Retorna una pila vacía si no se encontró camino.

        } // Finaliza la validación de ruta.

        V current = destination; // Inicia la reconstrucción de la ruta desde el destino.

        while (current != null) { // Recorre los predecesores hasta llegar al origen.

            path.push(current); // Agrega el vértice actual a la pila.

            current = previous.get(current); // Retrocede al vértice anterior de la ruta.

        } // Finaliza la reconstrucción inversa.

        Stack<V> correctPath = new Stack<>(); // Crea una nueva pila para ordenar la ruta desde origen a destino.

        while (!path.isEmpty()) { // Recorre la pila construida en orden inverso.

            correctPath.push(path.pop()); // Coloca los vértices en el orden correcto.

        } // Finaliza la inversión de la ruta.

        return correctPath; // Retorna la ruta más corta desde origen hasta destino.

    } // Finaliza el método Dijkstra.

    public int getDistance(V origin, V destination) { // Calcula el costo total de la ruta más corta.

        Stack<V> path = Dijkstra(origin, destination); // Obtiene la ruta más corta entre origen y destino.

        int total = 0; // Inicializa el acumulador del costo total.

        for (int i = 0; i < path.size() - 1; i++) { // Recorre los pares consecutivos de la ruta.

            total += adjacencyList.get(path.get(i)).get(path.get(i + 1)); // Suma el peso de cada arista del camino.

        } // Finaliza el recorrido de la ruta.

        return total; // Retorna el costo total calculado.

    } // Finaliza el método getDistance.

    @Override // Indica que se sobrescribe el método toString.
    public String toString() { // Muestra el grafo como lista de adyacencia.

        StringBuilder sb = new StringBuilder(); // Crea un acumulador de texto.

        for (V vertex : adjacencyList.keySet()) { // Recorre cada vértice del grafo.

            sb.append(vertex).append(" -> "); // Agrega el vértice actual a la salida.

            for (V neighbor : adjacencyList.get(vertex).keySet()) { // Recorre los vecinos del vértice actual.

                sb.append(neighbor).append("(").append(adjacencyList.get(vertex).get(neighbor)).append(") "); // Agrega el vecino y el peso de la arista.

            } // Finaliza el recorrido de vecinos.

            sb.append("\n"); // Agrega un salto de línea al finalizar cada vértice.

        } // Finaliza el recorrido de vértices.

        return sb.toString(); // Retorna la representación textual del grafo.

    } // Finaliza el método toString.

} // Finaliza la clase GraphLink.