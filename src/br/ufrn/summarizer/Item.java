package br.ufrn.summarizer;

import java.util.concurrent.ThreadLocalRandom;

public class Item {

    private final Long id;
    private final Double total;
    private final Integer group;

    public Item(Long id) {
        this.id = id;
        this.total = generateTotal();
        this.group = generateGroup();
    }

    /**
     * generates a double in range [0, 10)
     */
    private Double generateTotal() {
        return ThreadLocalRandom.current().nextDouble(0, 10);
    }

    /**
     * generates a integer in range [1, 5]
     */
    private Integer generateGroup() {
        return ThreadLocalRandom.current().nextInt(1, 6);
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

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", total=" + total +
                ", group=" + group +
                '}';
    }
}
