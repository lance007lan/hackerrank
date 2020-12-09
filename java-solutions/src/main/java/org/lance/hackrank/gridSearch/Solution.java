package org.lance.hackrank.gridSearch;
import java.io.*;
import java.util.*;

public class Solution {

    // Complete the gridSearch function below.
    static String gridSearch(String[] G, String[] P) {
        if (G == null || G.length == 0 || P == null || P.length == 0 || G.length < P.length || G[0].length() < P[0].length()) {
            return "NO";
        }

        for (int i = 0; i < G.length - P.length + 1; ++i) {
            int fromIndex = 0;
            do {
                int firstRowMatchIdx = G[i].indexOf(P[0], fromIndex++);
                if (firstRowMatchIdx < 0) {
                    break;
                }
                int k = 1;
                for (; k < P.length; ++k) {
                    if (G[i + k].indexOf(P[k], firstRowMatchIdx) != firstRowMatchIdx) {
                        break;
                    }
                }
                if (k >= P.length) {
                    return "YES";
                }
            } while (true);

        }
        return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] RC = scanner.nextLine().split(" ");

            int R = Integer.parseInt(RC[0]);

            int C = Integer.parseInt(RC[1]);

            String[] G = new String[R];

            for (int i = 0; i < R; i++) {
                String GItem = scanner.nextLine();
                G[i] = GItem;
            }

            String[] rc = scanner.nextLine().split(" ");

            int r = Integer.parseInt(rc[0]);

            int c = Integer.parseInt(rc[1]);

            String[] P = new String[r];

            for (int i = 0; i < r; i++) {
                String PItem = scanner.nextLine();
                P[i] = PItem;
            }

            String result = gridSearch(G, P);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
