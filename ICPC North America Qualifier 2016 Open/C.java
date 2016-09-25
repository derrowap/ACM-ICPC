import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Title: Big Truck
 * Passed: 0.48s
 * 
 * https://open.kattis.com/contests/naq16open/problems/bigtruck
 * 
 */
public class C {

	public static ArrayList<String> input = new ArrayList<>();

	public static void solve(String[] vertices, String[][] paths, int[] sums) {

		Vertex[] V = new Vertex[vertices.length];
		int k = 0;
		for (String v : vertices) {
			V[k] = new Vertex("" + (k + 1), Integer.parseInt(v));
			V[k].adjacencies = new Edge[sums[k]];
			k++;
		}

		for (String[] path : paths) {
			V[Integer.parseInt(path[0]) - 1].adjacencies[V[Integer.parseInt(path[0]) - 1].addedEdges] = new Edge(
					V[Integer.parseInt(path[1]) - 1], Integer.parseInt(path[2]));

			V[Integer.parseInt(path[1]) - 1].adjacencies[V[Integer.parseInt(path[1]) - 1].addedEdges] = new Edge(
					V[Integer.parseInt(path[0]) - 1], Integer.parseInt(path[2]));

			V[Integer.parseInt(path[0]) - 1].addedEdges++;
			V[Integer.parseInt(path[1]) - 1].addedEdges++;
		}

		computePaths(V[0]); // run Dijkstra

		if (Double.isInfinite(V[V.length - 1].minDistance))
			System.out.println("impossible");
		else
			System.out.println((int) V[V.length - 1].minDistance + " " + V[V.length - 1].maxBenefit);

	}

	public static void computePaths(Vertex source) {
		source.minDistance = 0.;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			// Visit each edge exiting u
			for (Edge e : u.adjacencies) {
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				int benefit = v.benefit;
				int bSum = u.maxBenefit + benefit;

				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);

					v.minDistance = distanceThroughU;
					v.maxBenefit = bSum;
					v.previous = u;
					vertexQueue.add(v);
				} else if (distanceThroughU == v.minDistance && bSum > v.maxBenefit) {
					vertexQueue.remove(v);

					v.minDistance = distanceThroughU;
					v.maxBenefit = bSum;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	public static List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);

		Collections.reverse(path);
		return path;
	}

	public static void main(String[] arg) {
		// get input
		Scanner sc = new Scanner(System.in);
		Integer n = Integer.parseInt(sc.nextLine());
		String[] vertices = new String[n];
		int k = 0;
		for (String j : sc.nextLine().split("\\s+")) {
			vertices[k] = j;
			k++;
		}
		int[] sum = new int[n];
		n = Integer.parseInt(sc.nextLine());
		String[][] paths = new String[n][3];
		for (int i = 0; i < n; i++) {
			k = 0;
			for (String j : sc.nextLine().split("\\s+")) {
				if (k != 2)
					sum[Integer.parseInt(j) - 1]++;
				paths[i][k] = j;
				k++;
			}
		}

		// close Scanner
		sc.close();

		// solve problem
		solve(vertices, paths, sum);
	}
}

class Vertex implements Comparable<Vertex> {
	public final String name;
	public Edge[] adjacencies;
	public int addedEdges = 0;
	public double minDistance = Double.POSITIVE_INFINITY;
	public int maxBenefit;;
	public Vertex previous;
	public int benefit;

	public Vertex(String argName, int b) {
		name = argName;
		benefit = b;
		maxBenefit = b;
	}

	public String toString() {
		return name;
	}

	public int compareTo(Vertex other) {
		int output = Double.compare(minDistance, other.minDistance);
		if (output == 0 && maxBenefit < other.maxBenefit) {
			output--;
		}
		return output;
	}

}

class Edge {
	public final Vertex target;
	public final double weight;

	public Edge(Vertex argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}
}
