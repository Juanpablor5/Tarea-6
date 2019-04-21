package grafos;

import java.io.BufferedReader;
import java.io.FileReader;

public class Matriz {

	private int[][] matriz;

	public Matriz(int size) {
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
	
	public void useDijkstra(int size) {		
		DijkstraAlgorithm p = new DijkstraAlgorithm();
		p.dijkstra(matriz, 0, size);
	}

	/**
	 * args[0]: Tamaño de la matriz que se va a cargar, puede ser 5, 100 o 1000
	 * args[1]: Número del algoritmo a usar:
	 * 		1. Dijkstra 
	 * 		2. Bellman Ford
	 * 		3. Floyd Warschall
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("Archivo distances" + args[0] + ".txt cargado");
		String ruta = "./data/distances" + args[0] + ".txt";
		Matriz grafos = new Matriz(Integer.parseInt(args[0]));
		grafos.cargarMatriz(ruta);
		if(args[1].equals("1")) {
			grafos.useDijkstra(Integer.parseInt(args[0]));
		}
	}
}
