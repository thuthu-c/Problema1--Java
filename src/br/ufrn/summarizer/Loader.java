package br.ufrn.summarizer;

import java.util.Collections;
import java.util.List;

public class Loader {

    private final List<Item> items;
    private final Long numberOfInsertions;

    public Loader(List<Item> items, Integer exponent) {
        this.items = items;
        numberOfInsertions = Math.round(Math.pow(10, exponent));
    }

    public void loadItems() {
        for (long i = 0L; i < numberOfInsertions; i++) {
            items.add(new Item(i));
        }
        Collections.shuffle(items);
    }
}
