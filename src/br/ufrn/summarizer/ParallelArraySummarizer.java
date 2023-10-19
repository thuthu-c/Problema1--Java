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
        processer.processItems();

        CountDownLatchSingleton.await();

        System.out.printf("Ids smaller than 5: %s%n", processer.getIdsSmallerThan5().toString());
    }
}
