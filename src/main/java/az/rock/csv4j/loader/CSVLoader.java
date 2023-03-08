package az.rock.csv4j.loader;

import az.rock.csv4j.exception.CSVHeaderNotFoundException;
import az.rock.csv4j.loader.fileModel.CSVFile;

public interface CSVLoader {
    void load() throws CSVHeaderNotFoundException;
    CSVFile getCsvFile();
}
