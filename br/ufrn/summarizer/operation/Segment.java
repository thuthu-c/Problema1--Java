package br.ufrn.summarizer.operation;

public class Segment {
    private Integer being;
    private Integer end;

    public Segment(Integer begin, Integer end) {
        this.being = begin;
        this.end = end;
    }

    public Integer getBeing() {
        return being;
    }

    public Integer getEnd() {
        return end;
    }
}