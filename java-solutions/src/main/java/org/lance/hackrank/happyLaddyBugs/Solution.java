package org.lance.hackrank.happyLaddyBugs;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    // Complete the happyLadybugs function below.
    static String happyLadybugs(String b) {
        if (b == null || b.isEmpty()) {
            return "YES";
        }
        final String UNDER_SCORE = "_";

        boolean containsUnderscore = b.indexOf(UNDER_SCORE) >= 0;
        if (containsUnderscore) {
            return b.chars().mapToObj(c -> (char) c).filter(c -> !c.equals('_'))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .values().stream().filter(cnt -> cnt == 1).findAny().isPresent() ? "NO" : "YES";
        }

        if (b.length() == 1) {
            return "NO";
        }

        for (int i = 0; i < b.length(); ++i) {
            if ((i == 0 && b.charAt(i) != b.charAt(i+1)) || (i == b.length() - 1 && b.charAt(i) != b.charAt(i - 1))
            || (i > 0 && i < b.length() - 1 && b.charAt(i) != b.charAt(i+1) && b.charAt(i) != b.charAt(i - 1))) {
                return "NO";
            }
        }
        return "YES";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int g = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int gItr = 0; gItr < g; gItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String b = scanner.nextLine();

            String result = happyLadybugs(b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

