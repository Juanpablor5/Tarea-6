package parte_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DFS {

	private final int vertice;
	private final List<List<Integer>> nodoAdya;

	public DFS(int vertice) {
		this.vertice = vertice;
		nodoAdya = new ArrayList<>(vertice);

		for (int i = 0; i < vertice; i++) {
			nodoAdya.add(new LinkedList<>());
		}
	}

	private boolean DFSRecursivo(int i, boolean[] visitado, boolean[] pila, int size) {

		if (pila[i]) {
			return true;
		}

		if (visitado[i]) {
			return false;
		}

		visitado[i] = true;

		pila[i] = true;
		List<Integer> hijo = nodoAdya.get(i);

		for (int c = 0; c < hijo.size(); c++) {
			if (DFSRecursivo(hijo.get(c), visitado, pila, size)) {
				return true;
			}
		}
		pila[i] = false;

		return false;
	}

	private void addEdge(int source, int dest) {
		nodoAdya.get(source).add(dest);
	}

	private boolean esCiclico(int size) {
		boolean[] visited = new boolean[vertice];
		boolean[] pila = new boolean[vertice];
		for (int i = 0; i < vertice; i++) {
			if (DFSRecursivo(i, visited, pila, size))
				return true;
		}
		return false;
	}

	public static void main(String args[]) {
		DFS graph = new DFS(Integer.parseInt(args[0]));
		// Crea la matriz del tama�o escogido por el usuario en los par�metros.
		int[][] matriz = new int[Integer.parseInt(args[0])][Integer.parseInt(args[0])];
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
						// Crea el grafo
						if (matriz[i][j] > 0) {
							graph.addEdge(i, j);
						}
					}
				}
				i++;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Archivo distances" + args[0] + ".txt cargado\n");

		if (graph.esCiclico(Integer.parseInt(args[0]))) {
			System.out.println("El grafo contiene al menos un ciclo");
		} else {
			System.out.println("El grafo no contiene ciclos");
		}

	}
}
