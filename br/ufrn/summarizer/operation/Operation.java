package br.ufrn.summarizer.operation;

import br.ufrn.summarizer.Item;

import java.util.List;

public abstract class Operation implements Runnable {

    private List<Item> items;

    private Segment segment;

    public Operation(List<Item> items, Segment segment) {
        this.items = items;
        this.segment = segment;
    }
}
