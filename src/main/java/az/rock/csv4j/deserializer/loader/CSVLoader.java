package az.rock.csv4j.deserializer.loader;

import az.rock.csv4j.exception.CSVHeaderNotFoundException;
import az.rock.csv4j.deserializer.loader.fileModel.CSVFile;

public interface CSVLoader {
    void load() throws CSVHeaderNotFoundException;
    CSVFile getCsvFile();
}
