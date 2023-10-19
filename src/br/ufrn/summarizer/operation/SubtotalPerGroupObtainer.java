package br.ufrn.summarizer.operation;

import br.ufrn.summarizer.CountDownLatchSingleton;
import br.ufrn.summarizer.Item;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class SubtotalPerGroupObtainer extends Operation {

    private HashMap<Integer, Double> subTotalPerGroup;
    private Lock lock;

    private void obtainSubtotalPerGroup() {
        // TODO
    }

    @Override
    public void run() {
        obtainSubtotalPerGroup();
        CountDownLatchSingleton.countDown();
    }

    public SubtotalPerGroupObtainer(List<Item> items, Segment segment, HashMap<Integer, Double> subTotalPerGroup, Lock lock) {
        super(items, segment);
        this.subTotalPerGroup = subTotalPerGroup;
        this.lock = lock;
    }
}
