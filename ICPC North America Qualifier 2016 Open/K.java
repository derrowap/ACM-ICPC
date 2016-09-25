import java.util.ArrayList;
import java.util.Scanner;

/**
 * Title: Robotopia
 * Not accepted: Wrong Answer, 4/20 accepted.
 * 
 * https://open.kattis.com/contests/naq16open/problems/robotopia
 * 
 */
public class K {

	public static int n;
	public static int[][] input;

	public static void solve() {

		for (int iter = 0; iter < n; iter++) {
			double[] B = new double[2];
			double[][] A = new double[2][3];

			A[0][0] = input[iter][0];
			A[0][1] = input[iter][2];
			A[1][0] = input[iter][1];
			A[1][1] = input[iter][3];

			B[0] = input[iter][input[iter].length - 2];
			B[1] = input[iter][input[iter].length - 1];

			gsolve(A, B, iter == n - 1);

		}

	}

	public static void gsolve(double[][] A, double[] B, boolean end) {
		int N = 2;
		for (int k = 0; k < N; k++) {
			/** find pivot row **/
			int max = k;
			for (int i = k + 1; i < N; i++)
				if (Math.abs(A[i][k]) > Math.abs(A[max][k]))
					max = i;

			/** swap row in A matrix **/
			double[] temp = A[k];
			A[k] = A[max];
			A[max] = temp;

			/** swap corresponding values in constants matrix **/
			double t = B[k];
			B[k] = B[max];
			B[max] = t;

			/** pivot within A and B **/
			for (int i = k + 1; i < N; i++) {
				double factor = A[i][k] / A[k][k];
				B[i] -= factor * B[k];
				for (int j = k; j < N; j++)
					A[i][j] -= factor * A[k][j];
			}
		}

		/** back substitution **/
		double[] solution = new double[N];
		for (int i = N - 1; i >= 0; i--) {
			double sum = 0;
			for (int j = i + 1; j < N; j++)
				sum += A[i][j] * solution[j];
			solution[i] = (B[i] - sum) / A[i][i];
		}

		// System.out.println("determinant: " + determinant(A));
		// printRowEchelonForm(A, B);
		printSolution(solution, A);
		if (!end)
			System.out.println();
	}

	public static double determinant(double[][] A) {
		return A[0][0] * A[1][1] - A[0][1] * A[1][0];
	}

	public static boolean accept(double[] sol, double[][] A) {
		return sol[0] < 1 || sol[1] < 1 || Double.isNaN(sol[0]) || Double.isNaN(sol[1]) || !Double.isFinite(sol[0])
				|| !Double.isFinite(sol[1]) || sol[0] % 1 != 0 || sol[1] % 1 != 0 || A[0][0] == 0 || A[1][1] == 0
				|| A[1][0] != 0 || (int) determinant(A) == 0;
	}

	/** function to print solution **/
	public static void printSolution(double[] sol, double[][] A) {
		int N = sol.length;
		if (accept(sol, A)) {
			System.out.print("?");
			return;
		}
		for (int i = 0; i < N; i++) {
			if (i == N - 1)
				System.out.print((int) sol[i]);
			else
				System.out.print((int) sol[i] + " ");
		}
	}

	public static void printRowEchelonForm(double[][] A, double[] B) {
		int N = 2;
		System.out.println("\nRow Echelon form : ");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(A[i][j] + " ");
			System.out.println("| " + B[i]);
		}
	}

	public static void main(String[] arg) {
		// get input
		Scanner sc = new Scanner(System.in);
		n = Integer.parseInt(sc.nextLine());
		input = new int[n][6];
		for (int i = 0; i < n; i++) {
			int k = 0;
			for (String j : sc.nextLine().split("\\s+")) {
				input[i][k] = Integer.parseInt(j);
				k++;
			}
		}

		// close Scanner
		sc.close();

		// solve problem
		solve();
	}

}