package parte_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {

	private final int vertice;
	private final List<List<Integer>> nodoAdya;

	public Graph(int vertice) {
		this.vertice = vertice;
		nodoAdya = new ArrayList<>(vertice);

		for (int i = 0; i < vertice; i++) {
			nodoAdya.add(new LinkedList<>());
		}
	}

	
	private boolean DFSRecursivo(int i, boolean[] visitado, boolean[] pila, int size) {

		int[] ciclo = new int[size];
		// Mark the current node as visited and
		// part of recursion stack
		if (pila[i])
			return true;

		if (visitado[i])
			return false;

		visitado[i] = true;

		pila[i] = true;
		List<Integer> hijo = nodoAdya.get(i);

		for (int c = 0; c < hijo.size(); c++) 			
			if (DFSRecursivo(hijo.get(c), visitado, pila, size)) {
				System.out.println(hijo.get(c));
				ciclo[c] = hijo.get(c);
				return true;
			}
		
		ciclo = new int[size];

		pila[i] = false;

		return false;
	}

	private void addEdge(int source, int dest) {
		nodoAdya.get(source).add(dest);
	}

	// Returns true if the graph contains a cycle, else false.
	// This function is a variation of DFS() in
	// https://www.geeksforgeeks.org/archives/18212
	private boolean esCiclico(int size) {

		// Mark all the vertices as not visited and
		// not part of recursion stack
		boolean[] visited = new boolean[vertice];
		boolean[] recStack = new boolean[vertice];

		// Call the recursive helper function to
		// detect cycle in different DFS trees
		for (int i = 0; i < vertice; i++)
			if (DFSRecursivo(i, visited, recStack, size))
				return true;

		return false;
	}

	public static void main(String args[]) {
		Graph graph = new Graph(Integer.parseInt(args[0]));
		// Crea la matriz del tamaño escogido por el usuario en los parámetros.
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
		
        if(graph.esCiclico(Integer.parseInt(args[0]))) 
            System.out.println("El grafo contiene al menos un ciclo, el primero encontrado es "); 
        else
            System.out.println("El grafo no contiene ciclos");	

	}
}