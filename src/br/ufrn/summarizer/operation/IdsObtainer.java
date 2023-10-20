package br.ufrn.summarizer.operation;

import br.ufrn.summarizer.CountDownLatchSingleton;
import br.ufrn.summarizer.Item;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.function.DoublePredicate;

public class IdsObtainer extends Operation {

    private DoublePredicate rule;
    private List<Long> ids;
    private Lock lock;

    private void obtainIds() {
        for (int i = segment.getBeing(); i <= segment.getEnd(); i++) {
            Item item = items.get(i);
            if (matchesRule(item.getTotal())) {
                lock.lock();
                ids.add(item.getId());
                lock.unlock();
            }
        }
    }

    private boolean matchesRule(Double total) {
        return rule.test(total);
    }

    @Override
    public void run() {
        obtainIds();
        CountDownLatchSingleton.countDown();
    }

    public IdsObtainer(List<Item> items, Segment segment, DoublePredicate rule, List<Long> ids, Lock lock) {
        super(items, segment);
        this.rule = rule;
        this.ids = ids;
        this.lock = lock;
    }
}
