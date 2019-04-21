package grafos;

public class DijkstraAlgorithm {

	public int distanciaMinima(int distancia[], Boolean booleanAr[], int size) {
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
	
	public void printGraph(int distancia[], int j) {
		System.out.println("La distancia del nodo fuente al nodo destino es: ");
		for (int i = 0; i < j; i++) {
			System.out.println(i+"-----" + distancia[i]);
		}
	}

	public void dijkstra(int matriz[][], int inicio, int size) {
		int distancia[] = new int[size];

		Boolean booleanAr[] = new Boolean[size];
		for (int i = 0; i < size; i++) {
			distancia[i] = Integer.MAX_VALUE;
			booleanAr[i] = false;
		}
		distancia[inicio] = 0;
		for (int i = 0; i < size; i++) {
			int nodo = distanciaMinima(distancia, booleanAr, size);
			booleanAr[nodo] = true;
			for (int j = 0; j < size; j++) {
				if (!booleanAr[j] && matriz[nodo][j] != 0 && distancia[nodo] != Integer.MAX_VALUE
						&& distancia[nodo] + matriz[nodo][j] < distancia[j]) {
					distancia[j] = distancia[nodo]+matriz[nodo][j];
				}
			}
			printGraph(distancia,size);
		}
	}
}
