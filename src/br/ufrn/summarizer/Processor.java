package br.ufrn.summarizer;

import br.ufrn.summarizer.operation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {

    private final List<Item> items;
    private final Integer numberOfThreads;
    private final AtomicDouble totalSum = new AtomicDouble(0.0);
    private final HashMap<Integer, Double> subTotalPerGroup = new HashMap<>();
    private final List<Long> idsSmallerThan5 = new ArrayList<>();
    private final List<Long> idsBiggerOrEqualTo5 = new ArrayList<>();

    private int getSegmentEnd(int begin) {
        int end = begin + items.size() / numberOfThreads;
        if (end >= items.size()) {
            end = items.size() - 1;
        }
        return end;
    }

    public void processItems() {

        Lock idsSmallerThan5Lock = new ReentrantLock();
        Lock idsBiggerOrEqualto5Lock = new ReentrantLock();
        Lock subTotalPerGroupLock = new ReentrantLock();


        int segmentBegin = 0;
        int segmentEnd;
        while (segmentBegin < items.size()) {

            segmentEnd = getSegmentEnd(segmentBegin);

            new Thread(new OperationsExecutor(new Segment(segmentBegin, segmentEnd), items, idsBiggerOrEqualto5Lock, idsSmallerThan5Lock, subTotalPerGroupLock, idsSmallerThan5, idsBiggerOrEqualTo5, totalSum, subTotalPerGroup)).start();

            segmentBegin = segmentEnd + 1;
        }
    }

    public Processor(List<Item> items, Integer numberOfThreads) {
        this.items = items;
        this.numberOfThreads = numberOfThreads;
    }

    public AtomicDouble getTotalSum() {
        return totalSum;
    }

    public HashMap<Integer, Double> getSubTotalPerGroup() {
        return subTotalPerGroup;
    }

    public List<Long> getIdsSmallerThan5() {
        return idsSmallerThan5;
    }

    public List<Long> getIdsBiggerOrEqualTo5() {
        return idsBiggerOrEqualTo5;
    }
}
