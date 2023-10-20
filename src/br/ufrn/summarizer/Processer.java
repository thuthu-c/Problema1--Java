package br.ufrn.summarizer;

import br.ufrn.summarizer.operation.IdsObtainer;
import br.ufrn.summarizer.operation.Segment;
import br.ufrn.summarizer.operation.TotalSumObtainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processer {

    private List<Item> items;
    private Integer numberOfThreads;
    private AtomicDouble totalSum = new AtomicDouble(0.0);
    private HashMap<Integer, Double> subTotalPerGroup = new HashMap<>();
    private List<Long> idsSmallerThan5 = new ArrayList<>();
    private List<Long> idsBiggerOrEqualTo5 = new ArrayList<>();


    private int getOperationAmountOfThreads() {
        return (numberOfThreads < 4) ? 1 : numberOfThreads / 4;
    }

    private int getSegmentEnd(int begin, Integer operationAmountOfThreads) {
        Integer end = begin + items.size() / operationAmountOfThreads;
        if (end >= items.size()) {
            end = items.size() - 1;
        }
        return end;
    }

    public void processItems() {

        Lock idsSmallerThan5Lock = new ReentrantLock();
        Lock idsBiggerOrEqualto5Lock = new ReentrantLock();
        Lock subTotalPerGroupLock = new ReentrantLock();
        Integer operationAmountOfThreads = getOperationAmountOfThreads();

        int segmentBegin = 0;
        int segmentEnd;
        while (segmentBegin < items.size()) {
            segmentEnd = getSegmentEnd(segmentBegin, operationAmountOfThreads);

            Segment segment = new Segment(segmentBegin, segmentEnd);
            new Thread(new IdsObtainer(items, segment, (total) -> total < 5, idsSmallerThan5, idsSmallerThan5Lock)).start();
            new Thread(new IdsObtainer(items, segment, (total) -> total >= 5, idsBiggerOrEqualTo5, idsBiggerOrEqualto5Lock)).start();
            new Thread(new TotalSumObtainer(items, segment, totalSum)).start();
//            new Thread(new SubtotalPerGroupObtainer(items, segment, subTotalPerGroup, subTotalPerGroupLock)).start();
            CountDownLatchSingleton.countDown();

            segmentBegin = segmentEnd + 1;
        }
    }

    public Processer(List<Item> items, Integer numberOfThreads) {
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
