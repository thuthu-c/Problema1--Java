public class ArgumentParser {

    private Integer exponent;
    private Integer numberOfThreads;


    public void parseArguments(String[] args) {
        if (hasValidNumberOfArguments(args)) {
            throw new RuntimeException("Invalid number of arguments.");
        }
        exponent = Integer.valueOf(args[0]);
        numberOfThreads = Integer.valueOf(args[1]);
    }

    private boolean hasValidNumberOfArguments(String[] args) {
        return args.length != 2;
    }

    public Integer getNumberOfThreads() {
        return numberOfThreads;
    }

    public Integer getExponent() {
        return exponent;
    }

    @Override
    public String toString() {
        return "ArgumentParser{" +
                "exponent=" + exponent +
                ", numberOfThreads=" + numberOfThreads +
                '}';
    }
}
