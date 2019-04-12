package grafos;

import java.io.BufferedReader;
import java.io.FileReader;

public class Grafos {

	private static int[][] matriz;

	public static void cargarMatriz(String pFile, int size) {
		try {
			BufferedReader br;
			br = new BufferedReader(new FileReader(pFile));
			String linea = "";
			matriz = new int[size][size];
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
		// Imprime la matriz
//		for (int i = 0; i < size; i++) {
//			for (int j = 0; j < size; j++) {
//				System.out.print(matriz[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

	/**
	 * args[0]: Tamaño de la matriz que se va a cargar, puede ser 5, 100 o 1000
	 */
	public static void main(String[] args) {
		System.out.println("Archivo distances" + args[0]);
		String ruta = "./data/distances" + args[0] + ".txt";
		cargarMatriz(ruta, Integer.parseInt(args[0]));
	}
}
