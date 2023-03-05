package az.rock.csv4j.manager;

import az.rock.csv4j.exception.CSVHeaderNotFoundException;
import az.rock.csv4j.loader.CSVLoader;

@SuppressWarnings("all")
public class CSVManager<T> {

    private final CSVLoader csvLoader;

    public CSVManager(Class<T> tClass,String csvResourcePath) throws CSVHeaderNotFoundException {
        this.csvLoader = CSVLoader.of(csvResourcePath);
    }

    public CSVLoader getCsvLoader() {
        return csvLoader;
    }
}
