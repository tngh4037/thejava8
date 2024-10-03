package com.study.java8.section7.arraysort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

// Arrays.parallelSort()
// : Fork/Join 프레임워크를 사용해서 배열을 병렬로 정렬하는 기능을 제공한다.
public class ArraySortMain {

    public static void main(String[] args) {
        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

        long start = System.nanoTime();
        Arrays.sort(numbers);
        System.out.println("serial sorting took " + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

        start = System.nanoTime();
        Arrays.parallelSort(numbers);
        System.out.println("parallel sorting took " + (System.nanoTime() - start));
    }
}
