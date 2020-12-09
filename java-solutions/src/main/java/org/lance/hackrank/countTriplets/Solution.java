package org.lance.hackrank.countTriplets;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        if (arr == null) {
            return 0L;
        }
        if (r == 1) {
            return arr.stream().collect(Collectors.groupingBy(num -> num, Collectors.counting()))
                .values().stream().map(num -> num >=3 ? num * (num -1) * (num - 3 + 1) / 6 : 0)
                .reduce(Long::sum).orElse(0L);
        }

        // every number can be written as base * r^exp
        Map<Long, List<Long>> factor2Exps = new HashMap<>();
        arr.forEach(number -> {
            DecomposedNumber decomposedNumber = decomposeNumber(number, r);
            if (!factor2Exps.containsKey(decomposedNumber.base)) {
                factor2Exps.put(decomposedNumber.base, new ArrayList<>());
            }
            factor2Exps.get(decomposedNumber.base).add(decomposedNumber.power);
        });


        return factor2Exps.values().stream().map(Solution::figureOutConsecutiveTriplets).reduce(Long::sum).orElse(0L);
    }

    static long figureOutConsecutiveTriplets(List<Long> powers) {
        Map<Long, Long> groupedPowers = powers.stream().collect(Collectors.groupingBy(pw -> pw, Collectors.counting()));
        List<Long> sortedPowers = groupedPowers.keySet().stream().sorted().collect(toList());
        long triplets = 0;
        for (int i = 0; i + 2 < sortedPowers.size(); ++i) {
            if (sortedPowers.get(i) + 1 == sortedPowers.get(i + 1) && sortedPowers.get(i + 1) + 1 == sortedPowers.get(i + 2)) {
                triplets = triplets + groupedPowers.get(sortedPowers.get(i))
                        * groupedPowers.get(sortedPowers.get(i + 1))
                        * groupedPowers.get(sortedPowers.get(i + 2));
            }
        }
        return triplets;
    }

    static class DecomposedNumber {
        long power = 0;
        long base;
    }

    static DecomposedNumber decomposeNumber(long number, long r) {
        DecomposedNumber rst = new DecomposedNumber();
        if (r == 1) {
            rst.base = number;
            rst.power = 1;
            return rst;
        }

        while (number % r == 0) {
            rst.power++;
            number = number / r;
        }
        rst.base = number;
        return rst;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

