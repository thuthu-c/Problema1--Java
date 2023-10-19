package br.ufrn.summarizer.operation;

import br.ufrn.summarizer.AtomicDouble;
import br.ufrn.summarizer.CountDownLatchSingleton;
import br.ufrn.summarizer.Item;

import java.util.List;

public class TotalSumObtainer extends Operation {

    private AtomicDouble totalSum;

    private void obtainTotalSum() {
        // TODO
    }

    @Override
    public void run() {
        obtainTotalSum();
        CountDownLatchSingleton.countDown();
    }

    public TotalSumObtainer(List<Item> items, Segment segment, AtomicDouble totalSum) {
        super(items, segment);
        this.totalSum = totalSum;
    }
}
