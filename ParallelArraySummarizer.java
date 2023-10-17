import java.util.ArrayList;
import java.util.List;

public class ParallelArraySummarizer {

    public static void main(String[] args) {

        List<Item> items = new ArrayList<>();

        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(args);

        Loader loader = new Loader(items, argumentParser.getExponent());
        loader.loadItems();
    }
}
