import java.util.Scanner;

/**
 * Title: Brackets
 * Not accepted: Time Limit Exceeded, 27/82 Passed.
 * 
 * https://open.kattis.com/contests/naq16open/problems/brackets
 * 
 */
public class D {
	public static String input = "";

	public static void solve() {
		String inv = inverse(input);
		int n = input.length();

		if (input.length() % 2 != 0) {
			System.out.print("impossible");
			return;
		}
		int k = 0;
		while (valid(input.substring(0, k))) {
			k++;
		}

		for (int i = k; i < n; i++) {

			for (int j = n; j >= i; j--) {
				StringBuilder builder = new StringBuilder(input.substring(0, i));
				builder.append(inv.substring(i, j));
				builder.append(input.substring(j, n));
				if (valid(builder.toString())) {
					System.out.print("possible");
					return;
				}
			}
		}

		System.out.print("impossible");

	}

	public static boolean valid(String i) {
		int count = 0;
		if (i == "")
			return true;

		for (char c : i.toCharArray()) {
			if (c == '(')
				count++;
			else
				count--;
			if (count < 0)
				return false;
		}
		return count == 0;
	}

	public static String inverse(String i) {
		StringBuilder b = new StringBuilder(i.length());
		for (char c : i.toCharArray()) {
			if (c == '(')
				b.append(')');
			else
				b.append('(');
		}
		return b.toString();
	}

	public static int toBinary(String i) {
		StringBuilder b = new StringBuilder(i.length());
		for (char c : i.toCharArray()) {
			if (c == '(')
				b.append("0");
			else
				b.append("1");
		}
		return Integer.parseInt(b.toString(), 2);
	}

	public static void main(String[] arg) {
		// get input
		Scanner sc = new Scanner(System.in);
		input = sc.nextLine();

		// close Scanner
		sc.close();

		// solve problem
		solve();
	}
}
