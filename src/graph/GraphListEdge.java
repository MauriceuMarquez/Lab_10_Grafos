package graph; // Define que esta clase pertenece al paquete graph.

import java.util.ArrayList; // Importa ArrayList para manejar listas dinámicas.
import java.util.HashSet; // Importa HashSet para evitar datos repetidos.
import java.util.LinkedHashMap; // Importa LinkedHashMap para conservar el orden de inserción.
import java.util.List; // Importa List para trabajar con listas.
import java.util.Map; // Importa Map para almacenar las relaciones del grafo.
import java.util.Set; // Importa Set para almacenar conjuntos sin repetidos.

public class GraphListEdge<V> { // Define una clase genérica para representar un grafo dirigido.

    private Map<V, List<V>> adjacencyList; // Declara la lista de adyacencia del grafo dirigido.

    public GraphListEdge() { // Define el constructor de la clase.

        adjacencyList = new LinkedHashMap<>(); // Inicializa la estructura del grafo conservando el orden de inserción.

    } // Finaliza el constructor.

    public void insertVertex(V vertex) { // Define el método para insertar un vértice.

        adjacencyList.putIfAbsent(vertex, new ArrayList<>()); // Inserta el vértice solo si todavía no existe.

    } // Finaliza el método insertVertex.

    public void insertEdge(V origin, V destination) { // Define el método para insertar una arista dirigida.

        insertVertex(origin); // Asegura que el vértice origen exista.

        insertVertex(destination); // Asegura que el vértice destino exista.

        if (!adjacencyList.get(origin).contains(destination)) { // Verifica que la arista no esté repetida.

            adjacencyList.get(origin).add(destination); // Agrega la conexión dirigida desde origen hacia destino.

        } // Finaliza la verificación de arista repetida.

    } // Finaliza el método insertEdge.

    public int vertexCount() { // Define el método para contar vértices.

        return adjacencyList.size(); // Retorna la cantidad de vértices del grafo.

    } // Finaliza el método vertexCount.

    public int edgeCount() { // Define el método para contar aristas.

        int count = 0; // Inicializa el contador de aristas.

        for (V vertex : adjacencyList.keySet()) { // Recorre cada vértice del grafo.

            count += adjacencyList.get(vertex).size(); // Suma la cantidad de aristas salientes del vértice.

        } // Finaliza el recorrido de vértices.

        return count; // Retorna la cantidad total de aristas.

    } // Finaliza el método edgeCount.

    public boolean isConexo() { // Define el método para verificar si el grafo es conexo.

        if (adjacencyList.isEmpty()) { // Verifica si el grafo no tiene vértices.

            return true; // Considera conexo al grafo vacío para evitar errores.

        } // Finaliza la verificación de grafo vacío.

        V start = adjacencyList.keySet().iterator().next(); // Obtiene el primer vértice del grafo.

        Set<V> visited = new HashSet<>(); // Crea un conjunto para registrar los vértices visitados.

        dfsUndirected(start, visited); // Realiza un recorrido considerando conexiones en ambos sentidos.

        return visited.size() == adjacencyList.size(); // Retorna true si todos los vértices fueron visitados.

    } // Finaliza el método isConexo.

    private void dfsUndirected(V vertex, Set<V> visited) { // Define un DFS auxiliar para evaluar conectividad.

        visited.add(vertex); // Marca el vértice actual como visitado.

        for (V neighbor : getUndirectedNeighbors(vertex)) { // Recorre los vecinos considerando entrada y salida.

            if (!visited.contains(neighbor)) { // Verifica si el vecino aún no fue visitado.

                dfsUndirected(neighbor, visited); // Continúa el recorrido desde el vecino.

            } // Finaliza la verificación del vecino.

        } // Finaliza el recorrido de vecinos.

    } // Finaliza el método dfsUndirected.

    private List<V> getUndirectedNeighbors(V vertex) { // Define el método para obtener vecinos como si el grafo fuera no dirigido.

        List<V> neighbors = new ArrayList<>(); // Crea una lista para almacenar vecinos.

        neighbors.addAll(adjacencyList.get(vertex)); // Agrega los destinos directos del vértice.

        for (V current : adjacencyList.keySet()) { // Recorre todos los vértices del grafo.

            if (adjacencyList.get(current).contains(vertex) && !neighbors.contains(current)) { // Verifica si existe una arista entrante hacia el vértice.

                neighbors.add(current); // Agrega el vértice que apunta al vértice actual.

            } // Finaliza la verificación de arista entrante.

        } // Finaliza el recorrido de vértices.

        return neighbors; // Retorna la lista de vecinos obtenidos.

    } // Finaliza el método getUndirectedNeighbors.

