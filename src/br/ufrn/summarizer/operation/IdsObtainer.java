package br.ufrn.summarizer.operation;

import br.ufrn.summarizer.CountDownLatchSingleton;
import br.ufrn.summarizer.Item;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.function.LongPredicate;

public class IdsObtainer extends Operation {

    private LongPredicate rule;
    private List<Long> ids;
    private Lock lock;

    private void obtainIds() {
        for (int i = segment.getBeing(); i <= segment.getEnd(); i++) {
            lock.lock();
            Long id = items.get(i).getId();
            if (matchesRule(id)) {
                ids.add(id);
            }
            lock.unlock();
        }
    }

    private boolean matchesRule(Long id) {
        return rule.test(id);
    }

    @Override
    public void run() {
        obtainIds();
        CountDownLatchSingleton.countDown();
    }

    public IdsObtainer(List<Item> items, Segment segment, LongPredicate rule, List<Long> ids, Lock lock) {
        super(items, segment);
        this.rule = rule;
        this.ids = ids;
        this.lock = lock;
    }
}
