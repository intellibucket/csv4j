package az.rock.csv4j.loader;

import az.rock.csv4j.CSVManager;
import az.rock.csv4j.exception.CSVHeaderNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class CSVLoader {

    private final LoadStrategy loadStrategy;

    private final List<Map<String,String>> csvList = new ArrayList<>();

    private CSVRawHeader header;

    private final List<CSVRawLine> pureCSV = new ArrayList<>();

    private final String resourcePath;

    private CSVLoader(String resourcePath){
        this.resourcePath = resourcePath;
        this.loadStrategy = LoadStrategy.defaultStrategy();
    }

    private CSVLoader(String resourcePath,LoadStrategy loadStrategy){
        this.resourcePath = resourcePath;
        this.loadStrategy = loadStrategy;
    }

    public static CSVLoader of(String resourcePath) throws CSVHeaderNotFoundException {
        CSVLoader csvLoader = new CSVLoader(resourcePath);
        csvLoader.load();
        return csvLoader;
    }

    public static CSVLoader of(String resourcePath,LoadStrategy loadStrategy) throws CSVHeaderNotFoundException {
        CSVLoader csvLoader = new CSVLoader(resourcePath,loadStrategy);
        csvLoader.load();
        return csvLoader;
    }

    private void load() throws CSVHeaderNotFoundException {
        this.csvList.clear();
        var csvFile = this.getCSVFromResource(this.resourcePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(csvFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (initHeader(scanner)){
            while (scanner.hasNext()){
                this.pureCSV.add(CSVRawLine.of(scanner.nextLine()));
            }
        }else throw new  CSVHeaderNotFoundException();
    }

    private boolean initHeader(Scanner scanner){
        if (scanner.hasNext()){
            this.header = CSVRawHeader.of(scanner.nextLine());
            return true;
        }else return false;
    }


    private File getCSVFromResource(String resourcePath){
        URL resource = CSVManager.class.getClassLoader().getResource(resourcePath);
        try {
            Objects.requireNonNull(resource,"File not found Exception");
            return Paths.get(resource.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException("File not found Exception");
        }
    }



}
