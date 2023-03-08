package az.rock.csv4j.loader;

import az.rock.csv4j.manager.CSVManager;
import az.rock.csv4j.exception.CSVHeaderNotFoundException;
import az.rock.csv4j.loader.fileModel.CSVFile;
import az.rock.csv4j.loader.fileModel.CSVRawHeader;
import az.rock.csv4j.loader.fileModel.CSVRawLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class DefaultCSVLoader implements CSVLoader {

    private final LoadStrategy loadStrategy;

    private final List<Map<String, String>> csvList = new ArrayList<>();

    private CSVFile csvFile = new CSVFile();

    private final String resourcePath;

    private DefaultCSVLoader(String resourcePath) {
        this.resourcePath = resourcePath;
        this.loadStrategy = LoadStrategy.defaultStrategy();
    }

    private DefaultCSVLoader(String resourcePath, LoadStrategy loadStrategy) {
        this.resourcePath = resourcePath;
        this.loadStrategy = loadStrategy;
    }

    public static DefaultCSVLoader of(String resourcePath) throws CSVHeaderNotFoundException {
        DefaultCSVLoader defaultCsvLoader = new DefaultCSVLoader(resourcePath);
        defaultCsvLoader.load();
        return defaultCsvLoader;
    }

    public static DefaultCSVLoader of(String resourcePath, LoadStrategy loadStrategy) throws CSVHeaderNotFoundException {
        DefaultCSVLoader defaultCsvLoader = new DefaultCSVLoader(resourcePath, loadStrategy);
        defaultCsvLoader.load();
        return defaultCsvLoader;
    }

    public void load() throws CSVHeaderNotFoundException {
        this.csvList.clear();
        var csvFile = this.getCSVFromResource(this.resourcePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(csvFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (initHeader(scanner)) {
            while (scanner.hasNext()) {
                this.csvFile.addLine(CSVRawLine.of(scanner.nextLine()));
            }
        } else throw new CSVHeaderNotFoundException();
    }

    private boolean initHeader(Scanner scanner) {
        if (scanner.hasNext()) {
            this.csvFile.setHeader(CSVRawHeader.of(scanner.nextLine()));
            return true;
        } else return false;
    }


    private File getCSVFromResource(String resourcePath) {
        URL resource = CSVManager.class.getClassLoader().getResource(resourcePath);
        try {
            Objects.requireNonNull(resource, "File not found Exception");
            return Paths.get(resource.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException("File not found Exception");
        }
    }

    public CSVFile getCsvFile() {
        return csvFile;
    }


}
