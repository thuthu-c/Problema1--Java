package br.ufrn.summarizer;

import java.util.concurrent.ThreadLocalRandom;

public class Item {

    private Long id;
    private Double total;
    private Integer group;

    public Item(Long id) {
        this.id = id;
        this.total = generateTotal();
        this.group = generateGroup();
    }

    /**
     * generates a number in range [0, 10]
     */
    private Double generateTotal() {
        return ThreadLocalRandom.current().nextDouble(0, 10 + 1);
    }

    /**
     * generates a number in range [1, 5]
     */
    private Integer generateGroup() {
        return ThreadLocalRandom.current().nextInt(0, 10 + 1);
    }

    public Long getId() {
        return id;
    }

    public Double getTotal() {
        return total;
    }

    public Integer getGroup() {
        return group;
    }
}
