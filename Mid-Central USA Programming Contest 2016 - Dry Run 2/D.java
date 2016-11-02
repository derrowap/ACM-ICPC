import java.util.Scanner;

// https://open.kattis.com/problems/dicecup
public class D {
	
	public static void solve(int d1, int d2) {
		int[] sumOccurances = new int[d1+d2-1];
		
		for (int i = 1; i <= d1; i++) {
			for (int j = 1; j <= d2; j++) {
				sumOccurances[i+j-2]++;
			}
		}
		int max = -1;
		for (int i = 0; i < sumOccurances.length; i++) {
			if (sumOccurances[i] > max) {
				max = i;
			}
		}
		for (int i = 0; i < max; i++) {
			if (sumOccurances[i] == sumOccurances[max]) {
				System.out.println(i+2);
			}
		}
		System.out.println(max+2);
		for (int i = max+1; i < sumOccurances.length; i++) {
			if (sumOccurances[i] == sumOccurances[max]) {
				System.out.println(i+2);
			}
		}
	}

	public static void main(String[] args) {
		// get input
		Scanner sc = new Scanner(System.in);
		String[] line = sc.nextLine().split("\\s+");
		int d1 = Integer.parseInt(line[0]);
		int d2 = Integer.parseInt(line[1]);
		sc.close();
		solve(d1, d2);

		// close Scanner
		
	}

}
