package com.project.paradoxplatformer.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Native;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.project.paradoxplatformer.MainApplication;

public class ResourcesFinder{

    private static final List<String> fxmlFiles = List.of(
        "hello-view.fxml"
    );

    private static final List<String> imagesStreams = List.of(
        
    );

    private static final String NUMBER_PREFIX = "_";
    private static final String EXTENSION = ".png"; 
    private static final String IMAGE_DIR = "images/";

    public static List<URL> FXMLfiles() {
        return fxmlFiles.stream().map(MainApplication.class::getResource).toList();
        
    }

    public static Map<String, InputStream> allImages() {
        
        return null;
                
    }

    public static URL getURL(String filePath) throws InvalidResourceException{
            return Optional.ofNullable(MainApplication.class.getResource(filePath))
                .orElseThrow(() -> 
                    new InvalidResourceException(filePath)
                );    
        
    }
}
