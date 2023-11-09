package br.ufrn.summarizer.operation;

public class Segment {
    private final Integer being;
    private final Integer end;

    public Segment(Integer begin, Integer end) {
        this.being = begin;
        this.end = end;
    }

    public Integer getBegin() {
        return being;
    }

    public Integer getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "being=" + being +
                ", end=" + end +
                '}';
    }
}