package br.ufrn.summarizer.operation;

import br.ufrn.summarizer.Item;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.function.IntPredicate;

public class IdsObtainer extends Operation {

    private IntPredicate rule;
    private List<Integer> ids;
    private Lock lock;

    private void obtainIds() {
        // TODO
    }

    @Override
    public void run() {
        obtainIds();
    }

    public IdsObtainer(List<Item> items, Segment segment, IntPredicate rule, List<Integer> ids, Lock lock) {
        super(items, segment);
        this.rule = rule;
        this.ids = ids;
        this.lock = lock;
    }
}
