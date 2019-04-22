package parte_1;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

	private int[][] matriz;

	public Main(int size) {
		matriz = new int[size][size];
	}

	public void cargarMatriz(String pFile) {
		try {
			BufferedReader br;
			br = new BufferedReader(new FileReader(pFile));
			String linea = "";
			int i = 0;
			while (linea != null) {
				linea = br.readLine();
				if (linea != null) {
					String[] separado = linea.split("	");
					for (int j = 0; j < separado.length; j++) {
						matriz[i][j] = Integer.parseInt(separado[j]);
					}
				}
				i++;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * Imprime la matriz
		 */
//		for (int i = 0; i < matriz.length; i++) {
//			for (int j = 0; j < matriz.length; j++) {
//				System.out.print(matriz[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

	/**
	 * Implementa el algoritmo de Dijkstra
	 * 
	 * @param fuente Nodo fuente
	 * @param size   Tamaño de la matriz
	 */
	public void useDijkstra(int fuente, int size) {
		DijkstraAlgorithm p = new DijkstraAlgorithm();
		p.dijkstra(matriz, fuente, size);
	}

	/**
	 * Implementa el algoritmo de Bellman Ford
	 * 
	 * @param fuente Nodo fuente
	 */
	public void useBellmanFord(int fuente) {
		BellmanFordAlgorithm p = new BellmanFordAlgorithm();
		p.BellmanFord(fuente, matriz);
	}
	
	/**
	 * Implementa el algoritmo de Dijkstra
	 * 
	 * @param fuente Nodo fuente
	 * @param size   Tamaño de la matriz
	 */
	public void useFloydWarshal(int fuente, int size) {
		FloydWarshalAlgorithm p = new FloydWarshalAlgorithm();
		p.floydWarshall(matriz, fuente, size);
	}

	/**
	 * Método Main del taller, requiere tres parámetros:
	 * 
	 * args[0]: Tamaño de la matriz que se va a cargar, puede ser 5, 100 o 1000
	 * 
	 * args[1]: Número del algoritmo a usar: 1. Dijkstra 2. Bellman Ford 3. Floyd
	 * Warshall
	 * 
	 * args[2]: Nodo fuente para probar el algoritmo. {0 <= args[2] < args[0]}
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("Archivo distances" + args[0] + ".txt cargado");
		String ruta = "./data/distances" + args[0] + ".txt";
		Main grafos = new Main(Integer.parseInt(args[0]));
		grafos.cargarMatriz(ruta);
		if (args[1].equals("1")) {
			System.out.println("Se escogió el algoritmo de Dijkstra con el nodo fuente " + args[2]+"\n");
			grafos.useDijkstra(Integer.parseInt(args[2]), Integer.parseInt(args[0]));
		} else if (args[1].equals("2")) {
			System.out.println("Se escogió el algoritmo de Bellman Ford con el nodo fuente " + args[2]+"\n");
			grafos.useBellmanFord(Integer.parseInt(args[2]));
		} else if (args[1].equals("3")) {
			System.out.println("Se escogió el algoritmo de Floyd Warshal con el nodo fuente " + args[2]+"\n");
			grafos.useFloydWarshal(Integer.parseInt(args[2]), Integer.parseInt(args[0]));
		}
	}
}
