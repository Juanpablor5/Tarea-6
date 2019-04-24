package parte_1;

import parte_1.BellmanFordAlgorithm.ClaseArista;

public class BellmanFordAlgorithm {
	// A class to represent a weighted edge in graph
	public class ClaseArista {
		int fuente, destino, peso;

		ClaseArista() {
			fuente = destino = peso = 0;
		}
	};

	private int nodo, arista;
	private ClaseArista arrAristas[];

	// Creates a graph with V vertices and E edges
	public BellmanFordAlgorithm(int pNodo, int pArista) {
		nodo = pNodo;
		arista = pArista;
		arrAristas = new ClaseArista[pArista];
		for (int i = 0; i < pArista; ++i)
			arrAristas[i] = new ClaseArista();
	}

	// The main function that finds shortest distances from src
	// to all other vertices using Bellman-Ford algorithm. The
	// function also detects negative weight cycle
	public void BellmanFord(BellmanFordAlgorithm graph, int fuente) {
		long startTime = System.currentTimeMillis();
		int nodo = graph.nodo, E = graph.arista;
		int dist[] = new int[nodo];

		// Step 1: Initialize distances from src to all other
		// vertices as INFINITE
		for (int i = 0; i < nodo; ++i)
			dist[i] = Integer.MAX_VALUE;
		dist[fuente] = 0;

		// Step 2: Relax all edges |V| - 1 times. A simple
		// shortest path from src to any other vertex can
		// have at-most |V| - 1 edges
		for (int i = 1; i < nodo; ++i) {
			for (int j = 0; j < E; ++j) {
				int u = graph.arrAristas[j].fuente;
				int v = graph.arrAristas[j].destino;
				int weight = graph.arrAristas[j].peso;
				if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
					dist[v] = dist[u] + weight;
			}
		}

		// Step 3: check for negative-weight cycles. The above
		// step guarantees shortest distances if graph doesn't
		// contain negative weight cycle. If we get a shorter
		// path, then there is a cycle.
		for (int j = 0; j < E; ++j) {
			int u = graph.arrAristas[j].fuente;
			int v = graph.arrAristas[j].destino;
			int weight = graph.arrAristas[j].peso;
			if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
				System.out.println("BellmanFordAlgorithm contains negative weight cycle");
		}
		long endTime = System.currentTimeMillis() - startTime;
		printBellmanFordAlgorithm(dist, nodo, fuente, endTime);
	}

	public void crearGrafoBF(int[][] matriz,int nodos, int aristas, int fuente) {
		int c=0;
		
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
	 * Imprime los resultados de los caminos de costos mínimos para el nodo fuente.
	 * 
	 * @param distancia Distancia acumulada desde el nodo fuente al nodo destino.
	 * @param size      Tamaño de la matriz cuadrada
	 * @param fuente    Nodo fuente
	 * @param time      Tiempo de ejecución del algoritmo
	 */
	public void printBellmanFordAlgorithm(int distancia[], int size, int fuente, long time) {
		System.out.println("El costo mínimo del nodo fuente número " + fuente + " a cada nodo destino es: ");
		for (int i = 0; i < size; i++) {
			System.out.println("Destino: nodo " + i + " -> Peso mínimo: " + distancia[i]);
		}
		System.out.println("\nTiempo de ejecución: " + time + " ms");
	}
	
}