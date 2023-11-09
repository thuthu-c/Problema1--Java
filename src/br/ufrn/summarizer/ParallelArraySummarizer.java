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
        Processor processor = new Processor(items, argumentParser.getNumberOfThreads());

        long start = System.currentTimeMillis();
        processor.processItems();

        CountDownLatchSingleton.await();

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.printf("Total sum: %s%n", processor.getTotalSum().getValue().toString());
        System.out.printf("Subtotal per group: %s%n", processor.getSubTotalPerGroup());
        System.out.printf("Amount of items which total is smaller than 5: %s%n", processor.getIdsSmallerThan5().size());
        System.out.printf("Amount of items which total is bigger or equal to 5: %s%n", processor.getIdsBiggerOrEqualTo5().size());
        System.out.printf("Elapsed processing time: %sms%n", timeElapsed);
    }
}
