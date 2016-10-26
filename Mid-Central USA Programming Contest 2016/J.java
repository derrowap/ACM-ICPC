import java.util.HashSet;
import java.util.Scanner;
// solved
// https://mcpc16.kattis.com/problems/everywhere
public class J {

	public static void main(String[] args) {
		// get input
		Scanner sc = new Scanner(System.in);
		
		int n = Integer.parseInt(sc.nextLine());
		int[] output = new int[n];
		for (int i = 0; i < n; i++) {
			int numCities = Integer.parseInt(sc.nextLine());
			HashSet<String> set = new HashSet<>();
			int distinct = 0;
			for (int j = 0; j < numCities; j++) {
				String city = sc.nextLine();
				if (!set.contains(city)) {
					set.add(city);
					distinct++;
				}
			}
			output[i] = distinct;
		}
		
		for (int d : output) {
			System.out.println(d);
		}

		// close Scanner
		sc.close();
	}
}
