package br.ufrn.summarizer;

import java.util.ArrayList;
import java.util.List;

public class ParallelArraySummarizer {

    public static void main(String[] args) {

        List<Item> items = new ArrayList<>();

        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(args);

        System.out.println(">>> Loading items...");
        Loader loader = new Loader(items, argumentParser.getExponent());
        loader.loadItems();
        System.out.println(">>> Items loaded!");

        CountDownLatchSingleton.setCountDown(argumentParser.getNumberOfThreads());

        System.out.println(">>> Processing items...");
        Processer processer = new Processer(items, argumentParser.getNumberOfThreads());

        long start = System.currentTimeMillis();
        processer.processItems();

        CountDownLatchSingleton.await();

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.printf("Ids which total is smaller than 5: %s%n", processer.getIdsSmallerThan5());
        System.out.printf("Ids which total bigger or equal to 5: %s%n", processer.getIdsBiggerOrEqualTo5());
        System.out.printf("Total sum: %s%n", processer.getTotalSum().getValue().toString());
        System.out.printf("Subtotal per group: %s%n", processer.getSubTotalPerGroup());
        System.out.printf("Elapsed processing time: %sms%n", timeElapsed);
    }
}
