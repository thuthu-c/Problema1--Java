package br.ufrn.summarizer.operation;

import br.ufrn.summarizer.AtomicDouble;
import br.ufrn.summarizer.CountDownLatchSingleton;
import br.ufrn.summarizer.Item;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.function.DoublePredicate;

public class OperationsExecutor implements Runnable {

    private final Segment segment;
    private final List<Item> items;
    private final Lock idsBiggerOrEqualto5Lock;
    private final Lock idsSmallerThan5Lock;
    private final Lock subTotalPerGroupLock;
    private final List<Long> idsSmallerThan5;
    private final List<Long> idsBiggerOrEqualTo5;
    private final AtomicDouble totalSum;
    private final HashMap<Integer, Double> subTotalPerGroup;

    @Override
    public void run() {
        for (int i = segment.getBegin(); i <= segment.getEnd() ; i++) {
            Item item = items.get(i);
            obtainIds(item, (total) -> total < 5, idsSmallerThan5, idsSmallerThan5Lock);
            obtainIds(item, (total) -> total >= 5, idsBiggerOrEqualTo5, idsBiggerOrEqualto5Lock);
            obtainTotalSum(item);
            obtainSubtotalPerGroup(item);
        }
        CountDownLatchSingleton.countDown();
    }

    private void obtainIds(Item item, DoublePredicate rule, List<Long> ids, Lock lock) {
        if (rule.test(item.getTotal())) {
            lock.lock();
            ids.add(item.getId());
            lock.unlock();
        }
    }

    private void obtainTotalSum(Item item) {
        totalSum.add(item.getTotal());
    }

    private void obtainSubtotalPerGroup(Item item) {
        Double groupSubtotal = getGroupSubtotal(item);
        subTotalPerGroupLock.lock();
        subTotalPerGroup.put(item.getGroup(), groupSubtotal + item.getTotal());
        subTotalPerGroupLock.unlock();
    }

    private Double getGroupSubtotal(Item item) {
        Double groupSubtotal = subTotalPerGroup.get(item.getGroup());
        if (groupSubtotal == null) {
            groupSubtotal = 0.0;
        }
        return groupSubtotal;
    }

    public OperationsExecutor(Segment segment, List<Item> items, Lock idsBiggerOrEqualto5Lock, Lock idsSmallerThan5Lock, Lock subTotalPerGroupLock, List<Long> idsSmallerThan5, List<Long> idsBiggerOrEqualTo5, AtomicDouble totalSum, HashMap<Integer, Double> subTotalPerGroup) {
        this.segment = segment;
        this.items = items;
        this.idsBiggerOrEqualto5Lock = idsBiggerOrEqualto5Lock;
        this.idsSmallerThan5Lock = idsSmallerThan5Lock;
        this.subTotalPerGroupLock = subTotalPerGroupLock;
        this.idsSmallerThan5 = idsSmallerThan5;
        this.idsBiggerOrEqualTo5 = idsBiggerOrEqualTo5;
        this.totalSum = totalSum;
        this.subTotalPerGroup = subTotalPerGroup;
    }
}
