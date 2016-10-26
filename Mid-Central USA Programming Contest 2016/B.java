import java.util.BitSet;
import java.util.HashSet;
import java.util.Scanner;
// not solved
// https://mcpc16.kattis.com/problems/flippingcards
public class B {
	
	static int[] first;
	static int[] second;
	static String[] output;
	
	public static void solve() {
		HashSet<Integer> set = new HashSet<>();
		BitSet bits = new BitSet(first.length);

		int processed = 0;
		for (int i = 0; i < first.length; i++) {
			if (set.contains(first[i])) {
				if (set.contains(second[i])) {
					// go back and restart at first instance of 0 before processed
					processed = i-1;
					if (bits.cardinality() >= processed) {
						System.out.println("impossible");
						return;
					} else {
						int firstIndex = bits.nextClearBit(0);
						bits.clear(firstIndex+1, first.length);
						
					}
				} else {
					bits.set(i);
				}
			} else {
				bits.clear(i);
			}
		}
	}

	public static void main(String[] args) {
		// get input
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		output = new String[n];
		for (int i = 0; i < n; i++) {
			int size = Integer.parseInt(sc.nextLine());
			first = new int[size];
			second = new int[size];
			for (int j = 0; j < size; j++) {
				String[] line = sc.nextLine().split("\\s+");
				first[j] = Integer.parseInt(line[0]);
				second[j] = Integer.parseInt(line[1]);
			}
			solve();
		}

		// close Scanner
		sc.close();

		// solve problem
		for (String s : output) {
			System.out.println(s);
		}
	}
}
