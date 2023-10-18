import java.util.List;

public class Processer {

    private Integer numberOfThreads;
    private AtomicDouble totalSum;
    private AtomicDouble subTotalPerGroup;
    private List<Integer> idsSmallerThan5;
    private List<Integer> idsBiggerThan4;

    public Double obtainTotalSum() {

        // TODO: Logic here

        return totalSum.getValue();
    }

    public Double obtainSubTotalPerGroup() {

        // TODO: Logic here

        return subTotalPerGroup.getValue();
    }

    public List<Integer> obtainIdsSmallerThan5() {

        // TODO: Logic here

        return idsSmallerThan5;
    }

    public List<Integer> obtainIdsBiggerThan4() {

        // TODO: Logic here

        return idsBiggerThan4;
    }
}
