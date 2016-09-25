import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Title: A New Alphabet
 * Passed: 0.28s
 * 
 * https://open.kattis.com/contests/naq16open/problems/anewalphabet
 * 
 */
public class A {

	public static ArrayList<String> input = new ArrayList<>();

	private static String getCharForNumber(int i) {
		return i > 0 && i < 27 ? String.valueOf((char) (i + 64)) : null;
	}

	public static void solve() {
		HashMap<String, String> dict = new HashMap<>();
		String[] label = { "@", "8", "(", "|)", "3", "#", "6", "[-]", "|", "_|", "|<", "1", "[]\\/[]", "[]\\[]", "0",
				"|D", "(,)", "|Z", "$", "']['", "|_|", "\\/", "\\/\\/", "}{", "`/", "2" };

		for (int i = 0; i < 26; i++) {
			// System.out.println(String.valueOf((char)(i + 97)));
			dict.put(String.valueOf((char) (i + 97)), label[i]);
		}

		boolean space = false;
		for (String i : input) {
			if (space)
				System.out.print(" ");
			for (char c : i.toCharArray()) {
				String replace = dict.get(String.valueOf(c).toLowerCase());
				if (replace == null)
					System.out.print(c);
				else
					System.out.print(replace);
			}
			space = true;
		}

	}

	public static void main(String[] arg) {
		// get input
		Scanner sc = new Scanner(System.in);
		for (String i : sc.nextLine().split("\\s+")) {
			input.add(i);
		}

		// close Scanner
		sc.close();

		// solve problem
		solve();
	}

}
