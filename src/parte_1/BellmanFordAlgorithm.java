package parte_1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BellmanFordAlgorithm {

	public int[] BellmanFord(int nodoOrigen, int[][] matriz) {
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		vertices.add(0);
		int suma = 0;
		// Vertices del grafo
		for (int i = 1; i < matriz.length; i++) {
			suma += i;
			vertices.add(Integer.MAX_VALUE);
		}
		// Inicio llenando los caminos

		Queue<Integer> alcanzables = new LinkedList<Integer>();
		int posicionJ = 0;
		int distanciaAcum = 0;
		// Estado inicial

		// Recorrido
		for (int j = 0; j < matriz[nodoOrigen].length; j++) {
			if (suma != 0) {
				if (matriz[nodoOrigen][j] > 0) {
					if (vertices.get(nodoOrigen) + matriz[nodoOrigen][j] < vertices.get(j)) {
						vertices.add(matriz[nodoOrigen][j]);
					}
					alcanzables.add(j);
					posicionJ = j;
				}
			}
		}
		verificarMenor(vertices, matriz, 0);
		for (int i = 0; i < alcanzables.size(); i++) {
			int nodo = alcanzables.remove();
			for (int x = 0; x < matriz.length; x++) {
				for (int j = 0; j < matriz.length; j++) {
					if (suma != 0) {
						if (matriz[x][j] > 0 && matriz[x][j] == nodo) {
							// Paso de relajación
							if (vertices.get(x) + matriz[x][j] < vertices.get(j)) {
								vertices.add(matriz[x][j]);
							}
							alcanzables.add(j);
							posicionJ = j;
						}
					}
				}
			}
		}

		// conversion a int[]

		int[] respuesta = new int[matriz.length];
		for (int i = 0; i < vertices.size(); i++) {
			respuesta[i] = vertices.get(i);
		}
		return respuesta;
	}

	public void verificarMenor(ArrayList<Integer> a, int[][] matriz, int i) {

		int[][] resp = new int[matriz.length][matriz.length];
		int menor = Integer.MAX_VALUE;
		int posX = 0;

		// Por cada nodo alcanzable
		for (int j = 0; j < a.size(); j++) {
			// Busco el de menor peso
			for (int x = 0; x < matriz.length; x++) {

				if (matriz[x][a.get(j)] < menor) {
					menor = matriz[x][a.get(j)];
					posX = x;
				}

				if (x + 1 == a.get(j)) {
					if (x + 2 < matriz.length)
						x = x + 2;
					else
						break;
				}
			}
			menor = Integer.MAX_VALUE;
		}

	}

}