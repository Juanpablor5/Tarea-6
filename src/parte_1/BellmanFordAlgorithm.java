package parte_1;

public class BellmanFordAlgorithm {

	/**
	 * Clase que crea cada arista con su fuente, destino y peso.
	 *
	 */
	public class ClaseArista {
		int fuente, destino, peso;

		ClaseArista() {
			fuente = destino = peso = 0;
		}
	};

	private int nodo, arista;
	private ClaseArista arrAristas[];

	/**
	 * Crea el grafo con el numero de nodos y aristas que le llega por parámetro
	 * 
	 * @param pNodo
	 *            Número de nodos en el grafo
	 * @param pArista
	 *            Número de aristas en el grafo
	 */
	public BellmanFordAlgorithm(int pNodo, int pArista) {
		nodo = pNodo;
		arista = pArista;
		arrAristas = new ClaseArista[pArista];
		for (int i = 0; i < pArista; ++i)
			arrAristas[i] = new ClaseArista();
	}

	/**
	 * Im
	 * 
	 * @param grafo
	 * @param fuente
	 */
	public void BellmanFord(BellmanFordAlgorithm grafo, int fuente) {
		long startTime = System.currentTimeMillis();
		int nodo = grafo.nodo, E = grafo.arista;
		int dist[] = new int[nodo];

		for (int i = 0; i < nodo; ++i)
			dist[i] = Integer.MAX_VALUE;
		dist[fuente] = 0;

		for (int i = 1; i < nodo; ++i) {
			for (int j = 0; j < E; ++j) {
				int u = grafo.arrAristas[j].fuente;
				int v = grafo.arrAristas[j].destino;
				int weight = grafo.arrAristas[j].peso;
				if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
					dist[v] = dist[u] + weight;
			}
		}

		for (int j = 0; j < E; ++j) {
			int u = grafo.arrAristas[j].fuente;
			int v = grafo.arrAristas[j].destino;
			int weight = grafo.arrAristas[j].peso;
			if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
				System.out.println("BellmanFordAlgorithm contains negative weight cycle");
		}
		long endTime = System.currentTimeMillis() - startTime;
		printBellmanFordAlgorithm(dist, nodo, fuente, endTime);
	}

	/**
	 * Ejecuta el algorítmo Bellman Ford
	 * 
	 * @param matriz
	 *            Matriz representativa del grafo, cargada en el main
	 * @param nodos
	 *            Número de nodos en el grafo
	 * @param aristas
	 *            Número de aristas en el grafo
	 * @param fuente
	 *            Nodo fuente
	 */
	public void crearGrafoBF(int[][] matriz, int nodos, int aristas, int fuente) {
		int c = 0;

		BellmanFordAlgorithm grafo = new BellmanFordAlgorithm(nodos, aristas);

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j] > 0) {
					grafo.arrAristas[c].fuente = i;
					grafo.arrAristas[c].destino = j;
					grafo.arrAristas[c].peso = matriz[i][j];
					c++;
				}
			}
		}
		BellmanFord(grafo, fuente);
	}

	/**
	 * Imprime los resultados de los caminos de costos mínimos para el nodo
	 * fuente.
	 * 
	 * @param distancia
	 *            Distancia acumulada desde el nodo fuente al nodo destino.
	 * @param size
	 *            Tamaño de la matriz cuadrada
	 * @param fuente
	 *            Nodo fuente
	 * @param time
	 *            Tiempo de ejecución del algoritmo
	 */
	public void printBellmanFordAlgorithm(int distancia[], int size, int fuente, long time) {
		System.out.println("El costo mínimo del nodo fuente número " + fuente + " a cada nodo destino es: ");
		for (int i = 0; i < size; i++) {
			System.out.println("Destino: nodo " + i + " -> Peso mínimo: " + distancia[i]);
		}
		System.out.println("\nTiempo de ejecución: " + time + " ms");
	}

}