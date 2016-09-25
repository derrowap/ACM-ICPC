import java.util.ArrayList;
import java.util.Scanner;

/**
 * Title: Quick Estimates
 * Passed: 0.18s
 * 
 * https://open.kattis.com/contests/naq16open/problems/quickestimate
 * 
 */
public class J {

	public static ArrayList<String> input = new ArrayList<>();

	public static void solve() {

		for (String i : input) {
			System.out.println(i.length());
		}

	}

	public static void main(String[] arg) {
		// get input
		Scanner sc = new Scanner(System.in);
		Integer n = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < n; i++) {
			for (String j : sc.nextLine().split("\\s+")) {
				input.add(j);
			}
		}

		// close Scanner
		sc.close();

		// solve problem
		solve();
	}
}
