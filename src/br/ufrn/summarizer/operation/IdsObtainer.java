package br.ufrn.summarizer.operation;

import br.ufrn.summarizer.CountDownLatchSingleton;
import br.ufrn.summarizer.Item;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.function.DoublePredicate;

public class IdsObtainer extends Operation {

    private final DoublePredicate rule;
    private final List<Long> ids;
    private final Lock lock;

    private void obtainIds() {
        for (int i = segment.getBegin(); i <= segment.getEnd(); i++) {
            Item item = items.get(i);
            if (matchesRule(item.getTotal())) {
                lock.lock();
                ids.add(item.getId());
                lock.unlock();
            }
        }
    }

    /**
     * Checks if item total matches the predicate established
     * to the id obtainer.
     *
     * @param total value to be tested
     * @return boolean indicating if total matches predicated passed to IdsObtainer
     */
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
