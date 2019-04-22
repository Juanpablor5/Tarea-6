package grafos;

public class FloydWarshalAlgorithm {

	final static int INF = 99999;

	/**
	 * Realiza el algoritmo de Floyd Warshal
	 * 
	 * @param matriz Matriz cargada en el Main
	 * @param fuente Nodo fuente
	 * @param size   Tama�o de la matriz cuadrada
	 */
	public void floydWarshall(int matriz[][], int fuente, int size) {
		long startTime = System.currentTimeMillis();
		int distancia[][] = new int[size][size];
		int i, j, k;
		for (i = 0; i < size; i++)
			for (j = 0; j < size; j++) {
				if (matriz[i][j] == -1) {
					matriz[i][j] = INF;
				}
				distancia[i][j] = matriz[i][j];
			}
		for (k = 0; k < size; k++) {
			for (i = 0; i < size; i++) {
				for (j = 0; j < size; j++) {
					if (distancia[i][k] + distancia[k][j] < distancia[i][j])
						distancia[i][j] = distancia[i][k] + distancia[k][j];
				}
			}
		}
		long endTime = System.currentTimeMillis() - startTime;
		printGraph(distancia, size, fuente, endTime);
	}

	/**
	 * Imprime los resultados de los caminos de costos m�nimos para el nodo fuente.
	 * 
	 * @param distancia Distancia acumulada desde el nodo fuente al nodo destino.
	 * @param size      Tama�o de la matriz cuadrada
	 * @param fuente    Nodo fuente
	 * @param time      Tiempo de ejecuci�n del algoritmo
	 */
	public void printGraph(int distancia[][], int size, int fuente, long time) {
		System.out.println("El costo m�nimo del nodo fuente n�mero " + fuente + " a cada nodo destino es: ");
		for (int j = 0; j < size; ++j) {
			if (distancia[fuente][j] == INF)
				System.out.print("INF ");
			else
				System.out.println("Destino: nodo " + j + " -> Peso m�nimo: " + distancia[fuente][j]);
		}
		System.out.println("\nTiempo de ejecuci�n: " + time + " ms");
	}
}