    public boolean isPlano() { // Define el método para evaluar si el grafo puede considerarse plano.

        int v = vertexCount(); // Obtiene la cantidad de vértices.

        int e = edgeCount(); // Obtiene la cantidad de aristas.

        if (v <= 4) { // Verifica si el grafo tiene cuatro vértices o menos.

            return true; // Retorna true porque los grafos pequeños suelen ser planos.

        } // Finaliza la verificación de grafos pequeños.

        return e <= (3 * v - 6); // Aplica la condición básica de planaridad para grafos simples.

    } // Finaliza el método isPlano.

    public boolean isIsomorfo(GraphListEdge<V> other) { // Define el método para verificar isomorfismo básico entre dos grafos.

        if (this.vertexCount() != other.vertexCount()) { // Compara la cantidad de vértices de ambos grafos.

            return false; // Retorna false si tienen diferente cantidad de vértices.

        } // Finaliza la comparación de vértices.

        if (this.edgeCount() != other.edgeCount()) { // Compara la cantidad de aristas de ambos grafos.

            return false; // Retorna false si tienen diferente cantidad de aristas.

        } // Finaliza la comparación de aristas.

        return this.degreeSequence().equals(other.degreeSequence()); // Compara la secuencia de grados para decidir si tienen la misma forma básica.

    } // Finaliza el método isIsomorfo.

    private List<Integer> degreeSequence() { // Define el método para obtener la secuencia de grados.

        List<Integer> degrees = new ArrayList<>(); // Crea una lista para guardar los grados.

        for (V vertex : adjacencyList.keySet()) { // Recorre cada vértice del grafo.

            int degree = adjacencyList.get(vertex).size() + inDegree(vertex); // Calcula el grado total sumando salidas y entradas.

            degrees.add(degree); // Agrega el grado calculado a la lista.

        } // Finaliza el recorrido de vértices.

        degrees.sort(Integer::compareTo); // Ordena los grados de menor a mayor.

        return degrees; // Retorna la secuencia ordenada de grados.

    } // Finaliza el método degreeSequence.

    private int inDegree(V vertex) { // Define el método para calcular el grado de entrada.

        int count = 0; // Inicializa el contador de entradas.

        for (V current : adjacencyList.keySet()) { // Recorre todos los vértices del grafo.

            if (adjacencyList.get(current).contains(vertex)) { // Verifica si el vértice actual apunta al vértice recibido.

                count++; // Incrementa el contador de entradas.

            } // Finaliza la verificación de entrada.

        } // Finaliza el recorrido de vértices.

        return count; // Retorna el grado de entrada.

    } // Finaliza el método inDegree.

    public boolean isAutoComplementario() { // Define el método para verificar si el grafo es autocomplementario.

        GraphListEdge<V> complement = getComplement(); // Genera el grafo complemento.

        return this.isIsomorfo(complement); // Verifica si el grafo original es isomorfo con su complemento.

    } // Finaliza el método isAutoComplementario.

    private GraphListEdge<V> getComplement() { // Define el método para construir el complemento del grafo.

        GraphListEdge<V> complement = new GraphListEdge<>(); // Crea un nuevo grafo para almacenar el complemento.

        for (V vertex : adjacencyList.keySet()) { // Recorre cada vértice del grafo original.

            complement.insertVertex(vertex); // Inserta el mismo vértice en el complemento.

        } // Finaliza la inserción de vértices.

        for (V origin : adjacencyList.keySet()) { // Recorre cada vértice como posible origen.

            for (V destination : adjacencyList.keySet()) { // Recorre cada vértice como posible destino.

                if (!origin.equals(destination) && !adjacencyList.get(origin).contains(destination)) { // Verifica que no sea lazo y que no exista la arista original.

                    complement.insertEdge(origin, destination); // Inserta en el complemento la arista que no existía.

                } // Finaliza la verificación de arista complementaria.

            } // Finaliza el recorrido de destinos.

        } // Finaliza el recorrido de orígenes.

        return complement; // Retorna el grafo complemento construido.

    } // Finaliza el método getComplement.

    @Override // Indica que se sobrescribe el método toString.

    public String toString() { // Define la forma de mostrar el grafo.

        StringBuilder sb = new StringBuilder(); // Crea un acumulador para construir el texto del grafo.

        for (V vertex : adjacencyList.keySet()) { // Recorre cada vértice del grafo.

            sb.append(vertex).append(" -> "); // Agrega el vértice actual al texto.

            for (V neighbor : adjacencyList.get(vertex)) { // Recorre los vecinos directos del vértice.

                sb.append(neighbor).append(" "); // Agrega cada vecino al texto.

            } // Finaliza el recorrido de vecinos.

            sb.append("\n"); // Agrega un salto de línea al terminar cada vértice.

        } // Finaliza el recorrido de vértices.

        return sb.toString(); // Retorna la representación textual del grafo.

    } // Finaliza el método toString.

} // Finaliza la clase GraphListEdge.