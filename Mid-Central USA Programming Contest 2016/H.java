import java.util.ArrayList;
import java.util.Scanner;
// solved
// https://mcpc16.kattis.com/problems/magical3
public class H {
	static ArrayList<Integer> input = new ArrayList<>();

	public static void solve() {

		for (int i : input) {
			boolean found = false;
			if (i < 3 || i == 4 || i == 5 || i == 6) {
				System.out.println("No such base");
				continue;
			}
			if (i == 3) {
				System.out.println(4);
				continue;
			}

			int smallest = i - 3;
			i -= 3;
			if (i % 2 == 0 && i >= 8) {
				smallest = i / 2;
			}
			if (i % 3 == 0 && i >= 12) {
				smallest = i / 3;
			}
			for (int base = 4; base <= (int) Math.sqrt(i + 3); base++) {
				if (i % base == 0) {
					if (base < smallest) {
						smallest = base;
						break;
					}
				}
			}

			System.out.println(smallest);
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (true) {
			int current = Integer.parseInt(sc.nextLine());
			if (current == 0)
				break;
			input.add(current);
		}

		// close Scanner
		sc.close();

		// solve problem
		solve();
	}

}
