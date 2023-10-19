package br.ufrn.summarizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Processer {

    private List<Item> items;
    private Integer numberOfThreads;
    private AtomicDouble totalSum = new AtomicDouble(0.0);
    private HashMap<Integer, Double> subTotalPerGroup = new HashMap<>();
    private List<Long> idsSmallerThan5 = new ArrayList<>();
    private List<Long> idsBiggerThan4 = new ArrayList<>();


    public Double obtainTotalSum() {

        // TODO: Logic here

        return totalSum.getValue();
    }

    public HashMap<Integer, Double> obtainSubTotalPerGroup() {

        // TODO: Logic here

        return subTotalPerGroup;
    }

    public List<Long> obtainIdsSmallerThan5() {

        // TODO: Logic here

        return idsSmallerThan5;
    }

    public List<Long> obtainIdsBiggerThan4() {

        // TODO: Logic here

        return idsBiggerThan4;
    }

    public Processer(List<Item> items, Integer numberOfThreads) {
        this.items = items;
        this.numberOfThreads = numberOfThreads;
    }
}
