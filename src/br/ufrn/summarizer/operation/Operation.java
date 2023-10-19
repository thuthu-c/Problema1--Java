package br.ufrn.summarizer.operation;

import br.ufrn.summarizer.Item;

import java.util.List;

public abstract class Operation implements Runnable {

    protected List<Item> items;

    protected Segment segment;

    public Operation(List<Item> items, Segment segment) {
        this.items = items;
        this.segment = segment;
    }
}
