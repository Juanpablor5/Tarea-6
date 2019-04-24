package parte_1;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

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
		// Crea la matriz del tamaño escogido por el usuario en los parámetros.
		int[][] matriz = new int[Integer.parseInt(args[0])][Integer.parseInt(args[0])];
		int aristas = 0;
		String ruta = "./data/distances" + args[0] + ".txt";
		try {
			BufferedReader br;
			br = new BufferedReader(new FileReader(ruta));
			String linea = "";
			int i = 0;
			while (linea != null) {
				linea = br.readLine();
				if (linea != null) {
					String[] separado = linea.split("	");
					for (int j = 0; j < separado.length; j++) {
						matriz[i][j] = Integer.parseInt(separado[j]);
						// Cuenta el numero de aristas del grafo.
						if (matriz[i][j] > 0)
							aristas++;
					}
				}
				i++;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Archivo distances" + args[0] + ".txt cargado");

		// Determina qué algoritmo usar según lo escogido por el usuario en los
		// parámetros.
		if (args[1].equals("1")) {
			System.out.println("Se escogió el algoritmo de Dijkstra con el nodo fuente " + args[2] + "\n");
			DijkstraAlgorithm p = new DijkstraAlgorithm();
			p.dijkstra(matriz, Integer.parseInt(args[2]), Integer.parseInt(args[0]));
		} else if (args[1].equals("2")) {
			System.out.println("Se escogió el algoritmo de Bellman Ford con el nodo fuente " + args[2] + "\n");
			BellmanFordAlgorithm p = new BellmanFordAlgorithm(Integer.parseInt(args[0]), aristas);
			p.crearGrafoBF(matriz, Integer.parseInt(args[0]), aristas, Integer.parseInt(args[2]));
		} else if (args[1].equals("3")) {
			System.out.println("Se escogió el algoritmo de Floyd Warshal con el nodo fuente " + args[2] + "\n");
			FloydWarshalAlgorithm p = new FloydWarshalAlgorithm();
			p.floydWarshall(matriz, Integer.parseInt(args[2]), Integer.parseInt(args[0]));
		}
	}
}
