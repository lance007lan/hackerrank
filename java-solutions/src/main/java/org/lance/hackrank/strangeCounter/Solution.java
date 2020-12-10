package org.lance.hackrank.strangeCounter;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the strangeCounter function below.
    static long strangeCounter(long t) {
        long grpIdx = (long) (Math.log((t - 1) / 3 + 1) / Math.log(2));
        long startIndex = (long) (3 * (Math.pow(2, grpIdx) - 1));
        long startFrom = (long) (3 * Math.pow(2, grpIdx));
        long offSet = t - 1 - startIndex;
        return startFrom - offSet;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        long t = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = strangeCounter(t);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
