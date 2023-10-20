package br.ufrn.summarizer.operation;

import br.ufrn.summarizer.CountDownLatchSingleton;
import br.ufrn.summarizer.Item;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class SubtotalPerGroupObtainer extends Operation {

    private final HashMap<Integer, Double> subTotalPerGroup;
    private final Lock lock;

    private void obtainSubtotalPerGroup() {
        for (int i = segment.getBegin(); i <= segment.getEnd(); i++) {
            Item item = items.get(i);
            Double groupSubtotal = getGroupSubtotal(item);
            lock.lock();
            subTotalPerGroup.put(item.getGroup(), groupSubtotal + item.getTotal());
            lock.unlock();
        }
    }

    private Double getGroupSubtotal(Item item) {
        Double groupSubtotal = subTotalPerGroup.get(item.getGroup());
        if (groupSubtotal == null) {
            groupSubtotal = 0.0;
        }
        return groupSubtotal;
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
