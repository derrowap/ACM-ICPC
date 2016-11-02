import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

// https://open.kattis.com/problems/sheldon
public class H {
	
	public static void solve(long d1, long d2) {
		int limit = Long.toBinaryString(d2).length();
		
		HashSet<Long> sheldons = new HashSet<>();
		
		// 1^n(0^m 1^n)^k
		for (int n = 1; n <= limit; n++) {
			for (int m = 0; m <= limit - n; m++) {
				for (int k = 0; n + k*(m + n) <= limit; k++) {
					StringBuilder instance = new StringBuilder();
					instance.append(repeatChar('1', n));
					for (int i = 0; i < k; i++) {
						instance.append(repeatChar('0', m));
						instance.append(repeatChar('1', n));
					}
					Long num = (long)-1;
					try {
						num = Long.parseLong(instance.toString(), 2);
					} catch(NumberFormatException e) {
//						System.out.println("EXCEPTION --> " + instance.toString());
					}
					if ((num <= d2) && (num >= d1)) {
						sheldons.add(num);
					}

					// 1^n(0^m 1^n)^k 0^m
					if (n + (k * (m + n)) + m <= limit) {
						instance.append(repeatChar('0', m));
						try {
							num = Long.parseLong(instance.toString(), 2);
						} catch(NumberFormatException e) {
//							System.out.println("EXCEPTION --> " + instance.toString());
						}
						if ((num <= d2) && (num >= d1)) {
							sheldons.add(num);
						}
					}
				}
			}
		}
		
//		for (Long number : sheldons) {
//			System.out.println(number + " --> " + Long.toBinaryString(number));
//		}
		
		System.out.println(sheldons.size());
	}
	
	public static String repeatChar(char c, int n) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < n; i++) {
			b.append(c);
		}
		return b.toString();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] line = sc.nextLine().split("\\s+");
		long d1 = Long.parseLong(line[0]);
		long d2 = Long.parseLong(line[1]);
		sc.close();
		solve(d1, d2);
	}

}
