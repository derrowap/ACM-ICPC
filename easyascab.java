import java.util.*;

/**
 * Created by lukecraig on 9/17/16.
 * MID-CENTRAL ICPC 2016 QUALIFIERS QUESTION A
 * SOLVED
 */

/**
 * This is our main data structure for the letter. It contains the
 * character for the letter as well as a list of all of those letters
 * that are greater than it. It self-percolates this list.
 * e.g.   A  >  D (read: a comes before D alphabetically)
 *        D  >  C
 *        it finds for itself that:
 *        A  >  C
 * This is important for the solution later.
 */
class Letter {
    Set<Letter> lessThan;
    char c;

    public Letter(char c){
        this.c = c;
        lessThan = new HashSet<Letter>();
    }

    /**
     * This recursively percolates rules from those that are above
     * the letter.
     * @param greaterThan
     */
    public void getAllLessThan(ArrayList<Letter> greaterThan){
        if (greaterThan.contains(this))
            return;
        greaterThan.add(this);
        for (Letter l : lessThan)
            l.getAllLessThan(greaterThan);
    }

    /**
     * The main method for adding rules. It adds said rule
     * and also all of the rules from its above letters using
     * the above method.
     * @param n
     */
    public void thisNodeislessThan(Letter n){
        if (n.c == c)
            return;
        lessThan.add(n);
        ArrayList<Letter> q = new ArrayList<>();
        getAllLessThan(q);
        for (Letter m : q)
            if (m.c != this.c)
                this.lessThan.add(m);
    }
}
public class easyascab {

    /**
     * We only ever use this method once and it calculates n choose 2
     * for the ambiguous/impossible trade off decision.
     */
    public static long choose(long total, long choose){
        if(total < choose)
            return 0;
        if(choose == 0 || choose == total)
            return 1;
        return choose(total-1,choose-1)+choose(total-1,choose);
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] z = sc.nextLine().split(" ");          // take input of the form "d 4"
        int range = z[0].charAt(0)-'a'+1;               // find length of range for d -> 4 [a,b,c,d]
        int len = Integer.parseInt(z[1]);               // get number of strings
        Letter[] list = new Letter[range];              // Setup array of letters (our primary data structure)
        for (int i=0; i<list.length; i++)               // initialize them
            list[i] = new Letter((char)('a'+i));
        char[] previous = sc.nextLine().toCharArray();

        //loop over and derive rules. List these as
        while (--len>0){
            char[] current = sc.nextLine().toCharArray();
            int pos = 0;
            while (current[pos] == previous[pos]) {
                pos++;
                if (pos >= current.length || pos >= previous.length)
                    break;
            }
            if (pos < current.length && pos < previous.length) {
                list[current[pos]-'a'].thisNodeislessThan(list[previous[pos]-'a']);
            }
            previous = current;
        }
        sc.close();     // close input. we don't need it anymore


        /**
         * This checks the outgoing connections of all of the letters
         */
        int[] checking = new int[range];
        for (Letter l : list){
            int size = l.lessThan.size();
            checking[size]++;
        }

        /**
         * The only configuration in which we can say a system is solvable
         * is where the number of outgoing connections from 0..n-1 are all represented
         * exactly once. Otherwise we need to find out if it is impossible or ambiguous.
         * We can say something is ambiguous if it has fewer than n choose 2 connections.
         * Otherwise we can say it is an impossible configuration.
         */
        for (int b : checking){
            if (b!=1) {
                int connections = 0;
                for (Letter l : list){
                    connections += l.lessThan.size();
                }
                if (connections < choose(list.length, 2)){
                    System.out.println("AMBIGUOUS");
                }else {
                    System.out.println("IMPOSSIBLE");
                }
                System.exit(0);
            }
        }

        /**
         * From this point on we know that the configuration is possible.
         * We simply sort by the number of connections, which makes sense
         * because the letter with the fewest outgoing connections (0) is
         * the head and the one with the most (n-1) is the tail (last).
         */
        Arrays.sort(list, new Comparator<Letter>() {
            public int compare(Letter d, Letter d1) {
                return d.lessThan.size() - d1.lessThan.size();
            }
        });

        /**
         * Here we pull our results together from our sorted list.
         */
        StringBuffer sb = new StringBuffer();
        for (Letter l : list)
            sb.append(l.c);

        System.out.println(sb.toString());     // print out result
    }
}
