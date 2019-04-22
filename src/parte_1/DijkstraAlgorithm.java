package parte_1;

public class DijkstraAlgorithm {

	/**
	 * Determina si la distancia del nodo fuente al nodo destino es la mínima.
	 * 
	 * @param distancia Distancia acumulada desde el nodo fuente al nodo destino.
	 * @param booleanAr Arreglo de booleanos donde es verdadero si el nodo hace
	 *                  parte del camino de menor peso.
	 * @param size      Tamaño de la matriz.
	 * @return Índice perteneciente al camino de menor peso.
	 */
	public int pesoMinimo(int distancia[], Boolean booleanAr[], int size) {
		int min = Integer.MAX_VALUE;
		int indice = -1;
		for (int i = 0; i < size; i++) {
			if (!booleanAr[i] && distancia[i] <= min) {
				min = distancia[i];
				indice = i;
			}
		}
		return indice;
	}

	/**
	 * Realiza el algoritmo de Dijkstra
	 * 
	 * @param matriz Matriz cargada en el Main
	 * @param fuente Nodo fuente
	 * @param size   Tamaño de la matriz cuadrada
	 */
	public void dijkstra(int matriz[][], int fuente, int size) {
		int distancia[] = new int[size];
		long startTime = System.currentTimeMillis();

		Boolean booleanAr[] = new Boolean[size];
		for (int i = 0; i < size; i++) {
			distancia[i] = Integer.MAX_VALUE;
			booleanAr[i] = false;
		}
		distancia[fuente] = 0;
		for (int i = 0; i < size; i++) {
			int nodo = pesoMinimo(distancia, booleanAr, size);
			booleanAr[nodo] = true;
			for (int j = 0; j < size; j++) {
				if (matriz[nodo][j] == -1)
					matriz[nodo][j] = 0;

				if (!booleanAr[j] && matriz[nodo][j] != 0 && distancia[nodo] != Integer.MAX_VALUE
						&& distancia[nodo] + matriz[nodo][j] < distancia[j]) {
					distancia[j] = distancia[nodo] + matriz[nodo][j];
				}
			}
		}
		long endTime = System.currentTimeMillis() - startTime;
		printGraph(distancia, size, fuente, endTime);
	}

	/**
	 * Imprime los resultados de los caminos de costos mínimos para el nodo fuente.
	 * 
	 * @param distancia Distancia acumulada desde el nodo fuente al nodo destino.
	 * @param size      Tamaño de la matriz cuadrada
	 * @param fuente    Nodo fuente
	 * @param time      Tiempo de ejecución del algoritmo
	 */
	public void printGraph(int distancia[], int size, int fuente, long time) {
		System.out.println("El costo mínimo del nodo fuente número " + fuente + " a cada nodo destino es: ");
		for (int i = 0; i < size; i++) {
			System.out.println("Destino: nodo " + i + " -> Peso mínimo: " + distancia[i]);
		}
		;
		System.out.println("\nTiempo de ejecución: " + time + " ms");
	}
}
