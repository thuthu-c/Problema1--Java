package br.ufrn.summarizer;

/**
 * AtomicDouble is a double value that may
 * be updated atomically and
 * is always up-to-date.
 *
 * @author Pedro Costa
 */
public class AtomicDouble {

    private volatile Double value;


    public synchronized void add(Double amount) {
        value += amount;
    }

    public AtomicDouble(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
